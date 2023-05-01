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
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AgendamentoTest extends BaseTest {
    AvaliacaoModel avaliacaoModel = new AvaliacaoModel();
    AvaliacaoService avaliacaoService = new AvaliacaoService();
    AgendamentoModel agendamentoModel = new AgendamentoModel();
    AgendamentoService agendamentoService = new AgendamentoService();

//region CRIAR AGENDAMENTO
    @Test
    @DisplayName("Criar agendamento com sucesso")
    public void testCriarAgendamento() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(710, 904);
        AvaliacaoModel deletarAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AgendamentoModel criarAgendamento = AgendamentoDataFactory.gerarAgendamento(deletarAvaliacao.getIdAvaliacao());
        var response = agendamentoService.cadastraragendamento(criarAgendamento)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(AgendamentoModel.class);
        Assertions.assertEquals(criarAgendamento.getidAvaliacao(), response.getidAvaliacao());
        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(deletarAvaliacao)
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
    @DisplayName("Atualizar agendamento com sucesso")
    public void testAtualizarAgendamento() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(710, 904);
        AvaliacaoModel deletarAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AgendamentoModel criarAgendamento = AgendamentoDataFactory.gerarAgendamento(deletarAvaliacao.getIdAvaliacao());
        AgendamentoModel agendamentoCriado = agendamentoService.cadastraragendamento(criarAgendamento).then().extract().as(AgendamentoModel.class);
        AgendamentoModel agendamentoAlterado = AgendamentoDataFactory.atualizarAgendamento(agendamentoCriado.getIdAgendamento());
        var response = agendamentoService.atualizarAgendamento(agendamentoCriado, agendamentoAlterado)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract()
                .as(AgendamentoModel.class);
        Assertions.assertEquals(agendamentoCriado.getResponsavel(), response.getResponsavel());
        Assertions.assertEquals(agendamentoCriado.getidAvaliacao(), response.getidAvaliacao());
        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(deletarAvaliacao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Erro ao atualizar agendamento, avaliação inexistente")
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
    @DisplayName("Buscar o agendamento pelo id do agendamento")
    public void btestBuscarAgendamentoPeloIdDoAgendamento() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(710, 904);
        AvaliacaoModel deletarAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AgendamentoModel criarAgendamento = AgendamentoDataFactory.gerarAgendamento(deletarAvaliacao.getIdAvaliacao());
        AgendamentoModel extrairId = agendamentoService.cadastraragendamento(criarAgendamento).then().extract().as(AgendamentoModel.class);
        AgendamentoModel agendamentoId = AgendamentoDataFactory.deletarAgendamento(extrairId.getIdAgendamento());
        var response = agendamentoService.buscarAgendamentoPorIdDoAgendamento(agendamentoId)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(AgendamentoModel.class);
        Assertions.assertEquals(extrairId.getResponsavel(), response.getResponsavel());
        Assertions.assertEquals(extrairId.getidAvaliacao(), response.getidAvaliacao());
        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(deletarAvaliacao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Erro ao buscar o agendamento, valor do id negativo")
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
//region LISTAR HORARIOS DISPONIVEIS
    @Test
    @DisplayName("lista os horarios disponiveis para agendamento")
    public void testListarHorariosDisponiveis(){
        var response = agendamentoService.listarhorariosDispiniveis(710)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
        assertNotNull(response);
    }
    @Test
    @DisplayName("Erro ao listar os horarios, id do acompanhamento invalido")
    public void testErroAoListarHorariosDisponiveis(){
        var response = agendamentoService.listarhorariosDispiniveis(-710)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listAllAvailableHorarios.idAcompanhamento: must be greater than or equal to 1"));
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
        var response = agendamentoService.buscarAgendamentoPorPaginas(-456,5)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listAll.page: must be greater than or equal to 0"));
    }
//endregion
//region DELETAR AGENDAMENTO
    @Test
    @DisplayName("Deletar agendamento com sucesso")
    public void testDeletarAgendamento() {
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(710, 904);
        AvaliacaoModel deletarAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoModel).then().extract().as(AvaliacaoModel.class);
        AgendamentoModel criarAgendamento = AgendamentoDataFactory.gerarAgendamento(deletarAvaliacao.getIdAvaliacao());
        AgendamentoModel extrairId = agendamentoService.cadastraragendamento(criarAgendamento).then().extract().as(AgendamentoModel.class);
        AgendamentoModel agendamentoId = AgendamentoDataFactory.deletarAgendamento(extrairId.getIdAgendamento());
        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(deletarAvaliacao)
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
    @DisplayName("Erro ao deletar agendamento, id inexistente")
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




