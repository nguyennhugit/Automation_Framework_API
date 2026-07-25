package com.api.practice.improve.api;

import com.api.login.LogInApi;
import com.api.login.UpdateProductApi;
import com.api.practice.dto.ProductDto;
import com.api.practice.dto.UserDto;
import com.api.practice.register.api.RandomUtil;
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
//        String body = "{ \"username\": \"test04nguyen\",\n" +
//                "    \"password\": \"test123\"\n" + "}";
        UserDto requestUpdateProductsID = UserDto.builder()
                .username("test04nguyen")
                .password("test123")
                .build();
//        ValidatableResponse response =
//                given()
//                        .baseUri("http://localhost:8080")
//                        .contentType(ContentType.JSON)
//                        .body(requestUpdateProductsID)
//                        .log().all()
//                        .when()
//                        .post("/api/auth/login")
//                        .then()
//                        .log().all()
//                        .statusCode(200);
        ValidatableResponse response = LogInApi.getInstance().logIn(requestUpdateProductsID, 200);
        token = response
                .extract()
                .jsonPath()
                .getString("token");
        Assert.assertNotNull(token);
    }

    @Test
    public void verifyUpdateProducts() {
        String productName = "product_" +RandomUtil.randomUsername();
        String productDescription = "testuser_" + RandomUtil.randomUsername();
//        String product = "{\n" +
//                "  \"name\": \"IPhone " + productName + "\",\n" +
//                "  \"description\": \"Máy quạt senko " + productDescription + "\",\n" +
//                "  \"price\": 390000001,\n" +
//                "  \"quantity\": 50\n" +
//                "}";
        ProductDto requestUpdateProducts = ProductDto.builder()
                .name(productName)
                .description(productDescription)
                .price(390000001)
                .quantity(50)
                .build();


//        ValidatableResponse response =
//                given()
//                        .baseUri("http://localhost:8080")
//                        .header("Authorization", "Bearer " + token)
//                        .body(requestUpdateProducts)
//                        .contentType(ContentType.JSON)
//                        .log().all()
//                        .when()
//                        .put("/api/products/11")
//                        .then()
//                        .log().all()
//                        .statusCode(200);
        ValidatableResponse response =
                UpdateProductApi.getInstance().updateProducts(token, 33, requestUpdateProducts, 200);
        String name = response.extract().jsonPath().getString("name");
        String description = response.extract().jsonPath().getString("description");
        int quantity = response.extract().jsonPath().getInt("quantity");
        double price = response.extract().jsonPath().getDouble("price");

        Assert.assertEquals(name, requestUpdateProducts.getName());
        Assert.assertEquals(description, requestUpdateProducts.getDescription());
        Assert.assertEquals(quantity, 50);
        Assert.assertEquals(price, 390000001);
    }
}
