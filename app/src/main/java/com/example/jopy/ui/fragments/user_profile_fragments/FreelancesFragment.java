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
import com.example.jopy.mvp.models.PostFreelance;
import com.example.jopy.ui.activities.HomePageActivity;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.activities.type_post.TypeFreelancePostActivity;
import com.example.jopy.ui.adapters.PostFreelanceAdapter;

import java.util.ArrayList;
import java.util.List;

public class FreelancesFragment extends Fragment {
    private RecyclerView postsRecyclerView ;
    private RecyclerView.Adapter postsAdapter ;
    List<Post> posts;
    private Button type_post_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_user_profile_freelances, container, false);
        posts      = new ArrayList<>();
        posts = HomePageActivity.userFreelance;

        postsRecyclerView = view.findViewById(R.id.freelances_recyclerview_user_profile);
        postsRecyclerView.setHasFixedSize(true);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        postsAdapter = new PostFreelanceAdapter(posts,this.getActivity());
        postsRecyclerView.setAdapter(postsAdapter);

        type_post_button=view.findViewById(R.id.type_post_button);


        type_post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(LoginActivity.user.getStill_have_posts()==0){
                    Toast.makeText(getActivity(),"Sorry, you don't have enough posts .",Toast.LENGTH_SHORT).show();

                }
                else
                {
                Intent intent = new Intent(getActivity(), TypeFreelancePostActivity.class);
                startActivity(intent);
          }
        }
        });
        return view;
    }
}
