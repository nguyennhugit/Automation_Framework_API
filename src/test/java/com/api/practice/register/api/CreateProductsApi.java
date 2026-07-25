package com.api.practice.register.api;

import com.beust.ah.A;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class CreateProductsApi {
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
                        .body(body)
                        .when()
                        .contentType(ContentType.JSON)
                        .post("/api/auth/login")
                        .then()
                        .log().all()
                        .statusCode(200);
        token= response
                .extract()
                .jsonPath()
                .getString("token");
        Assert.assertNotNull(token);

    }
    @Test
    public void verifycreateProduct(){
        String body = "{\"name\": \"iPhone test06\",\n" +
                "  \"description\": \"Điện thoại iPhone mới nhất\",\n" +
                "  \"price\": 800000 ,\n" +
                "  \"quantity\": 40 }";
        ValidatableResponse response =
                given()
                        .baseUri("http://localhost:8080")
                        .header("Authorization", "Bearer "+token)
                        .contentType(ContentType.JSON)
                        .body(body)
                        .log().all()
                        .when()
                        .post("/api/products")
                        .then()
                        .log().all()
                        .statusCode(201);
                String name = response
                        .extract()
                        .jsonPath()
                        .getString("name");
        Assert.assertEquals(name, "iPhone test06");

    }
}
