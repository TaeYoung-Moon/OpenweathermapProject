package com.example.openweathermap.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.openweathermap.listener.OnItemClickListener;
import com.example.openweathermap.R;
import com.example.openweathermap.adapter.CityAdapter;
import com.example.openweathermap.databinding.ActivityMainBinding;
import com.example.openweathermap.model.City;
import com.example.openweathermap.util.JsonUtil;
import com.example.openweathermap.viewmodel.MainViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        binding.evSerarch.setText("");
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

        Type listType = new TypeToken<ArrayList<City>>() {
        }.getType();
        ArrayList<City> cities = new Gson().fromJson(cityList, listType);

        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City lhs, City rhs) {
                return lhs.getCountry().compareTo(rhs.getCountry());
            }
        });


        // 뷰 어뎁터 설정
        mCityAdapter = new CityAdapter(cities, this);
        binding.listCity.setAdapter(mCityAdapter);

        binding.srlRefresh.setOnRefreshListener(() -> {
            binding.listCity.setAdapter(mCityAdapter);
            binding.srlRefresh.setRefreshing(false);
        });

        binding.evSerarch.addTextChangedListener(this);


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