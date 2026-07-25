package com.api.practice.improve.api;

import com.api.login.LogInApi;
import com.api.practice.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LogInUser {
    @Test
    public void verifyLoginUser() {
//        String body = "{\n" +
//                "    \"username\": \"test04nguyen\",\n" +
//                "    \"password\": \"test123\"\n" +
//                "}";
        UserDto loginUserSuccess = UserDto.builder()
                .username("test04nguyen")
                .password("test123")
                .build();
//        ValidatableResponse response =
//                given()
//                        .baseUri("http://localhost:8080")
//                        .body(loginUserSuccess)
//                        .log().all()
//                        .when()
//                        .contentType(ContentType.JSON)
//                        .post("/api/auth/login")
//                        .then()
//                        .log().all()
//                        .statusCode(200);
        ValidatableResponse response = LogInApi.getInstance().logIn(loginUserSuccess, 200);
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
        Assert.assertEquals(username, loginUserSuccess.getUsername());

    }
    }
