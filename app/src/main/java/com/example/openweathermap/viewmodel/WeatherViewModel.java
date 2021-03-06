package com.example.openweathermap.viewmodel;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.example.openweathermap.R;
import com.example.openweathermap.comm.Config;
import com.example.openweathermap.comm.RetrofitClient;
import com.example.openweathermap.model.CityInfo;
import com.example.openweathermap.model.Weather;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {

    private Context mContext;
    private static String mIconUrl;

    public static ObservableField<String> description = new ObservableField<>();
    public static ObservableField<String> temp = new ObservableField<>();
    public static ObservableField<String> feelsLike = new ObservableField<>();


    public WeatherViewModel(Context context) {
        this.mContext = context;
    }

    public WeatherViewModel() {

    }

    public void initCityInfo(CityInfo cityInfo) {
        description.set(cityInfo.getWeathers().get(0).getDescription());
        temp.set(Math.round(tempConversion(cityInfo.getMain().getTemp())) + " ℃");
        feelsLike.set("Feels like " + Math.round(tempConversion(cityInfo.getMain().getFeelsLike())) + " ℃");
    }

    public float tempConversion(float temp) {
        float kelvin = (float) 273.15;
        return temp - kelvin;
    }
}
