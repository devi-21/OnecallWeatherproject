package com.api.models.weather;

import java.util.List;

public class OneCallResponse {
    public double lat;
    public double lon;
    public String timezone;
    public int timezone_offset;
    public Current current;
    public List<Hourly> hourly;
    public List<Daily> daily;

    public static class Current {
        public long dt;
        public double temp;
        public int humidity;
        public double wind_speed;
        public List<Weather> weather;
    }
    public static class Hourly {
        public long dt;
        public double temp;
        public double wind_speed;
        public List<Weather> weather;
    }
    public static class Daily {
        public long dt;
        public Temp temp;
        public int humidity;
        public double wind_speed;
        public List<Weather> weather;
    }
    public static class Temp { public double min; public double max; }
    public static class Weather { public int id; public String main; public String description; public String icon; }
}
