package com.api.login;

import com.api.practice.dto.ProductDto;
import com.api.practice.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UpdateProductApi {
    private static final String API_URL2 = "http://localhost:8080";
    private static final String LOGIN_API_URL = API_URL2 + "/api/auth/login";
    private  static final  String UPDATEPRODUCT_URL = API_URL2 + "/api/products/";
    private static UpdateProductApi instance;
    private String token;

    private UpdateProductApi(){
    }
    public static UpdateProductApi getInstance(){
        if (instance== null){
            instance = new UpdateProductApi();
        }
        return instance;
    }
    public void setToken(String token){
        this.token= token;
    }
    public String getToken(){
        return  token;
    }
    public ValidatableResponse logIn(UserDto userDto, int statusCode){
        return given()
                .baseUri(API_URL2)
                .body(userDto)
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .post(LOGIN_API_URL)
                .then()
                .log().all()
                .statusCode(statusCode);
    }
    public ValidatableResponse updateProducts(String token, int productId, ProductDto requestUpdateProducts, int statusCode){
        return given()
                .baseUri(API_URL2)
                .header("Authorization", "Bearer " + token)
                .body(requestUpdateProducts)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .put(UPDATEPRODUCT_URL+ productId)
                .then()
                .log().all()
                .statusCode(statusCode);
    }

}
