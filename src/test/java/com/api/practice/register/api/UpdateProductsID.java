package com.api.practice.register.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateProductsID {
   String token;

    @BeforeMethod
    public void verifyLoginUser() {
        String body = "{ \"username\": \"test04nguyen\",\n" +
                "    \"password\": \"test123\"\n" + "}";
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
    public void verifyUpdateProducts() {
        String productDescription = "testuser_" +RandomUtil.randomUsername();
        String productName = "product_" +RandomUtil.randomUsername();
        String product = "{\n" +
                "  \"name\": \"IPhone " + productName + "\",\n" +
                "  \"description\": \"Máy quạt senko " + productDescription + "\",\n" +
                "  \"price\": 390000001,\n" +
                "  \"quantity\": 50\n" +
                "}";

        ValidatableResponse response =
                given()
                        .baseUri("http://localhost:8080")
                        .header("Authorization", "Bearer " + token)
                        .body(product)
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .put("/api/products/22")
                        .then()
                        .log().all()
                        .statusCode(200);
        String name = response.extract().jsonPath().getString("name");
        String description = response.extract().jsonPath().getString("description");
        int quantity = response.extract().jsonPath().getInt("quantity");
        double price = response.extract().jsonPath().getDouble("price");

        Assert.assertEquals(name, "IPhone "+ productName);
        Assert.assertEquals(description, "Máy quạt senko " + productDescription);
        Assert.assertEquals(quantity, 50);
        Assert.assertEquals(price, 390000001);
    }
}
