package com.api.practice.register.api;

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
        String body = "{\n" +
                "    \"username\": \"test04nguyen\",\n" +
                "    \"password\": \"test123\"\n" +
                "}";
        ValidatableResponse response =
                given()
                        .baseUri("http://localhost:8080")
                        .contentType(ContentType.JSON)
                        .body(body)
                        .log().all()
                        .when()
                        .post("/api/auth/login")
                        .then()
                        .log().all()
                        .statusCode(200);
        token = response
                .extract()
                .jsonPath()
                .getString("token");
        Assert.assertNotNull(token);
    }
    @Test
    public void verifyDeleteProducts(){
        ValidatableResponse response =
                given()
                        .baseUri("http://localhost:8080")
                        .header("Authorization","Bearer "+token)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .delete("/api/products/20")
                        .then()
                        .log().all()
                        .statusCode(200);

    }
}
