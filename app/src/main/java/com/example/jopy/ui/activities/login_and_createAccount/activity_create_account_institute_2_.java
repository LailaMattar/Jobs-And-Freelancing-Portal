package com.example.jopy.ui.activities.login_and_createAccount;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jopy.R;
import com.example.jopy.mvp.models.User;
import com.example.jopy.mvp.presenters.LoginPresenter;
import com.example.jopy.mvp.presenters.SignUpCompanyPresenter;
import com.example.jopy.ui.activities.profile.InstituteProfileActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class activity_create_account_institute_2_ extends AppCompatActivity implements SignUpCompanyPresenter.Listener , LoginPresenter.Listener {
    private ImageView verficationImage;
    private Button chooseImageVerButton,done;
    private EditText aboutInstitutionText;
    public static String path,aboutInstitution;
    private SignUpCompanyPresenter signUpCompanyPresenter;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_create_account_institute_2_);
        initView();
        signUpCompanyPresenter = new SignUpCompanyPresenter(activity_create_account_institute_2_.this,this);
        loginPresenter         = new LoginPresenter(activity_create_account_institute_2_.this,this);

        chooseImageVerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(activity_create_account_institute_2_.this)
                      .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                       .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutInstitution = aboutInstitutionText.getText().toString();
                CreateAccountCommonActivity.user.setAbout(aboutInstitution);
                CreateAccountCommonActivity.user.setAccountType("company");

                if(aboutInstitution.equals("") || path == null){
                    Toast.makeText(activity_create_account_institute_2_.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else {
                    signUpCompanyPresenter.sendToServer(CreateAccountCommonActivity.user);
                }
            }
        });
    }

    void initView(){
        aboutInstitutionText = findViewById(R.id.aboutInstitution);
        chooseImageVerButton = findViewById(R.id.chooseImageVerButton);
        verficationImage     = findViewById(R.id.verficationImage);
        done                 = findViewById(R.id.DoneInstitute2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri =data.getData();
        path = uri.getPath();
        verficationImage.setImageURI(uri);
    }

    @Override
    public void registerCompanyDone() {
        loginPresenter.sendToServer(CreateAccountCommonActivity.user);
        Intent intent = new Intent(activity_create_account_institute_2_.this, InstituteProfileActivity.class);
//        startActivity(intent);
    }

    @Override
    public void registerCompanyFailure() {
        Log.e("TAG", "registerCompanyFailure: register company failure");
    }

    @Override
    public void loginDone(User u, String token) {
        LoginActivity.user = new User(CreateAccountCommonActivity.user.getEmail(),CreateAccountCommonActivity.user.getPassword());
        LoginActivity.user = u;
        LoginActivity.user.setToken(token);
    }

    @Override
    public void loginFailure() {

    }
}