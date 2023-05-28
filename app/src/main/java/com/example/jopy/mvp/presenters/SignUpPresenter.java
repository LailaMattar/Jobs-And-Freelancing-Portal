package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.User;
import com.example.jopy.network.APIs;
import com.example.jopy.network.Response.RegisterResponse;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.util.UtilFunctions;

import retrofit2.Call;
import retrofit2.Response;

public class SignUpPresenter implements ServerPresenter<User, RegisterResponse<User>> {
    Activity activity;
    Listener listener;

    public SignUpPresenter(Activity activity, Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public void sendToServer(User request) {
        try{
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
//            UtilFunctions.showProgressBar(activity);
            Log.e("TAG", "sendToServer: rquest: "+request.toString());
            Call<RegisterResponse<User>> call = service.signUp(request);
            call.enqueue(this);
        }catch (Exception e){
            Log.e("TAG", "sendToServer: exception : "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<RegisterResponse<User>> call, Response<RegisterResponse<User>> response) {
//        UtilFunctions.hideProgressBar();
        Toast.makeText(activity,"register done",Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onResponse: response code : "+response.code() );
        switch (response.code()){
            case 201 :
                listener.done();
                Log.e("TAG", "onResponse: done response : "+response.code());
                break;
            case 422:
                listener.failure();
                Log.e("TAG", "onResponse: message : "+response.body().getMessage());
                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 500:
                listener.failure();
                //listener.categoryfailure();
//                showToast(activity , R.string.error_500);
                break;
            case 400:
                listener.failure();
                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 401:
                listener.failure();
                break;
            case 404:
                Toast.makeText(activity,"email and password do not match",Toast.LENGTH_SHORT).show();
                listener.failure();
                break;
        }
    }

    @Override
    public void onFailure(Call<RegisterResponse<User>> call, Throwable t) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test signUp");
        Log.e("TAG", "onFailure:"+t.getMessage());
        t.printStackTrace();
        listener.failure();
    }

    public interface Listener {
        public void done();
        public void failure();
    }
}
