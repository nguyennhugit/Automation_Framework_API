package com.api.login;

import com.api.practice.dto.ProductDto;
import com.api.practice.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class DeleteProductApi {
    private static final String API_URL3 = "http://localhost:8080";
    private static final String LOGIN_API_URL = API_URL3 + "/api/auth/login";
    private static final  String DELET_API_URL = API_URL3 + "/api/products/";
    private static DeleteProductApi instance;
    private String token;

    private  DeleteProductApi(){
    }
    public static  DeleteProductApi getInstance(){
        if (instance==null){
            instance= new DeleteProductApi();
        }
        return instance;
    }
    public void setToken(String token){
        this.token=token;
    }
    public String getToken(){
        return token;
    }
    public ValidatableResponse logIn(UserDto userDto, int statusCode){
        return given()
                .baseUri(API_URL3)
                .body(userDto)
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .post(LOGIN_API_URL)
                .then()
                .log().all()
                .statusCode(statusCode);
    }
    public ValidatableResponse deleteProducts(String token, int productId, int statusCode){
        return given()
                .baseUri(API_URL3)
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .delete(DELET_API_URL + productId)
                .then()
                .log().all()
                .statusCode(statusCode);

    }
}
