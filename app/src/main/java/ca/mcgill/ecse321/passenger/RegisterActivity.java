package ca.mcgill.ecse321.passenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {


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
        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

    }

    public void attemptRegister(){
        //assume it passes
        //need to check if username conflicts with another
        Intent RegisterIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(RegisterIntent);
    }
}
