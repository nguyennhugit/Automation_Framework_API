package com.api.practice.register.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetProductsDetailbyID {
    String token;

    @BeforeMethod
    public void VerifyLoginUser(){
        String body = "{ \"username\": \"test04nguyen\",\n" +
                "    \"password\": \"test123\"\n" +"}";
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
        token = response
                .extract()
                .jsonPath()
                .getString("token");
        Assert.assertNotNull(token);
    }
    @Test
    public void verifyGetProductsbyID(){
        ValidatableResponse response =
                given()
                        .baseUri("http://localhost:8080")
                        .header("Authorization","Bearer "+token)
                        .contentType(ContentType.JSON)
                        .pathParam("id",11)
                        .log().all()
                        .when()
                        .get("/api/products/{id}")
                        .then()
                        .log().all()
                        .statusCode(200);
    }

}
