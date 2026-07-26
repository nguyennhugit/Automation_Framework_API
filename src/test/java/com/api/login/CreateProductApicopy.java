package com.api.login;

import com.api.practice.dto.ProductDto;
import com.api.practice.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CreateProductApicopy {

    private static final String API_URL1 = "http://localhost:8080";
    private static final String LOGIN_API_URL = API_URL1 + "/api/auth/login";
    private static final String CREATEPRODUCT_URL= API_URL1 + "/api/products";
private static final String CREATEPRODUCT_URL= API_URL1 + "/api/products";
    private static CreateProductApicopy instance;
    private String token;

    private CreateProductApicopy(){
    }
    public static CreateProductApicopy getInstance(){
        if (instance == null){
            instance= new CreateProductApicopy();
        }
        return instance;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public ValidatableResponse logIn(UserDto userDto, int statusCode) {
        return given()
                .baseUri(API_URL1)
                .body(userDto)
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .post(LOGIN_API_URL)
                .then()
                .log().all()
                .statusCode(statusCode);
    }
    public ValidatableResponse createProducts(String token,
                                              ProductDto requestProduct,
                                              int statusCode) {

        return given()
                .baseUri(API_URL1)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestProduct)
                .log().all()
                .when()
                .post(CREATEPRODUCT_URL)
                .then()
                .log().all()
                .statusCode(statusCode);
    }

}
