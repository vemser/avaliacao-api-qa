package br.com.dbccompany.client;

import br.com.dbccompany.data.changeless.*;
import br.com.dbccompany.specs.AuthSpecs;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AtividadeClient {
    public Response cadastrar(Integer idModulo, Integer idTrilha, String cadastroAtividade) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(ModuloData.ID_MODULO, idModulo)
                    .queryParam(TrilhaData.ID_TRILHA, idTrilha)
                    .body(cadastroAtividade)
                .when()
                    .post(AtividadeData.CADASTRO_ATIVIDADE)
                ;
    }

    public Response entregarAtividade(Integer idAtividade) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                .when()
                    .put(AtividadeData.ENTREGAR_ATIVIDADE + idAtividade)
                ;
    }

    public Response buscarAtividadePorId(Integer idAtividade) {
        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(AtividadeData.ID_ATIVIDADE, idAtividade)
                .when()
                    .get(AtividadeData.BUSCAR_ATIVIDADE_POR_ID)
                ;
    }
}
