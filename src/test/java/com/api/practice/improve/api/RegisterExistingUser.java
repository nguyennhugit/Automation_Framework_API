package com.api.practice.improve.api;

import com.api.login.LogInApi;
import com.api.practice.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegisterExistingUser {
    @Test
    public void verifyRegister01() {
//        String body = "{\n" +
//                "    \"username\": \"test05nguyen\",\n" +
//                "    \"password\": \"test123\"\n" +
//                "}";
        UserDto requestRegisterExistUser = UserDto.builder()
                .username("test05nguyen")
                .password("test123")
                .build();

        ValidatableResponse response = LogInApi.getInstance().registerAccount(requestRegisterExistUser, 400);

//        ValidatableResponse response = given()
//                .baseUri("http://localhost:8080")
//                .body(requestRegisterExistUser)
//                .log().all()
//                .when()
//                .contentType(ContentType.JSON)
//                .post("/api/auth/register")
//                .then()
//                .log().all()
//                .statusCode(400);

        String message = response
                .extract()
                .jsonPath()
                .getString("message");
        Assert.assertEquals(message, "Username already exists");
        System.out.println(message);
    }
}
