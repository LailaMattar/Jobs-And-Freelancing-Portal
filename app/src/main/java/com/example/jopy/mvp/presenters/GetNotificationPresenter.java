package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.Applicant;
import com.example.jopy.mvp.models.Category;
import com.example.jopy.mvp.models.Notification;
import com.example.jopy.network.APIs;
import com.example.jopy.network.Response.NotificationResponse;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.util.UtilFunctions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class GetNotificationPresenter implements ServerPresenter<Applicant, NotificationResponse> {
    Activity activity;
    Listener listener;

    public GetNotificationPresenter(Activity activity, Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public void sendToServer(Applicant request) {
        try{
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
//            UtilFunctions.showProgressBar(activity);
            String token = "Bearer "+ LoginActivity.user.getToken();
            Log.e("tokeeen IS :  ", token);
            Call<NotificationResponse> call    = service.getNotification(token);//api function
            call.enqueue(this);
        }catch (Exception e){
            Log.e("TAG", "sendToServer: exception : "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
//        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onResponse: response code : "+response.code() );
        Log.e("TAG", "onResponse: response message : "+response.message() );
        switch (response.code()){
            case 200 :
                for(int i = 0 ; i < response.body().getNotification().getApplicants1().size() ; i ++){
                    Log.e("TAG", "onResponse: test notification data 1 : "+response.body().getNotification().getApplicants1().get(i).getPost().getUser().getName());
                }
                for(int i = 0 ; i < response.body().getNotification().getApplicants2().size() ; i ++){
                    Log.e("TAG", "onResponse: test notification data 2 : "+response.body().getNotification().getApplicants2().get(i).getPost().getUser().getName());
                }
                listener.notificationDone(response.body().getNotification());
                Toast.makeText(activity,"Get notification done",Toast.LENGTH_SHORT).show();
                break;
            case 422:
            case 401:
            case 404:
            case 500:
            case 400:
                listener.notificationFailure();
                break;
        }
    }

    @Override
    public void onFailure(Call<NotificationResponse> call, Throwable t) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test get Notification");
        Log.e("TAG", "onFailure: "+t.getMessage());
        t.printStackTrace();
        listener.notificationFailure();
    }

    public interface Listener{
        void notificationDone(Notification notification);
        void notificationFailure();
    }
}
