package com.example.jopy.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetorfitSingelton {
    private static Retrofit retrofit;
    public static final String lailaURL ="http://192.168.1.3:8000/api/";
   public static final String maysURL ="http://192.168.13.15:8000/api/";

    public static final String BASE_URL = maysURL;
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            createInstance();
        }
        return retrofit;
    }

    public static void createInstance(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15000 , TimeUnit.SECONDS)
                .readTimeout(10000 ,TimeUnit.SECONDS)
                .writeTimeout(15000, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .client(httpClient.build())
                .build();
    }

}
