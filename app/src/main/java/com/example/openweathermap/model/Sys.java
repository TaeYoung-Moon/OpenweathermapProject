package com.example.openweathermap.model;

import com.google.gson.annotations.SerializedName;

public class Sys {
    @SerializedName("type")
    private float type;
    @SerializedName("id")
    private float id;
    @SerializedName("country")
    private String country;
    @SerializedName("sunrise")
    private float sunrise;
    @SerializedName("sunset")
    private float sunset;

    @Override
    public String toString() {
        return "Sys{" +
                "type=" + type +
                ", id=" + id +
                ", country='" + country + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }

    public float getType() {
        return type;
    }

    public void setType(float type) {
        this.type = type;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getSunrise() {
        return sunrise;
    }

    public void setSunrise(float sunrise) {
        this.sunrise = sunrise;
    }

    public float getSunset() {
        return sunset;
    }

    public void setSunset(float sunset) {
        this.sunset = sunset;
    }
}
