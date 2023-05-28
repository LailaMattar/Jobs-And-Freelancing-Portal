package com.example.jopy.ui.adapters;

import android.content.Context;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Comment;
import com.example.jopy.mvp.models.Rating;
import com.example.jopy.mvp.models.Tag;
import com.example.jopy.mvp.models.User;
import com.example.jopy.mvp.presenters.AppliedBeforePresenter;
import com.example.jopy.mvp.presenters.GetUserByIdPresenter;
import com.example.jopy.ui.activities.post_details.ServicePostActivity;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>  {
    List<Rating> comments;
    Context context;

    public CommentsAdapter(List<Rating> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, int position) {
        Rating comment = comments.get(position);
        holder.userComment.setText(comment.getRating_text());
        holder.ratingBarCM.setRating(comment.getRating());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView userCM;
        TextView userComment;
        TextView timeCM;
        RatingBar ratingBarCM;
        ImageView timeImgCM;
        ConstraintLayout commentConst;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userComment= itemView.findViewById(R.id.userComment);
            ratingBarCM= itemView.findViewById(R.id.ratingBarCM);
            commentConst = itemView.findViewById(R.id.commentConst);
            commentConst.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.e("Comments", "onClick: CMNT");
        }
    }
}
