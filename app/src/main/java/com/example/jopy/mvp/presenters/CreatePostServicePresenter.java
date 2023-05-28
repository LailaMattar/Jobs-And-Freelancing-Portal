package com.example.jopy.mvp.presenters;


       import com.example.jopy.network.APIs;
       import com.example.jopy.network.Response.PostResponse;
       import com.example.jopy.network.RetorfitSingelton;
       import com.example.jopy.network.ServerPresenter;
       import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
       import com.example.jopy.ui.activities.type_post.TypeServicePostActivity;
       import com.example.jopy.util.UtilFunctions;

        import android.app.Activity;
       import android.util.Log;
       import android.widget.Toast;

       import com.example.jopy.mvp.models.Post;

       import java.io.File;
       import java.util.ArrayList;
       import java.util.List;

       import okhttp3.MediaType;
       import okhttp3.MultipartBody;
       import okhttp3.RequestBody;
       import retrofit2.Call;

public class CreatePostServicePresenter implements ServerPresenter<Post, PostResponse<Post>> {
    Activity activity;
    Listener listener;

    public CreatePostServicePresenter(Activity activity, Listener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public void sendToServer(Post post ) {
        try{
            APIs service = RetorfitSingelton.getRetrofitInstance().create(APIs.class);
            UtilFunctions.showProgressBar(activity);
            String token = "Bearer "+ LoginActivity.user.getToken();
            Log.e("tokeeen :  ", token);

            RequestBody postType = RequestBody.create(
                    MediaType.parse("text/plain"),   // Fixed here
                    post.getPost_type());
            RequestBody type = RequestBody.create(
                    MediaType.parse("text/plain"),   // Fixed here
                    post.getPost_type());
            RequestBody about = RequestBody.create(
                    MediaType.parse("text/plain"),   // Fixed here
                    post.getAbout());
            RequestBody rb2;
            List<String> categories = new ArrayList<>();
            for(int i=0;i<post.getCategories().size();i++)
            {
                rb2=RequestBody.create(MediaType.parse("multipart/form-data"), post.getCategories().get(i));
                categories.add(post.getCategories().get(i));
            }
//            RequestBody categories = RequestBody.create(
//                    MediaType.parse("text/plain"),   // Fixed here
//                    post.getCategories().get(0));
            RequestBody money = RequestBody.create(
                    MediaType.parse("text/plain"),   // Fixed here
                    post.getMoney());
            RequestBody title = RequestBody.create(
                    MediaType.parse("text/plain"),
                    post.getTitle());
            RequestBody serviceTime = RequestBody.create(
                    MediaType.parse("text/plain"),   // Fixed here
                    post.getServiceTime());
//            RequestBody requestBody2 = RequestBody.create(MediaType.parse("*/*"), post.getTags());
            MultipartBody.Part rb;
            List<String> mp= new ArrayList<>();
            for(int i=0;i<post.getTags().size();i++)
            {
                rb=MultipartBody.Part.createFormData("tags", post.getTags().get(i));
                mp.add(post.getTags().get(i));
            }

            File file = new File(TypeServicePostActivity.path);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

            Call<PostResponse<Post>> call    = service.CreatePostService(token,postType,type,about,categories,money,title,serviceTime,mp,image);//api function
            call.enqueue(this);
        }catch (Exception e){
            Log.e("TAG", "sendToServer: exception : "+e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void onResponse(Call<PostResponse<Post>> call, retrofit2.Response<PostResponse<Post>> response) {
        UtilFunctions.hideProgressBar();
        Toast.makeText(activity,"post done",Toast.LENGTH_SHORT).show();
        Log.e("TAG", "onResponse: response code : "+response.code() );
        switch (response.code()){
            case 200 :
                listener.postDone();
                Log.e("TAG", "onResponse: done response : "+response.code());
//                Log.e("TAG", "onResponse: body : "+response.body().getPost().toString());
                //UtilFunctions.hideProgressBar();
                //listener.CategoryDone(response.body().getData());
                break;
            case 422:
                listener.postFailure();
               // Log.e("TAG", "onResponse: body : "+response.body().getMessage());

                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 500:
                listener.postFailure();
                //listener.categoryfailure();
//                showToast(activity , R.string.error_500);
                break;
            case 400:
                listener.postFailure();
                //listener.categoryfailure();
//                responseError(response,activity);
                break;
            case 401:
                listener.postFailure();
                break;
            case 404:
                listener.postFailure();
                break;
        }
    }

    @Override
    public void onFailure(Call<PostResponse<Post>> call, Throwable t) {
        UtilFunctions.hideProgressBar();
        Log.e("TAG", "onFailure: test ");
        Log.e("TAG", "onFailure: 22 "+t.getMessage());
        t.printStackTrace();
        listener.postFailure();
    }


    public interface Listener {
        public void postDone();
        public void postFailure();
    }
}
