package com.example.jopy.ui.activities.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
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
import com.example.jopy.ui.adapters.InstituteProfileFragmentAdapter;
import com.example.jopy.ui.adapters.UserProfileFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class InstituteProfileActivity extends AppCompatActivity implements GetUserByIdPresenter.Listener {
//    private TabLayout tabLayout;
//    private ViewPager2 viewPager2;
    private TextView companyName,location,mainProfession,companyFiled,about,empNum;
    private InstituteProfileFragmentAdapter adapter;
    private ImageView accountImage;
    private GetUserByIdPresenter getUserByIdPresenter;
    private User user;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_institute_profile);
        initView();
        Bundle extras = getIntent().getExtras();
        int id =extras.getInt("id");
        Log.e("id", "onCreate: id   ::: "+id);
        user = new User(id);


        getUserByIdPresenter = new GetUserByIdPresenter(InstituteProfileActivity.this,this);
        getUserByIdPresenter.sendToServer(user);

        if(HomePageActivity.applicants.size() == 0){
            constraintLayout.setVisibility(View.INVISIBLE);
        }
        else constraintLayout.setVisibility(View.VISIBLE);

//        FragmentManager fm = getSupportFragmentManager();
//        adapter = new InstituteProfileFragmentAdapter(fm,getLifecycle());
//        viewPager2.setAdapter(adapter);
//
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
//
//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                tabLayout.selectTab(tabLayout.getTabAt(position));
//            }
//        });
    }
    private void initView(){
//        tabLayout      = findViewById(R.id.tab_layout);
//        viewPager2     = findViewById(R.id.profile_view_pager);
        companyName =   findViewById(R.id.institute_profile);
        location = findViewById(R.id.location_profile);
        mainProfession = findViewById(R.id.main_profession_profile);
        companyFiled = findViewById(R.id.institute_field);
        about = findViewById(R.id.about);
        empNum = findViewById(R.id.number_of_employees);
        accountImage = findViewById(R.id.imageView7);
        constraintLayout = findViewById(R.id.red_circle);
    }

    @Override
    public void getUserDone(User user) {
        companyName.setText(user.getDetails().getInstituteName());
        location.setText(user.getCountry()+" , "+user.getCity());
        mainProfession.setText(user.getDetails().getInstituteType());
        companyFiled.setText(user.getDetails().getInstituteField());
        about.setText(user.getDetails().getAbout());
        empNum.setText(user.getDetails().getNumberOfEmployees());
        if(user.getImage() == null){
            accountImage.setImageResource(R.drawable.blank_profile);
        }
        else {
            Picasso.get().load(user.getImage()).into(accountImage);
        }

    }

    @Override
    public void getUserFailure() {

    }
}