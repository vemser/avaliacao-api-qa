package service;

import io.restassured.response.Response;
import model.AcompanhamentoModel;
import model.TrilhaModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;
public class AcompanhamentoService {
//region CRIAR ACOMPANHAMENTO
    public Response criarAcompanhamento(AcompanhamentoModel acompanhamento) {
        Response response =
                given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .body(acompanhamento)
                .when()
                    .post("/acompanhamento/create");
        return response;
    }
//endregion
//region ALTERAR ACOMPANHAMENTO
    public Response atualizarAcompanhamento(AcompanhamentoModel acompanhamentoAntigo, AcompanhamentoModel acompanhamentoNovo) {
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(acompanhamentoAntigo)
                .pathParam("idAcompanhamento", acompanhamentoNovo.getIdAcompanhamento())
            .when()
                .put("/acompanhamento/update/{idAcompanhamento}");
        return response;
    }
//endregion
//region DESATIVAR ACOMPANHAMENTO PELO ID DO ACOMPANHAMENTO
    public Response desativarAcompanhamentoPeloId(AcompanhamentoModel idAcompanhamento) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", idAcompanhamento.getIdAcompanhamento())
                        .when()
                        .delete("/acompanhamento/deactivate/{idAcompanhamento}");
        return response;
    }
//endregion
//region DELETAR ACOMPANHAMENTO PELO ID DO ACOMPANHAMENTO
    public Response deletarAcompanhamentoPeloId(AcompanhamentoModel idAcompanhamento) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", idAcompanhamento.getIdAcompanhamento())
                        .when()
                        .delete("/acompanhamento/delete/{idAcompanhamento}");
        return response;
    }
//endregion
//region BUSCAR ACOMPANHAMENTO PELO ID DO PROGRAMA
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
//endregion
//region BUSCAR ACOMPANHAMENTOS POR PAGINA
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
//endregion
//region BUSCAR ACOMPANHAMENTOS SEM INFORMAR PAGINAS
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
//endregion
//region BUSCAR ACOMPANHAMENTO PELO ID DO ACOMPANHAMENTO
    public Response buscarAcompanhamentoPeloIdDoAcompanhamento(AcompanhamentoModel idAcompanhamento) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", idAcompanhamento.getIdAcompanhamento())
                        .when()
                        .get("/acompanhamento/get-by-id/{idAcompanhamento}");
        return response;
    }
//endregion
}
