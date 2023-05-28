package com.example.jopy.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Applicant;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.presenters.GetApplicantsPresenter;
import com.example.jopy.ui.activities.post_details.FreelancePostActivity;
import com.example.jopy.ui.activities.post_details.JobPostActivity;
import com.example.jopy.ui.activities.post_details.ServicePostActivity;
import com.example.jopy.ui.adapters.ApplicantsAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class ApplicantsListActivity extends AppCompatActivity implements GetApplicantsPresenter.Listener {
    private Bundle extras,extras2;
    private String postType, id;
    private int postType2;
    private Button allAppp,accAppp;
    private RecyclerView applicantsRecycler;
    private RecyclerView.Adapter applicantsAdapter;
    public static List<Applicant> waitApp,allApp,accApp;
    private static Post post2;
    private GetApplicantsPresenter getApplicantsPresenter;
private TextView titleAPP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_applicants_list);

        initView();


        getApplicantsPresenter = new GetApplicantsPresenter(ApplicantsListActivity.this, this);
        post2 = new Post();
        waitApp = new ArrayList<>();
        allApp = new ArrayList<>();
        accApp = new ArrayList<>();

        extras2 = getIntent().getExtras();
        id = extras2.getString("id","-1");
        postType2 = extras2.getInt("type_notification");

        if(!id.equals("-1")){
            post2 = new Post(id);
            Log.e("TAG", "index : " + postType2);
            if (postType2 == 2) {
                post2 = FreelancePostActivity.post1;
                titleAPP.setText("Applicants list");
            }
            else if(postType2 == 3) {
                post2 = ServicePostActivity.post1;
                titleAPP.setText("Purchasers list");
            }
            else{

                post2 = JobPostActivity.post1;
                titleAPP.setText("Applicants list");
            }
            getApplicantsPresenter.sendToServer(post2);
        }



        extras = getIntent().getExtras();
        postType = extras.getString("type");
        if(postType != null){
            if (postType.equals("freelance")) {
                post2 = FreelancePostActivity.post1;
                titleAPP.setText("Applicants list");
            }
            else if(postType.equals("service")) {
                post2 = ServicePostActivity.post1;
                titleAPP.setText("Purchasers list");
            }
            else{

                post2 = JobPostActivity.post1;
                titleAPP.setText("Applicants list");
            }

            getApplicantsPresenter.sendToServer(post2);
        }
        applicantsRecycler.setHasFixedSize(true);
        applicantsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

allAppp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        setlist(waitApp);
    }
});
accAppp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        setlist(accApp);

    }
});
    }


    void initView() {
        applicantsRecycler = findViewById(R.id.app1Recyc);
        titleAPP = findViewById(R.id.titleAPP);
        accAppp = findViewById(R.id.accAppp);
        allAppp = findViewById(R.id.allAppp);
    }

    @Override
    public void getApplicantsDone(List<Applicant> a) {
        allApp=a;
        for(int i=0;i<allApp.size();i++) {
            if (allApp.get(i).getApplication_status().equals("waiting")) {
                waitApp.add(allApp.get(i));

            }
            if (allApp.get(i).getApplication_status().equals("accepted")) {
                accApp.add(allApp.get(i));

            }
        }


    if(allApp.size()==0)
    {
        Toast.makeText(ApplicantsListActivity.this,"There are no applicants yet ..",Toast.LENGTH_SHORT).show();

    }}

    @Override
    public void getApplicantsFailure() {

    }
   void setlist(List<Applicant> a){
       applicantsAdapter = new ApplicantsAdapter(a, this,ApplicantsListActivity.this);
       applicantsRecycler.setAdapter(applicantsAdapter);
   }
}