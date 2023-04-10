package utils;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Autenticacao {
static String baseURL = "http://vemser-dbc.dbccompany.com.br:39000/vemser/usuario-back/usuario/login";
@Test
public static String token() {
    String token =
            given()
                    .contentType(ContentType.JSON)
                    .body("""
                        {
                         "username": "%s",
                         "password": "%s" 
                        }
                        """.formatted(System.getenv("DBC_USER"), System.getenv("DBC_PASSWORD")))
                    .when()
                    .post(baseURL)
                    .then()
                    .extract().asString();
    System.out.println(token);
    return token;
}
}