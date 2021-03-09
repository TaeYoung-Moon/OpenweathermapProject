package com.example.openweathermap.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.openweathermap.listener.OnItemClickListener;

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

//    public void onSearchClicked() {
//        Toast.makeText(mContext, "test", Toast.LENGTH_LONG).show();
//    }

//    public void onItemClick() {
//        onItemClickListener.onItemClick();
//    }


}
