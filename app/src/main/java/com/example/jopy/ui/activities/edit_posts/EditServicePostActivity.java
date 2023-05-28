package com.example.jopy.ui.activities.edit_posts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jopy.R;
import com.example.jopy.ui.activities.type_post.TypeServicePostActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class EditServicePostActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView eImageTS;
    Button echooseImageTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_edit_service_post);


        Spinner ecategoryTSSpinner = findViewById(R.id.ecategoryTSSpinner);
        ArrayAdapter<CharSequence> eadapterCategoryTS = ArrayAdapter.createFromResource(this, R.array.Country, R.layout.spinner_selector_style2);
        eadapterCategoryTS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ecategoryTSSpinner.setAdapter(eadapterCategoryTS);
        ecategoryTSSpinner.setOnItemSelectedListener(this);

        //
        eImageTS=findViewById(R.id.eImageTS);
        //


        echooseImageTS=findViewById(R.id.echooseImageTS);
        eImageTS.setImageResource(R.drawable.uu);

        echooseImageTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(EditServicePostActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });







        ///Tag chip

        NachoTextView enachoTextViewTS=findViewById(R.id.etagTS);
        enachoTextViewTS.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);

    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri =data.getData();
        eImageTS.setImageURI(uri);
    }









}
