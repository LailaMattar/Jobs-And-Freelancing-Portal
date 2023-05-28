package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.Applicant;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.network.APIs;
import com.example.jopy.network.Response.ApplicantsResponse;
import com.example.jopy.network.Response.PostResponse;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.util.UtilFunctions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetApplicantsPresenter  implements ServerPresenter<Post, ApplicantsResponse> {
    Activity activity;
    GetApplicantsPresenter.Listener listener;

    public GetApplicantsPresenter(Activity activity, GetApplicantsPresenter.Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public void sendToServer(Post post ) {
        try{
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
            UtilFunctions.showProgressBar(activity);
            String token = "Bearer "+ LoginActivity.user.getToken();
            Log.e("tokeeen :  ", token);

            Call<ApplicantsResponse> call   = service.GetApplicants(token,post);//api function
            call.enqueue(this);
        }catch (Exception e){
            Log.e("TAG", "sendToServer: exception : "+e.getMessage());
            e.printStackTrace();
        }
    }



    @Override
    public void onResponse(Call<ApplicantsResponse> call, Response<ApplicantsResponse> response) {
        UtilFunctions.hideProgressBar();
        Toast.makeText(activity,"post done",Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onResponse: response code : "+response.code() );
        switch (response.code()){
            case 200 :
                listener.getApplicantsDone(response.body().getApplicants());
                Log.e("TAG", "onResponse: getApplicants donee response : "+response.code());
                Log.e("TAG", "onResponse: body : "+response.body().getApplicants().toString());

                break;
            case 422:
                listener.getApplicantsFailure();
                // Log.e("TAG", "onResponse: body : "+response.body().getMessage());

                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 500:
                listener.getApplicantsFailure();
                //listener.categoryfailure();
//                showToast(activity , R.string.error_500);
                break;
            case 400:
                listener.getApplicantsFailure();
                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 401:
                listener.getApplicantsFailure();
                break;
            case 404:
                listener.getApplicantsFailure();
                break;
        }
    }

    @Override
    public void onFailure(Call<ApplicantsResponse> call, Throwable t) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test ");
        Log.e("TAG", "onFailure: 22 "+t.getMessage());
        t.printStackTrace();
        listener.getApplicantsFailure();
    }



    public interface Listener {
        public void getApplicantsDone(List<Applicant> A);
        public void getApplicantsFailure();
    }
}


