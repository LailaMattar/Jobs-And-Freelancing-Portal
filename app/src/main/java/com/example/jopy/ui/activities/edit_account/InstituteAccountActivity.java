package com.example.jopy.ui.activities.edit_account;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jopy.R;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class InstituteAccountActivity extends  AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_institute_account);


        Spinner countryAI = findViewById(R.id.countryAI);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Country, R.layout.spinner_selector_style2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryAI.setAdapter(adapter);
        countryAI.setOnItemSelectedListener(this);

        Spinner cityAI = findViewById(R.id.cityAI);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.city, R.layout.spinner_selector_style2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAI.setAdapter(adapter2);
        cityAI.setOnItemSelectedListener(this);


        Spinner  typeAI = findViewById(R.id.typeAI);

        ArrayAdapter<CharSequence> adapterTypeInst = ArrayAdapter.createFromResource(this, R.array.Country, R.layout.spinner_selector_style2);
        adapterTypeInst.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAI.setAdapter(adapterTypeInst);
        typeAI.setOnItemSelectedListener(this);


        Spinner numberOfEmpAI = findViewById(R.id.numberOfEmpAI);
        ArrayAdapter<CharSequence> adapterNumEmp = ArrayAdapter.createFromResource(this, R.array.Country, R.layout.spinner_selector_style2);
        adapterNumEmp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberOfEmpAI.setAdapter(adapterNumEmp);
        numberOfEmpAI.setOnItemSelectedListener(this);


    }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String text =parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    }