package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.Package1;
import com.example.jopy.mvp.models.User;
import com.example.jopy.network.APIs;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.activities.payment.E_WalletActivity;
import com.example.jopy.util.UtilFunctions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AllPackagePresenter implements ServerPresenter<Package1, List<Package1>> {
    Activity activity;
    AllPackagePresenter.Listener listener;

    public AllPackagePresenter(Activity activity, AllPackagePresenter.Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }


    @Override
    public void sendToServer(Package1 request) {
        try{
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
            UtilFunctions.showProgressBar(activity);


            Call<List<Package1>> call = service.getPackage();//api function
            call.enqueue(this);
        }catch (Exception e){
            Log.e("TAG", "sendToServer: exception : "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<List<Package1>> call, Response<List<Package1>> response) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onResponse: response code : "+response.code() );
        switch (response.code()){
            case 200 :
                listener.allPckgDone(response.body());
                Log.e("TAG", "onResponse: done response : "+response.code());
                Log.e("TAG", "onResponse: test size : "+response.body().size());

                break;
            case 422:
                listener.allPckgFailure();

                break;
            case 500:
                listener.allPckgFailure();

                break;
            case 400:
                listener.allPckgFailure();

                break;
            case 401:
                listener.allPckgFailure();
                break;
            case 404:
                listener.allPckgFailure();
                break;
        }
    }

    @Override
    public void onFailure(Call<List<Package1>> call, Throwable t) {
            UtilFunctions.hideProgressBar();
            Log.e("TAG", "onFailure: test ");
            Log.e("TAG", "onFailure:  "+t.getMessage());
            t.printStackTrace();
            listener.allPckgFailure();
        }



    public interface Listener {
        public void allPckgDone(List<Package1> t);
        public void allPckgFailure();
    }
}

