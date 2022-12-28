package br.com.dbccompany.client;

import br.com.dbccompany.data.changeless.ProgramaData;
import br.com.dbccompany.data.changeless.Values;
import br.com.dbccompany.specs.AuthSpecs;
import io.restassured.response.Response;
import lombok.Value;

import static io.restassured.RestAssured.given;

public class ProgramaClient {

    public Response cadastrar(String programa) {

        return
            given()
                    .log().all()
                    .spec(AuthSpecs.requestSpec())
                    .body(programa)
            .when()
                    .post(ProgramaData.PROGRAMA)
            ;
    }

    public Response deletar(Integer idPrograma) {

        return
            given()
                    .log().all()
                    .spec(AuthSpecs.requestSpec())
                    .pathParam(ProgramaData.ID_PROGRAMA, idPrograma)
            .when()
                    .delete(ProgramaData.PROGRAMA+String.format("{%d}", ProgramaData.ID_PROGRAMA))
            ;
    }

    public Response atualizar(String programa, Integer idPrograma) {

        return
            given()
                    .log().all()
                    .spec(AuthSpecs.requestSpec())
                    .pathParam(ProgramaData.ID_PROGRAMA, idPrograma)
                    .body(programa)
            .when()
                    .put(ProgramaData.PROGRAMA+String.format("{%d}", ProgramaData.ID_PROGRAMA))
            ;
    }

    public Response buscarProgramaPorId(Integer idPrograma) {

        return
            given()
                    .log().all()
                    .spec(AuthSpecs.requestSpec())
                    .queryParam(ProgramaData.ID_PROGRAMA, idPrograma)
            .when()
                    .get(ProgramaData.PROGRAMA)
            ;
    }

    public Response listarProgramas(Integer page, Integer size) {

        return
            given()
                    .log().all()
                    .spec(AuthSpecs.requestSpec())
                    .queryParam(Values.PAGE, page)
                    .queryParam(Values.SIZE, size)
            .when()
                    .get(ProgramaData.PROGRAMA)
            ;
    }

    public Response listarProgramasPorNome(Integer page, Integer size, String nomePrograma) {

        return
                given()
                        .log().all()
                        .spec(AuthSpecs.requestSpec())
                        .queryParam(Values.PAGE, page)
                        .queryParam(Values.SIZE, size)
                        .queryParam(ProgramaData.NOME_PROGRAMA, nomePrograma)
                .when()
                        .get(ProgramaData.PROGRAMA_POR_NOME)
                ;
    }
}
