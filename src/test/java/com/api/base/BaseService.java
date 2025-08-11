package com.api.base;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;

import static io.restassured.RestAssured.given;

/**
 * Wrapper for Rest Assured.
 * BASE URI, Creating request, Handling response.
 */
public class BaseService {

    private static final String BASE_URL = "https://api.openweathermap.org";
    private final RequestSpecification requestSpecification;

    static {
        // Global request/response logging
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public BaseService() {
        this.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .build();
    }

    protected void setAuthToken(String token) {
        requestSpecification.header("Authorization", "Bearer " + token);
    }

    protected Response postRequest(Object payload, String endpoint) {
        return given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(endpoint);
    }

    protected Response putRequest(Object payload, String endpoint) {
        return given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(payload)
                .put(endpoint);
    }

    protected Response getRequest(String endpoint) {
        return given().spec(requestSpecification)
                .get(endpoint);
    }

    protected Response postRequest(String baseUrl, Object payload, String endpoint) {
        return given().spec(requestSpecification)
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(endpoint);
    }
}
