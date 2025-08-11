package com.api.tests;

import com.api.base.Config;
import com.api.services.WeatherService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParamHandlingTests {
    private final WeatherService svc = new WeatherService();

    @Test(description = "Missing lat/lon returns 400 for One Call (when enabled)")
    public void onecall_missing_params_400() {
        if (!Config.ENABLE_ONECALL) { System.out.println("One Call disabled"); return; }
        Response res = svc.getRaw("/data/3.0/onecall?appid=" + Config.apiKey());
        System.out.println(res.asPrettyString());
        Assert.assertEquals(res.getStatusCode(), 400);
    }

    @Test(description = "Boundary lat > 90 returns 400 for One Call (when enabled)")
    public void onecall_boundary_lat_400() {
        if (!Config.ENABLE_ONECALL) { System.out.println("One Call disabled"); return; }
        Response res = svc.getOneCall("91", Config.MEL_LON, "metric", "en", null);
        System.out.println(res.asPrettyString());
        Assert.assertEquals(res.getStatusCode(), 400);
    }

    @Test(description = "Extra params ignored for current weather and still return 200")
    public void weather_extra_params_ignored_200() {
        Response res = svc.getRaw("/data/2.5/weather?lat=" + Config.MEL_LAT + "&lon=" + Config.MEL_LON + "&appid=" + Config.apiKey() + "&foo=bar");
        System.out.println(res.asPrettyString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}
