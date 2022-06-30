package com.elena.tests;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTestAPI {
    @BeforeAll
    static void StartUp() {
        RestAssured.baseURI = "https://reqres.in/api";
    }
}
