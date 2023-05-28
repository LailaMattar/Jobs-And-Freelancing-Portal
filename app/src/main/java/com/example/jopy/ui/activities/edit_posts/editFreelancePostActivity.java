package com.example.jopy.ui.activities.edit_posts;

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
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class editFreelancePostActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_edit_freelance_post);


        Spinner ecategoryTFSpinner = findViewById(R.id.ecategoryTFSpinner);
        ArrayAdapter<CharSequence> eadapterCategoryTF = ArrayAdapter.createFromResource(this, R.array.Country, R.layout.spinner_selector_style2);
        eadapterCategoryTF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ecategoryTFSpinner.setAdapter(eadapterCategoryTF);
        ecategoryTFSpinner.setOnItemSelectedListener(this);


        ///Tag chip

        NachoTextView enachoTextViewTF = findViewById(R.id.etagTF);
        enachoTextViewTF.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);

    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}