package ca.mcgill.ecse321.driver;

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

import org.json.JSONArray;
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
    private EditText mCarBrand;
    private EditText mCarModel;
    private EditText mLicensePlate;

    private int carId = -1;

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
        mCarBrand = (EditText) findViewById(R.id.txtcarbrand);
        mCarModel = (EditText) findViewById(R.id.txtcarmodel);
        mLicensePlate = (EditText) findViewById(R.id.txtlicenseplate);


    }

    //    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//    }
    public void attemptRegister(View view) {
        //assume it passes
        //need to check if username conflicts with another
        Intent RegisterIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(RegisterIntent);
        finish();
        addDriverUser();
        Toast.makeText(RegisterActivity.this, "User" + mUsername.getText().toString() + "was created", Toast.LENGTH_LONG).show();
    }

    public void backToLogin(View view) {
        Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(LoginIntent);
    }

    public void addDriverUser() {
        error = "";
        final TextView tv = (TextView) findViewById(R.id.txtusername);
        RequestParams rp = new RequestParams();
        rp.add("username", mUsername.getText().toString());
        rp.add("password", mPassword.getText().toString());
        rp.add("firstname", mFirstName.getText().toString());
        rp.add("lastname", mLastName.getText().toString());
        rp.add("phonenumber", mPhoneNumber.getText().toString());
        rp.add("city", mCity.getText().toString());
        rp.add("address", mAddress.getText().toString());
        rp.add("role", "Driver");

        //TODO
        //create user with post
        HttpUtils.post("api/user/create", rp, new JsonHttpResponseHandler() {
            @Override
            public void onFinish() {
                //refreshErrorMessage();
                tv.setText("");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    carId = (int) response.get("vehicleId");
                } catch (Exception e) {
                    error += e.getMessage();
                }
                //refreshErrorMessage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                System.out.println("USER: " + errorResponse);
                if (errorResponse.equals("User Created")) addDriverCar();;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println("USER: " + errorResponse);
                //refreshErrorMessage();
            }
        });
    }

    public void addDriverCar() {
        error = "";

        final TextView tv = (TextView) findViewById(R.id.txtusername);
        RequestParams rp = new RequestParams();
        final String username = mUsername.getText().toString();
        rp.add("brand", mCarBrand.getText().toString());
        rp.add("model", mCarModel.getText().toString());
        rp.add("plate", mLicensePlate.getText().toString());

        //create user with post
        HttpUtils.post("api/vehicle/create", rp, new JsonHttpResponseHandler() {
            @Override
            public void onFinish() {
                //refreshErrorMessage();
                tv.setText("");
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    carId = (int) response.get("vehicleId");
                } catch (Exception e) {
                    error += e.getMessage();
                }
                //refreshErrorMessage();
            }

            // For some reason it always fails, but the value we're looking for is stored in errorResponse
            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
            //System.out.println("CAR: " + errorResponse);
            carId = Integer.parseInt(errorResponse);
            System.out.println("CAR: " + carId);
            assignDriverToCar(username, carId);
            Toast.makeText(RegisterActivity.this, errorResponse, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(RegisterActivity.this, "Error in creating the car", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void assignDriverToCar(String username, int id) {
        error = "";
        final TextView tv = (TextView) findViewById(R.id.txtusername);
        RequestParams rp = new RequestParams();
        String path = "api/vehicle/assignCar/" + username + "/" + id;

        //create user with post
        //System.out.println("this is my path: " + path);
        HttpUtils.post(path, rp, new JsonHttpResponseHandler() {
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

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }

    }

}
