package com.example.jopy.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Applicant;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.SavedPost;
import com.example.jopy.mvp.presenters.GetApplicantsPresenter;
import com.example.jopy.mvp.presenters.GetSavedPostsPresenter;
import com.example.jopy.mvp.presenters.SavePostPresenter;
import com.example.jopy.ui.activities.post_details.FreelancePostActivity;
import com.example.jopy.ui.activities.post_details.ServicePostActivity;
import com.example.jopy.ui.adapters.ApplicantsAdapter;
import com.example.jopy.ui.adapters.SavedPostsAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class SavedPostsListActivity extends AppCompatActivity implements GetSavedPostsPresenter.Listener {
    private Bundle extras;
    private String postType;
    private RecyclerView savedPostsRecycler;
    private RecyclerView.Adapter savedPostsAdapter;
    public static List<Post> all;
    private static Post post2;
    private GetSavedPostsPresenter getSavedPostsPresenter;
    private TextView titleSVV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_saved_posts_list);

        initView();


        getSavedPostsPresenter = new GetSavedPostsPresenter(SavedPostsListActivity.this, this);
        post2 = new Post();
        all = new ArrayList<>();
SavedPost temp=new SavedPost();

        extras = getIntent().getExtras();
//        if (extras != null) {
//            postType = extras.getString("type");
//            Log.e("TAG", "index : " + postType);
//        }
//
//        if (postType.equals("freelance")) {
//            post2 = FreelancePostActivity.post1;
//            titleSVV.setText("Applicants list");
//        }
//        else if(postType.equals("service")) {
//            post2 = ServicePostActivity.post1;
//            titleSVV.setText("Purchasers list");
//        }

        getSavedPostsPresenter.sendToServer(temp);
        savedPostsRecycler.setHasFixedSize(true);
        savedPostsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }


    void initView() {
        savedPostsRecycler = findViewById(R.id.svvRecyc);
        titleSVV = findViewById(R.id.titleSVV);
    }


    @Override
    public void getSavedPostsDone(List<Post> s) {
        all=s;
        savedPostsAdapter = new SavedPostsAdapter(all, this);
        savedPostsRecycler.setAdapter(savedPostsAdapter);
        if(all.size()==0)
        {
            Toast.makeText(SavedPostsListActivity.this,"There are no Saved posts yet ..",Toast.LENGTH_SHORT).show();

        }}


    @Override
    public void getSavedPostsFailure() {

    }
}