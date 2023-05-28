package com.example.jopy.ui.fragments.user_profile_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.ui.activities.HomePageActivity;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.activities.type_post.TypeServicePostActivity;
import com.example.jopy.ui.adapters.PostServiceAdapter;

import java.util.ArrayList;
import java.util.List;

public class ServicesFragment extends Fragment {
    private RecyclerView postsRecyclerView ;
    private RecyclerView.Adapter postsAdapter ;
    List<Post> posts;
    List<String> tags;
    private Button type_post_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_user_profile_services, container, false);
        posts      = new ArrayList<>();
        tags       = new ArrayList<>();
        posts = HomePageActivity.userService;


        postsRecyclerView = view.findViewById(R.id.services_recyclerview_user_profile);
        postsRecyclerView.setHasFixedSize(true);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        postsAdapter = new PostServiceAdapter(posts,this.getActivity());
        postsRecyclerView.setAdapter(postsAdapter);

        type_post_button=view.findViewById(R.id.type_post_button3);


        type_post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(LoginActivity.user.getStill_have_posts()==0){
                    Toast.makeText(getActivity(),"Sorry, you don't have enough posts .",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Intent intent = new Intent(getActivity(), TypeServicePostActivity.class);
                    startActivity(intent);
                }}
        });

        return  view;

    }
}
