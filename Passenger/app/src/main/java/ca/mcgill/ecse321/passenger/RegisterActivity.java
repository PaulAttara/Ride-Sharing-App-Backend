package ca.mcgill.ecse321.passenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        addPassengerUser();
    }

    public void backToLogin(View view) {
        Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(LoginIntent);
    }

    public void addPassengerUser() {
        error = "";
        final TextView tv = (TextView) findViewById(R.id.txtusername);
        RequestParams rp = new RequestParams();
        final String username = mUsername.getText().toString();
        rp.add("username", username);
        rp.add("password", mPassword.getText().toString());
        rp.add("firstname",mFirstName.getText().toString());
        rp.add("lastname",mLastName.getText().toString());
        rp.add("phonenumber",mPhoneNumber.getText().toString());
        rp.add("city",mCity.getText().toString());
        rp.add("address",mAddress.getText().toString());
        rp.add("role", "passenger");

        // question
        HttpUtils.post("api/user/create", rp, new JsonHttpResponseHandler() {


            @Override
            public void onFinish() {
                //refreshErrorMessage();
                tv.setText("");
            }
           /* @Override DO I NEED THIS FOR PASSENGER
            public void onSucces (int statusCode, Header[] header, JSONObject response){
                try {

                }
            }*/
           public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable){
               System.out.println("USER: " + errorResponse);
               if (errorResponse.equals("User Created.")) {

                   Toast.makeText(RegisterActivity.this, errorResponse, Toast.LENGTH_LONG).show();
               }
               else{
                   Toast.makeText(RegisterActivity.this, errorResponse, Toast.LENGTH_LONG).show();
               }
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
/*
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
*/
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
