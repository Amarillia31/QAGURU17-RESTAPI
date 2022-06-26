package com.elena;
import static org.hamcrest.core.Is.is;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

public class RestApiTests extends BaseTestAPI {
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
    @Description("Check it is possible to create user")
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
    void deleteUserTest() {
        given()
                .when()
                .delete("/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }
}
