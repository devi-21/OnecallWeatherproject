package com.api.base;

public class Config {
	public static final String API_KEY = "06751f6b82724b7ae7cec1e290dda84a"; // provided
    public static final boolean ENABLE_ONECALL = false; // flip to true when you have One Call 3.0 subscription

    // Default Melbourne coords
    public static final String MEL_LAT = "-37.8136";
    public static final String MEL_LON = "144.9631";

    public static String apiKey() {
        return API_KEY;
    }
}
