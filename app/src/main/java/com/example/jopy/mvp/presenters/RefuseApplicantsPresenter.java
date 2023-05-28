package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.Applicant2;
import com.example.jopy.network.APIs;
import com.example.jopy.network.Response.PostResponse;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.util.UtilFunctions;

import retrofit2.Call;
import retrofit2.Response;

public class RefuseApplicantsPresenter  implements ServerPresenter<Applicant2, PostResponse<Applicant2>> {
    Activity activity;
    RefuseApplicantsPresenter.Listener listener;

    public RefuseApplicantsPresenter(Activity activity, RefuseApplicantsPresenter.Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }






    @Override
    public void sendToServer (Applicant2 request){
        try {
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
            UtilFunctions.showProgressBar(activity);
            String token = "Bearer " + LoginActivity.user.getToken();
            Log.e("tokeeen :  ", token);

            Call<PostResponse<Applicant2>> call = service.Refuse(token, request);//api function
            call.enqueue(this);
        } catch (Exception e) {
            Log.e("TAG", "sendToServer: exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse (Call<PostResponse<Applicant2>> call, Response<PostResponse<Applicant2>> response)
    {
        UtilFunctions.hideProgressBar();
        Toast.makeText(activity, "  refuse done", Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onResponse: response code : " + response.code());
        switch (response.code()) {
            case 200:
                listener.RefuseDone();
                Log.e("TAG", "onResponse: refuse donee  : " + response.code());
                Log.e("TAG", "onResponse message: body : " + response.body().getMessage());
                Toast.makeText(activity,response.body().getMessage(),Toast.LENGTH_SHORT).show();

                break;
            case 422:
                listener.RefuseFailure();
                // Log.e("TAG", "onResponse: body : "+response.body().getMessage());

                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 500:
                listener.RefuseFailure();
                //listener.categoryfailure();
//                showToast(activity , R.string.error_500);
                break;
            case 400:
                listener.RefuseFailure();
                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 401:
                listener.RefuseFailure();
                break;
            case 404:
                listener.RefuseFailure();
                break;
        }
    }


    @Override
    public void onFailure(Call<PostResponse<Applicant2>> call, Throwable t) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test ");
        Log.e("TAG", "onFailure: 22 " + t.getMessage());
        t.printStackTrace();
        listener.RefuseFailure();
    }



    public interface Listener {
        public void RefuseDone();
        public void RefuseFailure();
    }
}




