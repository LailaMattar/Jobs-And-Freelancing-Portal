package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.PostAccept;
import com.example.jopy.network.APIs;
import com.example.jopy.network.Response.AcceptedBeforeResponse;
import com.example.jopy.network.Response.PostResponse;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.util.UtilFunctions;

import retrofit2.Call;
import retrofit2.Response;

public class AcceptefBeforePresenter implements ServerPresenter<PostAccept, AcceptedBeforeResponse> {
    Activity activity;
    AcceptefBeforePresenter.Listener listener;

    public AcceptefBeforePresenter(Activity activity, AcceptefBeforePresenter.Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }




    @Override
    public void sendToServer(PostAccept request) {
        try{
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
            UtilFunctions.showProgressBar(activity);
            String token = "Bearer "+ LoginActivity.user.getToken();
            Log.e("tokeeen :  ", token);

            Call<AcceptedBeforeResponse> call    = service.AcceptedUser(token,request);//api function
            call.enqueue(this);
        }catch (Exception e){
            Log.e("TAG", "sendToServer: exception : "+e.getMessage());
            e.printStackTrace();
        }}

    @Override
    public void onResponse(Call<AcceptedBeforeResponse> call, Response<AcceptedBeforeResponse> response) {
        UtilFunctions.hideProgressBar();
        Toast.makeText(activity,"acceptedd before",Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onResponse: response code : "+response.code() );
        Log.e("TAG", "onResponse: bbb : "+response.body().isAccepted() );
        switch (response.code()){
            case 200 :
                listener.acceptedUserDone(response.body().isAccepted());
                Log.e("TAG", "onResponse: done response : "+response.code());
//                Log.e("TAG", "onResponse: body : "+response.body().getPost().toString());
                //UtilFunctions.hideProgressBar();
                //listener.CategoryDone(response.body().getData());
                break;
            case 422:
                listener.acceptedUserFailure();
                // Log.e("TAG", "onResponse: body : "+response.body().getMessage());

                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 500:
                listener.acceptedUserFailure();
                //listener.categoryfailure();
//                showToast(activity , R.string.error_500);
                break;
            case 400:
                listener.acceptedUserFailure();
                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 401:
                listener.acceptedUserFailure();
                break;
            case 404:
                listener.acceptedUserFailure();
                break;
        }}



    @Override
    public void onFailure(Call<AcceptedBeforeResponse> call, Throwable t) {

        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test ");
        Log.e("TAG", "onFailure: 22 "+t.getMessage());
        t.printStackTrace();
        listener.acceptedUserFailure();

    }




    public interface Listener{
        void acceptedUserDone(boolean b);
        void acceptedUserFailure();
    }
}

