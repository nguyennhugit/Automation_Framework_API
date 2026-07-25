package com.api.practice.improve.api;

import com.api.login.CreateProductApi;
import com.api.login.LogInApi;
import com.api.practice.dto.ProductDto;
import com.api.practice.dto.UserDto;
import com.api.practice.register.api.RandomUtil;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class CreateProductsApi {
    String token;
    @BeforeMethod
    public void verifyLoginUser(){
//        String body = "{\n" +
//                "    \"username\": \"test04nguyen\",\n" +
//                "    \"password\": \"test123\"\n" +
//                "}";

        UserDto loginUserRequest = UserDto.builder()
                .username("test04nguyen")
                .password("test123")
                .build();
//        ValidatableResponse response =
//                given()
//                        .baseUri("http://localhost:8080")
//                        .body(loginUserRequest)
//                        .log().all()
//                        .when()
//                        .contentType(ContentType.JSON)
//                        .post("/api/auth/login")
//                        .then()
//                        .log().all()
//                        .statusCode(200);
        ValidatableResponse response = LogInApi.getInstance().logIn(loginUserRequest, 200);
        token= response
                .extract()
                .jsonPath()
                .getString("token");
        Assert.assertNotNull(token);

    }
    @Test
    public void verifycreateProduct(){
        String productName = "product_"+ RandomUtil.randomUsername();
        String productDescription = "test_"+RandomUtil.randomUsername();
//        String body = "{\"name\": \"iPhone test06\",\n" +
//                "  \"description\": \"Điện thoại iPhone mới nhất\",\n" +
//                "  \"price\": 800000 ,\n" +
//                "  \"quantity\": 40 }";

        ProductDto requestProduct = ProductDto.builder()
                .name(productName)
                .description(productDescription)
                .price(800000)
                .quantity(40)
                .build();

//        ValidatableResponse response =
//                given()
//                        .baseUri("http://localhost:8080")
//                        .header("Authorization", "Bearer "+token)
//                        .contentType(ContentType.JSON)
//                        .body(requestProduct)
//                        .log().all()
//                        .when()
//                        .post("/api/products")
//                        .then()
//                        .log().all()
//                        .statusCode(201);
        ValidatableResponse response =
                CreateProductApi.getInstance().createProducts(token, requestProduct, 201);

        String name = response.extract().jsonPath().getString("name");
        String description = response.extract().jsonPath().getString("description");
        int quantity = response.extract().jsonPath().getInt("quantity");
        double price = response.extract().jsonPath().getDouble("price");

        Assert.assertEquals(name, requestProduct.getName());
        Assert.assertEquals(description, requestProduct.getDescription());
        Assert.assertEquals(quantity, requestProduct.getQuantity());
        Assert.assertEquals(price, requestProduct.getPrice());
//        Assert.assertEquals(name, "iPhone test06");

    }
}
