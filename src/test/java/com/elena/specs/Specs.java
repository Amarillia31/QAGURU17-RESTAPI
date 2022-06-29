package com.elena.specs;

import com.elena.tests.BaseTestAPI;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;

public class Specs  {
    public static RequestSpecification request = with()
            .log().all()
            .contentType(ContentType.JSON);

}
