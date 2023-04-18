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
    public void criarAvaliacao() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(712, 904);
        var response = avaliacaoService.criarAvaliacao(avaliacaoModel)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(AvaliacaoModel.class);
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.buscarAvaliacaoPorIdDaAvaliacao(response.getIdAvaliacao());
        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(avaliacao)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        System.out.println(response);

    }

    @Test
    public void criarAvaliacaoSemIdDeEstadiario() {
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
    public void atualizarAvaliacao() {
        avaliacaoModel = AvaliacaoDataFactory.alterarAvaliacao(712, 904);
        AvaliacaoModel criarAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.buscarAvaliacaoPorIdDaAvaliacao(criarAvaliacao.getIdAvaliacao());
        AvaliacaoModel avaliacaoAlterada = AvaliacaoDataFactory.alterarAvaliacao(criarAvaliacao.getIdAcompanhamento(), criarAvaliacao.getIdEstagiario());
        var response = avaliacaoService.atualizarAvaliacao(criarAvaliacao, avaliacaoAlterada)
                .then()
                .statusCode(HttpStatus.SC_OK);
        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.log().all();
    }

    //endregion
//    region BUSCAR AVALIACAO PELO ID DO ACOMPANHAMENTO
    @Test
    public void buscarAvaliacaoPeloIdDoAcompanhamento() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(712, 904);
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
        System.out.println(response);
    }

    @Test
    public void buscarAvaliacaoComIdErrado() {
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.buscarAvaliacaoComIdInvalido(0000000);
        avaliacaoService.buscarAvaliacaoPeloIdDoAcompanhamento(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    //    endregion
//region BUSCAR AVALIACAO PELO ID DA AVALIACAO
    @Test
    public void buscarAvaliacaoPeloIdDaAvaliacao() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(712, 904);
        AvaliacaoModel avaliacaoCriada = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.buscarAvaliacaoPorIdDaAvaliacao(avaliacaoCriada.getIdAvaliacao());
        var response = avaliacaoService.buscarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void buscarAvaliacaoPeloIdDaAvaliacaoComIdErrado() {
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.buscarAvaliacaoPorIdDaAvaliacao(00000000000000000);
        var response = avaliacaoService.buscarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON);
        response.log().all();
    }

    //endregion
//region BUSCAR AVALIACOES POR TAMANHO E PAGINA
    @ParameterizedTest(name = "{index} - Página: {0} - Tamanho: {1}")
    @DisplayName("Buscar todos os acompanhamentos")
    @Story("Buscar trilhas")
    @Description("Buscar todos os acompanhamentos")
    @MethodSource("dataFactory.GeralDataFactory#providePaginasETamanhosDePaginaValidos")
    public void testBuscarTodosAcompanhamentos(int pagina, int tamanhoPagina) {
        avaliacaoService.buscarAvaliacaoPorPaginaETamanho(pagina, tamanhoPagina)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("pagina", Matchers.is(pagina))
                .body("tamanho", Matchers.is(tamanhoPagina))
                .body("totalElementos", Matchers.greaterThanOrEqualTo(0))
        ;
    }

    @Test
    @DisplayName("Buscar todos os acompanhamentos")
    @Story("Buscar trilhas")
    @Description("Buscar todos os acompanhamentos")
    public void testBuscarTodosAcompanhamentosSemPagina(){
        avaliacaoService.buscarAvaliacaoSemPaginaETamanho()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    //endregion
//region DELETAR AVALIACAO
    @Test
    public void deletarAvaliacaoPeloIdDaAvaliacao() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(712, 904);
        AvaliacaoModel avaliacaoCriada = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.deletarAvaliacaoPorIdDaAvaliacao(avaliacaoCriada.getIdAvaliacao());
        var response = avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.log().all();
    }

    @Test
    public void deletarAvaliacaoPeloIdDaAvaliacaoComIdErrado() {
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.deletarAvaliacaoComIdInvalidodaAvaliacao(00000000000000000);
        var response = avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    //endregion
//region DESATIVAR AVALIACAO
    @Test
    public void desativarAvaliacaoPeloIdDaAvaliacao() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(712, 904);
        AvaliacaoModel avaliacaoCriar = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AvaliacaoModel avaliacao = AvaliacaoDataFactory.deletarAvaliacaoPorIdDaAvaliacao(avaliacaoCriar.getIdAvaliacao());
        avaliacaoService.desativarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .log().all()
        ;

    }
        @Test
        public void desativarAvaliacaoPeloIdDaAvaliacaoComIdErrado () {
            AvaliacaoModel avaliacao = AvaliacaoDataFactory.desativarAvaliacaoComIdInvalidodaAvaliacao(00000000000000000);
            avaliacaoService.desativarAvaliacaoPeloIdDaAvaliacao(avaliacao)
                    .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
        }
//endregion
}
