package br.com.dbccompany.utils;

import com.google.gson.Gson;

import br.com.dbccompany.data.changeless.LoginData;
import br.com.dbccompany.model.Login;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Auth {

    public String autenticacaoAdmin(){
        Login login = new Login();

        login.setEmail(Manipulation.getProp().getProperty("prop.email"));
        login.setSenha(Manipulation.getProp().getProperty("prop.password"));

        return
                given()
                    .contentType(ContentType.JSON)
                    .body(new Gson().toJson(login))
                .when()
                    .post(LoginData.SERVICE)
                .then()
                    .extract().asString()
                ;
    }

    public String autenticacaoGeral(String email, String senha){
        Login login = new Login();

        login.setEmail(email);
        login.setSenha(senha);

        return
                given()
                        .contentType(ContentType.JSON)
                        .body(new Gson().toJson(login))
                    .when()
                        .post(LoginData.SERVICE)
                    .then()
                        .extract().asString()
                ;
    }
}
