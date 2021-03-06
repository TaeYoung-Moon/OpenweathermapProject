package com.example.openweathermap.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.openweathermap.R;
import com.example.openweathermap.comm.Config;
import com.example.openweathermap.comm.RetrofitClient;
import com.example.openweathermap.databinding.ActivityMainBinding;
import com.example.openweathermap.databinding.ActivityWeatherDetailBinding;
import com.example.openweathermap.model.CityInfo;
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
//        weatherViewModel.getCurrentWeatherData(id);
        getCurrentWeatherData(id);
    }

    public void getCurrentWeatherData(String id) {

        Call<CityInfo> call = RetrofitClient.getInstance().getApiService().getCurrentWeatherData(id, Config.API_KEY);

        call.enqueue(new Callback<CityInfo>() {
            @Override
            public void onResponse(Call<CityInfo> call, Response<CityInfo> response) {
                if (response.code() == 404) {
                    return;
                } else {
                    CityInfo cityInfo = response.body();
                    Logger.d("## onResponse ==> " + cityInfo.toString());

                    String mIconUrl = Config.WEATHER_ICON_BASE_URL + cityInfo.getWeathers().get(0).getIcon() + Config.WEATHER_ICON_EXTENSION;
                    Logger.d("## mIconUrl ==> " + mIconUrl);

                    Glide.with(binding.ivWeatheIcon.getContext())
                            .load(mIconUrl)
                            .centerCrop()
                            .into(binding.ivWeatheIcon);

                    weatherViewModel.initCityInfo(cityInfo);
                }
            }

            @Override
            public void onFailure(Call<CityInfo> call, Throwable t) {
                Logger.e("## onFailure ==> " + t.getMessage());
            }
        });
    }

}