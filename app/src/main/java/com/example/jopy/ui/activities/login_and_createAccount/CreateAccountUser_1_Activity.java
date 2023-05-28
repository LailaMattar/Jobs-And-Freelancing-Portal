package com.example.jopy.ui.activities.login_and_createAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jopy.R;

import java.util.Calendar;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class CreateAccountUser_1_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ////Date of birth//
    TextView dat;
    DatePickerDialog.OnDateSetListener setListener;
    ///
    private Button next;
    private String firstName,lastName,gender,birthYear;
    private TextView firstNameText,lastNameText;
    private RadioButton male , female;
    private int yearStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_create_account_user_1_);
        initView();

        Calendar calendar = Calendar.getInstance();
        yearStr = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateAccountUser_1_Activity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, yearStr, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                yearStr = year;
                dat.setText(date);
            }
        };


//    gender

        Spinner gendreSpinner = findViewById(R.id.gendre);
        gendreSpinner.setVisibility(View.GONE);
        ArrayAdapter<CharSequence> adapterGen = ArrayAdapter.createFromResource(this, R.array.gendre, R.layout.spinner_selector_style);
        adapterGen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gendreSpinner.setAdapter(adapterGen);
        gendreSpinner.setOnItemSelectedListener(this);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameText.getText().toString();
                lastName  = lastNameText.getText().toString();
                birthYear = Integer.toString(yearStr);
                if(male.isChecked()) gender = "male";
                if(female.isChecked())gender = "female";

                CreateAccountCommonActivity.user.setFirstName(firstName);
                CreateAccountCommonActivity.user.setLastName(lastName);
                CreateAccountCommonActivity.user.setGender(gender);
                CreateAccountCommonActivity.user.setBirthYear(birthYear);
                Log.e("TAG", "onClick: year : "+birthYear );

                if(gender == null || firstName.equals("") || lastName.equals("")){
                    Toast.makeText(CreateAccountUser_1_Activity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(CreateAccountUser_1_Activity.this,activity_create_account_user_2_.class);
                    startActivity(intent);
                }
            }
        });

    }

    void initView(){
        next          = findViewById(R.id.nextUser1);
        dat           = findViewById(R.id.dateOfBirth);
        firstNameText = findViewById(R.id.firstName);
        lastNameText  = findViewById(R.id.lastName);
        male          = findViewById(R.id.male);
        female        = findViewById(R.id.female);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text =parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }}

