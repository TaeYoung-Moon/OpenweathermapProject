package com.example.openweathermap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.openweathermap.adapter.CityAdapter;
import com.example.openweathermap.comm.Config;
import com.example.openweathermap.comm.RetrofitClient;
import com.example.openweathermap.databinding.ActivityMainBinding;
import com.example.openweathermap.model.City;
import com.example.openweathermap.util.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // 뷰 바인딩 선언

    private CityAdapter mCityAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
    }

    public void init() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main); //뷰 바인딩 연결

        binding.btnSearch.setOnClickListener(view -> {
            getCurrentWeatherData();
        });

        String cityList = JsonUtil.getJsonFromAssets(this, "citylist.json");
        Logger.d("## cityList ==> " + cityList);

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<City>>() {
        }.getType();
        ArrayList<City> cities = new Gson().fromJson(cityList, listType);
        Logger.d("## users size ==> " + cities.size());

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.listCity.setLayoutManager(mLayoutManager);
        // 뷰 어뎁터 설정
        mCityAdapter = new CityAdapter(cities);
        binding.listCity.setAdapter(mCityAdapter);
    }

    /**
     * REST-API getCodeMaster - codeMasterInfo 가져오기
     */
    public void getCurrentWeatherData() {
        String id = "519168";

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