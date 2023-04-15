package test;

import base.BaseTest;
import dataFactory.*;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import model.*;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static test.AvaliacaoTest.acompanhamentoCriado;
import static test.AvaliacaoTest.estagiarioCriado;

public class FeedbackTest extends BaseTest {
    //region REQUISITOS
    FeedbackModel feedbackModel = new FeedbackModel();
    FeedbackDataFactory feedbackDataFactory = new FeedbackDataFactory();
    FeedbackService feedbackService = new FeedbackService();
    EstagiarioModel estagiarioModel = new EstagiarioModel();
    static ProgramaService programaService = new ProgramaService();
    static AcompanhamentoModel acompanhamentoModel = new AcompanhamentoModel();
    static TrilhaService trilhaService = new TrilhaService();
    static AcompanhamentoService acompanhamentoService = new AcompanhamentoService();
    static EstagiarioService estagiarioService = new EstagiarioService();

    static AvaliacaoModel avaliacaoCriada;
    static AvaliacaoModel idAvaliacao;
    static AvaliacaoService avaliacaoService = new AvaliacaoService();


    @BeforeAll
    public static void criarPreRequisitos() {
        ProgramaModel programaCriado = programaService.criarPrograma(ProgramaDataFactory.gerarProgramaValido())
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(ProgramaModel.class);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(TrilhaDataFactory.gerarTrilhaValida(programaCriado))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(TrilhaModel.class);
        AcompanhamentoModel acompanhamentoCriado = acompanhamentoService.criarAcompanhamento(AcompanhamentoDataFactory.gerarAcompanhamentoValido(programaCriado))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(AcompanhamentoModel.class);
        EstagiarioModel estagiarioCriado = estagiarioService.criarEstagiario(EstagiarioDataFactory.gerarEstagiarioValido(trilhaCriada))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(EstagiarioModel.class);
        avaliacaoCriada = AvaliacaoDataFactory.gerarAvaliacaoValida(acompanhamentoCriado, estagiarioCriado);
        var response = avaliacaoService.criarAvaliacao(avaliacaoCriada)
                .then()
                .extract()
                .as(AvaliacaoModel.class);
        idAvaliacao = AvaliacaoDataFactory.buscarAvaliacaoPorIdDaAvaliacao(response.getIdAvaliacao());
    }

    //endregion
//region CRIAR FEEDBACK
    @Test
    public void criarFeedback() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(idAvaliacao);
        var response = feedbackService.cadastrarFeedback(feedbackModel)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(FeedbackModel.class);
        assertThat(feedbackModel.getDescricao(), equalTo(response.getDescricao()));
        assertThat(feedbackModel.getTipoFeedback(), equalTo(response.getTipoFeedback()));
        FeedbackModel delete = feedbackService.buscarFeedbackPeloId(response).then().extract().as(FeedbackModel.class);
        feedbackService.deletarFeedbackPeloId(delete)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    public void criarFeedbackInvalido() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(idAvaliacao);
        feedbackService.cadastrarFeedback(feedbackModel)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region ATUALIZAR FEEDBACK
    @Test
    public void atualizarAvaliacao() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(idAvaliacao);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel atualizar = FeedbackDataFactory.atualizarFeedback(criarFeedback.getIdFeedBack());
        var response = feedbackService.atualizarFeedback(criarFeedback, atualizar)
                .then()
                .statusCode(HttpStatus.SC_OK);
        feedbackService.deletarFeedbackPeloId(criarFeedback)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void atualizarAvaliacaoComIdErrado() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(idAvaliacao);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel atualizar = FeedbackDataFactory.atualizarFeedbackComidErrado(0000000000);
        feedbackService.atualizarFeedback(criarFeedback, atualizar)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract();
        feedbackService.deletarFeedbackPeloId(criarFeedback)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
//endregion
//region BUSCAR FEEDBACK PELO ID
    @Test
    public void buscarAvaliacao() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(idAvaliacao);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel idFeedback = FeedbackDataFactory.buscarFeedback(criarFeedback.getIdFeedBack());
        var response = feedbackService.buscarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_OK);
        feedbackService.deletarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.log().all();
    }
    @Test
    public void buscarAvaliacaoComIdInvalido() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(idAvaliacao);
        FeedbackModel atualizar = FeedbackDataFactory.buscarFeedback(0000000000);
        var response = feedbackService.buscarFeedbackPeloId(atualizar)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region BUSCAR FEEDBACK PELO ID DA AVALIACAO
    @DisplayName("Buscar todos os acompanhamentos")
    @Story("Buscar trilhas")
    @Description("Buscar todos os acompanhamentos")
    @Test
    public void buscarAvaliacaoPeloIdDaAvaliacao() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(idAvaliacao);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel idFeedback = FeedbackDataFactory.buscarFeedback(criarFeedback.getIdFeedBack());
        feedbackService.deletarFeedbackPeloId(idFeedback)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    public void buscarAvaliacaoComIdDaAvaliacaoInvalida() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(idAvaliacao);
        feedbackService.cadastrarFeedback(feedbackModel);
        FeedbackModel atualizar = FeedbackDataFactory.buscarFeedbackPorIdDeAvaliacao(0000000000);
        feedbackService.buscarFeedbackPorPaginas(atualizar)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region DELETAR FEEDBACK
    @Test
    public void deletarFeedback() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(idAvaliacao);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel idFeedback = FeedbackDataFactory.deletarFeedbackPorId(criarFeedback.getIdFeedBack());
        var response = feedbackService.deletarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.log().all();
    }
    @Test
    public void deletarFeedbackComIdErrado() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(idAvaliacao);
        FeedbackModel idFeedback = FeedbackDataFactory.deletarFeedbackComIdErrado(000000000000000000000);
        var response = feedbackService.deletarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region DESATIVAR FEEDBACK
    @Test
    public void desativarFeedback() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackValido(idAvaliacao);
        FeedbackModel criarFeedback = feedbackService.cadastrarFeedback(feedbackModel).then().extract().as(FeedbackModel.class);
        FeedbackModel idFeedback = FeedbackDataFactory.deletarFeedbackPorId(criarFeedback.getIdFeedBack());
        var response = feedbackService.desativarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.log().all();
    }
    @Test
    public void desativarFeedbackComIdErrado() {
        feedbackModel = FeedbackDataFactory.gerarFeedbackSemId(idAvaliacao);
        FeedbackModel idFeedback = FeedbackDataFactory.deletarFeedbackComIdErrado(000000000000000000000);
        var response = feedbackService.deletarFeedbackPeloId(idFeedback)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

//endregion
}