package com.api.practice.register.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegisterSuccessUser {
    @Test
    public void verifyRegister02() {
        String username = "testuser_" +RandomUtil.randomUsername();

        String body = "{\n" +
                "    \"username\": \"" + username + "\",\n" +
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
                .statusCode(201);

        String token = response
                .extract()
                .jsonPath()
                .getString("token");
        String type = response
                .extract()
                .jsonPath()
                .getString("type");
        String usernameRespone = response
                .extract()
                .jsonPath()
                .getString("username");
        Assert.assertNotNull(token);
        Assert.assertEquals(type, "Bearer");
        Assert.assertEquals(usernameRespone, username);
    }
}
