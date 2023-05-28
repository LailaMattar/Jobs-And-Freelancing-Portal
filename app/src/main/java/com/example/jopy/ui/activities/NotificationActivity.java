package com.example.jopy.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Applicant;
import com.example.jopy.mvp.models.Notification;
import com.example.jopy.mvp.presenters.GetNotificationPresenter;
import com.example.jopy.ui.activities.type_post.TypeFreelancePostActivity;
import com.example.jopy.ui.adapters.NotificationAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class NotificationActivity extends AppCompatActivity  {
    private List<Applicant> applicantList;
    private RecyclerView notificationRecyclerView;
    private RecyclerView.Adapter notificationAdapter;
    private String from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        applicantList = new ArrayList<>();
        Bundle extras = new Bundle();
        extras = getIntent().getExtras();
        from = extras.getString("from");
        if(from.equals("company")){
             applicantList = HomeCompany.applicants;
        }
        else {
            applicantList = HomePageActivity.applicants;
        }
        setContentView(R.layout.activity_notification);
//        applicantList = HomePageActivity.applicants;

        notificationRecyclerView = findViewById(R.id.recyclerviewNotification);
        notificationRecyclerView.setHasFixedSize(true);
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        notificationAdapter = new NotificationAdapter(applicantList,NotificationActivity.this);
        notificationRecyclerView.setAdapter(notificationAdapter);

    }

    @Override
    public void onBackPressed() {
        HomePageActivity.applicants = new ArrayList<>();
        Intent intent = new Intent(NotificationActivity.this, HomePageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }
}