package com.example.openweathermap.model;

import com.example.openweathermap.util.StringUtil;

import java.util.Locale;

public class City {

    private String id;
    private String name;
    private String country;
    private Coord coord;

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", coord=" + coord +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountryName(String country) {
        if (StringUtil.getNvlStr(country, "").isEmpty()) {

            return "대륙 (Continent)";
        } else {
            Locale locale = new Locale("", country);
            return locale.getDisplayCountry() + " (" + country + ")";
        }
    }

}
