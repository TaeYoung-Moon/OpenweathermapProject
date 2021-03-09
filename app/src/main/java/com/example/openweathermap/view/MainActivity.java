package com.example.openweathermap.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.openweathermap.R;
import com.example.openweathermap.adapter.CityAdapter;
import com.example.openweathermap.databinding.ActivityMainBinding;
import com.example.openweathermap.listener.OnItemClickListener;
import com.example.openweathermap.model.City;
import com.example.openweathermap.util.JsonUtil;
import com.example.openweathermap.viewmodel.MainViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, TextWatcher {

    private CityAdapter mCityAdapter;

    private ActivityMainBinding binding; // 데이터 바인딩 작업
    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.tieSearch.setText("");
    }

    public void init() {
        if (viewModelFactory == null) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        }
        mainViewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mainViewModel);
        binding.executePendingBindings();

        String cityList = JsonUtil.getJsonFromAssets(this, "citylist.json");

        Type listType = new TypeToken<ArrayList<City>>() {
        }.getType();
        ArrayList<City> cities = new Gson().fromJson(cityList, listType);

        Collections.sort(cities, (obj1, obj2) -> obj1.getCountry().compareTo(obj2.getCountry()));

        // 뷰 어뎁터 설정
        mCityAdapter = new CityAdapter(cities, this);
        binding.listCity.setAdapter(mCityAdapter);

        binding.srlRefresh.setOnRefreshListener(() -> {
            binding.listCity.setAdapter(mCityAdapter);
            binding.srlRefresh.setRefreshing(false);
        });

        binding.tieSearch.addTextChangedListener(this);

    }

    @Override
    public void onItemClick(String id) {
        Intent intent = new Intent(this, LoadingActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Logger.d("## onTextChanged ==> " + charSequence.toString());
        mCityAdapter.getFilter().filter(charSequence);
    }

    @Override
    public void afterTextChanged(Editable edit) {

    }
}