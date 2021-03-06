package com.example.openweathermap.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.openweathermap.listener.OnItemClickListener;
import com.example.openweathermap.R;
import com.example.openweathermap.adapter.CityAdapter;
import com.example.openweathermap.databinding.ActivityMainBinding;
import com.example.openweathermap.model.City;
import com.example.openweathermap.util.JsonUtil;
import com.example.openweathermap.viewmodel.MainViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private CityAdapter mCityAdapter;

    private ActivityMainBinding binding; // 데이터 바인딩 작업
    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new MainViewModel(this));
        binding.executePendingBindings();
        if (viewModelFactory == null) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        }
        mainViewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);

        String cityList = JsonUtil.getJsonFromAssets(this, "citylist.json");

        Type listType = new TypeToken<ArrayList<City>>() {}.getType();
        ArrayList<City> cities = new Gson().fromJson(cityList, listType);

        // 뷰 어뎁터 설정
        mCityAdapter = new CityAdapter(cities, this);
        binding.listCity.setAdapter(mCityAdapter);

        binding.srlRefresh.setOnRefreshListener(() -> {
            binding.listCity.setAdapter(mCityAdapter);
            binding.srlRefresh.setRefreshing(false);
        });


    }
    @Override
    public void onItemClick(String id) {
        Intent intent = new Intent(this, WeatherDetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}