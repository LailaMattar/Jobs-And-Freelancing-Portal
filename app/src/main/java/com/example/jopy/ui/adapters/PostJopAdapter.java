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
import com.example.jopy.mvp.models.PostJop;
import com.example.jopy.ui.activities.SplashScreenActivity;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.activities.post_details.FreelancePostActivity;
import com.example.jopy.ui.activities.post_details.JobPostActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostJopAdapter extends RecyclerView.Adapter<PostJopAdapter.ViewHolder>{
    static public List<Post> posts;
    Context context;

    public PostJopAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_job, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Post post = posts.get(position);

        holder.title.setText(post.getTitle());
        holder.accountName.setText(post.getUser().getName());
        holder.address.setText(post.getLocation());
        holder.jopType.setText(post.getEmploymentType());
        holder.jopType.setText("Number of applicants : "+post.getNumber_of_applicants());
        holder.jopType.setText("Salary : "+post.getMoney());
        if(post.getUser().getImage() != null){
            Picasso.get().load(post.getUser().getImage()).into(holder.accountImg);
        }
        else {
            holder.accountImg.setImageResource(R.drawable.blank_profile);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onBindViewHolder: test job : "+position);
                Log.e("TAG", "onClick: test item : "+post.getTitle());
                Intent intent = new Intent(context, JobPostActivity.class);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title ,time ,accountName ,address ,jopType ,applicantsNum ,salary;
        ImageView accountImg;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title            = itemView.findViewById(R.id.title);
            time             = itemView.findViewById(R.id.time);
            accountName      = itemView.findViewById(R.id.account_name);
            address          = itemView.findViewById(R.id.address);
            jopType          = itemView.findViewById(R.id.job_type);
            applicantsNum    = itemView.findViewById(R.id.applicants_num);
            salary           = itemView.findViewById(R.id.salary);
            accountImg       = itemView.findViewById(R.id.account_img);
            constraintLayout = itemView.findViewById(R.id.post_job_card);
            constraintLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.e("TAG", "onClick: pressed");



        }


        }
    }
