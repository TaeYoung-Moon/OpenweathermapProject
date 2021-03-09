package com.example.openweathermap.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.openweathermap.R;
import com.example.openweathermap.databinding.ActivityWeatherDetailBinding;
import com.example.openweathermap.viewmodel.WeatherViewModel;

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