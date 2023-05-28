package com.example.jopy.ui.activities.edit_account;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jopy.R;
import com.example.jopy.ui.activities.login_and_createAccount.activity_create_account_institute_2_;
import com.github.dhaval2404.imagepicker.ImagePicker;

import static android.content.ContentValues.TAG;

public class VerfiyInstActivity extends AppCompatActivity {
    ImageView verficationImage1;
    Button chooseImageVerButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfiy_inst);
        verficationImage1=findViewById(R.id.verficationImage1);
        chooseImageVerButton1=findViewById(R.id.chooseImageVerButton1);

        chooseImageVerButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(VerfiyInstActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri =data.getData();
        verficationImage1.setImageURI(uri);
        Log.d(TAG, "onActivityResult: uri : " + uri);
    }

}