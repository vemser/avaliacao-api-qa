package test;

import base.BaseTest;
import dataFactory.*;
import io.restassured.http.ContentType;
import model.*;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.*;
public class AgendamentoTest extends BaseTest {
    AgendamentoModel agendamentoModel = new AgendamentoModel();
    AgendamentoService agendamentoService = new AgendamentoService();

//region CRIAR AGENDAMENTO
    @Test
    @DisplayName("Criar agendamento com sucesso")
    public void testCriarAgendamento() {
        AgendamentoModel criarAgendamento = AgendamentoDataFactory.gerarAgendamento(742);
        var response = agendamentoService.cadastraragendamento(criarAgendamento)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(AgendamentoModel.class);
        Assertions.assertEquals(criarAgendamento.getidAvaliacao(), response.getidAvaliacao());
        AgendamentoModel agendamentoId = AgendamentoDataFactory.deletarAgendamento(response.getIdAgendamento());
        agendamentoService.deletaragendamento(agendamentoId)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }
    @Test
    @DisplayName("Erro ao criar um agendamento por não informar o id correto")
    public void testCriarAgendamentoComIdInvalido() {
        agendamentoModel = AgendamentoDataFactory.gerarAgendamentoComIdErrado(00000000);
        var response = agendamentoService.cadastraragendamento(agendamentoModel)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("idAvaliacao: must be greater than or equal to 1"));
    }
//endregion
//region ATUALIZAR AGENDAMENTO
    @Test
    public void testAtualizarAgendamento() {
        agendamentoModel = AgendamentoDataFactory.gerarAgendamento(745);
        AgendamentoModel agendamentoCriado = agendamentoService.cadastraragendamento(agendamentoModel).then().extract().as(AgendamentoModel.class);
        AgendamentoModel agendamentoAlterado = AgendamentoDataFactory.atualizarAgendamento(agendamentoCriado.getIdAgendamento());
        var response = agendamentoService.atualizarAgendamento(agendamentoCriado, agendamentoAlterado)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract()
                .as(AgendamentoModel.class);
        Assertions.assertEquals(agendamentoCriado.getResponsavel(), response.getResponsavel());
        Assertions.assertEquals(agendamentoCriado.getidAvaliacao(), response.getidAvaliacao());
        agendamentoService.deletaragendamento(agendamentoAlterado)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    public void testErroAoAtualizarAgendamento() {
        agendamentoModel = AgendamentoDataFactory.gerarAgendamento(-452315745);
        AgendamentoModel agendamentoAlterado = AgendamentoDataFactory.atualizarAgendamento(-4562165);
        var response = agendamentoService.atualizarAgendamento(agendamentoModel, agendamentoAlterado)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("idAvaliacao: must be greater than or equal to 1"));

    }
//endregion
//region BUSCAR AGENDAMENTO PELO ID DO AGENDAMENTO
    @Test
    public void btestBuscarAgendamentoPeloIdDoAgendamento() {
        agendamentoModel = AgendamentoDataFactory.gerarAgendamento(745);
        AgendamentoModel extrairId = agendamentoService.cadastraragendamento(agendamentoModel).then().extract().as(AgendamentoModel.class);
        AgendamentoModel agendamentoId = AgendamentoDataFactory.deletarAgendamento(extrairId.getIdAgendamento());
        var response = agendamentoService.buscarAgendamentoPorIdDoAgendamento(agendamentoId)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(AgendamentoModel.class);
        Assertions.assertEquals(extrairId.getResponsavel(), response.getResponsavel());
        Assertions.assertEquals(extrairId.getidAvaliacao(), response.getidAvaliacao());
        agendamentoService.deletaragendamento(agendamentoId)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    public void testErroAoBuscarAgendamento() {
        AgendamentoModel idAcompanhamento = AgendamentoDataFactory.deletarAgendamento(-12345);
        var response = agendamentoService.buscarAgendamentoPorIdDoAgendamento(idAcompanhamento)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("getById.idAgendamento: must be greater than or equal to 1"));
    }
//endregion
//region BUSCAR AGENDAMENTO
    @Test
    @DisplayName("Buscar todos os acompanhamentos")
    public void testBuscarTodosAgendamentos() {
        agendamentoService.buscarAgendamentoPorPaginas(0, 5)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("pagina", Matchers.is(0))
                .body("tamanho", Matchers.is(5))
                .body("totalElementos", Matchers.greaterThanOrEqualTo(0))
        ;
    }
    @Test
    @DisplayName("Buscar todos os acompanhamentos")
    public void testBuscaragendamentossemPagina() {
        var response = agendamentoService.buscarAgendamentoPorPaginas(-456,-000000)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listAll.size: must be greater than or equal to 1, listAll.page: must be greater than or equal to 0"));
    }
//endregion
//region DELETAR AGENDAMENTO
    @Test
    public void testDeletarAgendamento() {
        agendamentoModel = AgendamentoDataFactory.gerarAgendamento(745);
        AgendamentoModel extrair = agendamentoService.cadastraragendamento(agendamentoModel).then().extract().as(AgendamentoModel.class);
        AgendamentoModel agendamentoId = AgendamentoDataFactory.deletarAgendamento(extrair.getIdAgendamento());
        agendamentoService.deletaragendamento(agendamentoId)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        var response = agendamentoService.buscarAgendamentoPorIdDoAgendamento(agendamentoId)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("Agendamento não encontrado"));
    }
        @Test
        public void testDeletarAgendamentoSemId() {
            agendamentoModel = AgendamentoDataFactory.deletarAgendamentoComIdErrado(-555);
           var response =  agendamentoService.deletaragendamento(agendamentoModel)
                    .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(JSONFailureResponse.class);
            Assertions.assertTrue(response.getMessage().contains("delete.idAgendamento: must be greater than or equal to 1"));
        }
//endregion
}




