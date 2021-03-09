package com.example.openweathermap.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.openweathermap.R;
import com.example.openweathermap.comm.Config;
import com.example.openweathermap.comm.RetrofitClient;
import com.example.openweathermap.listener.OnIntentListener;
import com.example.openweathermap.model.CityInfo;
import com.example.openweathermap.model.DescriptionKo;
import com.example.openweathermap.model.WindType;
import com.orhanobut.logger.Logger;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingViewModel extends ViewModel {

    public LoadingViewModel() {

    }

}
