package test;

import base.BaseTest;
import dataFactory.*;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import model.*;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
public class FeedbackTest extends BaseTest {
//region REQUISITOS
    FeedbackModel feedbackModel = new FeedbackModel();
    FeedbackService feedbackService = new FeedbackService();

//region CRIAR FEEDBACK
    @Test
    @DisplayName("Criar feedback com sucesso")
    @Story("Criar feedback")
    @Description("Criar feedback com sucesso")
    public void criarFeedback() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(495);
        var response = feedbackService.cadastrarFeedback(feedbackModel)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(FeedbackModel.class);
        assertThat(feedbackModel.getDescricao(), equalTo(response.getDescricao()));
        assertThat(feedbackModel.getTipoFeedback(), equalTo(response.getTipoFeedback()));
        FeedbackModel delete = feedbackService.buscarFeedbackPeloId(response).then().extract().as(FeedbackModel.class);
        feedbackService.desativarFeedbackPeloId(delete)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Criar feedback invalido")
    @Story("Criar feedback")
    @Description("Criar feedback invalido")
    public void criarFeedbackInvalido() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(495);
        feedbackService.cadastrarFeedback(feedbackModel)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region ATUALIZAR FEEDBACK
    @Test
    @DisplayName("Atualizar feedback com sucesso")
    @Story("Criar feedback")
    @Description("Atualizar feedback com sucesso")
    public void atualizarFeedback() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(495);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel atualizar = FeedbackDataFactory.atualizarFeedback(criarFeedback.getIdFeedBack());
        var response = feedbackService.atualizarFeedback(criarFeedback, atualizar)
                .then()
                .statusCode(HttpStatus.SC_OK);
        feedbackService.desativarFeedbackPeloId(criarFeedback)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Atualizar feedback com Id errado")
    @Story("Criar feedback")
    @Description("Atualizar feedback com Id errado")
    public void atualizarAvaliacaoComIdErrado() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(495);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel atualizar = FeedbackDataFactory.atualizarFeedbackComidErrado(0000000000);
        feedbackService.atualizarFeedback(criarFeedback, atualizar)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract();
        feedbackService.desativarFeedbackPeloId(criarFeedback)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
//endregion
//region BUSCAR FEEDBACK PELO ID
    @Test
    @DisplayName("Buscar feedback com sucesso")
    @Story("Criar feedback")
    @Description("Buscar feedback com sucesso")
    public void buscarFeedback() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(495);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel idFeedback = FeedbackDataFactory.buscarFeedback(criarFeedback.getIdFeedBack());
        var response = feedbackService.buscarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_OK);
        feedbackService.desativarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.log().all();
    }
    @Test
    @DisplayName("Criar feedback com id Invalido")
    @Story("Criar feedback")
    @Description("Criar feedback com od invalido")
    public void buscarFeedbackComIdInvalido() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(495);
        FeedbackModel atualizar = FeedbackDataFactory.buscarFeedback(0000000000);
        var response = feedbackService.buscarFeedbackPeloId(atualizar)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region BUSCAR FEEDBACK PELO ID DA AVALIACAO
    @Test
    @DisplayName("Buscar todos os feedbacks")
    @Story("Buscar trilhas")
    @Description("Buscar todos os feedbacks")
    public void buscarAvaliacaoPeloIdDaAvaliacao() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(495);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel gerarIdAvaliacao = FeedbackDataFactory.buscarFeedbackPorIdDeAvaliacao(criarFeedback.getIdAvaliacao());
        FeedbackModel idFeedback = FeedbackDataFactory.buscarFeedback(criarFeedback.getIdFeedBack());
        feedbackService.buscarFeedbackPorPaginas(gerarIdAvaliacao);
        feedbackService.desativarFeedbackPeloId(idFeedback)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Criar feedback com sucesso")
    @Story("Criar feedback")
    @Description("Criar feedback com sucesso")
    public void buscarAvaliacaoComIdDaAvaliacaoInvalida() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(495);
        feedbackService.cadastrarFeedback(feedbackModel);
        FeedbackModel atualizar = FeedbackDataFactory.buscarFeedbackPorIdDeAvaliacao(0000000000);
        feedbackService.buscarFeedbackPorPaginas(atualizar)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region DELETAR FEEDBACK
//    @Test
//    public void deletarFeedback() {
//        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(495);
//        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
//        FeedbackModel idFeedback = FeedbackDataFactory.deletarFeedbackPorId(criarFeedback.getIdFeedBack());
//        var response = feedbackService.deletarFeedbackPeloId(idFeedback)
//                .then()
//                .statusCode(HttpStatus.SC_NO_CONTENT);
//        response.log().all();
//    }
//    @Test
//    public void deletarFeedbackComIdErrado() {
//        feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(495);
//        FeedbackModel idFeedback = FeedbackDataFactory.deletarFeedbackComIdErrado(000000000000000000000);
//        var response = feedbackService.deletarFeedbackPeloId(idFeedback)
//                .then()
//                .statusCode(HttpStatus.SC_BAD_REQUEST);
//    }
//endregion
//region DESATIVAR FEEDBACK
    @Test
    @DisplayName("Desativar feedback com sucesso")
    @Story("Criar feedback")
    @Description("Desativar feedback com sucesso")
    public void desativarFeedback() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(495);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel idFeedback = FeedbackDataFactory.deletarFeedbackPorId(criarFeedback.getIdFeedBack());
        var response = feedbackService.desativarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.log().all();
    }
    @Test
    @DisplayName("Desativar feedback com com id errado")
    @Story("Criar feedback")
    @Description("Desativar feedback com id errado")
    public void desativarFeedbackComIdErrado() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(495);
        FeedbackModel idFeedback = FeedbackDataFactory.deletarFeedbackComIdErrado(000000000000000000000);
        var response = feedbackService.deletarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

//endregion
}