package br.com.dbccompany.client;

import br.com.dbccompany.data.changeless.ProgramaData;
import br.com.dbccompany.data.changeless.Values;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProgramaClient {

    public Response cadastrar(String programa) {

        return
            given()
                    .log().all()
//                    .spec(AuthSpecs.requestSpec())
                    .contentType(ContentType.JSON)
                    .body(programa)
            .when()
                    .post(ProgramaData.ENDPOINT_PROGRAMA)
            ;
    }

    public Response deletar(Integer idPrograma) {

        return
            given()
                    .log().all()
//                    .spec(AuthSpecs.requestSpec())
                    .contentType(ContentType.JSON)
                    .pathParam(ProgramaData.ID_PROGRAMA, idPrograma)
            .when()
                    .delete(ProgramaData.ENDPOINT_PROGRAMA +String.format("{%s}", ProgramaData.ID_PROGRAMA))
            ;
    }

    public Response atualizar(String programa, Integer idPrograma) {

        return
            given()
                    .log().all()
//                    .spec(AuthSpecs.requestSpec())
                    .contentType(ContentType.JSON)
                    .pathParam(ProgramaData.ID_PROGRAMA, idPrograma)
                    .body(programa)
            .when()
                    .put(ProgramaData.ENDPOINT_PROGRAMA +String.format("{%s}", ProgramaData.ID_PROGRAMA))
            ;
    }

    public Response buscarProgramaPorId(Integer idPrograma) {

        return
            given()
                    .log().all()
//                    .spec(AuthSpecs.requestSpec())
                    .pathParam(ProgramaData.ID_PROGRAMA, idPrograma)
            .when()
                    .get(ProgramaData.ENDPOINT_PROGRAMA +String.format("{%s}", ProgramaData.ID_PROGRAMA))
            ;
    }

    public Response listarProgramas(Integer page, Integer size) {

        return
            given()
                    .log().all()
//                    .spec(AuthSpecs.requestSpec())
                    .contentType(ContentType.JSON)
                    .queryParam(Values.PAGE, page)
                    .queryParam(Values.SIZE, size)
            .when()
                    .get(ProgramaData.ENDPOINT_PROGRAMA)
            ;
    }

    public Response listarProgramasPorNome(Integer page, Integer size, String nomePrograma) {

        return
                given()
                        .log().all()
//                        .spec(AuthSpecs.requestSpec())
                        .contentType(ContentType.JSON)
                        .queryParam(Values.PAGE, page)
                        .queryParam(Values.SIZE, size)
                        .queryParam(ProgramaData.NOME_PROGRAMA, nomePrograma)
                .when()
                        .get(ProgramaData.ENDPOINT_LISTNOME)
                ;
    }
}
