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

    //private static Retrofit retrofit = null;
    private static RetrofitApi apiService;

    public static final int REQ_ERR_NOT_RESPONSE = 10;

    public RetrofitClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // intercept메서드 영역은 Http 요청전에 이루어지는 로직
        builder.addInterceptor(chain -> {
            Request request = chain.request();
            Request newRequest;

            newRequest = request.newBuilder()
                    .build();
            return chain.proceed(newRequest);
        });
        OkHttpClient client = builder
//                .addNetworkInterceptor(new StethoInterceptor()) // Stetho Interceptor 추가해야 Chrome Inspect tool 에서 확인 가능, 필수 아님
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

    public static void releaseInstance() {
        retrofitClient = null;
    }

    public RetrofitApi getApiService() { // API Interface 객체 얻는 용
        return apiService;
    }


    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest;

        newRequest = request.newBuilder()
                .build();
        return chain.proceed(newRequest);
    }

}