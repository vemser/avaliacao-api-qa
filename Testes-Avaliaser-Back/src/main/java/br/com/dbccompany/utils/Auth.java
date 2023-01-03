package br.com.dbccompany.utils;

import br.com.dbccompany.data.changeless.LoginData;
import br.com.dbccompany.model.Login;
import com.google.gson.Gson;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Auth {

    public String autenticacao(){
        Login login = new Login();

        login.setUsername(Manipulation.getProp().getProperty("prop.email"));
        login.setPassword(Manipulation.getProp().getProperty("prop.password"));

        return
                given()
                    .contentType(ContentType.JSON)
                    .body(new Gson().toJson(login))
                .when()
                    .post(LoginData.LOGIN)
                .then()
                    .extract().asString()
                ;
    }
}
