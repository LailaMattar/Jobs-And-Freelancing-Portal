package com.example.jopy.ui.activities.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.User;
import com.example.jopy.mvp.presenters.GetUserByIdPresenter;
import com.example.jopy.ui.activities.HomePageActivity;
import com.example.jopy.ui.adapters.UserProfileFragmentAdapter;
import com.example.jopy.ui.adapters.UserProfileFragmentVisitAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class UserProfileActivity extends AppCompatActivity implements GetUserByIdPresenter.Listener {
//    private TabLayout tabLayout;
//    private ViewPager2 viewPager2;
    private TextView workFiled,experience,educationLevel,education,topSkills,languagesText,courses;
    private UserProfileFragmentAdapter adapter;
    private GetUserByIdPresenter getUserByIdPresenter;
    private TextView email_pr,accountName , mainProfession , location , birthYear;
    private ImageView gender,accountImage;
    private ConstraintLayout constraintLayout;
    public static User user;
    @SerializedName("user_id")
     private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_user_profile);
        initView();

        getUserByIdPresenter = new GetUserByIdPresenter(UserProfileActivity.this,this);
        FragmentManager fm = this.getSupportFragmentManager();
//        adapter = new UserProfileFragmentVisitAdapter(fm,getLifecycle());
//        viewPager2.setAdapter(adapter);
        Bundle extras = getIntent().getExtras();
         id = extras.getInt("id");
        user = new User(id);
        if(HomePageActivity.applicants.size() == 0){
            constraintLayout.setVisibility(View.INVISIBLE);
        }
        getUserByIdPresenter.sendToServer(user);




//        tabLayout.setSelectedTabIndicatorHeight(0);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager2.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                tabLayout.selectTab(tabLayout.getTabAt(position));
//            }
//        });
    }
    void initView(){
//        tabLayout      = findViewById(R.id.tab_layout);
//        viewPager2     = findViewById(R.id.profile_view_pager);
        accountName = findViewById(R.id.username_profile);
        mainProfession = findViewById(R.id.main_profession_profile);
        location = findViewById(R.id.location_profile);
        accountImage = findViewById(R.id.imageView7);
        birthYear = findViewById(R.id.birth_year_profile);
        gender = findViewById(R.id.gender);
        workFiled      = findViewById(R.id.work_field);
        experience     = findViewById(R.id.experince);
        educationLevel = findViewById(R.id.education_level);
        education      = findViewById(R.id.education);
        topSkills      = findViewById(R.id.top_skills);
        languagesText  = findViewById(R.id.languages);
        courses        = findViewById(R.id.courses);
        constraintLayout = findViewById(R.id.red_circle);
        email_pr = findViewById(R.id.email_pr);
    }

    @Override
    public void getUserDone(User user) {
        this.user = user;
        if(user.getImage() == null){
            accountImage.setImageResource(R.drawable.blank_profile);
        }
        else {
            Picasso.get().load(user.getImage()).into(accountImage);
        }
        accountName.setText(user.getDetails().getFirstName()+" "+user.getDetails().getLastName());
        mainProfession.setText(user.getDetails().getMainProfession()+" ");
        location.setText(user.getCountry()+" , "+user.getCity());
        birthYear.setText(user.getDetails().getBirthYear()+" ");
        if(!user.getDetails().getGender().equals("female")){
            gender.setImageResource(R.drawable.ic_mars);
            gender.setColorFilter(getResources().getColor(android.R.color.holo_blue_dark), PorterDuff.Mode.SRC_IN);
        }
        workFiled.setText(user.getCategories().get(0)+" ");
        experience.setText(user.getDetails().getWorkExperience()+" ");
        education.setText(user.getDetails().getEducation()+" ");
        education.setText(user.getDetails().getEmail()+" ");
        experience.setText(user.getDetails().getWorkExperience()+" ");
        topSkills.setText(user.getDetails().getSkills()+" ");
        String languages=" ";
        for (int i = 0 ; i < user.getDetails().getLanguages().size() ; i ++){
            languages+=user.getDetails().getLanguages().get(i)+"\n ";
        }
        languagesText.setText(languages);
    }

    @Override
    public void getUserFailure() {
        Log.e("TAG", "getUserFailure: ");
    }
}