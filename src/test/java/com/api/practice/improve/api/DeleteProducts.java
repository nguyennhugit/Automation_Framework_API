package com.api.practice.improve.api;

import com.api.login.DeleteProductApi;
import com.api.login.LogInApi;
import com.api.practice.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteProducts {
    String token;
    @BeforeMethod
    public void verifyLoginUser(){
//        String body = "{\n" +
//                "    \"username\": \"test04nguyen\",\n" +
//                "    \"password\": \"test123\"\n" +
//                "}";
        UserDto requestDeleteProducts = UserDto.builder()
                .username("test04nguyen")
                .password("test123")
                .build();
//        ValidatableResponse response =
//                given()
//                        .baseUri("http://localhost:8080")
//                        .contentType(ContentType.JSON)
//                        .body(requestDeleteProducts)
//                        .log().all()
//                        .when()
//                        .post("/api/auth/login")
//                        .then()
//                        .log().all()
//                        .statusCode(200);
        ValidatableResponse response = LogInApi.getInstance().logIn(requestDeleteProducts, 200);
        token = response
                .extract()
                .jsonPath()
                .getString("token");
        Assert.assertNotNull(token);
    }
    @Test
    public void verifyDeleteProducts(){
//        ValidatableResponse response =
//                given()
//                        .baseUri("http://localhost:8080")
//                        .header("Authorization","Bearer "+token)
//                        .contentType(ContentType.JSON)
//                        .log().all()
//                        .when()
//                        .delete("/api/products/11")
//                        .then()
//                        .log().all()
//                        .statusCode(200);
        ValidatableResponse response = DeleteProductApi.getInstance().deleteProducts(token, 32, 200);

    }
}
