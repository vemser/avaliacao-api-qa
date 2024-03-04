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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AgendamentoTest extends BaseTest {
    AvaliacaoService avaliacaoService = new AvaliacaoService();
    AgendamentoService agendamentoService = new AgendamentoService();

    //region CRIAR AGENDAMENTO
    @Test
    @DisplayName("Criar agendamento com sucesso")
    public void testCriarAgendamento() {
        AvaliacaoModel avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(20, 1);
        AvaliacaoModel deletarAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoModel).log().all().extract().as(AvaliacaoModel.class);

        AgendamentoModel criarAgendamento = AgendamentoDataFactory.gerarAgendamento(deletarAvaliacao.getIdAvaliacao(), LocalDateTime.of(2024, 3, 6, 9, 0, 0));

        System.out.println("teste " + criarAgendamento.idAgendamento);

        var response = agendamentoService.cadastraragendamento(criarAgendamento)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(AgendamentoModel.class);

        Assertions.assertEquals(criarAgendamento.getIdAvaliacao(), response.getIdAvaliacao());

        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(deletarAvaliacao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    @DisplayName("Erro ao criar um agendamento por não informar o id correto")
    public void testCriarAgendamentoComIdInvalido() {
        AgendamentoModel agendamentoModel = AgendamentoDataFactory.gerarAgendamentoComIdErrado();
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
        AvaliacaoModel avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(23, 1);
        AvaliacaoModel deletarAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoModel).extract().as(AvaliacaoModel.class);

        AgendamentoModel criarAgendamento = AgendamentoDataFactory.gerarAgendamento(deletarAvaliacao.getIdAvaliacao(), LocalDateTime.of(2024, 3, 13, 9, 0, 0));
        AgendamentoModel agendamentoCriado = agendamentoService.cadastraragendamento(criarAgendamento).then().extract().as(AgendamentoModel.class);
        AgendamentoModel agendamentoAlterado = AgendamentoDataFactory.atualizarAgendamento(agendamentoCriado, agendamentoCriado.idAvaliacao, LocalDateTime.of(2024, 3, 16, 9, 0, 0));

        var response = agendamentoService.atualizarAgendamento(agendamentoCriado, agendamentoAlterado)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract()
                .as(AgendamentoModel.class);

        Assertions.assertEquals(agendamentoCriado.getResponsavel(), response.getResponsavel());
        Assertions.assertEquals(agendamentoCriado.getIdAvaliacao(), response.getIdAvaliacao());

        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(deletarAvaliacao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Erro ao atualizar agendamento, avaliação inexistente")
    public void testErroAoAtualizarAgendamento() {
        AgendamentoModel agendamentoModel = AgendamentoDataFactory.gerarAgendamento(0, LocalDateTime.now());
        AgendamentoModel agendamentoAlterado = AgendamentoDataFactory.atualizarAgendamento(agendamentoModel, 0, LocalDateTime.now());
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
        AvaliacaoModel avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(1, 1);
        AvaliacaoModel deletarAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoModel).extract().as(AvaliacaoModel.class);

        AgendamentoModel criarAgendamento = AgendamentoDataFactory.gerarAgendamento(deletarAvaliacao.getIdAvaliacao(), LocalDateTime.of(2024, 4, 30, 0, 0, 0));
        AgendamentoModel extrairId = agendamentoService.cadastraragendamento(criarAgendamento).then().extract().as(AgendamentoModel.class);
        AgendamentoModel agendamentoId = AgendamentoDataFactory.deletarAgendamento(extrairId.getIdAgendamento());

        var response = agendamentoService.buscarAgendamentoPorIdDoAgendamento(agendamentoId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(AgendamentoModel.class);

        Assertions.assertEquals(extrairId.getResponsavel(), response.getResponsavel());
        Assertions.assertEquals(extrairId.getIdAvaliacao(), response.getIdAvaliacao());

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
    public void testListarHorariosDisponiveis() {
        var response = agendamentoService.listarhorariosDispiniveis(1)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);

        assertNotNull(response);
    }

    @Test
    @DisplayName("Erro ao listar os horarios, id do acompanhamento invalido")
    public void testErroAoListarHorariosDisponiveis() {
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
        var response = agendamentoService.buscarAgendamentoPorPaginas(-456, 5)
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
        AvaliacaoModel gerarAvaliacao = AvaliacaoDataFactory.gerarAvaliacaoValida(1, 1);
        AvaliacaoModel avaliacaoCriada = avaliacaoService.criarAvaliacao(gerarAvaliacao).extract().as(AvaliacaoModel.class);

        AgendamentoModel gerarAgendamento = AgendamentoDataFactory.gerarAgendamento(avaliacaoCriada.getIdAvaliacao(), LocalDateTime.of(2024, 4, 30, 0, 0, 0));
        AgendamentoModel agendamentoCriado = agendamentoService.cadastraragendamento(gerarAgendamento).then().extract().as(AgendamentoModel.class);
        AgendamentoModel gerarDeleteAgendamento = AgendamentoDataFactory.deletarAgendamento(agendamentoCriado.getIdAgendamento());

        avaliacaoService.deletarAvaliacaoPeloIdDaAvaliacao(avaliacaoCriada)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        var response = agendamentoService.buscarAgendamentoPorIdDoAgendamento(gerarDeleteAgendamento)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);

        Assertions.assertTrue(response.getMessage().contains("Agendamento não encontrado"));
    }

    @Test
    @DisplayName("Erro ao deletar agendamento, id inexistente")
    public void testDeletarAgendamentoSemId() {
        AgendamentoModel agendamentoModel = AgendamentoDataFactory.deletarAgendamentoComIdErrado();
        var response = agendamentoService.deletaragendamento(agendamentoModel)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("delete.idAgendamento: must be greater than or equal to 1"));
    }
//endregion
}




