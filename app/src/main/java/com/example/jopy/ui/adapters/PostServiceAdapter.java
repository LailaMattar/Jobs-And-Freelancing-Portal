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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.PostService;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.ui.activities.post_details.FreelancePostActivity;
import com.example.jopy.ui.activities.post_details.ServicePostActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostServiceAdapter extends RecyclerView.Adapter<PostServiceAdapter.ViewHolder> {
    List<Post> posts;
    Context context;
    RecyclerView.Adapter adapter;

    public PostServiceAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_service, parent, false);
        return new PostServiceAdapter.ViewHolder(view);
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

        Picasso.get().load(post.getImage()).into(holder.postImg);
        holder.tagsRecyclerView.setHasFixedSize(true);
        holder.tagsRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        adapter = new TagsAdapter(post.getTags(),context);
        holder.tagsRecyclerView.setAdapter(adapter);

        holder.title.setText(post.getTitle());
        holder.cost.setText(post.getMoney() +"SP");
        holder.time.setText("2 days ago");///////
        holder.accountName.setText(post.getUser().getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onBindViewHolder: test service : "+position);


                Intent intent = new Intent(context, ServicePostActivity.class);
                intent.putExtra("index",position);
                intent.putExtra("from", "home");

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title ,time ,accountName ,cost;
        ImageView accountImg ,postImg;
        ConstraintLayout constraintLayout;
        RecyclerView tagsRecyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title            = itemView.findViewById(R.id.service_title);
            time             = itemView.findViewById(R.id.timeSRV);
            accountName      = itemView.findViewById(R.id.account_nameSRV);
            cost             = itemView.findViewById(R.id.costSRV);
            accountImg       = itemView.findViewById(R.id.account_imgSRV);
            postImg          = itemView.findViewById(R.id.post_imgSRV);
            tagsRecyclerView = itemView.findViewById(R.id.tags_recyclerviewSRV);
            constraintLayout = itemView.findViewById(R.id.post_service_cardSRV);
        }

        @Override
        public void onClick(View v) {
            Log.e("TAG", "onClick: pressed");
        }
    }
}
