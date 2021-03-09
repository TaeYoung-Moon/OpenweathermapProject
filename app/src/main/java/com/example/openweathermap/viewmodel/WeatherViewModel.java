package com.example.openweathermap.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
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

public class WeatherViewModel extends ViewModel {

    public static ObservableField<String> description = new ObservableField<>();
    public static ObservableField<String> temp = new ObservableField<>();
    public static ObservableField<String> feelsLike = new ObservableField<>();

    public static ObservableField<String> country = new ObservableField<>();
    public static ObservableField<String> city = new ObservableField<>();
    public static ObservableField<String> minMax = new ObservableField<>();

    public static ObservableField<String> pressure = new ObservableField<>();
    public static ObservableField<String> humidity = new ObservableField<>();
    public static ObservableField<String> visibility = new ObservableField<>();
    public static ObservableField<String> speedDeg = new ObservableField<>();
    public static ObservableField<String> deg = new ObservableField<>();

    public static ObservableField<String> iconUrl = new ObservableField<>();

    private OnIntentListener onIntentListener;

    public WeatherViewModel() {

    }

    public void setOnIntentListener(OnIntentListener onIntentListener) {
        this.onIntentListener = onIntentListener;
    }

    public void getCurrentWeatherData(String id) {
        Call<CityInfo> call = RetrofitClient.getInstance().getApiService().getCurrentWeatherData(id, Config.API_KEY);
        call.enqueue(new Callback<CityInfo>() {
            @Override
            public void onResponse(Call<CityInfo> call, Response<CityInfo> response) {
                if (response.code() == 404) {
                    return;
                } else {
                    CityInfo cityInfo = response.body();
                    Logger.d("## onResponse ==> " + cityInfo.toString());

                    String mIconUrl = Config.WEATHER_ICON_BASE_URL + cityInfo.getWeathers().get(0).getIcon() + Config.WEATHER_ICON_EXTENSION;
                    Logger.d("## mIconUrl ==> " + mIconUrl);

                    initCityInfo(cityInfo, mIconUrl);

                }

            }

            @Override
            public void onFailure(Call<CityInfo> call, Throwable t) {
                Logger.e("## onFailure ==> " + t.getMessage());
            }

        });
    }

    public void initCityInfo(CityInfo cityInfo, String url) {
        Logger.d("## initCityInfo cityInfo ==> " + cityInfo.toString());

        Locale l = new Locale("", cityInfo.getSys().getCountry());
        description.set(getDescriptionKo(cityInfo.getWeathers().get(0).getDescription()));
        temp.set(Math.round(tempConversion(cityInfo.getMain().getTemp())) + " ℃");
        feelsLike.set("Feels like " + Math.round(tempConversion(cityInfo.getMain().getFeelsLike())) + " ℃");
        country.set(l.getDisplayCountry() + " (" + cityInfo.getSys().getCountry() + ")");
        city.set(cityInfo.getName());
        minMax.set("min " + Math.round(tempConversion(cityInfo.getMain().getTempMin())) + " ℃" + " / " + "max " + Math.round(tempConversion(cityInfo.getMain().getTempMax())) + " ℃");
        pressure.set(" : " + cityInfo.getMain().getPressure() + "hpa");
        humidity.set(" : " + cityInfo.getMain().getHumidity() + "%");
        visibility.set(" : " + meterToKilometer(cityInfo.getVisibility()) + "km");
        speedDeg.set(" :  " + cityInfo.getWind().getSpeed() + "m/s" + " " + getWindDirection(cityInfo.getWind().getDeg()));
        iconUrl.set(url);

        onIntentListener.onIntent();

    }

    /**
     * 켈빈 온도 -> 섭씨 온도로 변환
     *
     * @param temp
     * @return
     */
    public float tempConversion(float temp) {
        final float kelvin = (float) 273.15;
        return temp - kelvin;
    }

    public float meterToKilometer(float meter) {
        return (float) (meter * 0.001);
    }

    /**
     * 풍향 공식 계산을 통한 값 도출
     *
     * @param degree
     * @return
     */
    private String getWindDirection(int degree) {
        int result = (int) ((degree + 22.5 * 0.5) / 22.5);
        WindType windType = WindType.value(result);
        return windType.getName();
    }

    private String getDescriptionKo(String description) {
        DescriptionKo descriptionKo = DescriptionKo.value(description);
        return descriptionKo.getKr();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .into(imageView);
    }
}
