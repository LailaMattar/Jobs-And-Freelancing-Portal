package com.example.jopy.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jopy.R;
import com.example.jopy.mvp.models.Applicant;
import com.example.jopy.mvp.models.Applicant2;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.presenters.AcceptApplicantsPresenter;
import com.example.jopy.mvp.presenters.ApplyPresenter;
import com.example.jopy.mvp.presenters.RefuseApplicantsPresenter;
import com.example.jopy.ui.activities.post_details.FreelancePostActivity;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ApplicantsAdapter extends RecyclerView.Adapter<ApplicantsAdapter.ViewHolder> implements AcceptApplicantsPresenter.Listener, RefuseApplicantsPresenter.Listener {
    List<Applicant> applicants;
    Context context;
    public static Applicant app;
    Activity activity;
    public static Applicant2 a;
    public static  AcceptApplicantsPresenter acceptApplicantsPresenter;
    public static RefuseApplicantsPresenter refuseApplicantsPresenter;
    public ApplicantsAdapter(List<Applicant> applicants, Context context , Activity activity) {
        this.applicants = applicants;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ApplicantsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_applicant, parent, false);
        return new ApplicantsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicantsAdapter.ViewHolder holder, final int position) {
        app = applicants.get(position);

        holder.account_nameAP1.setText(app.getName());
      //  holder.account_imgAP1//////////




//        Date startDate = convertDate(app.getCreated_at());
//                Date endDate = Calendar.getInstance().getTime();
//
//
//        long duration  = endDate.getTime() - startDate.getTime();
//
//        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
//        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
//        long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
//        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);

        if(app.getImage() != null){
            Picasso.get().load(app.getImage()).into(holder.account_imgAP1);
        }
        else {
            holder.account_imgAP1.setImageResource(R.drawable.blank_profile);
        }
        holder.timeAPP.setText(". 2 h ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onBindViewHolder: test applic click : "+ position);

/////////////go to profile from name
//                Intent intent = new Intent(context, FreelancePostActivity.class);
//                intent.putExtra("index",position);
//                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return applicants.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       ImageView account_imgAP1;
       TextView account_nameAP1,timeAPP;
       Button confAP1,deleteAP1,sendAP1;
       ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            account_imgAP1=itemView.findViewById(R.id.account_imgAP1);
            account_nameAP1=itemView.findViewById(R.id.account_nameAP1);
            timeAPP=itemView.findViewById(R.id.timeAPP);
            confAP1=itemView.findViewById(R.id.confAP1);
            deleteAP1=itemView.findViewById(R.id.deleteAP1);
            sendAP1=itemView.findViewById(R.id.sendAP1);
            constraintLayout = itemView.findViewById(R.id.constraintAP1);



            constraintLayout.setOnClickListener( this);
confAP1.setOnClickListener(this);
            deleteAP1.setOnClickListener(this);
            sendAP1.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            a=new Applicant2();
            a.setApplicant_id(String.valueOf(app.getId()));
            switch (v.getId()) {
                case R.id.confAP1:
                    AcceptApplicant(a);
                    break;
                case R.id.deleteAP1:
                   RefuseApplicant(a);
                    break;
                    case R.id.sendAP1:
                MessageApplicant(a);
                    break;
                default:
                    break;
            }
        }
        }
        void  AcceptApplicant(Applicant2 applicant2 )
        {
            acceptApplicantsPresenter = new AcceptApplicantsPresenter(activity, this);
            Log.e("TAG", "idddd : 2 "+applicant2.getApplicant_id());
            Log.e("TAG", "idddd : 1 "+app.getId());

            acceptApplicantsPresenter.sendToServer(applicant2);
        }
        void  RefuseApplicant(Applicant2 applicant2 )
        {
            refuseApplicantsPresenter = new RefuseApplicantsPresenter(activity, this);
            Log.e("TAG", "idddd : 2 "+applicant2.getApplicant_id());
            Log.e("TAG", "idddd : 1 "+app.getId());

            refuseApplicantsPresenter.sendToServer(applicant2);
        }
        void  MessageApplicant(Applicant2 applicant2 )
        {
//            refuseApplicantsPresenter = new RefuseApplicantsPresenter(activity, this);
//            Log.e("TAG", "idddd : 2 "+applicant2.getApplicant_id());
//            Log.e("TAG", "idddd : 1 "+app.getId());
//
//            refuseApplicantsPresenter.sendToServer(applicant2);
        }
    @Override
    public void AcceptDone() {
        Log.e("TAG", "AcceptDone: Doneeeee!!!");
        Toast.makeText(context,"adapteeeeer AcceptDone",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void AcceptFailure() {

    }
    @Override
    public void RefuseDone() {
        Log.e("TAG", "RefuseDone: Doneeeee!!!");
        Toast.makeText(context,"adapteeeeer RefuseDone",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void RefuseFailure() {

    }
    Date convertDate(String time){
        String str=time;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(Long.parseLong(str));
        System.out.println(sf.format(date));
        return date;

    }
    }

