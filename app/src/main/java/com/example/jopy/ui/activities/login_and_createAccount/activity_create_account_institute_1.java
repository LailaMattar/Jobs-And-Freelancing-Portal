package com.example.jopy.ui.activities.login_and_createAccount;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.jopy.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class activity_create_account_institute_1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner typeOfInstitutionSpinner,numberOfEmployeesSpinner;
    private List<String> numOfEmployeeList, instituteTypeList;
    private Button next;
    private EditText institutionNameText,institutionFieldText;
    private String institutionName,institutionField, instituteType,numOfEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_create_account_institute_1);
        initView();

        numOfEmployeeList = new ArrayList<>();
        instituteTypeList = new ArrayList<>();

        instituteType = "Institute Type";
        instituteTypeList.add("Institute Type");
        instituteTypeList.add("Company");
        instituteTypeList.add("Bank");
        instituteTypeList.add("Clinic");
        instituteTypeList.add("Educational institution");
        instituteTypeList.add("University");
        instituteTypeList.add("Governmental institution");
        instituteTypeList.add("Gym");
        instituteTypeList.add("Factory");

        numOfEmployee = "Number of Employees";
        numOfEmployeeList.add("Number of Employees");
        numOfEmployeeList.add("1-10");
        numOfEmployeeList.add("11-50");
        numOfEmployeeList.add("51-200");
        numOfEmployeeList.add("200-500");
        numOfEmployeeList.add("500-1000");
        numOfEmployeeList.add("+1000");
        numOfEmployeeList.add("more than 20");

        ArrayAdapter adapterTypeInst = new ArrayAdapter(this,R.layout.spinner_selector_style, instituteTypeList);
        adapterTypeInst.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeOfInstitutionSpinner.setAdapter(adapterTypeInst);
        typeOfInstitutionSpinner.setOnItemSelectedListener(this);


        ArrayAdapter adapterNumEmp = new ArrayAdapter(this,R.layout.spinner_selector_style, numOfEmployeeList);
        adapterNumEmp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberOfEmployeesSpinner.setAdapter(adapterNumEmp);
        numberOfEmployeesSpinner.setOnItemSelectedListener(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                institutionName  = institutionNameText.getText().toString();
                institutionField = institutionFieldText.getText().toString();

                CreateAccountCommonActivity.user.setInstituteName(institutionName);
                CreateAccountCommonActivity.user.setInstituteField(institutionField);
                CreateAccountCommonActivity.user.setNumberOfEmployees(numOfEmployee);
                CreateAccountCommonActivity.user.setInstituteType(instituteType);

                Log.e("TAG", "onClick: tetoo : "+numOfEmployee);
                if(institutionName.equals("") ||
                        instituteType.equals("Institute Type") ||
                        numOfEmployee.equals("Number of Employees") ||
                        institutionField.equals("")){
                    Toast.makeText(activity_create_account_institute_1.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(activity_create_account_institute_1.this,activity_create_account_institute_2_.class);
                    startActivity(intent);
                }
            }
        });
    }

    void initView(){
        numberOfEmployeesSpinner = findViewById(R.id.numberOfEmployeesSpinner);
        typeOfInstitutionSpinner = findViewById(R.id.typeOfInstitution);
        next                     = findViewById(R.id.nextInstitute1);
        institutionFieldText     = findViewById(R.id.institutionField);
        institutionNameText      = findViewById(R.id.institutionName);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == typeOfInstitutionSpinner.getId()){
            String text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            instituteType = text;
        }
        if(parent.getId() == numberOfEmployeesSpinner.getId()){
            Log.e("TAG", "onItemSelected: test test test ");
            String text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            numOfEmployee = text;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}