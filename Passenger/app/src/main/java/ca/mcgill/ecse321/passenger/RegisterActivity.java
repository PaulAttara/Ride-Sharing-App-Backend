package ca.mcgill.ecse321.passenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppCompatActivity {

    public String error = null;

    private EditText mUsername;
    private EditText mPassword;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mPhoneNumber;
    private EditText mAddress;
    private EditText mCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Register");


        // Register Fields
        mUsername = (EditText) findViewById(R.id.txtusername);
        mPassword = (EditText) findViewById(R.id.txtpassword);
        mFirstName = (EditText) findViewById(R.id.txtfirstname);
        mLastName = (EditText) findViewById(R.id.txtlastname);
        mPhoneNumber = (EditText) findViewById(R.id.txtphone);
        mAddress = (EditText) findViewById(R.id.txtaddress);
        mCity = (EditText) findViewById(R.id.txtcity);
    }

    public void attemptRegister(View view) {
        //assume it passes
        //need to check if username conflicts with another
        Intent RegisterIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(RegisterIntent);
        finish();
        addDriver();
    }

    public void backToLogin(View view) {
        Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(LoginIntent);
    }

    public void addDriver() {
        error = "";
        final TextView tv = (TextView) findViewById(R.id.txtusername);
        RequestParams rp = new RequestParams();
        rp.add("brand", "Tesla");
        rp.add("model", "cheese");
        rp.add("plate", "1234");

        HttpUtils.post("api/vehicle/create", rp, new JsonHttpResponseHandler() {


            @Override
            public void onFinish() {
                //refreshErrorMessage();
                tv.setText("");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                //refreshErrorMessage();
            }
        });
    }

    public void newDriver(View view) {
//        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
//        dlgAlert.setMessage("This is an alert with no consequence");
//        dlgAlert.setTitle("App Title");
//        dlgAlert.setPositiveButton("OK", null);
//        dlgAlert.setCancelable(true);
//        dlgAlert.create().show();
        error = "";
        final TextView tv = (TextView) findViewById(R.id.txtusername);
        HttpUtils.post("participants/" + tv.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onFinish() {
                //refreshErrorMessage();
                tv.setText("");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                //refreshErrorMessage();
            }
        });


    }

//    private void refreshErrorMessage() {
//        // set the error message
//        TextView tvError = (TextView) findViewById(R.id.error);
//        tvError.setText(error);
//
//        if (error == null || error.length() == 0) {
//            tvError.setVisibility(View.GONE);
//        } else {
//            tvError.setVisibility(View.VISIBLE);
//        }
//
//    }

}
