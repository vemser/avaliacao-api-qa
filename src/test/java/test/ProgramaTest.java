package test;

import base.BaseTest;

import dataFactory.ProgramaDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import model.JSONFailureResponse;
import model.ProgramaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ProgramaService;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class ProgramaTest extends BaseTest {
    ProgramaService programaService = new ProgramaService();

    //region CRIAR PROGRAMA
    @Test
    @DisplayName("Criar um programa com sucesso")
    public void testAdicionarPrograma() {
        ProgramaModel programaValido = ProgramaDataFactory.gerarProgramaValido();
        var response = programaService.criarPrograma(programaValido)
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        ProgramaModel programaResponse = response
                .extract()
                .as(ProgramaModel.class);
        assertThat(programaValido.getNome(), equalTo(programaResponse.getNome()));
        assertThat(programaValido.getDescricao(), equalTo(programaResponse.getDescricao()));
        assertThat(programaValido.getDataInicio(), equalTo(programaResponse.getDataInicio()));
        assertThat(programaValido.getDataFim(), equalTo(programaResponse.getDataFim()));
        assertThat(programaValido.getStatus(), equalTo(programaResponse.getStatus()));
        programaService.deletarPrograma(programaResponse.getIdPrograma())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Falha ao criar um programa com nome nulo")
    public void testAdicionarProgramaComNomeNulo() {
        ProgramaModel programaSemNome = ProgramaDataFactory.gerarProgramaComNomeNulo();
        var response = programaService.criarPrograma(programaSemNome)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
        JSONFailureResponse jsonFailureResponse = response.extract().as(JSONFailureResponse.class);
        assertTrue(jsonFailureResponse.getErrors().contains("nome: Nome não pode ser vazio ou nulo."));
    }

    //endregion
//    region ATUALIZAR PROGRAMA
    @Test
    @DisplayName("Editar programa com sucesso")
    public void testEditarPrograma() {
        ProgramaModel criarPrograma = ProgramaDataFactory.gerarProgramaValido();
        ProgramaModel programaCriado = programaService.criarPrograma(criarPrograma).then().extract().as(ProgramaModel.class);
        ProgramaModel idPrograma = ProgramaDataFactory.programaComValorDeIdNegativo(programaCriado.getIdPrograma());
        var response = programaService.atualizarPrograma(idPrograma, criarPrograma)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(ProgramaModel.class);
        assertThat(criarPrograma.getNome(), equalTo(response.getNome()));
        assertThat(criarPrograma.getDescricao(), equalTo(response.getDescricao()));
        assertThat(programaCriado.getStatus(), equalTo(response.getStatus()));
        programaService.deletarPrograma(programaCriado.getIdPrograma())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Falha ao editar programa com id vazio")
    public void testEditarProgramaComIdNegativo() {
        ProgramaModel corpo = ProgramaDataFactory.gerarProgramaValido();
        ProgramaModel idVazio = ProgramaDataFactory.atualizarProgramaComValorDeIdVazio();
        var response = programaService.atualizarPrograma(idVazio, corpo)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("update.idPrograma: must be greater than or equal to 1"));
    }
//    endregion
//region BUSCAR TODOS OS PROGRAMAS

    @Test
    @DisplayName("Buscar todos os programas com sucesso")
    public void testBuscarTodosProgramas() {
        var response = programaService.buscarProgramas(0, 5)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body();
        int tamanho = response.path("size()");
        assertEquals(5, tamanho);
    }

    @Test
    @DisplayName("Falha ao buscar programas com numero de pagina com valor negativo")
    public void testBuscarTodosProgramasNumeroPaginaVazio() {
        var response = programaService.buscarProgramas(-5, 5)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listAll.page: must be greater than or equal to 0"));
    }

    @Test
    @DisplayName("Falha ao buscar programas com tamanho em vazio")
    public void testBuscarTodosProgramasSemTamanho() {
        var response = programaService.buscarProgramas(0, -5)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listAll.size: must be greater than or equal to 1"));
    }
//endregion
//region BUSCAR PROGRAMA POR ID

    @Test
    @DisplayName("Buscar programa por id com sucesso")
    public void testBuscarPorId() {
        ProgramaModel programaValido = ProgramaDataFactory.gerarProgramaValido();
        ProgramaModel programaCriado = programaService.criarPrograma(programaValido)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(ProgramaModel.class);
        var response = programaService.buscarPrograma(programaCriado)
                .then()
                .statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.JSON);
        response.assertThat().statusCode(HttpStatus.SC_OK);
        ProgramaModel programaResponse = response.extract().as(ProgramaModel.class);
        assertThat(programaValido.getNome(), equalTo(programaResponse.getNome()));
        assertThat(programaValido.getDescricao(), equalTo(programaResponse.getDescricao()));
        assertThat(programaCriado.getIdPrograma(), equalTo(programaResponse.getIdPrograma()));
        programaService.deletarPrograma(programaResponse.getIdPrograma())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Falha ao buscar programa com o valor do id negativo")
    public void testBuscarProgramaComLetrasNoId() {
        ProgramaModel idNegativo = ProgramaDataFactory.programaComValorDeIdNegativo(-145);
        var response = programaService.buscarPrograma(idNegativo)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("getById.idPrograma: must be greater than or equal to 1"));
    }

    //endregion
//region BUSCAR PROGRAMA ABERTO
    @Test
    @DisplayName(("Lista o programa que está aberto"))
    public void testBuscarProgramaAberto() {
        ProgramaModel programa = programaService.buscarProgramaAberto()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(ProgramaModel.class);
        assertNotNull(programa);
    }

    //endregion
//region BUSCAR PROGRAMAS COM TRILHA
    @Test
    @DisplayName("Buscar todos os programas que contenham trilhas")
    public void testBuscarProgramasComTrilha() {
        var response = programaService.buscarProgramasComTrilha(0, 5)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body();
        int tamanho = response.path("size()");
        assertEquals(5, tamanho);
    }

    @Test
    @DisplayName("Falha ao buscar programas com numero de pagina com valor negativo")
    public void testBuscarProgramasComTrilhaNumeroPaginaVazio() {
        var response = programaService.buscarProgramasComTrilha(-2, 5)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listWithTrilhas.page: must be greater than or equal to 0"));
    }

    //endregion
//    region DESATIVAR PROGRAMA
    @Test
    @DisplayName("Desativar programa com sucesso")
    public void testDesativarPrograma() {
        ProgramaModel criarPrograma = ProgramaDataFactory.gerarProgramaValido();
        ProgramaModel programaCriado = programaService.criarPrograma(criarPrograma)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(ProgramaModel.class);
        programaService.desativarPrograma(programaCriado)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Falha ao desativar programa com o valor do id zero")
    public void testDesativarProgramaComIdDeValorZero() {
        ProgramaModel idValorZero = ProgramaDataFactory.programaComIdDeValorZero();
        var response = programaService.desativarPrograma(idValorZero)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("deactivate.idPrograma: must be greater than or equal to 1"));
    }

    //    endregion
//    region DELETAR PROGRAMA
    @Test
    @DisplayName("Deletar programa com sucesso")
    public void testDeletarPrograma() {
        ProgramaModel criarPrograma = ProgramaDataFactory.gerarProgramaValido();
        ProgramaModel programaCriado = programaService.criarPrograma(criarPrograma)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(ProgramaModel.class);
        programaService.deletarPrograma(programaCriado.getIdPrograma())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        var response = programaService.buscarPrograma(programaCriado)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("Programa não encontrada."));
    }

    @Test
    @DisplayName("Falha ao deletar programa com id inexistente negativo")
    public void testDeletarProgramaComIdInexistente() {
        ProgramaModel idNegativo = ProgramaDataFactory.programaComValorDeIdNegativo(-45621);
        var response = programaService.deletarPrograma(idNegativo.getIdPrograma())
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("delete.idPrograma: must be greater than or equal to 1"));
    }
//    endregion
}