package com.example.openweathermap.comm;

import com.example.openweathermap.model.CityInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
    /***********************************************
     *
     * api.openweathermap.org/data/2.5/weather?id=519168&appid=3e019058d36afb29fca9ff3b1a98cfcc
     *
     ***********************************************/
    @GET("weather/")
    Call<CityInfo> getCurrentWeatherData(@Query("id") String id, @Query("appid") String appid);

}
