package test;

import base.BaseTest;
import dataFactory.AcompanhamentoDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import model.AcompanhamentoModel;
import model.JSONFailureResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.AcompanhamentoService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AcompanhamentoTest extends BaseTest {
    AcompanhamentoService acompanhamentoService = new AcompanhamentoService();
    AcompanhamentoModel acompanhamentoModel = new AcompanhamentoModel();

//region CRIAR ACOMPANHAMENTO
    @Test
    @DisplayName("Criar um acompanhamento com sucesso")
    public void testCadastrarAcompanhamento(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(861);
        var response = acompanhamentoService.criarAcompanhamento(acompanhamentoModel)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(AcompanhamentoModel.class);
        assertThat(acompanhamentoModel.getTitulo(), equalTo(response.getTitulo()));
        assertThat(acompanhamentoModel.getDataInicio(), equalTo(response.getDataInicio()));
        assertThat(acompanhamentoModel.getDescricao(), equalTo(response.getDescricao()));
        AcompanhamentoModel id = AcompanhamentoDataFactory.deletarAcompanhamentoPorId(response.getIdAcompanhamento());
        acompanhamentoService.deletarAcompanhamentoPeloId(id)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
      }

    @Test
    @DisplayName("Falha ao criar um acompanhamento sem id do programa")
    public void testCadastrarAcompanhamentoSemId(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoSemId(123);
        var response = acompanhamentoService.criarAcompanhamento(acompanhamentoModel)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("idPrograma: must not be null"));
    }
