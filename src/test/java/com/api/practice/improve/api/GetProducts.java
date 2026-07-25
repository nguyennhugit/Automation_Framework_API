package com.api.practice.improve.api;

import com.api.login.LogInApi;
import com.api.practice.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetProducts {
    String token;
    @BeforeMethod
    public void verifyLoginUser(){
//        String body = "{ \"username\": \"test04nguyen\",\n" +
//                "    \"password\": \"test123\"\n" +"}";
        UserDto requestGetProducts = UserDto.builder()
                .username("test04nguyen")
                .password("test123")
                .build();
//        ValidatableResponse response =
//                given()
//                        .baseUri("http://localhost:8080")
//                        .body(requestGetProducts)
//                        .log().all()
//                        .when()
//                        .contentType(ContentType.JSON)
//                        .post("/api/auth/login")
//                        .then()
//                        .log().all()
//                        .statusCode(200);
        ValidatableResponse response = LogInApi.getInstance().logIn(requestGetProducts , 200);
                token = response
                        .extract()
                        .jsonPath()
                        .getString("token");
        Assert.assertNotNull(token);
    }
    @Test
    public void verifyGetProducts(){
        ValidatableResponse response=
                given()
                        .baseUri("http://localhost:8080")
                        .header("Authorization", "Bearer " +token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get("/api/products")
                        .then()
                        .log().all()
                        .statusCode(200);

    }
}
