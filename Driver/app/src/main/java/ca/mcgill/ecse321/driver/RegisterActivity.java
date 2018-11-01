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

    }

    public void backToLogin(View view) {
        Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(LoginIntent);
    }

    public void addDriverUser() {
        error = "";
        final TextView tv = (TextView) findViewById(R.id.txtusername);
        RequestParams rp = new RequestParams();

        final String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        String firstname = mFirstName.getText().toString();
        String lastname = mLastName.getText().toString();
        String phonenumber = mPhoneNumber.getText().toString();
        String city = mCity.getText().toString();
        String address = mAddress.getText().toString();

        //checking for empty boxes
        if ("".equals(password) || "".equals(firstname) ||"".equals(lastname) ||"".equals(phonenumber) ||"".equals(city) || "".equals(address)){
            Toast.makeText(RegisterActivity.this, "One or more field empty in user", Toast.LENGTH_LONG).show();
            return;
        }

        String brand = mCarBrand.getText().toString();
        String model = mCarModel.getText().toString();
        String plate = mLicensePlate.getText().toString();

        if ("".equals(brand) || "".equals(model) ||"".equals(plate)) {
            Toast.makeText(RegisterActivity.this, "One or more field empty in car", Toast.LENGTH_LONG).show();
            return;
        }

        rp.add("username", username);
        rp.add("password", password);
        rp.add("firstname", firstname);
        rp.add("lastname", lastname);
        rp.add("phonenumber", phonenumber);
        rp.add("city", city);
        rp.add("address", address);
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

            // ONSUCCESS: For some reason it always fails, but the value we're looking for is stored in errorResponse
            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                System.out.println("USER: " + errorResponse);
                if (errorResponse.equals("User Created.")) addDriverCar(username);
                else Toast.makeText(RegisterActivity.this, errorResponse, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println("USER: " + errorResponse);
                //refreshErrorMessage();
            }
        });
    }

    public void addDriverCar(final String username) {
        error = "";

        final TextView tv = (TextView) findViewById(R.id.txtusername);
        RequestParams rp = new RequestParams();

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

            // ONSUCCESS: For some reason it always fails, but the value we're looking for is stored in errorResponse
            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
            //System.out.println("CAR: " + errorResponse);
            carId = Integer.parseInt(errorResponse);
            //System.out.println("CAR: " + carId);

            if (carId != -1) assignDriverToCar(username, carId);
            else Toast.makeText(RegisterActivity.this, "Error in creating the vehicle", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(RegisterActivity.this, "Error in creating the vehicle", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void assignDriverToCar(String username, int id) {
        error = "";
        final TextView tv = (TextView) findViewById(R.id.txtusername);
        RequestParams rp = new RequestParams();

        String pathUrl = "api/vehicle/assignCar/" + username + "/" + id;

        //create user with post
        System.out.println("this is my path: " + pathUrl);
        HttpUtils.post(pathUrl, rp, new JsonHttpResponseHandler() {
            @Override
            public void onFinish() {
                //refreshErrorMessage()
                tv.setText("");
            }

            // ONSUCCESS: For some reason it always fails, but the value we're looking for is stored in errorResponse
            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                Toast.makeText(RegisterActivity.this, errorResponse, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(RegisterActivity.this, "Error in assigning user to car", Toast.LENGTH_LONG).show();
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
