package com.example.jopy.ui.fragments.homepage_fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jopy.R;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.adapters.UserProfileFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;


public class ProfileFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private UserProfileFragmentAdapter adapter;
    private TextView accountName,mainProfession,location,birthYear;
    private ImageView accountImage;
    private ImageView gender;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        accountName.setText(LoginActivity.user.getDetails().getFirstName()+" "+LoginActivity.user.getDetails().getLastName());
        mainProfession.setText(LoginActivity.user.getDetails().getMainProfession()+" ");
        location.setText(LoginActivity.user.getCountry()+" , "+LoginActivity.user.getCity());
        birthYear.setText(LoginActivity.user.getDetails().getBirthYear()+" ");
        if(LoginActivity.user.getImage() != null){
            Picasso.get().load(LoginActivity.user.getImage()).into(accountImage);
        }
        if(!LoginActivity.user.getDetails().getGender().equals("female")){
            gender.setImageResource(R.drawable.ic_mars);
            gender.setColorFilter(getResources().getColor(android.R.color.holo_blue_dark), PorterDuff.Mode.SRC_IN);
        }

        FragmentManager fm = getFragmentManager();
        adapter = new UserProfileFragmentAdapter(fm,getLifecycle());
        viewPager2.setAdapter(adapter);

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
    void initView(View view){
        tabLayout      = view.findViewById(R.id.tab_layout);
        viewPager2     = view.findViewById(R.id.profile_view_pager);
        accountName    = view.findViewById(R.id.username);
        mainProfession = view.findViewById(R.id.main_profession);
        location       = view.findViewById(R.id.location);
        birthYear      = view.findViewById(R.id.birth_year);
        gender         = view.findViewById(R.id.gender);
        accountImage   = view.findViewById(R.id.imageView7);
    }
}
