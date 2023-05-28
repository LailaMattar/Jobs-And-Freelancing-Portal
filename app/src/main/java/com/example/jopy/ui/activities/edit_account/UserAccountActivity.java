package com.example.jopy.ui.activities.edit_account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jopy.R;
import com.example.jopy.ui.activities.login_and_createAccount.CreateAccountUser_1_Activity;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import java.util.Calendar;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class UserAccountActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ////Date of birth//
    TextView dat;
    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_user_account);

        dat  = findViewById(R.id.birthdayAU);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UserAccountActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                dat.setText(date);
            }
        };

///////
        Spinner countryAU = findViewById(R.id.countryAU);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Country, R.layout.spinner_selector_style2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryAU.setAdapter(adapter);
        countryAU.setOnItemSelectedListener(this);

        Spinner cityAU = findViewById(R.id.cityAU);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.city, R.layout.spinner_selector_style2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAU.setAdapter(adapter2);
        cityAU.setOnItemSelectedListener(this);


        Spinner  fieldAU = findViewById(R.id.fieldAU);

        ArrayAdapter<CharSequence> adapterfieldAU = ArrayAdapter.createFromResource(this, R.array.Country, R.layout.spinner_selector_style2);
        adapterfieldAU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fieldAU.setAdapter(adapterfieldAU);
        fieldAU.setOnItemSelectedListener(this);


        Spinner educLvlAU = findViewById(R.id.educLvlAU);
        ArrayAdapter<CharSequence> adaptereducLvlAU = ArrayAdapter.createFromResource(this, R.array.Country, R.layout.spinner_selector_style2);
        adaptereducLvlAU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        educLvlAU.setAdapter(adaptereducLvlAU);
        educLvlAU.setOnItemSelectedListener(this);

        Spinner gendreAU = findViewById(R.id.gendreAU);
        ArrayAdapter<CharSequence> adaptergendreAU = ArrayAdapter.createFromResource(this, R.array.Country, R.layout.spinner_selector_style2);
        adaptergendreAU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gendreAU.setAdapter(adaptergendreAU);
        gendreAU.setOnItemSelectedListener(this);

////
        NachoTextView tagAU=findViewById(R.id.tagAU);
        tagAU.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);

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