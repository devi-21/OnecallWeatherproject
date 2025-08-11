package com.api.models.weather;

import java.util.List;

public class CurrentWeatherResponse {
    public Coord coord;
    public List<Weather> weather;
    public Main main;
    public Wind wind;
    public Clouds clouds;
    public Sys sys;
    public String name;
    public int timezone;
    public long dt;

    public static class Coord { public double lon; public double lat; }
    public static class Weather { public int id; public String main; public String description; public String icon; }
    public static class Main { public double temp; public double feels_like; public double temp_min; public double temp_max; public int pressure; public int humidity; }
    public static class Wind { public double speed; public int deg; public Double gust; }
    public static class Clouds { public int all; }
    public static class Sys { public String country; public long sunrise; public long sunset; }
}
