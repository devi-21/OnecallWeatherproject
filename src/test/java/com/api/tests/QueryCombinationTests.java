package com.api.tests;

import com.api.base.Config;
import com.api.services.WeatherService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QueryCombinationTests {
    private final WeatherService svc = new WeatherService();

    @Test(description = "lat/lon with units and lang is accepted for current weather")
    public void weather_latlon_units_lang_200() {
        Response res = svc.getCurrentWeatherByLatLon(Config.MEL_LAT, Config.MEL_LON, "metric", "en");
        System.out.println(res.asPrettyString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(description = "One Call with exclude works (when enabled)")
    public void onecall_with_exclude_200() {
        if (!Config.ENABLE_ONECALL) { System.out.println("One Call disabled"); return; }
        Response res = svc.getOneCall(Config.MEL_LAT, Config.MEL_LON, "metric", "en", "hourly,minutely");
        System.out.println(res.asPrettyString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}
