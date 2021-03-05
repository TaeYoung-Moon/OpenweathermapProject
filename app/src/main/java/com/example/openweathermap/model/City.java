package com.example.openweathermap.model;

public class City {
    /**
     *  {
     *     "id": 707860,
     *     "name": "Hurzuf",
     *     "country": "UA",
     *     "coord": {
     *       "lon": 34.283333,
     *       "lat": 44.549999
     *   }
     */

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
}
