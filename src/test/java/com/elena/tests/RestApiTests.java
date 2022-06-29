package com.elena.tests;
import static org.hamcrest.core.Is.is;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

public class RestApiTests extends BaseTestAPI {
    String bodyCreateUser = "{ \"name\": \"morpheus\", \"job\": \"leader\" }",
            bodyUpdateUserJob = "{ \"name\": \"morpheus\", \"job\": \"zion resident\"}",
            bodyRegistrationPassed = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }",
            getBodyRegistrationFailed = "{ \"email\": \"sydney@fife\" }",
            nameCreateUser = "morpheus",
            jobCreateUser = "leader",
            jobUpdateUser = "zion resident",
            token = "QpwL5tke4Pnpja7X4",
            error= "Missing password";

    @Test
    @Description("Check it is possible to create user")
    void createUserTest() {
        given()
                .log().uri()
                .log().body()
                .body(bodyCreateUser)
                .contentType(JSON)
                .when()
                .post("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is(nameCreateUser))
                .body("job", is(jobCreateUser));
    }
    @Test
    @Description("Check it is possible update job via put request")
    void updateUserTestPut() {
        given()
                .log().uri()
                .log().body()
                .body(bodyUpdateUserJob)
                .contentType(JSON)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is(nameCreateUser))
                .body("job", is(jobUpdateUser));
    }
    @Test
    @Description("Check it is possible update job via patch request")
    void updateUserTestPatch() {
        given()
                .log().uri()
                .log().body()
                .body(bodyUpdateUserJob)
                .contentType(JSON)
                .when()
                .patch("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is(nameCreateUser))
                .body("job", is(jobUpdateUser));
    }
    @Test
    @Description("Check it is possible to create user")
    void postRegistrationUserTest() {
        given()
                .log().uri()
                .log().body()
                .body(bodyRegistrationPassed)
                .contentType(JSON)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is(token))
                .body("id", is(4));
    }
    @Test
    @Description("Check failed user registration")
    void postRegistrationFailedUserTest() {
        given()
                .log().uri()
                .log().body()
                .body(getBodyRegistrationFailed)
                .contentType(JSON)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is(error));
    }
    @Test
    @Description("Check it is possible to delete user")
    void deleteUserTest() {
        given()
                .when()
                .delete("/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }
}
