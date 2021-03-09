package com.example.openweathermap.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.openweathermap.R;
import com.example.openweathermap.comm.Config;
import com.example.openweathermap.comm.RetrofitClient;
import com.example.openweathermap.databinding.AcitivtyLoadingBinding;
import com.example.openweathermap.databinding.ActivityWeatherDetailBinding;
import com.example.openweathermap.listener.OnIntentListener;
import com.example.openweathermap.listener.OnItemClickListener;
import com.example.openweathermap.model.CityInfo;
import com.example.openweathermap.viewmodel.LoadingViewModel;
import com.example.openweathermap.viewmodel.WeatherViewModel;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity implements OnIntentListener {
    private WeatherViewModel weatherViewModel;
    private LoadingViewModel loadingViewModel;
    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;

    private AcitivtyLoadingBinding binding; // 데이터 바인딩 작업


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.acitivty_loading);
        binding.setViewModel(new LoadingViewModel());
        binding.executePendingBindings();
        if (viewModelFactory == null) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        }
        weatherViewModel = new ViewModelProvider(this, viewModelFactory).get(WeatherViewModel.class);
        weatherViewModel.setOnIntentListener(this);

        loadingViewModel = new ViewModelProvider(this, viewModelFactory).get(LoadingViewModel.class);

        Glide.with(this)
                .asGif()    // GIF 로딩
                .load(R.raw.loading)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)    // Glide에서 캐싱한 리소스와 로드할 리소스가 같을때 캐싱된 리소스 사용
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
    }
}