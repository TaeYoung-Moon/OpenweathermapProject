package com.example.openweathermap.viewmodel;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.openweathermap.comm.Config;
import com.example.openweathermap.comm.RetrofitClient;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {

    private Context mContext;

    public WeatherViewModel(Context context) {
        this.mContext = context;
    }

    public WeatherViewModel() {

    }

    public void getCurrentWeatherData(String id) {

        Call<ResponseBody> call = RetrofitClient.getInstance().getApiService().getCurrentWeatherData(id, Config.API_KEY);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 404) {
//                    handler.obtainMessage(RetrofitClient.REQ_ERR_NOT_RESPONSE, "[404]" + getString(R.string.msg_conn_failure)).sendToTarget();
                    return;
                }
                try {
                    String strJsonOutput = response.body().string();
                    Logger.d("## onResponse ==> " + strJsonOutput);

//                    Logger.d(TAG+ "SettingActivity.getCodeMaster().onResponse()=>API 요청결과:" + strJsonOutput);

                    //json문자열을 CommonResponse의 Generic 타입 캐스트를 통해 원하는 타입으로 변환하여 받는다.
//                    CommonResponse<ArrayList<CodeMaster>> commonResponse = mapper.readValue(strJsonOutput, new TypeReference<CommonResponse<ArrayList<CodeMaster>>>() {});

                } catch (IOException e) {
                    Logger.d("## catch ==> ");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Logger.d("## onFailure", t.getMessage());
            }
        });
    }
}
