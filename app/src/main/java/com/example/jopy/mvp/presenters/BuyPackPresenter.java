package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.Package2;
import com.example.jopy.mvp.models.User;
import com.example.jopy.network.APIs;
import com.example.jopy.network.Response.BuyPackResponse;
import com.example.jopy.network.Response.Response;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.util.UtilFunctions;

import retrofit2.Call;

public class BuyPackPresenter implements ServerPresenter<Package2, BuyPackResponse> {
    Activity activity;
    BuyPackPresenter.Listener listener;

    public BuyPackPresenter(Activity activity, BuyPackPresenter.Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public void sendToServer(Package2 pack_id) {
        try{
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
            UtilFunctions.showProgressBar(activity);
            String token = "Bearer "+ LoginActivity.user.getToken();
            Log.e("tokeeen IS :  ", token);

            Call<BuyPackResponse> call    = service.buyPackage(token,pack_id);//api function
            call.enqueue(this);
        }catch (Exception e){
            Log.e("TAG", "sendToServer: exception : "+e.getMessage());
            e.printStackTrace();
        }
    }



    @Override
    public void onResponse(Call<BuyPackResponse> call, retrofit2.Response<BuyPackResponse> response) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onResponse: response code : "+response.code() );
        switch (response.code()){
            case 200 :
                listener.buyDone();
                Toast.makeText(activity,response.body().getMessage(),Toast.LENGTH_SHORT).show();

                Log.e("TAG", "onResponse: done response : "+response.code());
                Log.e("TAG", "onResponse: body : MESSAGE  : "+response.body().toString());
                break;
            case 422:
                listener.buyFailure();
                break;
            case 500:
                listener.buyFailure();
            case 400:
                listener.buyFailure();
                break;
            case 401:
                listener.buyFailure();
                break;
            case 404:
                listener.buyFailure();
                break;
        }
    }

    @Override
    public void onFailure(Call<BuyPackResponse> call, Throwable t) {

        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test ");
        Log.e("TAG", "onFailure: 22 "+t.getMessage());
        t.printStackTrace();
        listener.buyFailure();
    }
    public interface Listener {
        public void buyDone();
        public void buyFailure();
    }
}

