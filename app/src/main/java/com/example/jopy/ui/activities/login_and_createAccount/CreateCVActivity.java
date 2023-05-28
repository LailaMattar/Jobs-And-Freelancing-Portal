package com.example.jopy.ui.activities.login_and_createAccount;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Course;
import com.example.jopy.mvp.models.User;
import com.example.jopy.mvp.presenters.LoginPresenter;
import com.example.jopy.mvp.presenters.SignUpPresenter;
import com.example.jopy.ui.activities.HomePageActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class CreateCVActivity extends AppCompatActivity implements SignUpPresenter.Listener , LoginPresenter.Listener {
    //lang
    private TextView tvDay,coursesText,skillsText,workExperienceText;
    boolean[] selectedDay;
    ArrayList<Integer> daylist = new ArrayList<>();
    String[] dayArray = {"english", "arabic", "french", "spanish ", "korean", "japanese"};
    private List<String> languages;
    private String courses;
    private Button finish;
    private SignUpPresenter presenter;
    private LoginPresenter loginPresenter;
    private String skills,workExperience;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_create_c_v);
        initView();
        presenter      = new SignUpPresenter(CreateCVActivity.this,this);
        loginPresenter = new LoginPresenter(CreateCVActivity.this,this);

        //lang
        selectedDay = new boolean[dayArray.length];

        tvDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateCVActivity.this);
                builder.setTitle("Select language");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(dayArray, selectedDay, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //check condition

                        if (isChecked) {
                            daylist.add(which);//add postion
                        } else {
                            for (int i = 0; i < daylist.size(); i++) {
                                {
                                    if (daylist.get(i) == which) {
                                        daylist.remove(daylist.get(i));
                                    }
                                }
                            }
                        }

                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < daylist.size(); i++) {
                            stringBuilder.append(dayArray[daylist.get(i)]);
                            if (i != daylist.size() - 1) {
                                stringBuilder.append("- ");
                            }}
                            tvDay.setText(stringBuilder.toString());
                        Log.d("ttt",stringBuilder.toString());

                    }

                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int j = 0; j < selectedDay.length; j++) {
                            selectedDay[j] = false;
                            daylist.clear();
                            tvDay.setText("");
                            tvDay.setHint("Languages");
                        }
                    }
                });
                //show dialog
                builder.show();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workExperience = workExperienceText.getText().toString();
                skills = skillsText.getText().toString();
                languages = new ArrayList<>();
                courses = coursesText.getText().toString();
                for (int i = 0; i < daylist.size(); i++) {
                    languages.add(dayArray[daylist.get(i)]);
                    Log.e("TAG", "onClick: "+languages.get(i));
                }

                CreateAccountCommonActivity.user.setLanguages(languages);
                CreateAccountCommonActivity.user.setCourse(courses);
                CreateAccountCommonActivity.user.setAccountType("normal_user");
                CreateAccountCommonActivity.user.setSkills(skills);
                CreateAccountCommonActivity.user.setWorkExperience(workExperience);
                presenter.sendToServer(CreateAccountCommonActivity.user);
            }
        });
    }
    void initView(){
        finish             = findViewById(R.id.finishCV);
        tvDay              = findViewById(R.id.languagesCV);
        coursesText        = findViewById(R.id.coursesCV);
        skillsText         = findViewById(R.id.skillsCV);
        workExperienceText = findViewById(R.id.workExperiencesCV);
    }


    @Override
    public void done() {
        Log.e("TAG", "done: Done!!2");
        Intent intent = new Intent(CreateCVActivity.this, HomePageActivity.class);
        loginPresenter.sendToServer(CreateAccountCommonActivity.user);
        startActivity(intent);
    }

    @Override
    public void failure() {
        Log.e("TAG", "failure: failure : register failed");
    }

    @Override
    public void loginDone(User u, String token) {
        LoginActivity.user = new User(CreateAccountCommonActivity.user.getEmail(),CreateAccountCommonActivity.user.getPassword());
        LoginActivity.user = u;
        LoginActivity.user.setToken(token);
        Intent intent = new Intent(CreateCVActivity.this,HomePageActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFailure() {

    }
}
