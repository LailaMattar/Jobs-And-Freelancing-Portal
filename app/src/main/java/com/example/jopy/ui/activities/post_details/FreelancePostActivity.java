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
import com.example.jopy.mvp.models.SavedPost;
import com.example.jopy.mvp.models.Tag;
import com.example.jopy.mvp.models.User;
import com.example.jopy.mvp.presenters.AcceptefBeforePresenter;
import com.example.jopy.mvp.presenters.AppliedBeforePresenter;
import com.example.jopy.mvp.presenters.ApplyPresenter;
import com.example.jopy.mvp.presenters.CreatePostPresenter;
import com.example.jopy.mvp.presenters.DeletePostPresenter;
import com.example.jopy.mvp.presenters.SavePostPresenter;
import com.example.jopy.ui.activities.ApplicantsListActivity;
import com.example.jopy.ui.activities.HomePageActivity;
import com.example.jopy.ui.activities.SavedPostsListActivity;
import com.example.jopy.ui.activities.edit_posts.editFreelancePostActivity;
import com.example.jopy.ui.activities.login_and_createAccount.CreateAccountCommonActivity;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.activities.profile.UserProfileActivity;
import com.example.jopy.ui.activities.report.ReportPostActivity;
import com.example.jopy.ui.activities.type_post.TypeFreelancePostActivity;
import com.example.jopy.ui.adapters.SavedPostsAdapter;
import com.example.jopy.ui.adapters.TagsAdapter;
import com.example.jopy.ui.fragments.homepage_fragments.FreelancesFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class FreelancePostActivity extends AppCompatActivity implements AppliedBeforePresenter.Listener,ApplyPresenter.Listener, SavePostPresenter.Listener , DeletePostPresenter.Listener {
    private RecyclerView tagsFPRecycler;
    private List<String> tagsFPList;
    private RecyclerView.Adapter tagsFPAdapter;
    private Bundle extras;
    private int ind;
    private String from;
    private  PostAccept isAccepted;

    public static Post post1;
    private ConstraintLayout repLayoutFP;


    private TextView appliedFP,freelancePostTitle, userNameFP, timeFP, categoryFP, applicantsFP,
            desTextFP, salTextFP, reportFPTxt;
    private Button editFP, applyFP, sendFP, deleteFP, reportFPBtn;
    private ImageView accountImg, savedFP;


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
        setContentView(R.layout.activity_freelance_post);
        initView();
        post1 = new Post();
        applyPresenter = new ApplyPresenter(FreelancePostActivity.this, this);
        savePostPresenter = new SavePostPresenter(FreelancePostActivity.this, this);
        deletePostPresenter = new DeletePostPresenter(FreelancePostActivity.this, this);
        appliedBeforePresenter = new AppliedBeforePresenter(FreelancePostActivity.this, this);

        tagsFPList = new ArrayList<>();
        tagsFPRecycler.setHasFixedSize(true);
        tagsFPRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        extras = getIntent().getExtras();
        if (extras != null) {
            from = extras.getString("from");
        }
            if(from.equals("saved")){
                post1= SavedPostsAdapter.saved;
            }
            else{
            ind = extras.getInt("index");
                post1 = FreelancesFragment.temp.get(ind);

                Log.e("TAG", "index : " + ind);
        }

///////////////////////////////////////////
/////////////////////////////////



        tagsFPList = post1.getTags();

        if(post1.getUser().getImage() == null)
            accountImg.setImageResource(R.drawable.blank_profile);
        else
            Picasso.get().load(post1.getUser().getImage()).into(accountImg);

        accountImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = post1.getUserId();
                Intent intent = new Intent(getBaseContext(), UserProfileActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        freelancePostTitle.setText(post1.getTitle());
        //  freelancePostImageView=.setText(FreelancesFragment.temp.get(ind).);
        timeFP.setText("2 days ago");
        userNameFP.setText(post1.getUser().getName());
        categoryFP.setText(post1.getCategories().get(0));///
        applicantsFP.setText(String.valueOf(post1.getNumber_of_applicants()) + " Applicants");
        desTextFP.setText(post1.getAbout());
        salTextFP.setText(post1.getMoney() +"SP");

        tagsFPAdapter = new TagsAdapter(tagsFPList, this);
        tagsFPRecycler.setAdapter(tagsFPAdapter);

        post1.setPost_id(String.valueOf(post1.getId()));
        if (post1.getUserId() == LoginActivity.user.getId()) {

            editFP.setVisibility(View.VISIBLE);
            deleteFP.setVisibility(View.VISIBLE);
            repLayoutFP.setVisibility(View.GONE);
            applyFP.setVisibility(View.INVISIBLE);
            savedFP.setVisibility(View.INVISIBLE);
        } else {
            editFP.setVisibility(View.INVISIBLE);
            deleteFP.setVisibility(View.INVISIBLE);
            reportFPBtn.setVisibility(View.VISIBLE);
            reportFPTxt.setVisibility(View.VISIBLE);
            savedFP.setVisibility(View.VISIBLE);
            isAccepted = new PostAccept();
            isAccepted.setPost_id(post1.getId());
            appliedBeforePresenter.sendToServer(isAccepted);

        }

        applyFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                applyPresenter.sendToServer(post1);
            }
        });

        if (post1.getUserId() == LoginActivity.user.getId()) {
            applicantsFP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(FreelancePostActivity.this, ApplicantsListActivity.class);
                    intent.putExtra("type", "freelance");
                    startActivity(intent);
                }
            });
        }

        reportFPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreelancePostActivity.this, ReportPostActivity.class);
                intent.putExtra("report","freelance");
                startActivity(intent);

            }
        });

        savedFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavedPost savedPost = new SavedPost();
                savedPost.setPost_id(post1.getId());
                Log.e("TAG", "getId : " + post1.getId());
                Log.e("TAG", "getPost_id : " + post1.getPost_id());
                savePostPresenter.sendToServer(savedPost);

            }
        });
        editFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreelancePostActivity.this, editFreelancePostActivity.class);
                startActivity(intent);
            }
        });

        deleteFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post deletPost=new Post();
                deletPost=post1;
                deletePostPresenter.sendToServer(deletPost);
            }
        });
    }

    void initView() {
        tagsFPRecycler = findViewById(R.id.tagsFPRecycler);
        freelancePostTitle = findViewById(R.id.freelancePostTitle);
        accountImg = findViewById(R.id.freelancePostImageView);
        timeFP = findViewById(R.id.timeFP);
        userNameFP = findViewById(R.id.userNameFP);
        categoryFP = findViewById(R.id.categoryFP);
        applicantsFP = findViewById(R.id.applicantsFP);
        desTextFP = findViewById(R.id.desTextFP);
        salTextFP = findViewById(R.id.salTextFP);
        reportFPBtn = findViewById(R.id.reportFPBtn);
        editFP = findViewById(R.id.editFP);
        applyFP = findViewById(R.id.applyFP);
        sendFP = findViewById(R.id.sendFP);
        deleteFP = findViewById(R.id.deleteFP);
        reportFPTxt = findViewById(R.id.reportFPTxt);
        repLayoutFP = findViewById(R.id.repLayoutFP);
        savedFP = findViewById(R.id.savedFP);
        appliedFP = findViewById(R.id.appliedFP);


    }

    @Override
    public void applyDone(String message) {
        Log.e("TAG", "apply: Doneeeee!!!");

        if(message.equals("done")){
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(FreelancePostActivity.this, SweetAlertDialog.SUCCESS_TYPE);
            Log.e("TAG", "apply: Doneeeee!!!");

            sweetAlertDialog
                    .setTitleText("DONE")
                    .setContentText("Your request has been successfully sent")
                    .show();
            sweetAlertDialog.setConfirmButton("OK", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent intent = new Intent(FreelancePostActivity.this, HomePageActivity.class);
                    sweetAlertDialog.dismiss();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    FreelancePostActivity.this.finish();
                }
            });
        }
        else {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(FreelancePostActivity.this, SweetAlertDialog.WARNING_TYPE);
            sweetAlertDialog
                    .setTitleText("OOPs")
                    .setContentText(message)
                    .show();
            sweetAlertDialog.setConfirmButton("OK", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismiss();
                }
            });
        }


    }

    @Override
    public void applyFailure() {

    }

    @Override
    public void saveDone() {
        Log.e("TAG", "save: Doneeeee!!!");
        Toast.makeText(FreelancePostActivity.this, "Post saved", Toast.LENGTH_SHORT).show();
        savedFP.setImageResource(R.drawable.ic_bookmarkfill);
    }

    @Override
    public void saveFailure() {

    }

    @Override
    public void deletePostDone() {
        Log.e("TAG", "save: Doneeeee!!!");
        Toast.makeText(FreelancePostActivity.this, "Post deleted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(FreelancePostActivity.this, HomePageActivity.class);
        startActivity(intent);
    }
    @Override
    public void deletePostFailure() {

    }



    @Override
    public void appliledBeforeDone(boolean b) {
        if (b==true) {
            applyFP.setVisibility(View.INVISIBLE);
            appliedFP.setVisibility(View.VISIBLE);

        }else {
            applyFP.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void appliledBeforeFailure() {

    }
}


