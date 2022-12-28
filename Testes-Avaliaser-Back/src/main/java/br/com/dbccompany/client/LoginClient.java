package br.com.dbccompany.client;

import br.com.dbccompany.data.changeless.LoginData;
import br.com.dbccompany.data.changeless.Values;
import br.com.dbccompany.utils.Auth;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginClient {

    public Response logar(String login) {

        return
                given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(login)
                .when()
                    .post(LoginData.SERVICE)
                ;
    }

    public Response usuarioLogado(String email, String senha) {

        Auth auth = new Auth();
        return
                given()
                    .log().all()
                    .header(Values.AUTHORIZATION, auth.autenticacaoGeral(email, senha))
                .when()
                    .get(LoginData.BUSCAR_USUARIO_LOGADO)
                ;
    }
}
