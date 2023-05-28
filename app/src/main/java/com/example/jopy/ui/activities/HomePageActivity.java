package com.example.jopy.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Applicant;
import com.example.jopy.mvp.models.Category;
import com.example.jopy.mvp.models.Notification;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.presenters.GetAllPostsPresenter;
import com.example.jopy.mvp.presenters.GetCategoriesPresenter;
import com.example.jopy.mvp.presenters.GetNotificationPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.CreateAccountCommonActivity;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.activities.login_and_createAccount.activity_create_account_institute_1;
import com.example.jopy.ui.activities.payment.E_WalletActivity;
import com.example.jopy.ui.activities.profile.InstituteProfileActivity;
import com.example.jopy.ui.activities.type_post.TypeFreelancePostActivity;
import com.example.jopy.ui.adapters.PostJopAdapter;
import com.example.jopy.ui.fragments.homepage_fragments.AllJobsFragment;
import com.example.jopy.ui.fragments.homepage_fragments.FreelancesFragment;
import com.example.jopy.ui.fragments.homepage_fragments.MyJobsFragment;
import com.example.jopy.ui.fragments.homepage_fragments.ProfileFragment;
import com.example.jopy.ui.fragments.homepage_fragments.ServicesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class HomePageActivity extends AppCompatActivity implements GetAllPostsPresenter.Listener, GetCategoriesPresenter.Listener , GetNotificationPresenter.Listener {
    private ImageView searchImageView,notification,wallet;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView.Adapter adapter;
    private ConstraintLayout constraintLayout;
    private RecyclerView recyclerView;
    private GetAllPostsPresenter getAllPostsPresenter;
    private GetCategoriesPresenter getCategoriesPresenter;
    private GetNotificationPresenter getNotificationPresenter;
    public static List<Category> categoriesService,categoriesFreelance,categoriesAllJobs,categories;
    public static List<Post> myJobsList ,allJobsList ,freelanceList ,servicesList,allPosts,userFreelance,userService;
    public static Map<Category,Post> catPost, catFreelance,catService;
    public static List<Applicant> applicants;
private ImageView imageView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_home_page);
        imageView4=findViewById(R.id.imageView4);
        initVar();
        Category category        = new Category("*","category");
        applicants               = new ArrayList<>();
        getCategoriesPresenter   = new GetCategoriesPresenter(HomePageActivity.this,this);
        getAllPostsPresenter     = new GetAllPostsPresenter(HomePageActivity.this,this);
        getNotificationPresenter = new GetNotificationPresenter(HomePageActivity.this,this);
        getCategoriesPresenter.sendToServer(category);

        catPost             = new HashMap<>();
        catFreelance        = new HashMap<>();
        catService          = new HashMap<>();
        allPosts            = new ArrayList<>();
        myJobsList          = new ArrayList<>();
        allJobsList         = new ArrayList<>();
        freelanceList       = new ArrayList<>();
        servicesList        = new ArrayList<>();
        categories          = new ArrayList<>();
        categoriesService   = new ArrayList<>();
        categoriesFreelance = new ArrayList<>();
        categoriesAllJobs   = new ArrayList<>();
        userService = new ArrayList<>();
        userFreelance = new ArrayList<>();

        categories.add(new Category("All",true));
        categoriesService.add(new Category("All",true));
        categoriesFreelance.add(new Category("All",true));
        categoriesAllJobs.add(new Category("All",true));


        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        searchImageView.setTag(R.drawable.ic_magnifiying_glass);
        searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, E_WalletActivity.class);
                startActivity(intent);
            }
        });
        searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this,SavedPostsListActivity.class);
                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent employeeIntent = new Intent(HomePageActivity.this, NotificationActivity.class);
                employeeIntent.putExtra("from","user");
                startActivity(employeeIntent);
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this,E_WalletActivity.class);
                startActivity(intent);
            }
        });
        constraintLayout.setVisibility(View.INVISIBLE);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_my_jobs :
                            selectedFragment = new MyJobsFragment();
                            searchImageView.setImageResource(R.drawable.ic_magnifiying_glass);
                            searchImageView.setTag(R.drawable.ic_magnifiying_glass);
                            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                            break;
                        case R.id.nav_all_jobs :
                            selectedFragment = new AllJobsFragment();
                            searchImageView.setImageResource(R.drawable.ic_bookmarkfill);
                            searchImageView.setTag(R.drawable.ic_bookmarkfill);
                            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                            break;
                        case R.id.nav_freelance :
                            selectedFragment = new FreelancesFragment();
                            searchImageView.setImageResource(R.drawable.ic_bookmarkfill);
                            searchImageView.setTag(R.drawable.ic_bookmarkfill);
                            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                            break;
                        case R.id.nav_services :
                            selectedFragment = new ServicesFragment();
                            searchImageView.setImageResource(R.drawable.ic_bookmarkfill);
                            searchImageView.setTag(R.drawable.ic_bookmarkfill);
                            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                            break;
                        case R.id.nav_profile :
                            selectedFragment = new ProfileFragment();
                            searchImageView.setImageResource(R.drawable.ic_bookmarkfill);
                            searchImageView.setTag(R.drawable.ic_bookmarkfill);
                            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };

    void initVar(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        if(LoginActivity.user == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AllJobsFragment()).commit();
        }
        else
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MyJobsFragment()).commit();

        wallet = findViewById(R.id.imageView4);
        navigationView   = findViewById(R.id.side_menu);
        drawerLayout     = findViewById(R.id.drawer_layout);
        searchImageView  = findViewById(R.id.global_search);
        notification     = findViewById(R.id.notification);
        constraintLayout = findViewById(R.id.red_circle);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);


        if (drawer.isDrawerOpen(GravityCompat.START)) {
            System.out.println(";l;;l;l;l;l;l");
            drawer.closeDrawer(GravityCompat.START); }
        else {
            System.out.println("oioioioioioi");

            super.onBackPressed(); }
    }
    @Override
    public void done(List<Post> posts) {
        if(posts.size() != 0){
            allPosts = posts;
            for(int i = 0 ; i  < posts.size() ; i ++){
                if(posts.get(i).getType_id() == 1){
                    if(posts.get(i).getCategories().get(0).equals(LoginActivity.user.getCategories().get(0))){
                        myJobsList.add(posts.get(i));
                    }
                    allJobsList.add(posts.get(i));
                }
                else if(posts.get(i).getType_id() == 2){
                    freelanceList.add(posts.get(i));
                }
                else {
                    Log.e("TAG", "done:service test : "+posts.get(i).getImage());
                    servicesList.add(posts.get(i));
                }
                if(posts.get(i).getType_id() == 2 && LoginActivity.user.getId() == posts.get(i).getUserId()){
                    userFreelance.add(posts.get(i));
                }
                if(posts.get(i).getType_id() == 3 && LoginActivity.user.getId() == posts.get(i).getUserId()){
                    userService.add(posts.get(i));
                }
            }
            Log.e("TAG", "done: size my job : "+myJobsList.size());
            adapter = new PostJopAdapter(myJobsList,this);
            recyclerView = findViewById(R.id.my_jobs_recyclerview);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);


            for(int i = 0 ; i < categories.size() ; i ++){
                categoriesService.get(i).setPosts(new ArrayList<Post>());
                categoriesFreelance.get(i).setPosts(new ArrayList<Post>());
                categoriesAllJobs.get(i).setPosts(new ArrayList<Post>());
                categories.get(i).setPosts(new ArrayList<Post>());
            }

            if(allJobsList.size() != 0)
            for(int i = 0 ; i < allJobsList.size() ; i ++){
                categoriesAllJobs.get(0).getPosts().add(allJobsList.get(i));
                for(int j = 1 ; j < categories.size() ; j ++){
                    if(allJobsList.get(i).getCategories().get(0).equals(categories.get(j).getTitle())){
                        categoriesAllJobs.get(j).getPosts().add(allJobsList.get(i));
                    }
                }
            }

            for(int i = 0 ; i < freelanceList.size() ; i ++){
                categoriesFreelance.get(0).getPosts().add(freelanceList.get(i));
                for(int j = 1 ; j < categories.size() ; j ++){
                    if(freelanceList.get(i).getCategories().get(0).equals(categories.get(j).getTitle())){
                        categoriesFreelance.get(j).getPosts().add(freelanceList.get(i));
                    }
                }
            }

            for(int i = 0 ; i < servicesList.size() ; i ++){
                categoriesService.get(0).getPosts().add(servicesList.get(i));
                for(int j = 1 ; j < categories.size() ; j ++){
                    if(servicesList.get(i).getCategories().get(0).equals(categories.get(j).getTitle())){
                        categoriesService.get(j).getPosts().add(servicesList.get(i));
                    }
                }
            }
        }
        if(LoginActivity.user != null){
            getNotificationPresenter.sendToServer(null);
        }
    }

    @Override
    public void failure() {

    }

    @Override
    public void categoriesDone(List<String> categories) {
        for(int i = 0 ; i < categories.size() ; i ++){
            this.categories.add(new Category(categories.get(i),false));
            categoriesService.add(new Category(categories.get(i),false));
            categoriesFreelance.add(new Category(categories.get(i),false));
            categoriesAllJobs.add(new Category(categories.get(i),false));
        }
        getAllPostsPresenter.sendToServer(null);
    }

    @Override
    public void categoriesFailure() {

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