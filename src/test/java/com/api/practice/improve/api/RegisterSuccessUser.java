package com.api.practice.improve.api;

import com.api.login.LogInApi;
import com.api.practice.dto.UserDto;
import com.api.practice.register.api.RandomUtil;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegisterSuccessUser {
    @Test
    public void verifyRegister02() {
        String username1 = "testuser_" + RandomUtil.randomUsername();

//        String body = "{\n" +
//                "    \"username\": \"" + username + "\",\n" +
//                "    \"password\": \"test123\"\n" +
//                "}";
        UserDto registerUserRequest = UserDto.builder()
                .username(username1)
                .password("test123")
                .build();

//        ValidatableResponse response = given()
//                .baseUri("http://localhost:8080")
//                .body(registerUserRequest)
//                .log().all()
//                .when()
//                .contentType(ContentType.JSON)
//                .post("/api/auth/register")
//                .then()
//                .log().all()
//                .statusCode(201);
        ValidatableResponse response = LogInApi.getInstance().registerAccount(registerUserRequest, 201);


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
        Assert.assertEquals(usernameRespone, registerUserRequest.getUsername());
    }
}
