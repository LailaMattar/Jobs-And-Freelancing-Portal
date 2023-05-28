package com.example.jopy.ui.activities.post_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.PostAccept;
import com.example.jopy.mvp.models.PostJop;
import com.example.jopy.mvp.models.SavedPost;
import com.example.jopy.mvp.models.Tag;
import com.example.jopy.mvp.presenters.AppliedBeforePresenter;
import com.example.jopy.mvp.presenters.ApplyPresenter;
import com.example.jopy.mvp.presenters.DeletePostPresenter;
import com.example.jopy.mvp.presenters.SavePostPresenter;
import com.example.jopy.ui.activities.ApplicantsListActivity;
import com.example.jopy.ui.activities.HomePageActivity;
import com.example.jopy.ui.activities.edit_posts.EditJobPostActivity;
import com.example.jopy.ui.activities.edit_posts.editFreelancePostActivity;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.activities.profile.InstituteProfileActivity;
import com.example.jopy.ui.activities.report.ReportPostActivity;
import com.example.jopy.ui.adapters.PostJopAdapter;
import com.example.jopy.ui.adapters.SavedPostsAdapter;
import com.example.jopy.ui.adapters.TagsAdapter;
import com.example.jopy.ui.fragments.homepage_fragments.AllJobsFragment;
import com.example.jopy.ui.fragments.homepage_fragments.FreelancesFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class JobPostActivity extends AppCompatActivity implements  AppliedBeforePresenter.Listener,ApplyPresenter.Listener, SavePostPresenter.Listener , DeletePostPresenter.Listener {

    private RecyclerView tagsJPRecycler;
    private List<String> tagsJPList;
    private RecyclerView.Adapter tagsJPAdapter;

    private TextView appliedJP, title, location, intsname, time, catg, applicants, desc1, desc2, req1, req2, emTyp1, emTyp2, sal1;

    private  PostAccept isAccepted;


    private Button editJP, applyJP, sendJP, deleteJP, reportJPBtn;
    private ImageView imageViewJob, savedJP;


    private ConstraintLayout repLayoutJP;

    private String from;
    private int ind;
    private Bundle extras;


    public static Post post1;



    private ApplyPresenter applyPresenter;
    private SavePostPresenter savePostPresenter;
    private DeletePostPresenter deletePostPresenter;
    private AppliedBeforePresenter appliedBeforePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_job_post);
        initView();
        post1 = new Post();
        applyPresenter = new ApplyPresenter(JobPostActivity.this, this);
        savePostPresenter = new SavePostPresenter(JobPostActivity.this, this);
        deletePostPresenter = new DeletePostPresenter(JobPostActivity.this, this);
        appliedBeforePresenter = new AppliedBeforePresenter(JobPostActivity.this, this);


        tagsJPList = new ArrayList<>();
        tagsJPRecycler.setHasFixedSize(true);
        tagsJPRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        extras = getIntent().getExtras();
        if (extras != null) {
            ind = extras.getInt("index");
            Log.e("TAG", "index : " + ind);
        }


        tagsJPList = new ArrayList<>();
        tagsJPRecycler.setHasFixedSize(true);
        tagsJPRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        extras = getIntent().getExtras();
        if (extras != null) {
            from = extras.getString("from");
        }
        if (from.equals("saved")) {
            post1 = SavedPostsAdapter.saved;
        } else {
            ind = extras.getInt("index");
            post1 = PostJopAdapter.posts.get(ind);

            Log.e("TAG", "index : " + ind);
        }


        ///////////////////////////////////////////
