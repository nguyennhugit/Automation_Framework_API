package com.api.practice.register.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegisterExistingUser {
    @Test
    public void verifyRegister01() {
        String body = "{\n" +
                "    \"username\": \"test05nguyen\",\n" +
                "    \"password\": \"test123\"\n" +
                "}";

        ValidatableResponse response = given()
                .baseUri("http://localhost:8080")
                .body(body)
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .post("/api/auth/register")
                .then()
                .log().all()
                .statusCode(400);

        String message = response
                .extract()
                .jsonPath()
                .getString("message");
        Assert.assertEquals(message,"Username already exists");
        System.out.println(message);
    }
}
