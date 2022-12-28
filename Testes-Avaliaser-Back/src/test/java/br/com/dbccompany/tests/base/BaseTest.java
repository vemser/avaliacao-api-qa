package br.com.dbccompany.tests.base;

import br.com.dbccompany.utils.Utils;

import io.restassured.RestAssured;

import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = Utils.getBaseUrl();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}