package com.example.jopy.ui.fragments.visite_profile_user;

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
import com.example.jopy.ui.activities.profile.UserProfileActivity;

public class VisitUserCv extends Fragment {
    private TextView workFiled,experience,educationLevel,education,topSkills,languagesText,courses;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_user_profile_cv_visite, container, false);
        initView(view);
//        workFiled.setText(UserProfileActivity.user.getCategories().get(0)+" ");
//        experience.setText(UserProfileActivity.user.getDetails().getWorkExperience()+" ");
//        education.setText(UserProfileActivity.user.getDetails().getEducation()+" ");
//        experience.setText(UserProfileActivity.user.getDetails().getWorkExperience()+" ");
//        topSkills.setText(UserProfileActivity.user.getDetails().getSkills()+" ");
//        String languages=" ";
//        for (int i = 0 ; i < UserProfileActivity.user.getLanguages().size() ; i ++){
//            languages+=UserProfileActivity.user.getLanguages().get(i)+"\n ";
//        }
//        languagesText.setText(languages);
        return view;
    }
    public void initView(View view){
        workFiled      = view.findViewById(R.id.work_field);
        experience     = view.findViewById(R.id.experince);
        educationLevel = view.findViewById(R.id.education_level);
        education      = view.findViewById(R.id.education);
        topSkills      = view.findViewById(R.id.top_skills);
        languagesText  = view.findViewById(R.id.languages);
        courses        = view.findViewById(R.id.courses);
    }
}
