package com.elena.tests;

import com.elena.lombok.UserCreated;
import com.elena.lombok.UserData;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static com.elena.specs.Specs.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RestApiTests extends BaseTestAPI {
    String bodyCreateUser = "{ \"name\": \"morpheus\", \"job\": \"leader\" }",
            bodyUpdateUserJob = "{ \"name\": \"morpheus\", \"job\": \"zion resident\"}",
            bodyRegistrationPassed = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }",
            getBodyRegistrationFailed = "{ \"email\": \"sydney@fife\" }",
            nameCreateUser = "morpheus",
            jobCreateUser = "leader",
            jobUpdateUser = "zion resident",
            token = "QpwL5tke4Pnpja7X4",
            listUserEmail = "tobias.funke@reqres.in",
            error= "Missing password";

    @Test
    void listUser1(){
        given()
                .spec(request)
                .when()
                .get("/users?page=2")
                .then()
                .log().body()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()[2]",
                        is(listUserEmail));
 //                       hasItem(listUserEmail));
    }

    @Test
    void listUser2(){
        given()
                .spec(request)
                .when()
                .get("/users?page=2")
                .then()
                .log().body()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                       hasItem(listUserEmail));
    }

    @Test
    @Description("Check it is possible to create user")
    void createUserTestUseSpecAndLombok() {
       UserData data = given()
                .spec(request)
                .body(bodyCreateUser)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .spec(response201)
                .extract().as(UserData.class);
        assertEquals(nameCreateUser, data.getName());
        assertEquals(jobCreateUser,data.getJob());
    }

    @Test
    @Description("Check it is possible update job via put request")
    void updateUserTestPutSpecAndLombok() {
        UserData data = (UserData) given()
                .spec(request)
                .body(bodyUpdateUserJob)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .extract().as(UserData.class);
        assertEquals(nameCreateUser, data.getName());
        assertEquals(jobUpdateUser,data.getJob());
    }

    @Test
    @Description("Check it is possible update job via patch request")
    void updateUserTestPatchSpecAndLombok() {
       UserData data = (UserData) given()
                .spec(request)
                .body(bodyUpdateUserJob)
                .when()
                .patch("/users/2")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .extract().as(UserData.class);
        assertEquals(nameCreateUser, data.getName());
        assertEquals(jobUpdateUser,data.getJob());
    }
    @Test
    @Description("Check it is possible to create user")
    void postRegistrationUserTestSpecAndLombok() {
    UserCreated data = (UserCreated) given()
                .spec(request)
                .body(bodyRegistrationPassed)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .extract().as(UserCreated.class);
    assertEquals(token,data.getToken());
    assertEquals(4,data.getId());
    }
    @Test
    @Description("Check failed user registration")
    void postRegistrationFailedUserTestSpec() {
        given()
                .spec(request)
                .body(getBodyRegistrationFailed)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .spec(response400)
                .body("error", is(error));
    }

    @Test
    @Description("Check it is possible to delete user")
    void deleteUserTestSpec() {
        given()
                .spec(request)
                .when()
                .delete("/users/2")
                .then()
                .log().all()
                .spec(response204);

    }
}
