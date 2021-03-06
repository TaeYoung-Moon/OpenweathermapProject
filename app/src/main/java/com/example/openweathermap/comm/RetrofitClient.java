package com.example.openweathermap.comm;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static RetrofitClient retrofitClient;
    private static RetrofitApi apiService;

    public RetrofitClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(chain -> {
            Request request = chain.request();
            Request newRequest;
            newRequest = request.newBuilder()
                    .build();
            return chain.proceed(newRequest);
        });
        OkHttpClient client = builder
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.API_URL) //api의 baseURL
                .addConverterFactory(GsonConverterFactory.create()) // 나는 데이터를 자동으로 컨버팅할 수 있게 GsonFactory를 씀
                .client(client)
                .build();

        apiService = retrofit.create(RetrofitApi.class); //실제 api Method들이선언된 Interface객체 선언
    }

    public static RetrofitClient getInstance() { //싱글톤으로 선언된 레트로핏 객체 얻는 용
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }

        return retrofitClient;
    }

    public RetrofitApi getApiService() { // API Interface 객체 얻는 용
        return apiService;
    }

}