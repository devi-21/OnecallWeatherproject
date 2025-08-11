package com.api.tests;

import com.api.base.Config;
import com.api.services.WeatherService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;

public class DataAccuracyTests {
    private final WeatherService svc = new WeatherService();

    @Test(description = "Weather values within reasonable ranges for Melbourne")
    public void values_within_ranges() throws Exception {
        Response res = svc.getCurrentWeatherByLatLon(Config.MEL_LAT, Config.MEL_LON, "metric", "en");
        JsonPath jp = res.then().statusCode(200).extract().jsonPath();

        double temp = jp.getDouble("main.temp");
        int humidity = jp.getInt("main.humidity");
        double wind = jp.getDouble("wind.speed");

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream("baseline/CurrentWeatherResponse.json");
        JsonNode base = mapper.readTree(is);

        double tMin = base.path("allowedTempRangeC").path("min").asDouble();
        double tMax = base.path("allowedTempRangeC").path("max").asDouble();
        int hMin = base.path("allowedHumidityRange").path("min").asInt();
        int hMax = base.path("allowedHumidityRange").path("max").asInt();
        double wMin = base.path("allowedWindSpeedRange").path("min").asDouble();
        double wMax = base.path("allowedWindSpeedRange").path("max").asDouble();

        Assert.assertTrue(temp >= tMin && temp <= tMax, "Temp out of range: " + temp);
        Assert.assertTrue(humidity >= hMin && humidity <= hMax, "Humidity out of range: " + humidity);
        Assert.assertTrue(wind >= wMin && wind <= wMax, "Wind speed out of range: " + wind);
    }
}
