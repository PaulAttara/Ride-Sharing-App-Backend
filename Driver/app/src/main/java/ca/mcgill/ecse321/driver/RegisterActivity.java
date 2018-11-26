package ca.mcgill.ecse321.driver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//Http request
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;


public class RegisterActivity extends AppCompatActivity {

    // Http request
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

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

        // Building url parameters
        String requestParams = "?";
        requestParams += "username=" + username;
        requestParams += "&firstname=" + firstname;
        requestParams += "&lastname=" + lastname;
        requestParams += "&password=" + password;
        requestParams += "&phonenumber=" + phonenumber;
        requestParams += "&city=" + city;
        requestParams += "&address=" + address;
        requestParams += "&role=driver";

        //Http GET request to login starts here
        String baseURL = "https://sharefare.herokuapp.com/api";
        String pathURL = baseURL + "/user/create" + requestParams;

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.POST, pathURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals(username)) addDriverCar(username);
                else Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Error:" + error.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            }
        });

        mRequestQueue.add(mStringRequest);
    }

    public void addDriverCar(final String username) {

        // Building url parameters
        String requestParams = "?";
        requestParams += "brand=" + mCarBrand.getText().toString();
        requestParams += "&model=" + mCarModel.getText().toString();
        requestParams += "&plate=" + mLicensePlate.getText().toString();

        //Http GET request to login starts here
        String baseURL = "https://sharefare.herokuapp.com/api";
        String pathURL = baseURL + "/vehicle/create" + requestParams;

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.POST, pathURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                carId = Integer.parseInt(response);
                if (carId != -1) assignDriverToCar(username, carId);
                else Toast.makeText(RegisterActivity.this, "Error in creating the vehicle", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Response error in creating the vehicle", Toast.LENGTH_LONG).show();;//display the response on screen
            }
        });

        mRequestQueue.add(mStringRequest);
    }

    public void assignDriverToCar(String username, int carId) {

        //Http GET request to login starts here
        String baseURL = "https://sharefare.herokuapp.com/api";
        String pathURL = baseURL + "/vehicle/assignCar/" + username + "/" + carId + "/";

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.POST, pathURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegisterActivity.this, "New User Created!" + "\n" + response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Request error in assigning user to car.", Toast.LENGTH_LONG).show();;//display the response on screen
            }
        });

        mRequestQueue.add(mStringRequest);

    }

}