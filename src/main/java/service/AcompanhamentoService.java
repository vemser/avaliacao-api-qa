package service;

import io.restassured.response.Response;
import model.AcompanhamentoModel;
import model.ProgramaModel;
import model.TrilhaModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class AcompanhamentoService {
    public Response criarAcompanhamento(AcompanhamentoModel acompanhamento) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(acompanhamento)
                        .when()
                        .post("/acompanhamento/create");
        return response;
    }

    public Response desativarAcompanhamentoPeloId(AcompanhamentoModel idAcompanhamento) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", idAcompanhamento.getIdAcompanhamento())
                        .when()
                        .delete("/acompanhamento/delete/{idAcompanhamento}");
        return response;
    }
    public Response deletarAcompanhamentoPeloId(AcompanhamentoModel idAcompanhamento) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", idAcompanhamento.getIdAcompanhamento())
                        .when()
                        .delete("/acompanhamento/deactivate/{idAcompanhamento}");
        return response;
    }

    public Response buscarAcompanhamentoPeloId(AcompanhamentoModel idPrograma) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", idPrograma.getIdPrograma())
                        .queryParam("pagina",0)
                        .queryParam("tamanho",5)
                        .when()
                        .get("/acompanhamento/list-by-programa/{idPrograma}");
        return response;
    }
    public Response buscarAcompanhamentoPorPaginas(Integer pagina, Integer tamanho) {
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .queryParam("pagina",pagina)
                .queryParam("tamanho",tamanho)
            .when()
                .get("/acompanhamento/list-all");
        return response;
    }
    public Response buscarAcompanhamentoSemInformarPaginas() {
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .queryParam("pagina")
                .queryParam("tamanho")
            .when()
                .get("/acompanhamento/list-all");
        return response;
    }
    public Response buscarAcompanhamentoPeloIdDoAcompanhamento(AcompanhamentoModel idAcompanhamento) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", idAcompanhamento.getIdAcompanhamento())
                        .when()
                        .get("/acompanhamento/get-by-id/{idAcompanhamento}");
        return response;
    }
}
