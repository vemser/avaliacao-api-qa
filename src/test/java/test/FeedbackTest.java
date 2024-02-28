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
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(745);
        var response = feedbackService.cadastrarFeedback(feedbackModel)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(FeedbackModel.class);
        assertThat(feedbackModel.getDescricao(), equalTo(response.getDescricao()));
        assertThat(feedbackModel.getTipoFeedback(), equalTo(response.getTipoFeedback()));
        FeedbackModel extrair = feedbackService.buscarFeedbackPeloId(response).then().extract().as(FeedbackModel.class);
        FeedbackModel delete = FeedbackDataFactory.deletarFeedbackPorId(extrair.getIdFeedBack());
        feedbackService.desativarFeedbackPeloId(delete)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Criar feedback com sucesso")
    @Story("Criar feedback")
    @Description("Criar feedback com sucesso")
    public void testErroAoCriarFeedback() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(-456745);
        var response = feedbackService.cadastrarFeedback(feedbackModel)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("idAvaliacao: must be greater than or equal to 1"));
    }

//endregion
//region ATUALIZAR FEEDBACK
    @Test
    @DisplayName("Atualizar feedback com sucesso")
    public void testAtualizarFeedback() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(745);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel atualizar = FeedbackDataFactory.atualizarFeedback(criarFeedback.getIdFeedBack());
        var response = feedbackService.atualizarFeedback(criarFeedback, atualizar)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(FeedbackModel.class);
        Assertions.assertEquals(atualizar.getDescricao(), response.getDescricao());
        feedbackService.deletarFeedbackPeloId(criarFeedback)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Erro ao atualizar feedback, sem informações no Body")
    public void testAtualizarFeedbackSemBody() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(745);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel atualizar = FeedbackDataFactory.atualizarFeedbackSemDescricao();
        var response = feedbackService.atualizarFeedback(criarFeedback, atualizar)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("tipoFeedback: must not be null"));
        Assertions.assertTrue(response.getErrors().contains("status: must not be null"));
        Assertions.assertTrue(response.getErrors().contains("nota: must not be null"));
        feedbackService.deletarFeedbackPeloId(criarFeedback)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
//endregion
//region BUSCAR FEEDBACK PELO ID
    @Test
    @DisplayName("Buscar feedback com sucesso")
    public void buscarFeedback() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(745);
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
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(495);
        FeedbackModel atualizar = FeedbackDataFactory.buscarFeedback(0);
        var response = feedbackService.buscarFeedbackPeloId(atualizar)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("getById.idFeedBack: must be greater than or equal to 1"));
    }
//endregion
//region BUSCAR FEEDBACK PELO ID DA AVALIACAO
    @Test
    @DisplayName("Buscar todos os feedbacks")
    public void buscarFeedbackPeloIdDaAvaliacao() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(745);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel idFeedback = FeedbackDataFactory.buscarFeedback(criarFeedback.getIdFeedBack());
        feedbackService.buscarFeedbackPorPaginas(feedbackModel)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("elementos.size()", greaterThan(0));
        feedbackService.deletarFeedbackPeloId(idFeedback)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }
    @Test
    @DisplayName("Erro ao buscar feedback")
    public void buscarFeedbackComIdDaAvaliacaoInvalida() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(745);
        feedbackService.cadastrarFeedback(feedbackModel);
        var response = feedbackService.buscarFeedbackPorPaginas(feedbackModel)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listAllByAvaliacao.idAvaliacao: must be greater than or equal to 1"));
    }
//endregion
//region DELETAR FEEDBACK
    @Test
    public void deletarFeedback() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(745);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel idFeedback = FeedbackDataFactory.deletarFeedbackPorId(criarFeedback.getIdFeedBack());
       feedbackService.deletarFeedbackPeloId(idFeedback)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        var response = feedbackService.buscarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("FeedBack não encontrado."));
    }
    @Test
    @DisplayName("Erro ao deletar o feedback por inserir o id errado")
    public void deletarFeedbackComIdErrado() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(495);
        FeedbackModel idFeedback = FeedbackDataFactory.deletarFeedbackComIdErrado();
        var response = feedbackService.deletarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("delete.idFeedBack: must be greater than or equal to 1"));
    }
//endregion
//region DESATIVAR FEEDBACK
    @Test
    @DisplayName("Desativar feedback com sucesso")
    public void desativarFeedback() {
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackValido(745);
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
        FeedbackModel feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(495);
        FeedbackModel idFeedback = FeedbackDataFactory.deletarFeedbackComIdErrado();
        var response = feedbackService.desativarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("deactivate.idFeedBack: must be greater than or equal to 1"));
    }

//endregion
}