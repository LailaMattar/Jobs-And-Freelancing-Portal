package com.example.jopy.ui.activities.login_and_createAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Notification;
import com.example.jopy.mvp.models.User;
import com.example.jopy.mvp.presenters.LoginPresenter;
import com.example.jopy.ui.activities.HomeCompany;
import com.example.jopy.ui.activities.HomePageActivity;
import com.example.jopy.ui.activities.NotificationActivity;
import com.example.jopy.ui.activities.payment.E_WalletActivity;
import com.example.jopy.ui.activities.SavedPostsListActivity;
import com.example.jopy.util.UtilFunctions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.Listener {

    private LoginPresenter loginPresenter;
    private EditText inputEmail, inputPassword;
    private TextView createAccount,wrongData,skip;
    private Button loginButton;
    private String email, password;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(LoginActivity.this,this);
        initView();
        wrongData.setVisibility(View.INVISIBLE);
        skip.setVisibility(View.INVISIBLE);


        final Intent intent = new Intent(this,SelectTypeActivity.class);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email    = inputEmail.getText().toString();
                password = inputPassword.getText().toString();
                Log.e("TAG", "onClick: email : "+email);
                Log.e("TAG", "onClick: password : "+password);

                user = new User(email,password);
                loginPresenter.sendToServer(user);
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }

    void initView(){
        inputEmail     = findViewById(R.id.inputEmail);
        inputPassword  = findViewById(R.id.inputPassword);
        loginButton    = findViewById(R.id.loginButton);
        createAccount  = findViewById(R.id.createNewAccountLogin);
        wrongData      = findViewById(R.id.wrongData);
        skip           = findViewById(R.id.skip);
    }

    @Override
    public void loginDone(User u,String token) {
        Log.e("TAG", "loginDone: Doneeeee!!!");
        user=u;
        user.setToken(token);
        Log.e("TAG", "loginDone: tttt"+user.getGender() );
        wrongData.setText(" ");
        UtilFunctions.saveLocal("token",user.getToken(),LoginActivity.this);

        if(u.getDetails().getInstituteName() == null){
            Intent intent = new Intent(this, HomePageActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, HomeCompany.class);
            startActivity(intent);
        }

    }

    @Override
    public void loginFailure() {
        wrongData.setVisibility(View.VISIBLE);
        Log.e("TAG", "loginFailure: failure");
    }
}