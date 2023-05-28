package com.example.jopy.ui.activities.login_and_createAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jopy.R;
import com.example.jopy.mvp.models.User;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class CreateAccountCommonActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button next;
    private int type;
    private String email,password,confirmPassword,mobileNumber,country,city;
    private EditText emailText,passwordText,confirmPasswordText,mobileNumberText;
    private Spinner countrySpinner,citySpinner;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_create_account_common);

        Intent intent = getIntent();
        initView();
        type = intent.getIntExtra("type",0);


        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.Country,R.layout.spinner_selector_style);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);
        countrySpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2= ArrayAdapter.createFromResource(this,R.array.city,R.layout.spinner_selector_style);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter2);
        citySpinner.setOnItemSelectedListener(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    email           = emailText.getText().toString();
                    password        = passwordText.getText().toString();
                    confirmPassword = confirmPasswordText.getText().toString();
                    mobileNumber    = mobileNumberText.getText().toString();

                    user = new User(email,password);
                    user.setPhoneNumber(mobileNumber);
                    user.setCountry(country);
                    user.setCity(city);

                    if(email.equals("")||password.equals("")||confirmPassword.equals("")||mobileNumber.equals("")
                        ||country.equals("Country")||city.equals("City")){
                        Toast.makeText(CreateAccountCommonActivity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(password.equals(confirmPassword)){
                            if(type == 1){
                                Intent employeeIntent = new Intent(CreateAccountCommonActivity.this,CreateAccountUser_1_Activity.class);
                                startActivity(employeeIntent);
                            }
                            else {
                                Intent employeeIntent = new Intent(CreateAccountCommonActivity.this,activity_create_account_institute_1.class);
                                startActivity(employeeIntent);
                            }
                        }
                        else{
                            Toast.makeText(CreateAccountCommonActivity.this,"Please confirm your password correctly",Toast.LENGTH_SHORT).show();
                        }
                    }

            }
        });

    }

    void initView(){
        next                = findViewById(R.id.nextCommon);
        emailText           = findViewById(R.id.emailCommon);
        passwordText        = findViewById(R.id.createPassword);
        confirmPasswordText = findViewById(R.id.confirmPassword);
        mobileNumberText    = findViewById(R.id.mobileNumber);
        countrySpinner      = findViewById(R.id.countrySpinner);
        citySpinner         = findViewById(R.id.citySpinner);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == countrySpinner.getId()){
            String text =parent.getItemAtPosition(position).toString();
            country = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
        }
        if(parent.getId() == citySpinner.getId()){
            String text =parent.getItemAtPosition(position).toString();
            city = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(parent.getId() == countrySpinner.getId()){
            country = "Country";
        }
        if(parent.getId() == citySpinner.getId()){
            city = "City";
        }
    }

}