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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Comment;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.PostAccept;
import com.example.jopy.mvp.models.Rating;
import com.example.jopy.mvp.models.SavedPost;
import com.example.jopy.mvp.models.Tag;
import com.example.jopy.mvp.presenters.AcceptefBeforePresenter;
import com.example.jopy.mvp.presenters.AppliedBeforePresenter;
import com.example.jopy.mvp.presenters.ApplyPresenter;
import com.example.jopy.mvp.presenters.DeletePostPresenter;
import com.example.jopy.mvp.presenters.RatingPresenter;
import com.example.jopy.mvp.presenters.SavePostPresenter;
import com.example.jopy.ui.activities.ApplicantsListActivity;
import com.example.jopy.ui.activities.HomePageActivity;
import com.example.jopy.ui.activities.edit_posts.EditServicePostActivity;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.activities.report.ReportPostActivity;
import com.example.jopy.ui.activities.type_post.TypeFreelancePostActivity;
import com.example.jopy.ui.adapters.CommentsAdapter;
import com.example.jopy.ui.adapters.SavedPostsAdapter;
import com.example.jopy.ui.adapters.TagsAdapter;
import com.example.jopy.ui.fragments.homepage_fragments.FreelancesFragment;
import com.example.jopy.ui.fragments.homepage_fragments.ServicesFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class ServicePostActivity extends AppCompatActivity implements AppliedBeforePresenter.Listener,AcceptefBeforePresenter.Listener,ApplyPresenter.Listener, SavePostPresenter.Listener, RatingPresenter.Listener, DeletePostPresenter.Listener {
    private RecyclerView commentsSPRecycler;
    private List<Rating> commentsSPList;
    private RecyclerView.Adapter commentsSPAdapter;
    String from;
    private  PostAccept isAccepted;
    private RecyclerView tagsSPRecycler;
    private List<String> tagsSPList;
    private RecyclerView.Adapter tagsSPAdapter;
    private ConstraintLayout urRateLayoutSP;
    private ConstraintLayout repLayoutSP;
    private Bundle extras;
    private int ind;
    public static Post post1;
    private RatingBar ratingBarSP, urRatingBarSP;
    private EditText writeCMSP;
    private TextView servicePostTitle, userNameSP, timeSP, categorySP, delvTextSP, applicantsSP,
            appliedSP,desTextSP, salTextSP, commentSP, reportSPTxt;
    private Button editSP, applySP, sendSP, deleteSP, reportSPBtn, postSP;
    private ImageView servicePostImageView, savedSP, imgSP;


    private ApplyPresenter applyPresenter;
    private SavePostPresenter savePostPresenter;
    private RatingPresenter ratingPresenter;
    private DeletePostPresenter deletePostPresenter;
    private AcceptefBeforePresenter acceptefBeforePresenter;
    private AppliedBeforePresenter appliedBeforePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_service_post);


        initView();
        post1 = new Post();
        applyPresenter = new ApplyPresenter(ServicePostActivity.this, this);
        savePostPresenter = new SavePostPresenter(ServicePostActivity.this, this);
        ratingPresenter = new RatingPresenter(ServicePostActivity.this, this);
        deletePostPresenter = new DeletePostPresenter(ServicePostActivity.this, this);
        acceptefBeforePresenter = new AcceptefBeforePresenter(ServicePostActivity.this, this);
        appliedBeforePresenter = new AppliedBeforePresenter(ServicePostActivity.this, this);


        extras = getIntent().getExtras();


        if (extras != null) {
            from = extras.getString("from");
        }
        if (from.equals("saved")) {
            post1 = SavedPostsAdapter.saved;
        } else {
            ind = extras.getInt("index");
            post1 = ServicesFragment.temp.get(ind);

            Log.e("TAG", "index : " + ind);
        }

        commentsSPList = new ArrayList<>();
        commentsSPRecycler.setHasFixedSize(true);
        commentsSPRecycler.setLayoutManager(new LinearLayoutManager(this));

        tagsSPList = new ArrayList<>();
        tagsSPRecycler.setHasFixedSize(true);
        tagsSPRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


