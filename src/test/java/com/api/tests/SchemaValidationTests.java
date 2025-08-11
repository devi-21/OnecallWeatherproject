package com.api.tests;

import com.api.base.Config;
import com.api.services.WeatherService;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class SchemaValidationTests {
    private final WeatherService svc = new WeatherService();

    @Test(description = "Current weather JSON matches basic schema")
    public void current_weather_schema_ok() {
        Response res = svc.getCurrentWeatherByLatLon(Config.MEL_LAT, Config.MEL_LON, "metric", "en");
        res.then().statusCode(200)
           .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/current_weather_schema.json"));
    }

    @Test(description = "One Call 3.0 JSON matches schema (disabled by default)")
    public void one_call_schema_ok() {
        if (!Config.ENABLE_ONECALL) throw new SkipException("One Call 3.0 disabled in Config");
        Response res = svc.getOneCall(Config.MEL_LAT, Config.MEL_LON, "metric", "en", null);
        res.then().statusCode(200)
           .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/one_call_schema.json"));
    }
}
