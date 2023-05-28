package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.User;
import com.example.jopy.network.APIs;
import com.example.jopy.network.Response.Response;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.util.UtilFunctions;

import retrofit2.Call;

public class LoginPresenter implements ServerPresenter<User, Response<User>> {
    Activity activity;
    Listener listener;

    public LoginPresenter(Activity activity, Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public void sendToServer(User user) {
        try{
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
            UtilFunctions.showProgressBar(activity);
            Call<Response<User>> call    = service.login(user);//api function
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
        switch (response.code()){
            case 200 :
                if(response.body().getMessage().equals("done")){
                    LoginActivity.user = new User();
                    LoginActivity.user.setToken(response.body().getToken());
                    Log.e("TAG", "onResponse: body : "+response.body().getUser().toString());
                    listener.loginDone(response.body().getUser(),response.body().getToken());
                }
               else
                {
                    Toast.makeText(activity,"email or password do not match",Toast.LENGTH_SHORT).show();

                }
                break;
            case 422:
            case 500:
            case 400:
            case 401:
                listener.loginFailure();
                break;
            case 404:
                Toast.makeText(activity,"email and password do not match",Toast.LENGTH_SHORT).show();
                listener.loginFailure();
                break;
        }
    }

    @Override
    public void onFailure(Call<Response<User>> call, Throwable t) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test ");
        Log.e("TAG", "onFailure login : 22"+t.getMessage());
        t.printStackTrace();
        listener.loginFailure();
    }

    public interface Listener {
        public void loginDone(User u,String token);
        public void loginFailure();
    }
}
