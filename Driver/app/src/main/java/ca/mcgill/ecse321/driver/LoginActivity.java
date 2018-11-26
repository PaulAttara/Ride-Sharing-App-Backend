package ca.mcgill.ecse321.driver;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import android.util.Log;



import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 0;

    // Http request
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    // UI references.
    private EditText mUsername;
    private EditText mPassword;
    private View mProgressView;
    private View mLoginFormView;
    private String error = null;
    private boolean loginSuccess = false;


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

    public void attemptLogin(View view) {
        attemptLogin();
    }

    public void attemptLogin(){
        error = "";
        final String username = mUsername.getText().toString();
        final String password = mPassword.getText().toString();

        //attempt admin login
        if (username.equals("admin") && password.equals("admin")){
            Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(MainIntent);
            finish();
            MainActivity.username = username;
            Toast.makeText(LoginActivity.this, "Logged in as admin", Toast.LENGTH_LONG).show();
            return;
        }

        //Http GET request to login starts here
        String pathURL = "https://sharefare.herokuapp.com/api/user/login/" + username + "/" + password+"/";

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, pathURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("true")){
                    openRouteListings(username);
                    Toast.makeText(LoginActivity.this, "You are successfully signed in", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Error:" + error.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            }
        });

        mRequestQueue.add(mStringRequest);
    }

    public void openRegisterPage(View view){
        Intent RegisterIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(RegisterIntent);
        finish();
    }

    public void openRouteListings(String username) {
        Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(MainIntent);
        finish();
        MainActivity.username = username;
    }


}
