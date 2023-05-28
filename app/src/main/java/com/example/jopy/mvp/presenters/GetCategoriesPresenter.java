package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.Category;
import com.example.jopy.network.APIs;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.util.UtilFunctions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetCategoriesPresenter implements ServerPresenter<Category,Category> {
    Activity activity;
    Listener listener;

    public GetCategoriesPresenter(Activity activity, Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public void sendToServer(Category request) {
        try{
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
            UtilFunctions.showProgressBar(activity);
            request = new Category("*","category");
            Call<Category> call    = service.getCategories(request);//api function
            call.enqueue(this);
        }catch (Exception e){
            Log.e("TAG", "sendToServer: exception : "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<Category> call, Response<Category> response) {
        UtilFunctions.hideProgressBar();
        Toast.makeText(activity,"get categories done",Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onResponse: response code : "+response.code());
        switch (response.code()){
            case 200 :
                Log.e("TAG", "onResponse: done get categories : "+response.body().getCategories().get(0));
                listener.categoriesDone(response.body().getCategories());
                break;
            case 422:
                listener.categoriesFailure();
                Log.e("TAG", "onResponse: message : "+response.message());
                break;
            case 500:
            case 401:
            case 400:
                listener.categoriesFailure();
                break;
            case 404:
                Toast.makeText(activity,"email and password do not match",Toast.LENGTH_SHORT).show();
                listener.categoriesFailure();
                break;
        }
    }

    @Override
    public void onFailure(Call<Category> call, Throwable t) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test get all posts");
        Log.e("TAG", "onFailure:"+t.getMessage());
        t.printStackTrace();
        listener.categoriesFailure();
    }

    public interface Listener{
        public void categoriesDone(List<String> categories);
        public void categoriesFailure();
    }
}