/////////////////////////////////



        tagsJPList = post1.getTags();

        if (post1.getUser().getImage() == null)
            imageViewJob.setImageResource(R.drawable.blank_profile);
        else
            Picasso.get().load(post1.getUser().getImage()).into(imageViewJob);

        imageViewJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobPostActivity.this, InstituteProfileActivity.class);
                intent.putExtra("id",post1.getUserId());
                startActivity(intent);
            }
        });


        title.setText(post1.getTitle());
        time.setText("2 days ago");
        intsname.setText(post1.getUser().getInstituteName());
        catg.setText(post1.getCategories().get(0));///
        applicants.setText(post1.getNumber_of_applicants() + " Applicants");
        desc2.setText(post1.getAbout());
        sal1.setText(post1.getMoney() + " SP");
        req2.setText(post1.getAbout());
        emTyp2.setText(post1.getEmploymentType());
        location.setText(post1.getLocation());
        tagsJPAdapter = new TagsAdapter(tagsJPList, this);
        tagsJPRecycler.setAdapter(tagsJPAdapter);

        post1.setPost_id(String.valueOf(post1.getId()));
        if (post1.getUserId() == LoginActivity.user.getId()) {

            editJP.setVisibility(View.VISIBLE);
            deleteJP.setVisibility(View.VISIBLE);
            repLayoutJP.setVisibility(View.INVISIBLE);

            applyJP.setVisibility(View.INVISIBLE);
            savedJP.setVisibility(View.INVISIBLE);
        } else {
            editJP.setVisibility(View.INVISIBLE);
            deleteJP.setVisibility(View.INVISIBLE);
            reportJPBtn.setVisibility(View.VISIBLE);
            savedJP.setVisibility(View.VISIBLE);
            isAccepted = new PostAccept();
            isAccepted.setPost_id(post1.getId());
            appliedBeforePresenter.sendToServer(isAccepted);
        }

        applyJP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                applyPresenter.sendToServer(post1);
            }
        });

        if (post1.getUserId() == LoginActivity.user.getId()) {
            applicants.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(JobPostActivity.this, ApplicantsListActivity.class);
                    intent.putExtra("type", "job");
                    startActivity(intent);
                }
            });
        }

        reportJPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobPostActivity.this, ReportPostActivity.class);
                intent.putExtra("report", "job");
                startActivity(intent);

            }
        });

        savedJP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavedPost savedPost = new SavedPost();
                savedPost.setPost_id(post1.getId());
                Log.e("TAG", "getId : " + post1.getId());
                Log.e("TAG", "getPost_id : " + post1.getPost_id());
                savePostPresenter.sendToServer(savedPost);

            }
        });
        editJP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobPostActivity.this, EditJobPostActivity.class);
                startActivity(intent);
            }
        });

        deleteJP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post deletPost = new Post();
                deletPost = post1;
                deletePostPresenter.sendToServer(deletPost);
            }
        });
    }

    void initView() {
        tagsJPRecycler = findViewById(R.id.tagsJPRecycler);
        title = findViewById(R.id.jobPostTitle);
        imageViewJob = findViewById(R.id.jobPostImageView);
        location = findViewById(R.id.locationJP);
        time = findViewById(R.id.timeJP);
        intsname = findViewById(R.id.instNameJP);
        catg = findViewById(R.id.categoryJP);
        applicants = findViewById(R.id.applicantsJP);
        desc1 = findViewById(R.id.descriptionJP);
        desc2 = findViewById(R.id.desTextJP);
        req1 = findViewById(R.id.experienceJP);
        req2 = findViewById(R.id.expTextJP);
        emTyp1 = findViewById(R.id.empTypeJP);
        emTyp2 = findViewById(R.id.empTextJP);
        sal1 = findViewById(R.id.salTextJP);
        reportJPBtn = findViewById(R.id.reportJPBtn);
        repLayoutJP = findViewById(R.id.repLayoutJP);
        savedJP = findViewById(R.id.savedJP);
        editJP = findViewById(R.id.editJP);
        sendJP = findViewById(R.id.sendJP);
        deleteJP = findViewById(R.id.deleteJP);
        reportJPBtn = findViewById(R.id.reportJPBtn);
        applyJP = findViewById(R.id.applyJP);
        appliedJP = findViewById(R.id.appliedJP);

    }

    @Override
    public void applyDone(String message) {
        Log.e("TAG", "apply: Doneeeee!!!");

        if(message.equals("done")){
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(JobPostActivity.this, SweetAlertDialog.SUCCESS_TYPE);
            Log.e("TAG", "apply: Doneeeee!!!");

            sweetAlertDialog
                    .setTitleText("DONE")
                    .setContentText("Your request has been successfully sent")
                    .show();
            sweetAlertDialog.setConfirmButton("OK", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent intent = new Intent(JobPostActivity.this, HomePageActivity.class);
                    sweetAlertDialog.dismiss();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    JobPostActivity.this.finish();
                }
            });
        }
        else {
            Toast.makeText(JobPostActivity.this, message, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void applyFailure() {

    }

    @Override
    public void saveDone() {
        Log.e("TAG", "save: Doneeeee!!!");
        Toast.makeText(JobPostActivity.this, "Post saved", Toast.LENGTH_SHORT).show();
        savedJP.setImageResource(R.drawable.ic_bookmarkfill);
    }

    @Override
    public void saveFailure() {

    }

    @Override
    public void deletePostDone() {
        Log.e("TAG", "save: Doneeeee!!!");
        Toast.makeText(JobPostActivity.this, "Post deleted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(JobPostActivity.this, HomePageActivity.class);
        startActivity(intent);
    }

    @Override
    public void deletePostFailure() {

    }

    @Override
    public void appliledBeforeDone(boolean b) {
        Log.e("TAG", "b : " + b);

        if (b==true) {
            applyJP.setVisibility(View.INVISIBLE);
            appliedJP.setVisibility(View.VISIBLE);

        }
        else {
            applyJP.setVisibility(View.VISIBLE);

        }
        }




    @Override
    public void appliledBeforeFailure() {

    }
}