package com.api.login;
import com.api.practice.dto.UserDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class LogInApi {
    private static final String API_URL = "http://localhost:8080";
    private static final String REGISTER_API_URL = API_URL + "/api/auth/register";
    private static final String LOGIN_API_URL = API_URL + "/api/auth/login";
    private static LogInApi instance;

    private LogInApi() {
    }

    public static LogInApi getInstance() {
        if (instance == null) {
            instance = new LogInApi();
//nếu đối tượng chưa có thì tạo mới đối tượng
        }
        return instance;
//        nếu đối tượng đã được tạo thì sử dụng lại nó và k tạo mới
    }

    public ValidatableResponse registerAccount(UserDto userDto, int statusCode) {
     return given()
                .baseUri(API_URL)
                .body(userDto)
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .post(REGISTER_API_URL)
                .then()
                .log().all()
                .statusCode(statusCode);
    }
    public ValidatableResponse logIn(UserDto userDto, int statusCode) {
        return given()
                .baseUri(API_URL)
                .body(userDto)
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .post(LOGIN_API_URL)
                .then()
                .log().all()
                .statusCode(statusCode);
    }
}