package service;

import io.restassured.response.Response;
import model.AvaliacaoModel;
import model.FeedbackModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class FeedbackService {
//region CADASTRAR FEEDBACK
    public Response cadastrarFeedback(FeedbackModel corpo){
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(corpo)
            .when()
                .post("/feedback/create");
    }
//    endregion
//region ATUALIZAR FEEDBACK
    public Response atualizarFeedback(FeedbackModel feedbackId, FeedbackModel feedbackBody) {
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(feedbackBody)
                .pathParam("idFeedBack", feedbackId.getIdFeedBack())
            .when()
                .put("/feedback/update/{idFeedBack}");
    }
//endregion
//region BUSCAR FEEDBACK
    public Response buscarFeedbackPeloId(FeedbackModel feedback) {
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idFeedBack", feedback.getIdFeedBack())
            .when()
                .get("/feedback/get-by-id/{idFeedBack}");
    }
//endregion
//region BUSCAR FEEDBACK POR PAGINAS
    public Response buscarFeedbackPorPaginas(FeedbackModel feedback) {
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idAvaliacao", feedback.getIdAvaliacao())
                .queryParam("pagina",0)
                .queryParam("tamanho",5)
            .when()
                .get("/feedback/list-by-avaliacao/{idAvaliacao}");
    }
//endregion
//region DELETAR FEEDBACK
    public Response deletarFeedbackPeloId(FeedbackModel feedback) {
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idFeedBack", feedback.getIdFeedBack())
            .when()
                .delete("/feedback/delete/{idFeedBack}");
    }
    //    endregion
//region DESATIVAR FEEDBACK
    public Response desativarFeedbackPeloId(FeedbackModel feedback) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idFeedBack", feedback.getIdFeedBack())
                        .when()
                        .delete("/feedback/deactivate/{idFeedBack}");
    }
//    endregion
}
