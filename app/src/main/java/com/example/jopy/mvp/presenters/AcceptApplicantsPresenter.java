package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.Applicant;
import com.example.jopy.mvp.models.Applicant2;
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

public class AcceptApplicantsPresenter  implements ServerPresenter<Applicant2, PostResponse<Applicant2>> {
    Activity activity;
  Listener listener;

    public AcceptApplicantsPresenter(Activity activity,Listener listener) {
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

                Call<PostResponse<Applicant2>> call = service.Accept(token, request);//api function
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
            Toast.makeText(activity, "  accept done", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "onResponse: response code : " + response.code());
            switch (response.code()) {
                case 200:
                    listener.AcceptDone();
                    Log.e("TAG", "onResponse: acceptttt donee  : " + response.code());
                    Log.e("TAG", "onResponse message: body : " + response.body().getMessage());
                    Toast.makeText(activity,response.body().getMessage(),Toast.LENGTH_SHORT).show();

                    break;
                case 422:
                    listener.AcceptFailure();
                    // Log.e("TAG", "onResponse: body : "+response.body().getMessage());

                    //listener.categoryfailure();
//                responseError(response,activity);
                    break;
                case 500:
                    listener.AcceptFailure();
                    //listener.categoryfailure();
//                showToast(activity , R.string.error_500);
                    break;
                case 400:
                    listener.AcceptFailure();
                    //listener.categoryfailure();
//                responseError(response,activity);
                    break;
                case 401:
                    listener.AcceptFailure();
                    break;
                case 404:
                    listener.AcceptFailure();
                    break;
            }
        }


    @Override
    public void onFailure(Call<PostResponse<Applicant2>> call, Throwable t) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test ");
        Log.e("TAG", "onFailure: 22 " + t.getMessage());
        t.printStackTrace();
        listener.AcceptFailure();
    }



    public interface Listener {
        public void AcceptDone();
        public void AcceptFailure();
    }
}




