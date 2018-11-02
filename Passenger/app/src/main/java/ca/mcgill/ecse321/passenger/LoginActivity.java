package ca.mcgill.ecse321.passenger;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import android.util.Log;

import cz.msebera.android.httpclient.Header;

import static android.Manifest.permission.READ_CONTACTS;
public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private EditText mUsername;
    private EditText mPassword;
    private View mProgressView;
    private View mLoginFormView;
    private String error =null;
    private Boolean loginSuccess = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        // Set up the login form.
        mUsername = (EditText) findViewById(R.id.txtusername);
        mPassword = (EditText) findViewById(R.id.txtpassword);


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    public void attemptLogin(View view)
    {
        attemptLogin();
    }
    public void attemptLogin () {
        error = "";
        final String username = mUsername.getText().toString();
        final String password = mPassword.getText().toString();
        String pathURL = "api/user/login/" + username + "/" + password + "/";
        //attempt admin login
        if (username.equals("admin") && password.equals("admin")){
            Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(MainIntent);
            finish();
            MainActivity.username = username;
            Toast.makeText(LoginActivity.this, "Logged in as admin", Toast.LENGTH_LONG).show();
        }

        HttpUtils.get(pathURL, new RequestParams(), new JsonHttpResponseHandler() {
              @Override
                 public void onSuccess (int statusCode, Header[] headers, JSONArray response) {

              try {
                  loginSuccess =  response.getBoolean(0);
                    }   catch (Exception e) {
                    error += e.getMessage();
                    }


                    if (loginSuccess){
                    Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(MainIntent);
                    finish();
                    MainActivity.username = username;
                    Toast.makeText(LoginActivity.this,  " you are succesfully signed in", Toast.LENGTH_LONG).show();
                    } else {
                    Toast.makeText(LoginActivity.this,  " Incorrect Username or Password", Toast.LENGTH_LONG).show();
                    }
                   }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                loginSuccess = errorResponse.equals("true");
                Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();

                if (loginSuccess) {
                    Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(MainIntent);
                    finish();
                    MainActivity.username = username;
                    Toast.makeText(LoginActivity.this, " you are succesfully signed in", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, " Incorrect Username or Password", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
               /* try {
                  error += errorResponse.get("message").toString();

                } catch (JSONException e) {
                    error += e.getMessage();
                }
*/
            }
        });
    }
    public void openRegisterPage(View view){
        Intent RegisterIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(RegisterIntent);
        finish();
    }

//    private  void refreshErrorMessage (){
//        TextView tvError = (TextView) findViewById(R.id.error);
//        tvError.setText(error);
//
//        if (error == null || error.length()==0){
//            tvError.setVisibility(View.GONE);
//        }
//        else {
//            tvError.setVisibility(View.VISIBLE);
//
//        }
//    }

}

