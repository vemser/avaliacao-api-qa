package br.com.dbccompany.client;

import br.com.dbccompany.data.changeless.*;
import br.com.dbccompany.specs.AuthSpecs;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ComentarioClient {

    public Response cadastrar(Integer idAluno, String feedBack, String comentario) {

        return
                given()
                        .log().all()
                        .spec(AuthSpecs.requestSpecAdmin())
                        .queryParam(ComentarioData.ID_ALUNO, idAluno)
                        .queryParam(ComentarioData.TIPO_FEEDBACK, feedBack)
                        .body(comentario)
                    .when()
                        .post(ComentarioData.ADICIONAR_COMENTARIO)
                ;
    }

    public Response atualizar(Integer idAluno, Integer idAtividade, String comentario) {

        return
                given()
                        .log().all()
                        .spec(AuthSpecs.requestSpecAdmin())
                        .queryParam(ComentarioData.ID_ALUNO, idAluno)
                        .queryParam(AtividadeData.ID_ATIVIDADE, idAtividade)
                        .body(comentario)
                    .when()
                        .put(ComentarioData.ATUALIZAR_COMENTARIO)
                ;
    }

    public Response buscarComentarioTipoFeedback(String tipoFeedback) {

        return
                given()
                        .log().all()
                        .spec(AuthSpecs.requestSpecAdmin())
                        .queryParam(ComentarioData.TIPO_FEEDBACK, tipoFeedback)
                    .when()
                        .get(ComentarioData.LISTAR_FEEDBACK)
                ;
    }
}
