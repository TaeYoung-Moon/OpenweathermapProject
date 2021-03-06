package com.example.openweathermap.model;

public enum DescriptionKo {
    THUNDERSTORM_WITH_LIGHT_RAIN("thunderstorm with light rain", "가벼운 비를 동반한 천둥구름"),
    THUNDERSTORM_WITH_RAIN("thunderstorm with rain", "비를 동반한 천둥구름"),
    THUNDERSTORM_WITH_HEAVY_RAIN("thunderstorm with heavy rain", "폭우를 동반한 천둥구름"),
    LIGHT_THUNDERSTORM("light thunderstorm", "약한 천둥구름"),
    THUNDERSTORM("thunderstorm", "천둥구름"),
    HEAVY_THUNDERSTORM("heavy thunderstorm", "강한 천둥구름"),
    RAGGED_THUNDERSTORM("ragged thunderstorm", "불규칙적 천둥구름"),
    THUNDERSTORM_WITH_LIGHT_DRIZZLE("thunderstorm with light drizzle", "약한 연무를 동반한 천둥구름"),
    THUNDERSTORM_WITH_DRIZZLE("thunderstorm with drizzle", "연무를 동반한 천둥구름"),
    THUNDERSTORM_WITH_HEAVY_DRIZZLE("thunderstorm with heavy drizzle", "강한 안개비를 동반한 천둥구름"),
    LIGHT_INTENSITY_DRIZZLE("light intensity drizzle", "가벼운 안개비"),
    DRIZZLE("drizzle", "안개비"),
    HEAVY_INTENSITY_DRIZZLE("heavy intensity drizzle", "강한 안개비"),
    LIGHT_INTENSITY_DRIZZLE_RAIN("light intensity drizzle rain", "가벼운 적은비"),
    DRIZZLE_RAIN("drizzle rain", "적은비"),
    HEAVY_INTENSITY_DRIZZLE_RAIN("heavy intensity drizzle rain", "강한 적은비"),
    SHOWER_RAIN_AND_DRIZZLE("shower rain and drizzle", "소나기와 안개비"),
    HEAVY_SHOWER_RAIN_AND_DRIZZLE("heavy shower rain and drizzle", "강한 소나기와 안개비"),
    SHOWER_DRIZZLE("shower drizzle", "소나기"),
    LIGHT_RAIN("light rain", "악한 비"),
    MODERATE_RAIN("moderate rain", "중간 비"),
    HEAVY_INTENSITY_RAIN("heavy intensity rain", "강한 비"),
    VERY_HEAVY_RAIN("very heavy rain", "매우 강한 비"),
    EXTREME_RAIN("extreme rain", "극심한 비"),
    FREEZING_RAIN("freezing rain", "우박"),
    LIGHT_INTENSITY_SHOWER_RAIN("light intensity shower rain", "약한 소나기 비"),
    SHOWER_RAIN("shower rain", "소나기 비"),
    HEAVY_INTENSITY_SHOWER_RAIN("heavy intensity shower rain", "강한 소나기 비"),
    RAGGED_SHOWER_RAIN("ragged shower rain", "불규칙적 소나기 비"),
    LIGHT_SNOW("light snow", "가벼운 눈"),
    SNOW("snow", "눈"),
    HEAVY_SNOW("heavy snow", "강한 눈"),
    SLEET("sleet", "진눈깨비"),
    SHOWER_SLEET("shower sleet", "소나기 진눈깨비"),
    LIGHT_RAIN_AND_SNOW("light rain and snow", "약한 비와 눈"),
    RAIN_AND_SNOW("rain and snow", "비와 눈"),
    LIGHT_SHOWER_SNOW("light shower snow", "약한 소나기 눈"),
    SHOWER_SNOW("shower snow", "소나기 눈"),
    HEAVY_SHOWER_SNOW("heavy shower snow", "강한 소나기 눈"),
    MIST("mist", "박무"),
    SMOKE("smoke", "연기"),
    HAZE("haze", "연무"),
    SAND_DUST_WHIRL("sand, dust whirls", "모래 먼지"),
    FOG("fog", "안개"),
    SAND("sand", "모래"),
    DUST("dust", "먼지"),
    VOLCANIC_ASH("volcanic ash", "화산재"),
    SQUALLS("squalls", "돌풍"),
    CLEAR_SKY("clear sky", "구름 한 점 없는 맑은 하늘"),
    FEW_CLOUDS("few clouds", "약간의 구름이 낀 하늘"),
    SCATTERED_CLOUDS("scattered clouds", "드문드문 구름이 낀 하늘"),
    BROKEN_CLOUDS("broken clouds", "구름이 거의 없는 하늘"),
    OVERCAST_CLOUDS("overcast clouds", "구름으로 뒤덮인 흐린 하늘"),
    TORNADO("tornado", "토네이도"),
    TROPICAL_STORM("tropical storm", "태풍"),
    HURRICANE("hurricane", "허리케인"),
    COLD("cold", "한랭"),
    HOT("hot", "고온"),
    WINDY("windy", "바람부는"),
    HAIL("hail", "우박"),
    CALM("calm", "바람이 거의 없는"),
    LIGHT_BREEZE("light breeze", "약한 바람"),
    GENTLE_BREEZE("gentle breeze", "부드러운 바람"),
    MODERATE_BREEZE("moderate breeze", "중간 세기 바람"),
    FRESH_BREEZE("fresh breeze", "신선한 바람"),
    STRONG_BREEZE("strong breeze", "센 바람"),
    HIGH_WIN("high win", "돌풍에 가까운 센 바람"),
    GALE("gale", "돌풍"),
    SEVERE_GALE("severe gale", "심각한 돌풍"),
    STORM("storm", "폭풍"),
    VIOLENT_STORM("violent storm", "강한 폭풍");

    private String en;
    private String kr;

    DescriptionKo(String en, String kr) {
        this.en = en;
        this.kr = kr;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getKr() {
        return kr;
    }

    public void setKr(String kr) {
        this.kr = kr;
    }

    public static DescriptionKo value(String value) {
        for (DescriptionKo ko : DescriptionKo.values()) {
            if (ko.en.equals(value)) {
                return ko;
            }
        }
        return null;
    }
}
