package com.example.jopy.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Applicant;
import com.example.jopy.ui.activities.ApplicantsListActivity;
import com.example.jopy.ui.activities.HomePageActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    List<Applicant> applicants;
    Context context;

    public NotificationAdapter(List<Applicant> applicants, Context context) {
        this.applicants = applicants;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Applicant applicant = applicants.get(position);
        if(applicant.getPost().getUser().getImage() == null){
            holder.accountImage.setImageResource(R.drawable.blank_profile);
        }
        else {
            Picasso.get().load(applicant.getPost().getUser().getImage()).into(holder.accountImage);
        }
        holder.accountName.setText(applicant.getPost().getUser().getName());
        if(applicant.getNotificationType() == 1) {
            holder.go.setVisibility(View.INVISIBLE);
            String title = applicant.getApplication_status();
            if(applicant.getPost().getType_id() == 2){
                title+=" freelance project : "+applicant.getPost().getTitle();
            }
            else if(applicant.getPost().getType_id() == 3){
                title+=" service : "+applicant.getPost().getTitle();
            }
            else {
                title+=" job : "+applicant.getPost().getTitle();
            }
            holder.title.setText(title);
        }
        else {
            String title = "you have request from : ";
            if(applicant.getPost().getType_id() == 2){
                title+=" freelance project : "+applicant.getPost().getTitle();
            }
            else if(applicant.getPost().getType_id() == 3){
                title+=" service : "+applicant.getPost().getTitle();
            }
            else {
                title+=" job : "+applicant.getPost().getTitle();
            }
            holder.title.setText(title);
        }
        holder.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0 ; i < HomePageActivity.allPosts.size() ; i ++){
                    if(applicant.getPost_id() == HomePageActivity.allPosts.get(i).getId()){
                        Intent intent = new Intent(context, ApplicantsListActivity.class);
                        intent.putExtra("id",Integer.toString(applicant.getPost_id()));
                        intent.putExtra("type_notification",applicant.getPost().getType_id());
                        intent.putExtra("from","notification");
                        context.startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return applicants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Button go;
        ImageView accountImage;
        TextView accountName,title;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            accountImage     = itemView.findViewById(R.id.accountImageNotification);
            accountName      = itemView.findViewById(R.id.accountNameNotification);
            title            = itemView.findViewById(R.id.titleNotification);
            go               = itemView.findViewById(R.id.goButton);
            constraintLayout = itemView.findViewById(R.id.constraintNotification);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
