package service;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import model.AcompanhamentoModel;
import model.AvaliacaoModel;
import model.ProgramaModel;
import model.TrilhaModel;
import net.minidev.json.JSONObject;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class AvaliacaoService {
    public ValidatableResponse criarAvaliacao(AvaliacaoModel body) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(new JSONObject()
                                .appendField("idAcompanhamento", body.getIdAcompanhamento())
                                .appendField("idEstagiario", body.getIdEstagiario())
                                .appendField("objetivoProfissional", body.getObjetivoProfissional())
                                .appendField("recomendacaoMelhorias", body.getRecomendacaoMelhorias())
                                .appendField("status", body.getStatus())
                        )
                        .when()
                        .post("/avaliacao/create")
                        .then();
    }

    public Response atualizarAvaliacao(AvaliacaoModel avaliacaoAntiga, AvaliacaoModel avaliacaoNova) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(avaliacaoNova)
                        .pathParam("idAvaliacao", avaliacaoAntiga.getIdAvaliacao())
                        .when()
                        .put("/avaliacao/update/{idAvaliacao}");
    }

    public Response buscarAvaliacaoPeloIdDoAcompanhamento(AvaliacaoModel avaliacao) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", avaliacao.getIdAcompanhamento())
                        .queryParam("pagina", 0)
                        .queryParam("tamanho", 1)
                        .when()
                        .get("/avaliacao/list-by-acompanhamento/{idAcompanhamento}");
    }


    public Response buscarAvaliacaoPeloIdDaAvaliacao(AvaliacaoModel avaliacao) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAvaliacao", avaliacao.getIdAvaliacao())
                        .when()
                        .get("/avaliacao/get-by-id/{idAvaliacao}");
    }


    public Response buscarAvaliacaoPorPaginaETamanho(Integer pagina, Integer tamanho) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina", pagina)
                        .queryParam("tamanho", tamanho)
                        .when()
                        .get("/avaliacao/list-all");
    }

    public Response buscarAvaliacaoSemPaginaETamanho() {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina")
                        .queryParam("tamanho")
                        .when()
                        .get("/avaliacao/list-all");
    }

    public Response desativarAvaliacaoPeloIdDaAvaliacao(AvaliacaoModel avaliacao) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAvaliacao", avaliacao.getIdAvaliacao())
                        .when()
                        .delete("/avaliacao/delete/{idAvaliacao}");
    }

    public Response deletarAvaliacaoPeloIdDaAvaliacao(AvaliacaoModel avaliacao) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAvaliacao", avaliacao.getIdAvaliacao())
                        .when()
                        .delete("/avaliacao/delete/{idAvaliacao}");
    }
}
