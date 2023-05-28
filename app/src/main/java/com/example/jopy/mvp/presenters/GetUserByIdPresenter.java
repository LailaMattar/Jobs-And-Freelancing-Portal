package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.User;
import com.example.jopy.network.APIs;
import com.example.jopy.network.Response.NotificationResponse;
import com.example.jopy.network.Response.Response;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.util.UtilFunctions;

import retrofit2.Call;

public class GetUserByIdPresenter implements ServerPresenter<User, Response<User>> {
    Activity activity;
    Listener listener;

    public GetUserByIdPresenter(Activity activity, Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public void sendToServer(User request) {
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
            UtilFunctions.showProgressBar(activity);
            Log.e("TAG", "sendToServer: user : "+request.getUserId());
            String token = "Bearer "+ LoginActivity.user.getToken();
            Log.e("tokeeen IS :  ", token);
        try{
            Call<Response<User>> call    = service.getUserById(token,request);//api function
            call.enqueue(this);
        }catch (Exception e){
            Log.e("TAG", "sendToServer: exception : "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onResponse: response code : "+response.code() );
        Log.e("TAG", "onResponse: response message : "+response.message() );
        switch (response.code()){
            case 200 :
                listener.getUserDone(response.body().getUser());
                Toast.makeText(activity,"Get user done",Toast.LENGTH_SHORT).show();
                break;
            case 422:
            case 401:
            case 404:
            case 500:
            case 400:
                listener.getUserFailure();
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<User>> call, Throwable t) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test get Notification");
        Log.e("TAG", "onFailure: "+t.getMessage());
        t.printStackTrace();
        listener.getUserFailure();
    }

    public interface Listener{
        void getUserDone(User user);
        void getUserFailure();
    }
}
