package com.example.openweathermap.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.openweathermap.Listener.OnItemClickListener;
import com.example.openweathermap.adapter.CityAdapter;
import com.example.openweathermap.comm.Config;
import com.example.openweathermap.comm.RetrofitClient;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public MainViewModel() {
    }


//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//
//    }

    public MainViewModel(Context context) {
        this.mContext = context;
    }

    public void onSearchClicked() {
        Toast.makeText(mContext, "test", Toast.LENGTH_LONG).show();
    }

//    public void onItemClick() {
//        onItemClickListener.onItemClick();
//    }


}