///////////////////////////////////////////
/////////////////////////////////



////////////////////////////////////////
        tagsSPList = post1.getTags();
        commentsSPList = post1.getRatings();
        if (commentsSPList.size() > 3) {
            //commentsSPRecycler.setVisibility();
            ViewGroup.LayoutParams params = commentsSPRecycler.getLayoutParams();
            params.height = 500;
            commentsSPRecycler.setLayoutParams(params);

        }
        servicePostTitle.setText(post1.getTitle());
        if (post1.getUser().getImage() == null)
            servicePostImageView.setImageResource(R.drawable.blank_profile);
        else
            Picasso.get().load(post1.getUser().getImage()).into(servicePostImageView);

        Picasso.get().load(post1.getImage()).into(imgSP);
        //  servicePostImageView=.setText(FreelancesFragment.temp.get(ind).);
        timeSP.setText("2 days ago");
        userNameSP.setText(post1.getUser().getName());
        categorySP.setText(post1.getCategories().get(0));///
        applicantsSP.setText(String.valueOf(post1.getNumber_of_applicants()) + " purchasers");
        desTextSP.setText(post1.getAbout());
        salTextSP.setText(post1.getMoney() + " SP");
        ratingBarSP.setRating(post1.getTotal_rating());//////////////////
        tagsSPAdapter = new TagsAdapter(tagsSPList, this);
        tagsSPRecycler.setAdapter(tagsSPAdapter);
        delvTextSP.setText(post1.getServiceTime());
        commentsSPAdapter = new CommentsAdapter(commentsSPList, this);
        commentsSPRecycler.setAdapter(commentsSPAdapter);
        commentSP.setText("Comment (" + commentsSPList.size() + ") ");
        post1.setPost_id(String.valueOf(post1.getId()));
        if (post1.getUserId() == LoginActivity.user.getId()) {

            editSP.setVisibility(View.VISIBLE);
            deleteSP.setVisibility(View.VISIBLE);
            applySP.setVisibility(View.INVISIBLE);
            savedSP.setVisibility(View.INVISIBLE);
            repLayoutSP.setVisibility(View.GONE);
        } else {
            editSP.setVisibility(View.INVISIBLE);
            deleteSP.setVisibility(View.INVISIBLE);
            savedSP.setVisibility(View.VISIBLE);
            repLayoutSP.setVisibility(View.VISIBLE);
            isAccepted = new PostAccept();
            isAccepted.setPost_id(post1.getId());
            appliedBeforePresenter.sendToServer(isAccepted);

        }
        applySP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                applyPresenter.sendToServer(post1);
            }
        });

        if (post1.getUserId() == LoginActivity.user.getId()) {
            applicantsSP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(ServicePostActivity.this, ApplicantsListActivity.class);
                    intent.putExtra("type", "service");
                    startActivity(intent);
                }
            });
        }

        reportSPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ServicePostActivity.this, ReportPostActivity.class);
                intent.putExtra("report", "service");
                startActivity(intent);

            }
        });

        savedSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavedPost savedPost = new SavedPost();
                savedPost.setPost_id(post1.getId());
                Log.e("TAG", "getId : " + post1.getId());
                Log.e("TAG", "getPost_id : " + post1.getPost_id());
                savePostPresenter.sendToServer(savedPost);

            }
        });


        editSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServicePostActivity.this, EditServicePostActivity.class);
                startActivity(intent);
            }
        });
        deleteSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post deletPost = new Post();
                deletPost = post1;
                deletePostPresenter.sendToServer(deletPost);

            }
        });
        postSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rating rate1 = new Rating();
                float stars = urRatingBarSP.getRating();
                String comment = writeCMSP.getText().toString();
                if (comment.equals("") || stars == 0) {
                    Toast.makeText(ServicePostActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

                } else {
                    rate1.setRating((int) stars);
                    rate1.setRating_text(comment);
                    rate1.setPost_id(post1.getId());
                    ratingPresenter.sendToServer(rate1);
                }
            }
        });


    }

    void initView() {
        imgSP = findViewById(R.id.imgSP);
        tagsSPRecycler = findViewById(R.id.tagsSPRecycler);
        servicePostTitle = findViewById(R.id.servicePostTitle);
        servicePostImageView = findViewById(R.id.servicePostImageView);
        timeSP = findViewById(R.id.timeSP);
        userNameSP = findViewById(R.id.userNameSP);
        categorySP = findViewById(R.id.categorySP);
        applicantsSP = findViewById(R.id.applicantsSP);
        desTextSP = findViewById(R.id.desTextSP);
        salTextSP = findViewById(R.id.prcTextSP);
        reportSPBtn = findViewById(R.id.reportSPBtn);
        editSP = findViewById(R.id.editSP);
        applySP = findViewById(R.id.buySP);
        sendSP = findViewById(R.id.sendSP);
        deleteSP = findViewById(R.id.deletesP);
        reportSPTxt = findViewById(R.id.reportSPTxt);
        savedSP = findViewById(R.id.savedSP);
        commentsSPRecycler = findViewById(R.id.cmntSPRecycler);
        ratingBarSP = findViewById(R.id.ratingBarSP);
        postSP = findViewById(R.id.postSP);
        tagsSPRecycler = findViewById(R.id.tagsSPRecycler);
        delvTextSP = findViewById(R.id.delvTextSP);
        commentSP = findViewById(R.id.commentSP);
        writeCMSP = findViewById(R.id.writeCMSP);
        urRatingBarSP = findViewById(R.id.urRatingBarSP);
        urRateLayoutSP = findViewById(R.id.urRateLayoutSP);
        repLayoutSP = findViewById(R.id.repLayoutSP);
        appliedSP = findViewById(R.id.appliedSP);


    }

    @Override
    public void applyDone(String message) {

        Log.e("TAG", "apply: Doneeeee!!!");

        if (message.equals("done")) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ServicePostActivity.this, SweetAlertDialog.SUCCESS_TYPE);
            Log.e("TAG", "apply: Doneeeee!!!");

            sweetAlertDialog
                    .setTitleText("DONE")
                    .setContentText("Your request has been successfully sent")
                    .show();
            sweetAlertDialog.setConfirmButton("OK", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent intent = new Intent(ServicePostActivity.this, HomePageActivity.class);
                    sweetAlertDialog.dismiss();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    ServicePostActivity.this.finish();
                }
            });

        } else {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ServicePostActivity.this, SweetAlertDialog.WARNING_TYPE);
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
        Toast.makeText(ServicePostActivity.this, "Post saved", Toast.LENGTH_SHORT).show();
        savedSP.setImageResource(R.drawable.ic_bookmarkfill);
    }


    @Override
    public void saveFailure() {

    }

    @Override
    public void rateDone(String message) {
        Log.e("TAG", "rate: Doneeeee!!!");
        if (message.equals("done")) {
            Toast.makeText(ServicePostActivity.this, "Post rated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ServicePostActivity.this, HomePageActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(ServicePostActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void rateFailure() {

    }

    @Override
    public void deletePostDone() {
        Log.e("TAG", "save: Doneeeee!!!");
        Toast.makeText(ServicePostActivity.this, "Post deleted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ServicePostActivity.this, HomePageActivity.class);
        startActivity(intent);

    }

    @Override
    public void deletePostFailure() {

    }

    @Override
    public void acceptedUserDone(boolean b) {
        Log.e("TAG", "b : " + b);

        if (b == true) {

            urRateLayoutSP.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void acceptedUserFailure() {

    }

    @Override
    public void appliledBeforeDone(boolean b) {
        Log.e("TAG", "b : " + b);

        if (b==true) {
            applySP.setVisibility(View.INVISIBLE);
            appliedSP.setVisibility(View.VISIBLE);

        }
            else {
                applySP.setVisibility(View.VISIBLE);

            }


        acceptefBeforePresenter.sendToServer(isAccepted);

    }

    @Override
    public void appliledBeforeFailure() {

    }
}
