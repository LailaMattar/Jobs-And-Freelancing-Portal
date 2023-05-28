package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.SavedPost;
import com.example.jopy.network.APIs;
import com.example.jopy.network.Response.PostResponse;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.util.UtilFunctions;

import retrofit2.Call;

public class SavePostPresenter implements ServerPresenter<SavedPost, PostResponse<Post>> {
    Activity activity;
    SavePostPresenter.Listener listener;

    public SavePostPresenter(Activity activity, SavePostPresenter.Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }


    @Override
    public void sendToServer(SavedPost request) {
        try{
                APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
                UtilFunctions.showProgressBar(activity);
                String token = "Bearer "+ LoginActivity.user.getToken();
                Log.e("tokeeen :  ", token);

                Call<PostResponse<Post>> call    = service.SavePost(token,request);//api function
                call.enqueue(this);
            }catch (Exception e){
                Log.e("TAG", "sendToServer: exception : "+e.getMessage());
                e.printStackTrace();
            }
        }
    @Override
    public void onResponse(Call<PostResponse<Post>> call, retrofit2.Response<PostResponse<Post>> response) {
        UtilFunctions.hideProgressBar();
        Toast.makeText(activity,"save post done",Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onResponse: response code : "+response.code() );
        switch (response.code()){
            case 200 :
                listener.saveDone();
                Log.e("TAG", "onResponse: save post response : "+response.code());
                Log.e("TAG", "onResponse: body : "+response.body().getMessage());
                //UtilFunctions.hideProgressBar();
                //listener.CategoryDone(response.body().getData());
                break;
            case 422:
                listener.saveFailure();
                // Log.e("TAG", "onResponse: body : "+response.body().getMessage());

                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 500:
                listener.saveFailure();
                //listener.categoryfailure();
//                showToast(activity , R.string.error_500);
                break;
            case 400:
                listener.saveFailure();
                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 401:
                listener.saveFailure();
                break;
            case 404:
                listener.saveFailure();
                break;
        }
    }

    @Override
    public void onFailure(Call<PostResponse<Post>> call, Throwable t) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test ");
        Log.e("TAG", "onFailure: 22 "+t.getMessage());
        t.printStackTrace();
        listener.saveFailure();
    }



    public interface Listener {
        public void saveDone();
        public void saveFailure();
}
}


