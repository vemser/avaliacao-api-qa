package service;

import io.restassured.response.Response;
import model.AcompanhamentoModel;
import model.AvaliacaoModel;
import model.ProgramaModel;
import model.TrilhaModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

    public class AvaliacaoService {
        public Response criarAvaliacao(AvaliacaoModel body) {
            Response response =
                given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .body(body)
                .when()
                    .post("/avaliacao/create");
        return response;
    }

        public Response atualizarAvaliacao(AvaliacaoModel avaliacaoAntiga, AvaliacaoModel avaliacaoNova) {
            Response response =
                given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .body(avaliacaoNova)
                    .pathParam("idAvaliacao", avaliacaoAntiga.getIdAvaliacao())
                .when()
                    .put("/avaliacao/update/{idAvaliacao}");
            return response;
        }

        public Response buscarAvaliacaoPeloIdDoAcompanhamento(AvaliacaoModel avaliacao) {
            Response response =
                given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .pathParam("idAcompanhamento", avaliacao.getIdAcompanhamento())
                    .queryParam("pagina",0)
                    .queryParam("tamanho",1)
                .when()
                    .get("/avaliacao/list-by-acompanhamento/{idAcompanhamento}");
            return response;
        }


        public Response buscarAvaliacaoPeloIdDaAvaliacao(AvaliacaoModel avaliacao) {
            Response response =
                given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .pathParam("idAvaliacao", avaliacao.getIdAvaliacao())
                .when()
                    .get("/avaliacao/get-by-id/{idAvaliacao}");
            return response;
        }


        public Response buscarAvaliacaoPorPaginaETamanho(Integer pagina, Integer tamanho) {
            Response response =
                given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .queryParam("pagina",pagina)
                    .queryParam("tamanho",tamanho)
                .when()
                    .get("/avaliacao/list-all");
            return response;
        }
        public Response buscarAvaliacaoSemPaginaETamanho() {
            Response response =
                given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .queryParam("pagina")
                    .queryParam("tamanho")
                .when()
                    .get("/avaliacao/list-all");
            return response;
        }

        public Response desativarAvaliacaoPeloIdDaAvaliacao(AvaliacaoModel avaliacao) {
            Response response =
                given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .pathParam("idAvaliacao", avaliacao.getIdAvaliacao())
                .when()
                    .delete("/avaliacao/delete/{idAvaliacao}");
            return response;
        }

        public Response deletarAvaliacaoPeloIdDaAvaliacao(AvaliacaoModel avaliacao) {
            Response response =
                given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .pathParam("idAvaliacao", avaliacao.getIdAvaliacao())
                .when()
                    .delete("/avaliacao/delete/{idAvaliacao}");
            return response;
        }
}
