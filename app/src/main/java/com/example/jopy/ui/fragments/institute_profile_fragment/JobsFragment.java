package com.example.jopy.ui.fragments.institute_profile_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.PostJop;
import com.example.jopy.ui.activities.HomeCompany;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.activities.type_post.TypeJobPostActivity;
import com.example.jopy.ui.adapters.InstituteProfileFragmentAdapter;
import com.example.jopy.ui.adapters.PostJopAdapter;

import java.util.ArrayList;
import java.util.List;

public class JobsFragment extends Fragment {
    private RecyclerView postsRecyclerView ;
    private RecyclerView.Adapter postsAdapter;
    private Button type;
    private TextView companyName,location,mainProfession,companyFiled,about,empNum;
    private InstituteProfileFragmentAdapter adapter;
    private ImageView accountImage;
    List<Post> posts;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_institute_jobs, container, false);
        type = view.findViewById(R.id.type_post_button);
        posts      = new ArrayList<>();
        posts = HomeCompany.allJobPosts;

        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginActivity.user.getStill_have_posts()==0){   Intent intent = new Intent(getActivity(), TypeJobPostActivity.class);
                    startActivity(intent);
                }

                    else{
                    Intent intent = new Intent(getActivity(), TypeJobPostActivity.class);
                startActivity(intent);

            }}
        });
//        posts.add(new PostJop("Software Engineer","5 m ago","Google","Amsterdam, North Holland","Full time",
//                10,300,400,R.drawable.google_logo));
//        posts.add(new PostJop("Softwa
//        re Engineer","5 m ago","Google","Amsterdam, North Holland","Full time",
//                10,300,400,R.drawable.google_logo));
//        posts.add(new PostJop("Software Engineer test test test test test","5 m ago","Google","Amsterdam, North Holland","Full time",
//                10,300,400,R.drawable.google_logo));
//        posts.add(new PostJop("Software Engineer","5 m ago","Google","Amsterdam, North Holland","Full time",
//                10,300,400,R.drawable.google_logo));

        postsRecyclerView = view.findViewById(R.id.jobs_recyclerview_institute_profile);
        postsRecyclerView.setHasFixedSize(true);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        postsAdapter = new PostJopAdapter(posts,this.getActivity());
        postsRecyclerView.setAdapter(postsAdapter);
        return view;
    }
}
