package test;

import base.BaseTest;
import dataFactory.*;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
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

public class AvaliacaoTest extends BaseTest {
    AvaliacaoModel avaliacaoModel = new AvaliacaoModel();
    AvaliacaoService avaliacaoService = new AvaliacaoService();

//region CRIAR AVALIACAO
    @Test
    @DisplayName("Criar avaliação válida")
    public void testCriarAvaliacao() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(710, 904);
        var response = avaliacaoService.criarAvaliacao(avaliacaoModel)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(AvaliacaoModel.class);
        Assertions.assertEquals(avaliacaoModel.getLink(), response.getLink());
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.buscarAvaliacaoPorIdDaAvaliacao(response.getIdAvaliacao());
        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(avaliacao)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Erro ao criar avaliação pois o id do acompanhamento e estagiario são zero")
    public void testCriarAvaliacaoSemIdDeEstadiario() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoComIdInvalido(0000000, 00000000);
        var response = avaliacaoService.criarAvaliacao(avaliacaoModel)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("idEstagiario: must be greater than or equal to 1"));
    }

    // endregion
//region ATUALIZAR AVALIACAO
    @Test
    @DisplayName("Atualizar avaliação com sucesso")
    public void testAtualizarAvaliacao() {
        avaliacaoModel = AvaliacaoDataFactory.alterarAvaliacao(710, 904);
        AvaliacaoModel criarAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.buscarAvaliacaoPorIdDaAvaliacao(criarAvaliacao.getIdAvaliacao());
        AvaliacaoModel avaliacaoAlterada = AvaliacaoDataFactory.alterarAvaliacao(criarAvaliacao.getIdAcompanhamento(), criarAvaliacao.getIdEstagiario());
        var response = avaliacaoService.atualizarAvaliacao(criarAvaliacao, avaliacaoAlterada)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(AvaliacaoModel.class);
        Assertions.assertEquals(avaliacaoAlterada.getLink(), response.getLink());
        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Erro ao Atualizar avaliação")
    public void testAtualizarAvaliacaoSemIdDeAcompanhamento() {
        avaliacaoModel = AvaliacaoDataFactory.alterarAvaliacao(-456, 904);
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.buscarAvaliacaoPorIdDaAvaliacao(-456);
        AvaliacaoModel avaliacaoAlterada = AvaliacaoDataFactory.alterarAvaliacao(-456, avaliacao.getIdEstagiario());
        var response = avaliacaoService.atualizarAvaliacao(avaliacao, avaliacaoAlterada)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("update.idAvaliacao: must be greater than or equal to 1"));
    }

    //endregion
//    region BUSCAR AVALIACAO PELO ID DO ACOMPANHAMENTO
    @Test
    @DisplayName("Buscar a avaliação utilizando o id do acompanhamento")
    public void testBuscarAvaliacaoPeloIdDoAcompanhamento() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(710, 904);
        AvaliacaoModel criarAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AvaliacaoModel delete = AvaliacaoDataFactory.buscarAvaliacaoPorIdDaAvaliacao(criarAvaliacao.getIdAvaliacao());
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.buscarAvaliacaoPorIdDoAcompanhamento(avaliacaoModel.getIdAcompanhamento());
        var response = avaliacaoService.buscarAvaliacaoPeloIdDoAcompanhamento(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(delete)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Erro ao buscar o acompanhamento sem o id do acompanhamento")
    public void testBuscarAvaliacaoComIdErrado() {
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.buscarAvaliacaoComIdInvalido(0000000);
        avaliacaoService.buscarAvaliacaoPeloIdDoAcompanhamento(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    //    endregion
//region BUSCAR AVALIACAO PELO ID DA AVALIACAO
    @Test
    @DisplayName("Buscar o acompanhamento utilizando o id do acompanhamento")
    public void testBuscarAvaliacaoPeloIdDaAvaliacao() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(710, 904);
        AvaliacaoModel avaliacaoCriada = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.buscarAvaliacaoPorIdDaAvaliacao(avaliacaoCriada.getIdAvaliacao());
        var response = avaliacaoService.buscarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(AvaliacaoModel.class);
        Assertions.assertEquals(response.getIdAcompanhamento(), avaliacaoCriada.getIdAcompanhamento());
        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Erro ao buscar avaliação com id inexistente")
    public void testBuscarAvaliacaoPeloIdDaAvaliacaoComIdErrado() {
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.buscarAvaliacaoPorIdDaAvaliacao(00000000000000000);
        var response = avaliacaoService.buscarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("getById.idAvaliacao: must be greater than or equal to 1"));
    }
//endregion
//region BUSCAR AVALIACOES POR TAMANHO E PAGINA
    @Test
    @DisplayName("Buscar avaliações por pagina e tamanho")
    public void testBuscarTodosAcompanhamentos() {
        avaliacaoService.buscarAvaliacaoPorPaginaETamanho(0, 5)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("pagina", Matchers.is(0))
                .body("tamanho", Matchers.is(5))
                .body("totalElementos", Matchers.greaterThanOrEqualTo(0))
        ;
    }

    @Test
    @DisplayName("Ero ao buscar avaliações por pagina e tamanho sem informar valor")
    public void testBuscarTodosAcompanhamentosSemPagina(){
        avaliacaoService.buscarAvaliacaoSemPaginaETamanho()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region DELETAR AVALIACAO
    @Test
    @DisplayName("deletar avaliação com sucesso")
    public void testDeletarAvaliacaoPeloIdDaAvaliacao() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(710, 904);
        AvaliacaoModel avaliacaoCriada = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.deletarAvaliacaoPorIdDaAvaliacao(avaliacaoCriada.getIdAvaliacao());
        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        var response = avaliacaoService.buscarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("Avaliação inexistente ou inativa"));
    }

    @Test
    @DisplayName("Erro ao deletar avaliação por não informar o id correto")
    public void testDeletarAvaliacaoPeloIdDaAvaliacaoComIdErrado() {
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.deletarAvaliacaoComIdInvalidodaAvaliacao(00000000000000000);
        var response = avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("delete.idAvaliacao: must be greater than or equal to 1"));
    }

    //endregion
//region DESATIVAR AVALIACAO
    @Test
    @DisplayName("Desativar avaliação com sucesso")
    public void testDesativarAvaliacaoPeloIdDaAvaliacao() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(710, 904);
        AvaliacaoModel avaliacaoCriar = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.deletarAvaliacaoPorIdDaAvaliacao(avaliacaoCriar.getIdAvaliacao());
        avaliacaoService.desativarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Erro ao desativar avaliação por nao informar o id correto")
    public void testDesativarAvaliacaoPeloIdDaAvaliacaoComIdErrado () {
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.desativarAvaliacaoComIdInvalidodaAvaliacao(00000000000000000);
        var response = avaliacaoService.desativarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("delete.idAvaliacao: must be greater than or equal to 1"));

    }
//endregion
}
