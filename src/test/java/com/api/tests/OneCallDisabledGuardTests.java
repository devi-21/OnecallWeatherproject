package com.api.tests;

import com.api.base.Config;
import com.api.services.WeatherService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class OneCallDisabledGuardTests {
    private final WeatherService svc = new WeatherService();

    @Test(description = "One Call test is skipped unless enabled")
    public void onecall_skipped_by_default() {
        if (!Config.ENABLE_ONECALL) throw new SkipException("One Call 3.0 disabled. Set ENABLE_ONECALL=true in Config.");
        Response res = svc.getOneCall(Config.MEL_LAT, Config.MEL_LON, "metric", "en", "minutely,hourly");
        System.out.println(res.asPrettyString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}
