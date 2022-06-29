package com.elena.tests;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTestAPI {
//    String bodyCreateUser = "{ \"name\": \"morpheus\", \"job\": \"leader\" }",
//            bodyUpdateUserJob = "{ \"name\": \"morpheus\", \"job\": \"zion resident\"}",
//            bodyRegistrationPassed = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }",
//            getBodyRegistrationFailed = "{ \"email\": \"sydney@fife\" }",
//            nameCreateUser = "morpheus",
//            jobCreateUser = "leader",
//            jobUpdateUser = "zion resident",
//            token = "QpwL5tke4Pnpja7X4",
//            error= "Missing password";
    @BeforeAll
    static void StartUp() {
        RestAssured.baseURI = "https://reqres.in/api";
    }
}
