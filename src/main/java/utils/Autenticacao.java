package utils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

import static io.restassured.RestAssured.given;

public class Autenticacao {
    static String baseURL = "http://vemser-dbc.dbccompany.com.br:39000/vemser/usuario-back/usuario/login";

    public static String token() {
        String username = "admin.teste";
        String password = "Jvs9^@B809lP";

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(new JSONObject()
                                .appendField("username", username)
                                .appendField("password", password)
                        )
                        .when()
                        .post(baseURL);

        return response.getBody().asString();
    }
}