package com.example.jopy.mvp.presenters;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.jopy.mvp.models.User;
import com.example.jopy.network.APIs;
import com.example.jopy.network.Response.RegisterResponse;
import com.example.jopy.network.RetorfitSingelton;
import com.example.jopy.network.ServerPresenter;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.ui.activities.login_and_createAccount.activity_create_account_institute_2_;
import com.example.jopy.ui.activities.type_post.TypeServicePostActivity;
import com.example.jopy.util.UtilFunctions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class SignUpCompanyPresenter implements ServerPresenter<User, RegisterResponse<User>> {
    Activity activity;
    Listener listener;

    public SignUpCompanyPresenter(Activity activity, Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public void sendToServer(User request) {
        try{
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
            UtilFunctions.showProgressBar(activity);

            File file = new File(activity_create_account_institute_2_.path);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part image = MultipartBody.Part.createFormData("verification", file.getName(), requestBody);

            RequestBody email = RequestBody.create(
                    MediaType.parse("text/plain"),request.getEmail());
            RequestBody password = RequestBody.create(
                    MediaType.parse("text/plain"),request.getPassword());
            RequestBody accountType = RequestBody.create(
                    MediaType.parse("text/plain"),request.getAccountType());
            RequestBody phoneNumber = RequestBody.create(
                    MediaType.parse("text/plain"),request.getPhoneNumber());
            RequestBody country = RequestBody.create(
                    MediaType.parse("text/plain"),request.getCountry());
            RequestBody city = RequestBody.create(
                    MediaType.parse("text/plain"),request.getCity());
            List<String> tags= new ArrayList<>();
            tags.add("test");
            List<String> categories= new ArrayList<>();
            categories.add("test");
            RequestBody instituteType = RequestBody.create(
                    MediaType.parse("text/plain"),request.getInstituteType());
            RequestBody instituteName = RequestBody.create(
                    MediaType.parse("text/plain"),request.getInstituteName());
            RequestBody numberOfEmployees = RequestBody.create(
                    MediaType.parse("text/plain"),request.getNumberOfEmployees());
            RequestBody instituteField = RequestBody.create(
                    MediaType.parse("text/plain"),request.getInstituteField());
            RequestBody about = RequestBody.create(
                    MediaType.parse("text/plain"),request.getAbout());
            Log.e("TAG", "sendToServer: tetoo2 : "+request.getNumberOfEmployees());
            Call<RegisterResponse<User>> call = service.signUpCompany(image,email,password,accountType,phoneNumber,country,city,tags,categories,
                    instituteType,instituteName,numberOfEmployees,instituteField,about);
            call.enqueue(this);
        }catch (Exception e){
            Log.e("TAG", "sendToServer: exception : "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<RegisterResponse<User>> call, Response<RegisterResponse<User>> response) {
        UtilFunctions.hideProgressBar();
        Toast.makeText(activity,"create company account done",Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onResponse: register company response code : "+response.code() );
        switch (response.code()){
            case 201 :
                listener.registerCompanyDone();
                Log.e("TAG", "onResponse: done response : "+response.code());
//                Log.e("TAG", "onResponse: body : "+response.body().getPost().toString());
                //UtilFunctions.hideProgressBar();
                //listener.CategoryDone(response.body().getData());
                break;
            case 422:
                listener.registerCompanyFailure();
                // Log.e("TAG", "onResponse: body : "+response.body().getMessage());

                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 500:
                listener.registerCompanyFailure();
                //listener.categoryfailure();
//                showToast(activity , R.string.error_500);
                break;
            case 400:
                listener.registerCompanyFailure();
                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 401:
                listener.registerCompanyFailure();
                break;
            case 404:
                listener.registerCompanyFailure();
                break;
        }
    }

    @Override
    public void onFailure(Call<RegisterResponse<User>> call, Throwable t) {

    }

    public interface Listener{
        void registerCompanyDone();
        void registerCompanyFailure();
    }
}
