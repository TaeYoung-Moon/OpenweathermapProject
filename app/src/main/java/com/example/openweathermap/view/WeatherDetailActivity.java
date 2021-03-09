package com.example.openweathermap.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.openweathermap.R;
import com.example.openweathermap.comm.Config;
import com.example.openweathermap.comm.RetrofitClient;
import com.example.openweathermap.databinding.ActivityWeatherDetailBinding;
import com.example.openweathermap.model.CityInfo;
import com.example.openweathermap.viewmodel.WeatherViewModel;
import com.orhanobut.logger.Logger;

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
        init();
    }

    public void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_detail);
        binding.setViewModel(new WeatherViewModel());
        binding.executePendingBindings();
        if (viewModelFactory == null) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        }
        weatherViewModel = new ViewModelProvider(this, viewModelFactory).get(WeatherViewModel.class);

    }

}