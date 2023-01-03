package br.com.dbccompany.client;

import br.com.dbccompany.data.changeless.ProgramaData;
import br.com.dbccompany.data.changeless.TrilhaData;
import br.com.dbccompany.data.changeless.Values;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TrilhaClient {

    public Response cadastrar(String trilha) {

        return
                given()
                        .log().all()
//                    .spec(AuthSpecs.requestSpec())
                        .contentType(ContentType.JSON)
                        .body(trilha)
                .when()
                        .post(TrilhaData.ENDPOINT_TRILHA)
                ;
    }

    public Response deletar(Integer idTrilha) {

        return
                given()
                        .log().all()
//                    .spec(AuthSpecs.requestSpec())
                        .contentType(ContentType.JSON)
                        .pathParam(TrilhaData.ID_TRILHA, idTrilha)
                .when()
                        .delete(TrilhaData.ENDPOINT_TRILHA+String.format("{%s}", TrilhaData.ID_TRILHA))
                ;
    }

    public Response atualizar(String trilha, Integer idTrilha) {

        return
                given()
                        .log().all()
//                    .spec(AuthSpecs.requestSpec())
                        .contentType(ContentType.JSON)
                        .pathParam(TrilhaData.ENDPOINT_TRILHA, idTrilha)
                        .body(trilha)
                .when()
                        .put(TrilhaData.ENDPOINT_TRILHA_UPDATE+String.format("{%s}", TrilhaData.ID_TRILHA))
                ;
    }

    public Response listarTrilha(Integer page, Integer size) {

        return
                given()
                        .log().all()
//                    .spec(AuthSpecs.requestSpec())
                        .contentType(ContentType.JSON)
                        .queryParam(Values.PAGE, page)
                        .queryParam(Values.SIZE, size)
                .when()
                        .get(TrilhaData.ENDPOINT_TRILHA_LISTA_TRILHA_PAGE)
                ;
    }

    public Response listarTrilhaPorNome(String nome) {

        return
                given()
                        .log().all()
//                    .spec(AuthSpecs.requestSpec())
                        .contentType(ContentType.JSON)
                        .queryParam(TrilhaData.NOME_TRILHA, nome)
                .when()
                        .get(TrilhaData.ENDPOINT_TRILHA_LISTA_TRILHA_PAGE)
                ;
    }

    public Response listarRankingTrilhaPorId(Integer idTrilha) {

        return
                given()
                        .log().all()
//                    .spec(AuthSpecs.requestSpec())
                        .contentType(ContentType.JSON)
                        .pathParam(TrilhaData.ENDPOINT_TRILHA, idTrilha)
                .when()
                        .get(TrilhaData.ENDPOINT_TRILHA_LISTA_RANKING+String.format("{%s}", TrilhaData.ID_TRILHA))
                ;
    }

    public Response buscarIdTrilha(Integer idTrilha) {

        return
                given()
                        .log().all()
//                    .spec(AuthSpecs.requestSpec())
                        .contentType(ContentType.JSON)
                        .pathParam(TrilhaData.ENDPOINT_TRILHA, idTrilha)
                .when()
                        .get(TrilhaData.ENDPOINT_TRILHA_FIND_ID_TRILHA+String.format("{%s}", TrilhaData.ID_TRILHA))
                ;
    }
}
