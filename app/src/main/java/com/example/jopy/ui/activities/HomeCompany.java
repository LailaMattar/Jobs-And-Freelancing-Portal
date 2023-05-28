package com.example.jopy.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Applicant;
import com.example.jopy.mvp.models.Notification;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.User;
import com.example.jopy.mvp.presenters.GetAllPostsPresenter;
import com.example.jopy.mvp.presenters.GetNotificationPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.activities.payment.E_WalletActivity;
import com.example.jopy.ui.adapters.InstituteProfileFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class HomeCompany extends AppCompatActivity implements GetAllPostsPresenter.Listener , GetNotificationPresenter.Listener {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private InstituteProfileFragmentAdapter adapter;
    private GetAllPostsPresenter getAllPostsPresenter;
    private User user;
    private ConstraintLayout constraintLayout;
    private ImageView accountImage,wallet,notificationButton,saved;
    public static List<Post> allJobPosts;
    private TextView companyName,location,mainProfession;
    private GetNotificationPresenter getNotificationPresenter;
    public static List<Applicant> applicants;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_home_company);
        user = LoginActivity.user;

        initView();
        getNotificationPresenter = new GetNotificationPresenter(HomeCompany.this,this);
        constraintLayout = findViewById(R.id.red_circle);
        wallet = findViewById(R.id.imageView4);
        saved = findViewById(R.id.global_search);
        notificationButton = findViewById(R.id.notification);
        allJobPosts = new ArrayList<>();
        applicants = new ArrayList<>();
        getAllPostsPresenter = new GetAllPostsPresenter(HomeCompany.this,this);
        getAllPostsPresenter.sendToServer(null);

        getNotificationPresenter.sendToServer(null);
        saved.setVisibility(View.INVISIBLE);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new InstituteProfileFragmentAdapter(fm,getLifecycle());
        viewPager2.setAdapter(adapter);

        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeCompany.this,NotificationActivity.class);
                intent.putExtra("from","company");
                startActivity(intent);
            }
        });

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeCompany.this, E_WalletActivity.class);
                startActivity(intent);
            }
        });

        companyName.setText(user.getDetails().getInstituteName());
        location.setText(user.getCountry()+" , "+user.getCity());
        mainProfession.setText(user.getDetails().getInstituteType());
        if(user.getImage() == null){
            accountImage.setImageResource(R.drawable.blank_profile);
        }
        else {
            Picasso.get().load(user.getImage()).into(accountImage);
        }

        tabLayout.setSelectedTabIndicatorHeight(0);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
    private void initView(){
        tabLayout      = findViewById(R.id.tab_layout);
        viewPager2     = findViewById(R.id.profile_view_pager);
        companyName =   findViewById(R.id.institute_profile);
        location = findViewById(R.id.location_profile);
        mainProfession = findViewById(R.id.main_profession_profile);
        accountImage = findViewById(R.id.imageView7);
    }

    @Override
    public void done(List<Post> posts) {
        for(int i = 0 ; i < posts.size() ; i ++){
            if(posts.get(i).getType_id() == 1 && LoginActivity.user.getId() == posts.get(i).getUserId()){
                allJobPosts.add(posts.get(i));
            }
        }
    }

    @Override
    public void failure() {

    }

    @Override
    public void notificationDone(Notification notification) {
        if(notification.getApplicants1().size() != 0){
            for(int i = 0 ; i < notification.getApplicants1().size() ; i ++){
                notification.getApplicants1().get(i).setNotificationType(1);
                applicants.add(notification.getApplicants1().get(i));
            }
        }

        if(notification.getApplicants2().size() !=0){
            for(int i = 0 ; i < notification.getApplicants2().size() ; i ++){
                notification.getApplicants2().get(i).setNotificationType(2);
                applicants.add(notification.getApplicants2().get(i));
            }
        }
        Log.e("TAG", "notificationDone: size : "+applicants.size());
        if (applicants.size() == 0) {
            Log.e("TAG", "onCreate: test ff");
            constraintLayout.setVisibility(View.INVISIBLE);
        }
        else {
            constraintLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void notificationFailure() {

    }
}