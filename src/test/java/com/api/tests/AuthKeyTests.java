package com.api.tests;

import com.api.base.Config;
import com.api.services.WeatherService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthKeyTests {

    private final WeatherService svc = new WeatherService();

    @Test(description = "Valid API key returns 200 for /data/2.5/weather")
    public void validKey() {
        Response res = svc.getCurrentWeatherByLatLon(Config.MEL_LAT, Config.MEL_LON, "metric", "en");
        System.out.println(res.asPrettyString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(description = "Invalid API key returns 401")
    public void invalidKey() {
        Response res = svc.getRaw("/data/2.5/weather?lat=" + Config.MEL_LAT + "&lon=" + Config.MEL_LON + "&appid=bad-key");
        System.out.println(res.asPrettyString());
        Assert.assertEquals(res.getStatusCode(), 401);
    }

    @Test(description = "Missing API key returns 401")
    public void missingKey() {
        Response res = svc.getRaw("/data/2.5/weather?lat=" + Config.MEL_LAT + "&lon=" + Config.MEL_LON);
        System.out.println(res.asPrettyString());
        Assert.assertEquals(res.getStatusCode(), 401);
    }
}
