package com.example.jopy.ui.fragments.user_profile_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jopy.R;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;

public class CvFragment extends Fragment {
    private TextView workFiled,experience,educationLevel,education,topSkills,languages,courses;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_user_profile_cv, container, false);
        initView(view);
        workFiled.setText(LoginActivity.user.getCategories().get(0)+" ");
        experience.setText(LoginActivity.user.getDetails().getWorkExperience()+" ");
        education.setText(LoginActivity.user.getDetails().getEducation()+" ");
        experience.setText(LoginActivity.user.getDetails().getWorkExperience()+" ");
        topSkills.setText(LoginActivity.user.getDetails().getSkills()+" ");

        return view;
    }
    public void initView(View view){
        workFiled      = view.findViewById(R.id.work_field);
        experience     = view.findViewById(R.id.experince);
        educationLevel = view.findViewById(R.id.education_level);
        education      = view.findViewById(R.id.education);
        topSkills      = view.findViewById(R.id.top_skills);
        languages      = view.findViewById(R.id.languages);
        courses        = view.findViewById(R.id.courses);
    }
}
