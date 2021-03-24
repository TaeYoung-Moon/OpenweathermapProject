package com.example.openweathermap.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.openweathermap.R;
import com.example.openweathermap.databinding.AcitivtyLoadingBinding;
import com.example.openweathermap.listener.OnIntentListener;
import com.example.openweathermap.viewmodel.LoadingViewModel;
import com.example.openweathermap.viewmodel.WeatherViewModel;

public class LoadingActivity extends AppCompatActivity implements OnIntentListener {
    private WeatherViewModel weatherViewModel;
    private LoadingViewModel loadingViewModel;
    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private AcitivtyLoadingBinding binding; // 데이터 바인딩

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        if (viewModelFactory == null) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        }
        weatherViewModel = new ViewModelProvider(this, viewModelFactory).get(WeatherViewModel.class);
        loadingViewModel = new ViewModelProvider(this, viewModelFactory).get(LoadingViewModel.class);
        weatherViewModel.setOnIntentListener(this);

        binding = DataBindingUtil.setContentView(this, R.layout.acitivty_loading);
        binding.setViewModel(loadingViewModel);
        binding.executePendingBindings();

        Glide.with(this)
                .asGif()
                .load(R.raw.loading)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE) // Glide 에서 캐싱한 리소스와 로드할 리소스가 같을때 캐싱된 리소스 사용
                .into(binding.loadingImage);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        weatherViewModel.getCurrentWeatherData(id);

    }

    @Override
    public void onIntent() {
        Intent intent = new Intent(this, WeatherDetailActivity.class);
        startActivity(intent);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        finish();
    }
}