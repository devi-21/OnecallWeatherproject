package com.api.services;

import com.api.base.BaseService;
import com.api.base.Config;
import io.restassured.response.Response;


public class WeatherService extends BaseService {

    private static final String CURRENT_WEATHER_PATH = "/data/2.5/weather";
    private static final String ONE_CALL_PATH = "/data/3.0/onecall";

    // Helper to call arbitrary path (for negative tests)
    public Response getRaw(String path) { return getRequest(path); }

    // Free tier - current weather by city
    public Response getCurrentWeatherByCity(String cityAndCountry) {
        String endpoint = CURRENT_WEATHER_PATH + "?q=" + cityAndCountry + "&appid=" + Config.apiKey();
        return getRequest(endpoint);
    }

    // Free tier - current weather by lat/lon
    public Response getCurrentWeatherByLatLon(String lat, String lon, String units, String lang) {
        StringBuilder sb = new StringBuilder(CURRENT_WEATHER_PATH)
                .append("?lat=").append(lat)
                .append("&lon=").append(lon)
                .append("&appid=").append(Config.apiKey());
        if (units != null) sb.append("&units=").append(units);
        if (lang != null) sb.append("&lang=").append(lang);
        return getRequest(sb.toString());
    }

    // One Call 3.0 - current + minutely + hourly + daily (requires paid plan)
    public Response getOneCall(String lat, String lon, String units, String lang, String exclude) {
        StringBuilder sb = new StringBuilder(ONE_CALL_PATH)
                .append("?lat=").append(lat)
                .append("&lon=").append(lon)
                .append("&appid=").append(Config.apiKey());
        if (units != null) sb.append("&units=").append(units);
        if (lang != null) sb.append("&lang=").append(lang);
        if (exclude != null) sb.append("&exclude=").append(exclude);
        return getRequest(sb.toString());
    }
}
