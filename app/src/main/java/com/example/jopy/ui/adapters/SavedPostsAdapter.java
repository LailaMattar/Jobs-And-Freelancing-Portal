package com.example.jopy.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.Rating;
import com.example.jopy.ui.activities.post_details.FreelancePostActivity;
import com.example.jopy.ui.activities.post_details.JobPostActivity;
import com.example.jopy.ui.activities.post_details.ServicePostActivity;

import java.util.List;

public class SavedPostsAdapter extends RecyclerView.Adapter<SavedPostsAdapter.ViewHolder> {
    List<Post> posts;
    Context context;
  public static Post  saved;

    public SavedPostsAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public SavedPostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saved_post, parent, false);
        return new SavedPostsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        saved = posts.get(position);
        String Typeee;
        if (saved.getType_id() == 1) {
            Typeee = "Job";
        }
        else if (saved.getType_id() == 2) {
            Typeee = "Freelance";
        } else {
            Typeee = "Service";
        }
        holder.titleSV.setText(saved.getTitle() + "   (" + Typeee+")");

    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleSV;

        ConstraintLayout savedLay2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleSV = itemView.findViewById(R.id.titleSV);
            savedLay2 = itemView.findViewById(R.id.savedLay2);
            savedLay2.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            saved = posts.get(position);


            if (saved.getType_id() == 1) {
                Intent intent = new Intent(context, JobPostActivity.class);
                intent.putExtra("savedList", saved.getPost_id());
                intent.putExtra("from", "saved");
                context.startActivity(intent);

            } else if (saved.getType_id() == 2) {
                Intent intent = new Intent(context, FreelancePostActivity.class);
                intent.putExtra("savedList", saved.getPost_id());
                intent.putExtra("from", "saved");

                context.startActivity(intent);

            } else {
                Intent intent = new Intent(context, ServicePostActivity.class);
                intent.putExtra("savedList", saved.getPost_id());
                intent.putExtra("from", "saved");

                context.startActivity(intent);


            }

        }}}



