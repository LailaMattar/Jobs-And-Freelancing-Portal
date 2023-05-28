package com.example.jopy.ui.fragments.homepage_fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Category;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.ui.activities.HomePageActivity;
import com.example.jopy.ui.adapters.CategoriesAdapter;
import com.example.jopy.ui.adapters.PostFreelanceAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FreelancesFragment extends Fragment implements CategoriesAdapter.Listener{
    private RecyclerView postsRecyclerView , categoriesRecyclerView;
    private RecyclerView.Adapter postsAdapter, categoriesAdapter;
    public  static List<Post> temp;
    private List<Category> categories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_freelances, container, false);
        categories = new ArrayList<>();
        temp = new ArrayList<>();
        temp = HomePageActivity.freelanceList;

        categories.add(new Category(HomePageActivity.categories.get(0).getTitle(),true));
        for(int i = 1 ; i < HomePageActivity.categories.size() ; i ++){
            categories.add(new Category(HomePageActivity.categories.get(i).getTitle(),false));
        }

        postsRecyclerView = view.findViewById(R.id.freelances_recyclerview);
        postsRecyclerView.setHasFixedSize(true);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        postsAdapter = new PostFreelanceAdapter(temp,this.getActivity());
        postsRecyclerView.setAdapter(postsAdapter);

        categoriesRecyclerView = view.findViewById(R.id.freelances_categories_recyclerview);
        categoriesRecyclerView.setHasFixedSize(true);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL,false));

        categoriesAdapter = new CategoriesAdapter(categories,this.getActivity(),this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);
        return  view;
    }

    @Override
    public void setOnCategoryClickedListener() {
        temp = new ArrayList<>();
        for(int i = 0 ; i < HomePageActivity.categoriesFreelance.size() ; i ++){
            if(categories.get(i).isClicked()){
                temp  = HomePageActivity.categoriesFreelance.get(i).getPosts();
            }
        }

        postsAdapter = new PostFreelanceAdapter(temp,this.getActivity());
        postsRecyclerView.setAdapter(postsAdapter);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();
    }
}
