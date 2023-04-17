package test;

import base.BaseTest;
import dataFactory.*;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import model.*;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class AgendamentoTest extends BaseTest {
    AgendamentoModel agendamentoModel = new AgendamentoModel();
    AgendamentoService agendamentoService = new AgendamentoService();

//region CRIAR AGENDAMENTO
    @Test
    public void criarAgendamento() {
        agendamentoModel = AgendamentoDataFactory.gerarAgendamento(495);
        var response = agendamentoService.cadastraragendamento(agendamentoModel)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract().as(AgendamentoModel.class);
        AgendamentoModel agendamentoId = AgendamentoDataFactory.deletarAgendamento(response.getIdAgendamento());
        agendamentoService.deletaragendamento(agendamentoId)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }
    @Test
    public void criarAgendamentoComIdErrado() {
        agendamentoModel = AgendamentoDataFactory.gerarAgendamentoComIdErrado(00000000);
        var response = agendamentoService.cadastraragendamento(agendamentoModel)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                ;
    }
//endregion
//region ATUALIZAR AGENDAMENTO
    @Test
    public void atualizarAgendamento() {
        agendamentoModel = AgendamentoDataFactory.gerarAgendamento(495);
        AgendamentoModel agendamentoCriado = agendamentoService.cadastraragendamento(agendamentoModel).then().extract().as(AgendamentoModel.class);
        AgendamentoModel extrairId = AgendamentoDataFactory.deletarAgendamento(agendamentoCriado.getIdAgendamento());
        AgendamentoModel agendamentoAlterado = AgendamentoDataFactory.atualizarAgendamento(extrairId.getIdAgendamento());
        var response = agendamentoService.atualizarAgendamento(agendamentoAlterado , extrairId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().as(AgendamentoModel.class);
        agendamentoService.deletaragendamento(agendamentoAlterado)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }
//endregion
//region BUSCAR AGENDAMENTO PELO ID DO AGENDAMENTO
    @Test
    public void buscarAgendamentoPeloId() {
        agendamentoModel = AgendamentoDataFactory.gerarAgendamento(495);
        AgendamentoModel extrair = agendamentoService.cadastraragendamento(agendamentoModel).then().extract().as(AgendamentoModel.class);
        AgendamentoModel agendamentoId = AgendamentoDataFactory.deletarAgendamento(extrair.getIdAgendamento());
        var response = agendamentoService.buscarAgendamentoPorIdDoAgendamento(agendamentoId)
                .then()
                .statusCode(HttpStatus.SC_OK);
        response.log().all();
    }
    @Test
    public void buscarAgendamentoPeloIdDoAcompanhamento() {
        agendamentoModel = AgendamentoDataFactory.gerarAgendamento(495);
        AgendamentoModel extrair = agendamentoService.cadastraragendamento(agendamentoModel).then().extract().as(AgendamentoModel.class);
        AgendamentoModel idAcompanhamento = AgendamentoDataFactory.deletarAgendamento(agendamentoModel.getIdAcompanhamento());
        agendamentoService.buscarAgendamentoPorIdDoAcompanhamento(idAcompanhamento)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
//endregion
//region BUSCAR AGENDAMENTO
    @ParameterizedTest(name = "{index} - Página: {0} - Tamanho: {1}")
    @DisplayName("Buscar todos os acompanhamentos")
    @Story("Buscar trilhas")
    @Description("Buscar todos os acompanhamentos")
    @MethodSource("dataFactory.GeralDataFactory#providePaginasETamanhosDePaginaValidos")
    public void testBuscarTodosAgendamentos(int pagina, int tamanhoPagina) {
        agendamentoService.buscarAgendamentoPorPaginas(pagina, tamanhoPagina)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("pagina", Matchers.is(pagina))
                .body("tamanho", Matchers.is(tamanhoPagina))
                .body("totalElementos", Matchers.greaterThanOrEqualTo(0))
        ;
    }
    @ParameterizedTest(name = "{index} - Página: {0} - Tamanho: {1}")
    @DisplayName("Buscar todos os acompanhamentos")
    @Story("Buscar trilhas")
    @Description("Buscar todos os acompanhamentos")
    @MethodSource("dataFactory.GeralDataFactory#providePaginasETamanhosDePaginaValidos")
    public void testBuscaragendamentossemPagina(int pagina, int tamanhoPagina) {
        agendamentoService.buscarAgendamentoPorPaginas(tamanhoPagina,-000000)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON);
    }
//endregion
//region DELETAR AGENDAMENTO
    @Test
    public void deletarAgendamento() {
        agendamentoModel = AgendamentoDataFactory.gerarAgendamento(495);
        AgendamentoModel extrair = agendamentoService.cadastraragendamento(agendamentoModel).then().extract().as(AgendamentoModel.class);
        AgendamentoModel agendamentoId = AgendamentoDataFactory.deletarAgendamento(extrair.getIdAgendamento());
        agendamentoService.deletaragendamento(agendamentoId)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
        @Test
        public void deletarAgendamentoSemId() {
            agendamentoModel = AgendamentoDataFactory.deletarAgendamentoComIdErrado(000000000000000000);
            agendamentoService.deletaragendamento(agendamentoModel)
                    .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
        }
//endregion
}




