package com.api.tests;

import com.api.base.Config;
import com.api.services.WeatherService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StatusCodeTests {
    private final WeatherService svc = new WeatherService();

    @Test(description = "Valid call returns 200")
    public void weather_valid() {
        Response res = svc.getCurrentWeatherByLatLon(Config.MEL_LAT, Config.MEL_LON, "metric", "en");
        System.out.println(res.asPrettyString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(description = "Wrong path returns 404")
    public void weather_wrong_path() {
    		String invalidPath = "/data/2.5/not-real-endpoint?appid=" + Config.apiKey();
        Response res = svc.getRaw(invalidPath);
        System.out.println(res.asPrettyString());
        Assert.assertEquals(res.getStatusCode(), 404);
    }
}
