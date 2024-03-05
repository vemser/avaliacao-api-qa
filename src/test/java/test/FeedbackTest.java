package test;

import base.BaseTest;
import dataFactory.*;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import model.*;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class FeedbackTest extends BaseTest {
    FeedbackService feedbackService = new FeedbackService();

//region CRIAR FEEDBACK
    @Test
    @DisplayName("Criar feedback com sucesso")
    @Story("Criar feedback")
    @Description("Criar feedback com sucesso")
    public void criarFeedback() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(611);

        var response = feedbackService.cadastrarFeedback(feedbackModel)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(FeedbackModel.class);

        assertThat(feedbackModel.getDescricao(), equalTo(response.getDescricao()));
        assertThat(feedbackModel.getTipoFeedback(), equalTo(response.getTipoFeedback()));

        FeedbackModel extrair = feedbackService.buscarFeedbackPeloId(response).then().extract().as(FeedbackModel.class);

        feedbackService.desativarFeedbackPeloId(extrair)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("ID invalido para criar feedback")
    @Story("ID invalido para criar feedback")
    @Description("ID invalido para criar feedback")
    public void testErroAoCriarFeedback() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(-456745);
        var response = feedbackService.cadastrarFeedback(feedbackModel)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);

        Assertions.assertEquals("Avaliação inexistente ou inativa", response.getMessage());
    }

//endregion
//region ATUALIZAR FEEDBACK
    @Test
    @DisplayName("Atualizar feedback com sucesso")
    public void testAtualizarFeedback() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(629);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().log().body().extract().as(FeedbackModel.class);
        FeedbackModel atualizar = FeedbackDataFactory.atualizarFeedback(629);

        var response = feedbackService.atualizarFeedback(criarFeedback, atualizar)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(FeedbackModel.class);
        Assertions.assertEquals(atualizar.getDescricao(), response.getDescricao());

        feedbackService.desativarFeedbackPeloId(response)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

//endregion
//region BUSCAR FEEDBACK PELO ID
    @Test
    @DisplayName("Buscar feedback com sucesso")
    public void buscarFeedback() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(616);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel idFeedback = FeedbackDataFactory.buscarFeedback(criarFeedback.getIdFeedBack());
        var response = feedbackService.buscarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(FeedbackModel.class);
        assertThat(feedbackModel.getDescricao(), equalTo(response.getDescricao()));
        assertThat(feedbackModel.getTipoFeedback(), equalTo(response.getTipoFeedback()));
        feedbackService.deletarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Criar feedback com id Invalido")
    @Story("Criar feedback")
    @Description("Criar feedback com od invalido")
    public void buscarFeedbackComIdInvalido() {
        FeedbackModel feedback = FeedbackDataFactory.buscarFeedback(0);

        var response = feedbackService.buscarFeedbackPeloId(feedback)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);

        Assertions.assertEquals("FeedBack não encontrado.", response.getMessage() );
    }
//endregion
//region BUSCAR FEEDBACK PELO ID DA AVALIACAO
    @Test
    @DisplayName("Buscar feedback por avaliação")
    public void buscarFeedbackPeloIdDaAvaliacao() {
        Integer id = 617;

        feedbackService.buscarFeedbackPorPaginas(id)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("elementos.size()", greaterThan(0));



    }
    @Test
    @DisplayName("Erro ao buscar feedback com ID invalido")
    public void buscarFeedbackComIdDaAvaliacaoInvalida() {
       Integer idInvalido = 239934982;

        var response = feedbackService.buscarFeedbackPorPaginas(idInvalido)
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertEquals("Avaliação inexistente ou inativa", response.getMessage());
    }
//endregion
//region DELETAR FEEDBACK
    @Test
    public void deletarFeedback() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(624);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().log().body().extract().as(FeedbackModel.class);
        FeedbackModel idFeedback = FeedbackDataFactory.buscarFeedback(criarFeedback.getIdFeedBack());

        feedbackService.deletarFeedbackPeloId(idFeedback)
        .then()
            .log().body()
            .statusCode(HttpStatus.SC_NO_CONTENT);

    }
    @Test
    @DisplayName("Erro deletar feedback com id errado")
    public void deletarFeedbackComIdErrado() {

        Integer idInvalido = 983903;
        var response = feedbackService.deletarFeedbackPeloIdSemModel(idInvalido)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);

    }
//endregion
//region DESATIVAR FEEDBACK
    @Test
    @DisplayName("Desativar feedback com sucesso")
    public void desativarFeedback() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(631);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel idFeedback = FeedbackDataFactory.deletarFeedbackPorId(criarFeedback.getIdFeedBack());
        feedbackService.desativarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        var response = feedbackService.buscarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(FeedbackModel.class);
        assertThat(criarFeedback.getDescricao(), equalTo(response.getDescricao()));

    }
    @Test
    @DisplayName("Erro ao desativar feedback com com id errado")
    public void desativarFeedbackComIdErrado() {
        Integer idInvalido = 239934982;

        var response = feedbackService.desativarFeedbackSemModel(idInvalido)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertEquals("FeedBack inexistente ou inativa.", response.getMessage());
    }

//endregion
}