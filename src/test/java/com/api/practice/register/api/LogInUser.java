package com.api.practice.register.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LogInUser {
    @Test
    public void verifyLoginUser() {
        String body = "{\n" +
                "    \"username\": \"test04nguyen\",\n" +
                "    \"password\": \"test123\"\n" +
                "}";
        ValidatableResponse response =
                given()
                        .baseUri("http://localhost:8080")
                        .body(body)
                        .log().all()
                        .when()
                        .contentType(ContentType.JSON)
                        .post("/api/auth/login")
                        .then()
                        .log().all()
                        .statusCode(200);
        String token = response
                .extract()
                .jsonPath()
                .getString("token");
        String type = response
                .extract()
                .jsonPath()
                .getString("type");
        String username = response
                .extract()
                .jsonPath()
                .getString("username");
        Assert.assertNotNull(token);
        Assert.assertEquals(type, "Bearer");
        Assert.assertEquals(username, "test04nguyen");

    }
    }
