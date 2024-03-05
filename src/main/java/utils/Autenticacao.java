package utils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

import static io.restassured.RestAssured.given;

public class Autenticacao {
    static String baseURL = "https://usuario-back.onrender.com/usuario/login";

    public static String token() {
        ConfigProperties.initializePropertyFile();
        String username = ConfigProperties.properties.getProperty("admin_username");
        String password = ConfigProperties.properties.getProperty("admin_password");

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