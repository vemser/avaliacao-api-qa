package service;

import io.restassured.response.Response;
import model.AvaliacaoModel;
import model.FeedbackModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class FeedbackService {
//region CADASTRAR FEEDBACK
    public Response cadastrarFeedback(FeedbackModel corpo){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(corpo)
            .when()
                .post("/feedback/create");
        return response;
    }
//    endregion
//region ATUALIZAR FEEDBACK
    public Response atualizarFeedback(FeedbackModel feedbackId, FeedbackModel feedbackBody) {
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(feedbackBody)
                .pathParam("idFeedBack", feedbackId.getIdFeedBack())
            .when()
                .put("/feedback/update/{idFeedBack}");
        return response;
    }
//endregion
//region BUSCAR FEEDBACK
    public Response buscarFeedbackPeloId(FeedbackModel feedback) {
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idFeedBack", feedback.getIdFeedBack())
            .when()
                .get("/feedback/get-by-id/{idFeedBack}");
        return response;
    }
//endregion
//region BUSCAR FEEDBACK POR PAGINAS
    public Response buscarFeedbackPorPaginas(FeedbackModel feedback) {
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", feedback.getIdPrograma())
                .queryParam("pagina",0)
                .queryParam("tamanho",5)
            .when()
                .get("/feedback/list-by-avaliacao/{idPrograma}");
        return response;
    }
//endregion
//region DELETAR FEEDBACK
    public Response deletarFeedbackPeloId(FeedbackModel feedback) {
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idFeedBack", feedback.getIdFeedBack())
            .when()
                .delete("/feedback/delete/{idFeedBack}");
        return response;
    }
    //    endregion
//region DESATIVAR FEEDBACK
    public Response desativarFeedbackPeloId(FeedbackModel feedback) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idFeedBack", feedback.getIdFeedBack())
                        .when()
                        .delete("/feedback/deactivate/{idFeedBack}");
        return response;
    }
//    endregion
}
