package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.SavedPost;
import com.example.jopy.network.APIs;
import com.example.jopy.network.Response.SavedPostsResponse;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.util.UtilFunctions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetSavedPostsPresenter  implements ServerPresenter<SavedPost, SavedPostsResponse> {
    Activity activity;
    GetSavedPostsPresenter.Listener listener;

    public GetSavedPostsPresenter(Activity activity, GetSavedPostsPresenter.Listener listener) {
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

            Call<SavedPostsResponse> call    = service.GetSavedPosts(token,request);//api function
            call.enqueue(this);
        }catch (Exception e){
            Log.e("TAG", "sendToServer: exception : "+e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void onResponse(Call<SavedPostsResponse> call, Response<SavedPostsResponse> response) {
        UtilFunctions.hideProgressBar();
        Toast.makeText(activity,"save post done",Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onResponse: response code : "+response.code() );
        switch (response.code()){
            case 200 :
                listener.getSavedPostsDone(response.body().getFavorites());
                Log.e("TAG", "onResponse:  get saved posts response : "+response.code());
                Log.e("TAG", "onResponse: body : "+response.body().getFavorites().get(0).getTitle());
                //UtilFunctions.hideProgressBar();
                //listener.CategoryDone(response.body().getData());
                break;
            case 422:
                listener.getSavedPostsFailure();
                // Log.e("TAG", "onResponse: body : "+response.body().getMessage());

                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 500:
                listener.getSavedPostsFailure();
                //listener.categoryfailure();
//                showToast(activity , R.string.error_500);
                break;
            case 400:
                listener.getSavedPostsFailure();
                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 401:
                listener.getSavedPostsFailure();
                break;
            case 404:
                listener.getSavedPostsFailure();
                break;
        }
    }

    @Override
    public void onFailure(Call<SavedPostsResponse> call, Throwable t) {

            UtilFunctions.hideProgressBar();
            Log.e("TAG", "onFailure: test ");
            Log.e("TAG", "onFailure: 22 "+t.getMessage());
            t.printStackTrace();
            listener.getSavedPostsFailure();
        }


        public interface Listener {
        public void getSavedPostsDone(List<Post> s);
        public void getSavedPostsFailure();
    }
}


