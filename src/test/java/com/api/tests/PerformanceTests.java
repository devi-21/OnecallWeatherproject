package com.api.tests;

import com.api.base.Config;
import com.api.services.WeatherService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PerformanceTests {
    private final WeatherService svc = new WeatherService();

    @Test(description = "Current weather responds under 2 seconds")
    public void weather_under_2s() {
        long ms = svc.getCurrentWeatherByLatLon(Config.MEL_LAT, Config.MEL_LON, "metric", "en").time();
        System.out.println("Response time: " + ms + " ms");
        Assert.assertTrue(ms <= 2000, "Took " + ms + "ms (>2000ms)");
    }

    @Test(description = "Concurrent calls complete under 2 seconds each")
    public void weather_concurrent_under_2s_each() throws Exception {
        int threads = 5;
        ExecutorService exec = Executors.newFixedThreadPool(threads);
        List<Callable<Long>> tasks = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            tasks.add(() -> svc.getCurrentWeatherByLatLon(Config.MEL_LAT, Config.MEL_LON, "metric", "en").time());
        }
        List<Future<Long>> results = exec.invokeAll(tasks);
        exec.shutdown();
        for (Future<Long> f : results) {
            long t = f.get();
            System.out.println("One call time: " + t + " ms");
            Assert.assertTrue(t <= 2000, "One call took " + t + "ms (>2000ms)");
        }
    }
}
