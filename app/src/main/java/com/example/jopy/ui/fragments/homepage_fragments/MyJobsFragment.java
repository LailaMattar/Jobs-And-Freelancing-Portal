package com.example.jopy.ui.fragments.homepage_fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.PostJop;
import com.example.jopy.ui.activities.HomePageActivity;
import com.example.jopy.ui.adapters.PostJopAdapter;

import java.util.ArrayList;
import java.util.List;


public class MyJobsFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    List<Post> posts;
    public static List<Post> temp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_jobs, container, false);
        posts = new ArrayList<>();
        temp = new ArrayList<>();
        temp = HomePageActivity.myJobsList;
//        posts.add(new PostJop("Software Engineer","5 m ago","Google","Amsterdam, North Holland","Full time",
//                10,300,400,R.drawable.google_logo));
//        posts.add(new PostJop("Software Engineer","5 m ago","Google","Amsterdam, North Holland","Full time",
//                10,300,400,R.drawable.google_logo));
//        posts.add(new PostJop("Software Engineer test test test test test","5 m ago","Google","Amsterdam, North Holland","Full time",
//                10,300,400,R.drawable.google_logo));
//        posts.add(new PostJop("Software Engineer","5 m ago","Google","Amsterdam, North Holland","Full time",
//                10,300,400,R.drawable.google_logo));

        recyclerView = view.findViewById(R.id.my_jobs_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        adapter = new PostJopAdapter(temp,this.getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

}