//endregion
//region ATUALIZAR ACOMPANHAMENTO
    @Test
    @DisplayName("Atualizar acompanhamento com sucesso")
    public void testAtualizarAcompanhamento() {
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(861);
        AcompanhamentoModel criarAcompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
        AcompanhamentoModel extrairId = AcompanhamentoDataFactory.deletarAcompanhamentoPorId(criarAcompanhamento.getIdAcompanhamento());
        AcompanhamentoModel acompanhamentoAlterado = AcompanhamentoDataFactory.alterarAcompanhamento(criarAcompanhamento.getIdAcompanhamento());
        var response = acompanhamentoService.atualizarAcompanhamento(acompanhamentoAlterado, criarAcompanhamento)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(AcompanhamentoModel.class);
        assertThat(acompanhamentoModel.getTitulo(), equalTo(response.getTitulo()));
        assertThat(acompanhamentoModel.getDataInicio(), equalTo(response.getDataInicio()));
        assertThat(acompanhamentoModel.getDescricao(), equalTo(response.getDescricao()));
        acompanhamentoService.deletarAcompanhamentoPeloId(extrairId)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Erro ao atualizar o acompanhamento, pois nao foi inserido o id do programa")
    public void testAtualizarAcompanhamentoSemId() {
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(861);
        AcompanhamentoModel criarAcompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
        AcompanhamentoModel acompanhamentoAlterado = AcompanhamentoDataFactory.alterarAcompanhamentoSemId(000000000000);
        var response = acompanhamentoService.atualizarAcompanhamento(acompanhamentoAlterado, criarAcompanhamento)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("idPrograma: must be greater than or equal to 1"));
        acompanhamentoService.deletarAcompanhamentoPeloId(criarAcompanhamento)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
//endregion
//region LISTAR ACOMPANHAMENTOS POR PROGRAMA
    @Test
    @DisplayName("Listar acompanhamento pelo id do programa")
    public void testBuscarAcompanhamentoPorIdDePrograma(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(1346);
        var response = acompanhamentoService.buscarAcompanhamentoPeloId(acompanhamentoModel)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("idPrograma", equalTo(1346));
    }
    @Test
    @DisplayName("Falha ao listar acompanhamento com numero de id invalido")
    public void testBuscarProgramaPorIdDePrograma(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(00000000000000000);
        var response = acompanhamentoService.buscarAcompanhamentoPeloId(acompanhamentoModel)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listAllByPrograma.idPrograma: must be greater than or equal to 1"));
    }
//endregion
//region BUSCAR TODOS ACOMPANHAMENTOS
    @Test
    @DisplayName("Buscar todos os acompanhamentos")
    public void testBuscarTodosAcompanhamentos() {
        acompanhamentoService.buscarAcompanhamentoPorPaginas(0, 2)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("pagina", Matchers.is(0))
                .body("tamanho", Matchers.is(2))
                .body("totalElementos", Matchers.greaterThanOrEqualTo(0));
    }
    @Test
    @DisplayName("Falha ao buscar todos os acompanhamentos por nao informar o numero de paginas e tamanho")
    public void testBuscarTodosAcompanhamentosSemPagina() {
        acompanhamentoService.buscarAcompanhamentoSemInformarPaginas()
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region BUSCAR ACOMPANHAMENTO PELO ID DO ACOMPANHAMENTO
    @Test
    @DisplayName("Buscar acompanhamento por id do programa")
    public void testBuscarAcompanhamentoPorIdDoPrograma(){
        var response = acompanhamentoService.buscarAcompanhamentoPeloIdDoAcompanhamento(724)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract()
                .as(AcompanhamentoModel.class);
        assertThat(acompanhamentoModel.getNome(), equalTo(response.getNome()));
    }
    @Test
    @DisplayName("Falha ao buscar acompanhamento com id do acompanhamento inexistente")
    public void testBuscarAcompanhamentoSemId(){
        var response = acompanhamentoService.buscarAcompanhamentoPeloIdDoAcompanhamento(000000000)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("getById.idAcompanhamento: must be greater than or equal to 1"));
    }
//endregion
//region DESATIVAR ACOMPANHAMENTO
    @Test
    @DisplayName("Desativar um acompanhamento com sucesso")
    public void testDesativatAcompanhamento(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(861);
        AcompanhamentoModel acompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
        AcompanhamentoModel idAcompanhamento = AcompanhamentoDataFactory.deletarAcompanhamentoPorId(acompanhamento.getIdAcompanhamento());
        acompanhamentoService.desativarAcompanhamentoPeloId(idAcompanhamento)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        acompanhamentoService.deletarAcompanhamentoPeloId(idAcompanhamento)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Falha em deletar um acompanhamento com id inexistente")
    public void testDesativarAcompanhamentoIdInvalido(){
        acompanhamentoModel = AcompanhamentoDataFactory.deletarAcompanhamentocomIdErrado(00000000);
        var response = acompanhamentoService.desativarAcompanhamentoPeloId(acompanhamentoModel)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("deactivate.idAcompanhamento: must be greater than or equal to 1"));

    }
//endregion
//region DELETAR ACOMPANHAMENTO
    @Test
    @DisplayName("Deletar um acompanhamento com sucesso")
    public void testDeletarAcompanhamento(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(861);
        AcompanhamentoModel acompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
        AcompanhamentoModel idAcompanhamento = AcompanhamentoDataFactory.deletarAcompanhamentoPorId(acompanhamento.getIdAcompanhamento());
        acompanhamentoService.deletarAcompanhamentoPeloId(idAcompanhamento)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        var response = acompanhamentoService.buscarAcompanhamentoPeloIdDoAcompanhamento(idAcompanhamento.getIdAcompanhamento())
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("Acompanhamento não encontrado"));
    }
    @Test
    @DisplayName("Falha em deletar um acompanhamento com id inexistente")
    public void testDeletarAcompanhamentoIdInvalido(){
        acompanhamentoModel = AcompanhamentoDataFactory.desativarAcompanhamentocomIdErrado(00000000);
        var response = acompanhamentoService.desativarAcompanhamentoPeloId(acompanhamentoModel)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("deactivate.idAcompanhamento: must be greater than or equal to 1"));
    }
//endregion
}
