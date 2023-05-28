package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.Report;
import com.example.jopy.network.APIs;
import com.example.jopy.network.Response.PostResponse;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.util.UtilFunctions;

import retrofit2.Call;
import retrofit2.Response;

public class ReportPresenter implements ServerPresenter<Report, PostResponse<Report>> {
    Activity activity;
    ReportPresenter.Listener listener;

    public ReportPresenter(Activity activity, ReportPresenter.Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }


    @Override
    public void sendToServer(Report request) {
        try{
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
            UtilFunctions.showProgressBar(activity);
            String token = "Bearer "+ LoginActivity.user.getToken();
            Log.e("tokeeen :  ", token);

            Call<PostResponse<Report>> call    = service.Report(token,request);//api function
            call.enqueue(this);
        }catch (Exception e){
            Log.e("TAG", "sendToServer: exception : "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<PostResponse<Report>> call, Response<PostResponse<Report>> response) {
        UtilFunctions.hideProgressBar();
        Toast.makeText(activity,"post done",Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onResponse: response code : "+response.code() );
        switch (response.code()){
            case 200 :
                listener.reportDone();
                Log.e("TAG", "onResponse: done response : "+response.code());
                Log.e("TAG", "onResponse: body : "+response.body().getMessage().toString());
                //UtilFunctions.hideProgressBar();
                //listener.CategoryDone(response.body().getData());
                break;
            case 422:
                listener.reportFailure();
                // Log.e("TAG", "onResponse: body : "+response.body().getMessage());

                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 500:
                listener.reportFailure();
                //listener.categoryfailure();
//                showToast(activity , R.string.error_500);
                break;
            case 400:
                listener.reportFailure();
                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 401:
                listener.reportFailure();
                break;
            case 404:
                listener.reportFailure();
                break;
        }
    }
    @Override
    public void onFailure(Call<PostResponse<Report>> call, Throwable t) {

        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test ");
        Log.e("TAG", "onFailure: 22 "+t.getMessage());
        t.printStackTrace();
        listener.reportFailure();
    }

    public interface Listener {
        public void reportDone();
        public void reportFailure();
    }
}


