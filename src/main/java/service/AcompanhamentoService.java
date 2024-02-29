package service;

import io.restassured.response.Response;
import model.AcompanhamentoModel;
import model.TrilhaModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class AcompanhamentoService {
    //region CRIAR ACOMPANHAMENTO
    public Response criarAcompanhamento(AcompanhamentoModel acompanhamento) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(acompanhamento)
                        .when()
                        .post("/acompanhamento/create");
    }

    //endregion
//region ALTERAR ACOMPANHAMENTO
    public Response atualizarAcompanhamento(AcompanhamentoModel acompanhamentoAntigo, AcompanhamentoModel acompanhamentoNovo) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(acompanhamentoAntigo)
                        .pathParam("idAcompanhamento", acompanhamentoNovo.getIdAcompanhamento())
                        .when()
                        .put("/acompanhamento/update/{idAcompanhamento}");
    }

    //endregion
//region DESATIVAR ACOMPANHAMENTO PELO ID DO ACOMPANHAMENTO
    public Response desativarAcompanhamentoPeloId(AcompanhamentoModel idAcompanhamento) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", idAcompanhamento.getIdAcompanhamento())
                        .when()
                        .delete("/acompanhamento/deactivate/{idAcompanhamento}");
    }

    //endregion
//region DELETAR ACOMPANHAMENTO PELO ID DO ACOMPANHAMENTO
    public Response deletarAcompanhamentoPeloId(AcompanhamentoModel idAcompanhamento) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", idAcompanhamento.getIdAcompanhamento())
                        .when()
                        .delete("/acompanhamento/delete/{idAcompanhamento}");
    }

    //endregion
//region BUSCAR ACOMPANHAMENTO PELO ID DO PROGRAMA
    public Response buscarAcompanhamentoPeloId(AcompanhamentoModel idPrograma) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", idPrograma.getIdPrograma())
                        .queryParam("pagina", 0)
                        .queryParam("tamanho", 5)
                        .when()
                        .get("/acompanhamento/list-by-programa/{idPrograma}");
    }

    //endregion
//region BUSCAR ACOMPANHAMENTOS POR PAGINA
    public Response buscarAcompanhamentoPorPaginas(Integer pagina, Integer tamanho) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina", pagina)
                        .queryParam("tamanho", tamanho)
                        .when()
                        .get("/acompanhamento/list-all");
    }

    //endregion
//region BUSCAR ACOMPANHAMENTOS SEM INFORMAR PAGINAS
    public Response buscarAcompanhamentoSemInformarPaginas() {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina")
                        .queryParam("tamanho")
                        .when()
                        .get("/acompanhamento/list-all");
    }

    //endregion
//region BUSCAR ACOMPANHAMENTO PELO ID DO ACOMPANHAMENTO
    public Response buscarAcompanhamentoPeloIdDoAcompanhamento(Integer idAcompanhamento) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", idAcompanhamento)
                        .when()
                        .get("/acompanhamento/get-by-id/{idAcompanhamento}");
    }
//endregion
}
