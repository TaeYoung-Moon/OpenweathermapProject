package com.example.openweathermap;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = this;
        // 디버그 시에만 Log 출력
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

//         Debug 모드일때만 Stetho init
//        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this);
//
//         Joda-Time 이니셜라이징
//        JodaTimeAndroid.init(this);
//
//        Hawk.init(context).build();

    }

}


/* 로그레벨별 추천하는 색상
    Debug  : 6897BB
    Info   : 6A8759
    Warn   : BBB529
    Error  : FF6B68
    Assert : 9876AA
 */