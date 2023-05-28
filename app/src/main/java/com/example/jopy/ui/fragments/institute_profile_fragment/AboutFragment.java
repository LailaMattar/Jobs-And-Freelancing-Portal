package com.example.jopy.ui.fragments.institute_profile_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.jopy.R;
import com.example.jopy.mvp.models.User;
import com.example.jopy.mvp.presenters.GetUserByIdPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.adapters.InstituteProfileFragmentAdapter;
import com.squareup.picasso.Picasso;

public class AboutFragment extends Fragment {
    private TextView companyName,location,mainProfession,companyFiled,about,empNum;
    private InstituteProfileFragmentAdapter adapter;
    private ImageView accountImage;
    private GetUserByIdPresenter getUserByIdPresenter;
    private User user;
    private ConstraintLayout constraintLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_institute_about, container, false);
        initView(view);
        user = LoginActivity.user;
        companyFiled.setText(user.getDetails().getInstituteField());
        about.setText(user.getDetails().getAbout());
        empNum.setText(user.getDetails().getNumberOfEmployees());
        return view;
    }
    void initView(View view){
        companyFiled = view.findViewById(R.id.institute_field);
        about = view.findViewById(R.id.about);
        empNum = view.findViewById(R.id.number_of_employees);
//        accountImage = view.findViewById(R.id.imageView7);
        constraintLayout = view.findViewById(R.id.red_circle);
    }
}
