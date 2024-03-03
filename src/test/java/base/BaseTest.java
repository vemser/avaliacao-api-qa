package base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://avaliacao-back-hml.onrender.com";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
