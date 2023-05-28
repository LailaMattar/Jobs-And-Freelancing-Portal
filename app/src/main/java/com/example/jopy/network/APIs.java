package com.example.jopy.network;

import com.example.jopy.mvp.models.Applicant2;
import com.example.jopy.mvp.models.Category;
import com.example.jopy.mvp.models.Notification;
import com.example.jopy.mvp.models.Package1;
import com.example.jopy.mvp.models.Package2;
import com.example.jopy.mvp.models.Post;
import com.example.jopy.mvp.models.PostAccept;
import com.example.jopy.mvp.models.Rating;
import com.example.jopy.mvp.models.Report;
import com.example.jopy.mvp.models.SavedPost;
import com.example.jopy.mvp.models.User;
import com.example.jopy.network.Response.AcceptedBeforeResponse;
import com.example.jopy.network.Response.ApplicantsResponse;
import com.example.jopy.network.Response.BuyPackResponse;
import com.example.jopy.network.Response.GetAllPostsResponse;
import com.example.jopy.network.Response.NotificationResponse;
import com.example.jopy.network.Response.PostResponse;
import com.example.jopy.network.Response.RegisterResponse;
import com.example.jopy.network.Response.Response;
import com.example.jopy.network.Response.SavedPostsResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIs {
    @Headers("Content-Type: application/json")
    @POST("login")
    Call<Response<User>> login(@Body User user);

    @Headers("Content-Type: application/json")
    @POST("register")
    Call<RegisterResponse<User>> signUp(@Body User user);

    @Multipart
    @POST("register")
    Call<RegisterResponse<User>> signUpCompany(@Part MultipartBody.Part verification,
                                               @Part("email") RequestBody email,
                                               @Part("password") RequestBody password,
                                               @Part("account_type") RequestBody accountType,
                                               @Part("phone_number") RequestBody phoneNumber,
                                               @Part("country") RequestBody country,
                                               @Part("city") RequestBody city,
                                               @Query("tags[]") List<String> tags,
                                               @Query("categories[]") List<String> categories,
                                               @Part("institute_type") RequestBody instituteType,
                                               @Part("institute_name") RequestBody instituteName,
                                               @Part("number_of_employees") RequestBody numberOfEmployees,
                                               @Part("institute_field") RequestBody instituteField,
                                               @Part("about") RequestBody about);

    @Multipart
    @POST("create-post")
    Call<PostResponse<Post>> CreatePostService(@Header("Authorization") String token,
                                               @Part("post_type") RequestBody postType,
                                               @Part("type") RequestBody type,
                                               @Part("about") RequestBody about,
                                               @Query("categories[]") List<String> categories,
                                               @Part("money") RequestBody money,
                                               @Part("title") RequestBody title,
                                               @Part("servise_time") RequestBody serviceTime,
                                               @Query("tags[]") List<String> tags,
                                               @Part MultipartBody.Part image);

    @POST("create-post")
    Call<PostResponse<Post>> createPost(@Header("Authorization") String token,@Body Post post);
@POST("update-post-by-id")
    Call<PostResponse<Post>> editPost(@Header("Authorization") String token,@Body Post post);

@POST("delete-post-by-id")
    Call<PostResponse<Post>> DeletePost(@Header("Authorization") String token,@Body Post post);



    @Headers("Content-Type: application/json")
    @POST("report-post-by-id")
    Call<PostResponse<Report>> Report(@Header("Authorization") String token, @Body Report report);

    @Headers("Content-Type: application/json")
    @POST("accept-applicant")
    Call<PostResponse<Applicant2>> Accept(@Header("Authorization") String token, @Body Applicant2 applicant);

@Headers("Content-Type: application/json")
    @POST("refuse-applicant")
    Call<PostResponse<Applicant2>> Refuse(@Header("Authorization") String token, @Body Applicant2 applicant);

  @Headers("Content-Type: application/json")
    @POST("apply")
    Call<PostResponse<Post>> Apply(@Header("Authorization") String token, @Body Post post);
@Headers("Content-Type: application/json")
    @POST("is-user-accepted-in-post")
    Call<AcceptedBeforeResponse> AcceptedUser(@Header("Authorization") String token, @Body PostAccept post);

@Headers("Content-Type: application/json")
    @POST("rate-post-by-id")
    Call<PostResponse<Post>> RateService(@Header("Authorization") String token, @Body Rating post);

@Headers("Content-Type: application/json")
    @POST("add-post-to-favorite")
    Call<PostResponse<Post>> SavePost(@Header("Authorization") String token, @Body SavedPost post);

@Headers("Content-Type: application/json")
    @POST("get-favorites")
    Call<SavedPostsResponse> GetSavedPosts(@Header("Authorization") String token, @Body SavedPost post);

  @Headers("Content-Type: application/json")
    @POST("get-applicants")
    Call<ApplicantsResponse> GetApplicants(@Header("Authorization") String token, @Body Post post);

    @Headers("Content-Type: application/json")
    @POST("get-all-packages")
    Call<List<Package1>> getPackage();

    @Headers("Content-Type: application/json")
    @POST("get-all-posts")
    Call<GetAllPostsResponse<List<Post>>> getAllPosts();

    @Headers("Content-Type: application/json")
    @POST("suggest-tags-or-categories")
    Call<Category> getCategories(@Body Category category);

    @Headers("Content-Type: application/json")
    @POST("buy-package")
    Call<BuyPackResponse> buyPackage(@Header("Authorization") String token, @Body Package2 package_id);

    @Headers("Content-Type: application/json")
    @POST("notifications")
    Call<NotificationResponse> getNotification(@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("get-user-by-id")
    Call<Response<User>> getUserById(@Header("Authorization") String token,@Body User user);

}
