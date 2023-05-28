package com.example.jopy.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    List<Review> reviews;
    Context context;

    public ReviewAdapter(List<Review> reviews, Context context) {
        this.reviews = reviews;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ReviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.accountName.setText(review.getAccountName());
        holder.time.setText(review.getTime());
        holder.review.setText(review.getReview());
        holder.accountImg.setImageResource(review.getAccountImg());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView accountName ,time ,review;
        ImageView accountImg ,more;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            accountName      = itemView.findViewById(R.id.account_name_review);
            accountImg       = itemView.findViewById(R.id.account_img);
            time             = itemView.findViewById(R.id.time);
            review           = itemView.findViewById(R.id.review);
            more             = itemView.findViewById(R.id.more);
            constraintLayout = itemView.findViewById(R.id.review_constraint);
            constraintLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.e("TAG", "onClick: pressed category");
        }
    }
}
