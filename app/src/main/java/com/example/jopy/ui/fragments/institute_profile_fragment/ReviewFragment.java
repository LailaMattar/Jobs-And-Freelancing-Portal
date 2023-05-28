package com.example.jopy.ui.fragments.institute_profile_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Review;
import com.example.jopy.ui.adapters.ReviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends Fragment {
    RecyclerView reviewsRecyclerview;
    RecyclerView.Adapter reviewsAdapter;
    List<Review> reviews;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_institute_reviews, container, false);

        reviews = new ArrayList<>();

        reviews.add(new Review(R.drawable.ic_empty_profile ,"Mays Al-Khateeb","5 m ago","test review omg"));
        reviews.add(new Review(R.drawable.ic_empty_profile ,"Mahmoud Mattar","10 m ago","test review omg"));
        reviews.add(new Review(R.drawable.ic_empty_profile ,"Ahmad Mattar","20 m ago","test review omg"));

        reviewsRecyclerview = view.findViewById(R.id.reviews_recyclerview_institute);
        reviewsRecyclerview.setHasFixedSize(true);
        reviewsRecyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        reviewsAdapter = new ReviewAdapter(reviews,this.getActivity());
        reviewsRecyclerview.setAdapter(reviewsAdapter);
        return view;
    }
}
