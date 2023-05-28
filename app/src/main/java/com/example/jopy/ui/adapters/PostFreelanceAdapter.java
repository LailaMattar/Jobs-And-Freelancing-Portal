package com.example.jopy.ui.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.PostFreelance;
import com.example.jopy.ui.activities.post_details.FreelancePostActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostFreelanceAdapter extends RecyclerView.Adapter<PostFreelanceAdapter.ViewHolder>{
    List<Post> posts;
    Context context;

    public PostFreelanceAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_freelance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Post post = posts.get(position);

        if(post.getUser().getImage() != null){
            Picasso.get().load(post.getUser().getImage()).into(holder.accountImg);
        }
        else {
            holder.accountImg.setImageResource(R.drawable.blank_profile);
        }

        holder.title.setText(post.getTitle());
        holder.time.setText("2 days ago");///////
        holder.applicantsNum.setText(post.getNumber_of_applicants()+" Applicants ");
        holder.accountName.setText(post.getUser().getName());
        holder.cost.setText(post.getMoney()+" S.P");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onBindViewHolder: test freelance : "+position);
                Log.e("TAG", "onClick: test item : "+post.getTitle());
                Intent intent = new Intent(context, FreelancePostActivity.class);
                intent.putExtra("index",position);
                intent.putExtra("from", "home");

                context.startActivity(intent);
            }
        });
        //holder.time.setText(post.getTime());

        //holder.accountImg.setImageResource(post.getAccountImg());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title ,time ,accountName ,applicantsNum ,cost;
        ImageView accountImg;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title            = itemView.findViewById(R.id.title);
            time             = itemView.findViewById(R.id.time);
            accountName      = itemView.findViewById(R.id.account_name);
            applicantsNum    = itemView.findViewById(R.id.applicants_num);
            cost             = itemView.findViewById(R.id.cost);
            accountImg       = itemView.findViewById(R.id.accountImage);
            constraintLayout = itemView.findViewById(R.id.post_freelance_card);
            constraintLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.e("TAG", "onClick: pressed");
        }
    }
}
