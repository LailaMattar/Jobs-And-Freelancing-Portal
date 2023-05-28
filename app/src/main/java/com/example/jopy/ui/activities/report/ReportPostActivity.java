package com.example.jopy.ui.activities.report;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Report;
import com.example.jopy.mvp.presenters.ApplyPresenter;
import com.example.jopy.mvp.presenters.ReportPresenter;
import com.example.jopy.ui.activities.post_details.FreelancePostActivity;
import com.example.jopy.ui.activities.post_details.JobPostActivity;
import com.example.jopy.ui.activities.post_details.ServicePostActivity;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class ReportPostActivity extends AppCompatActivity  implements ReportPresenter.Listener {
    private Bundle extras;

    private EditText reasonRep;
    private Button sendRep;
    private Report r;
    private ReportPresenter reportPresenter;
    private String reportType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_report_post);

        initView();


        extras = getIntent().getExtras();
        if (extras != null) {
            reportType = extras.getString("report");
            Log.e("TAG", "report : " + reportType);
        }


        reportPresenter=new ReportPresenter(ReportPostActivity.this, this);


        sendRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r=new Report();

                r.setReason(reasonRep.getText().toString());

                if(reportType.equals("freelance")){
                    r.setPost_id(FreelancePostActivity.post1.getPost_id());

                }
                else if (reportType.equals("service")){

                    r.setPost_id(ServicePostActivity.post1.getPost_id());
                }
                else {
                    r.setPost_id(JobPostActivity.post1.getPost_id());

                }
                Log.e("TAG", "repp : id  + reason "+ r.getPost_id() + r.getReason());

                reportPresenter.sendToServer(r);


            }
        });







    }






void initView(){
        reasonRep=findViewById(R.id.reasonRep);
    sendRep=findViewById(R.id.sendRep);


}


    @Override
    public void reportDone() {
        Log.e("TAG", "report: Doneeeee!!!");
        Toast.makeText(ReportPostActivity.this,"The post has been reported successfully",Toast.LENGTH_SHORT).show();
        finish();///go back to previous activity android

    }

    @Override
    public void reportFailure() {

    }
}