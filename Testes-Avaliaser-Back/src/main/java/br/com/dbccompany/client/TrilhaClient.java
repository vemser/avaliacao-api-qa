package br.com.dbccompany.client;

import br.com.dbccompany.data.changeless.TrilhaData;
import br.com.dbccompany.specs.AuthSpecs;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TrilhaClient {

    public Response cadastrar(String cadastroAtividade) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .body(cadastroAtividade)
                .when()
                    .post(TrilhaData.CADASTRO_TRILHA)
                ;
    }

    public Response vincularTrilhaAluno(Integer idTrilha, String login, String vinculacao) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam("login", login)
                    .queryParam("idTrilha", idTrilha)
                    .body(vinculacao)
                .when()
                    .post(TrilhaData.VINCULAR_TRILHA_AO_ALUNO)
                ;
    }

    public Response vincularTrilhaInstrutor(Integer idTrilha, String login, String vinculacao) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam("login", login)
                    .queryParam("idTrilha", idTrilha)
                    .body(vinculacao)
                .when()
                    .post(TrilhaData.VINCULAR_TRILHA_AO_INSTRUTOR)
                ;
    }
}
