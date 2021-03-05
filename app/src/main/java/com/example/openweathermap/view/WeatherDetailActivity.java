package com.example.openweathermap.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.openweathermap.R;
import com.example.openweathermap.comm.Config;
import com.example.openweathermap.comm.RetrofitClient;
import com.example.openweathermap.databinding.ActivityMainBinding;
import com.example.openweathermap.databinding.ActivityWeatherDetailBinding;
import com.example.openweathermap.viewmodel.MainViewModel;
import com.example.openweathermap.viewmodel.WeatherViewModel;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDetailActivity extends AppCompatActivity {
    private WeatherViewModel weatherViewModel;
    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;

    private ActivityWeatherDetailBinding binding; // 데이터 바인딩 작업

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        init();
    }

    public void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_detail);
        binding.setViewModel(new WeatherViewModel(this));
        binding.executePendingBindings();
        if (viewModelFactory == null) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        }
        weatherViewModel = new ViewModelProvider(this, viewModelFactory).get(WeatherViewModel.class);



        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Logger.d("## id ==> " + id);

        weatherViewModel.getCurrentWeatherData(id);

//        getCurrentWeatherData(id);
    }

//    public void getCurrentWeatherData(String id) {
////        String id = "519168";
//
//        Call<ResponseBody> call = RetrofitClient.getInstance().getApiService().getCurrentWeatherData(id, Config.API_KEY);
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.code() == 404) {
////                    handler.obtainMessage(RetrofitClient.REQ_ERR_NOT_RESPONSE, "[404]" + getString(R.string.msg_conn_failure)).sendToTarget();
//                    return;
//                }
//                try {
//                    String strJsonOutput = response.body().string();
//                    Logger.d("## onResponse ==> " + strJsonOutput);
//
////                    Logger.d(TAG+ "SettingActivity.getCodeMaster().onResponse()=>API 요청결과:" + strJsonOutput);
//
//                    //json문자열을 CommonResponse의 Generic 타입 캐스트를 통해 원하는 타입으로 변환하여 받는다.
////                    CommonResponse<ArrayList<CodeMaster>> commonResponse = mapper.readValue(strJsonOutput, new TypeReference<CommonResponse<ArrayList<CodeMaster>>>() {});
//
//                } catch (IOException e) {
//                    Logger.d("## catch ==> ");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Logger.d("## onFailure", t.getMessage());
//            }
//        });
//    }

}