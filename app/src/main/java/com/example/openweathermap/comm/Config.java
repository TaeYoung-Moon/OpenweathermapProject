package com.example.openweathermap.comm;

public final class Config {
    public static BuildType buildType = BuildType.DEBUG;

    public static final BuildType BUILD_TYPE = buildType;

    protected static enum BuildType {
        DEBUG, RELEASE
    }

    public static String API_URL = "https://api.openweathermap.org/data/2.5/";
    public static String API_KEY = "3e019058d36afb29fca9ff3b1a98cfcc";

}
