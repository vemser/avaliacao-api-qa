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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProgramaTest extends BaseTest {
    ProgramaModel programaValido = ProgramaDataFactory.gerarProgramaValido();
    ProgramaService programaService = new ProgramaService();
//    region TESTES DE CRIAR PROGRAMA!
    @Test
    @DisplayName("Criar um programa com sucesso")
    @Story("Criar um programa")
    @Description("Criar um programa com sucesso")
    public void testAdicionarPrograma(){
        var response = programaService.criarPrograma(programaValido)
                .then()
                    .statusCode(HttpStatus.SC_CREATED);
        response.assertThat().contentType(ContentType.JSON);
        response.assertThat().statusCode(HttpStatus.SC_CREATED);
        ProgramaModel programaResponse = response.extract().as(ProgramaModel.class);
        assertThat(programaValido.getNome(), equalTo(programaResponse.getNome()));
        assertThat(programaValido.getDescricao(), equalTo(programaResponse.getDescricao()));
        assertThat(programaValido.getDataInicio(), equalTo(programaResponse.getDataInicio()));
        assertThat(programaValido.getDataFim(), equalTo(programaResponse.getDataFim()));
        assertThat(programaValido.getStatus(), equalTo(programaResponse.getStatus()));
        programaService.deletarPrograma(programaResponse)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Falha ao criar um programa com nome nulo")
    @Story("Criar um programa")
    @Description("Falha ao criar um programa com nome nulo")
    public void testAdicionarProgramaComErro(){
        var response = programaService.criarProgramaSemNome()
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
        JSONFailureResponse jsonFailureResponse = response.extract().as(JSONFailureResponse.class);
        Assertions.assertTrue(jsonFailureResponse.getErrors().contains("nome: size must be between 10 and 2147483647"));
        Assertions.assertTrue(jsonFailureResponse.getErrors().contains("nome: Nome não pode ser vazio ou nulo."));
    }
    @Test
    @DisplayName("Falha ao criar um programa com datas abaixo da atual")
    @Story("Criar um programa")
    @Description("Falha ao criar um programa com datas abaixo da atual")
    public void testCriarProgramaComDataAbaixoDaAtual(){
        var response = programaService.criarProgramaComDatasAbaixoDaAtual()
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
        JSONFailureResponse jsonFailureResponse = response.extract().as(JSONFailureResponse.class);
        Assertions.assertTrue(jsonFailureResponse.getErrors().contains("dataInicio: must be a date in the present or in the future"));
        Assertions.assertTrue(jsonFailureResponse.getErrors().contains("dataFim: must be a date in the present or in the future"));
    }
//    endregion
//    region TESTES DE BUSCAR PROGRAMA

    @Test
    @DisplayName("Buscar todos os programas com sucesso")
    @Story("Buscar programas")
    @Description("Buscar todos os programas com sucesso")
    public void testBuscarTodosProgramas(){
        var response = programaService.buscarProgramas()
            .then()
                .statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.JSON);
        response.assertThat().statusCode(HttpStatus.SC_OK);
    }
    @Test
    @DisplayName("Falha ao buscar programas com numero de pagina em vazio")
    @Story("Buscar programas")
    @Description("Falha ao buscar programas com numero de pagina em vazio")
    public void testBuscarTodosProgramasNumeroPaginaVazio(){
        var response = programaService.buscarProgramasNumeroPaginaVazio()
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @Test
    @DisplayName("Falha ao buscar programas com tamanho em vazio")
    @Story("Buscar programas")
    @Description("Falha ao buscar programas com tamanho em vazio")
    public void testBuscarTodosProgramasSemTamanho(){
        var response = programaService.buscarProgramasTamanhoVazio()
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//    region TESTES DE BUSCAR PROGRAMA POR ID

    @Test
    @DisplayName("Buscar programa por id com sucesso")
    @Story("Buscar programas")
    @Description("Buscar programa por id com sucesso")
    public void testBuscarPorId(){
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
        assertThat(programaValido.getDataInicio(), equalTo(programaResponse.getDataInicio()));
        assertThat(programaValido.getDataFim(), equalTo(programaResponse.getDataFim()));
        assertThat(programaValido.getStatus(), equalTo(programaResponse.getStatus()));
        assertThat(programaCriado.getIdPrograma(), equalTo(programaResponse.getIdPrograma()));
        assertThat(programaCriado.getAtivo(), equalTo(programaResponse.getAtivo()));
        programaService.deletarPrograma(programaResponse)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Falha ao buscar programa por id vazio")
    @Story("Buscar programas")
    @Description("Falha ao buscar programa por id vazio")
    public void testBuscarProgramaSemId(){
        var response = programaService.buscarProgramaSemId()
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().contentType(ContentType.JSON);
        JSONFailureResponse jsonFailureResponse = response.extract().as(JSONFailureResponse.class);
        assertThat(jsonFailureResponse.getError(), equalTo("Not Found"));
        assertThat(jsonFailureResponse.getPath(), equalTo("/programa/get-by-id/"));
    }
    @Test
    @DisplayName("Falha ao buscar programa por id invalido com letras")
    @Story("Buscar programas")
    @Description("Falha ao buscar programa por id invalido com letras")
    public void testBuscarProgramaComLetrasNoId(){
        var response = programaService.buscarProgramaComLetrasNoId()
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//    region EDITAR PROGRAMA
    @Test
    @DisplayName("Editar programa com sucesso")
    @Story("Editar programa")
    @Description("Editar programa com sucesso")
    public void testEditarPrograma(){
        ProgramaModel programaCriado = programaService.criarPrograma(programaValido)
                .then()
                    .statusCode(HttpStatus.SC_CREATED)
                .extract().as(ProgramaModel.class);
        ProgramaModel programaNovo = ProgramaDataFactory.gerarProgramaValido();
        var response = programaService.atualizarPrograma(programaCriado, programaNovo)
                .then()
                    .statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.JSON);
        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.JSON);
        ProgramaModel programaResponse = response.extract().as(ProgramaModel.class);
        assertThat(programaNovo.getNome(), equalTo(programaResponse.getNome()));
        assertThat(programaNovo.getDescricao(), equalTo(programaResponse.getDescricao()));
        assertThat(programaNovo.getDataInicio(), equalTo(programaResponse.getDataInicio()));
        assertThat(programaNovo.getDataFim(), equalTo(programaResponse.getDataFim()));
        assertThat(programaNovo.getStatus(), equalTo(programaResponse.getStatus()));
        assertThat(programaCriado.getIdPrograma(), equalTo(programaResponse.getIdPrograma()));
        assertThat(programaCriado.getAtivo(), equalTo(programaResponse.getAtivo()));
        //Deleta o programa criado
        programaService.deletarPrograma(programaCriado)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Falha ao editar programa com id vazio")
    @Story("Editar programa")
    @Description("Falha ao editar programa com id vazio")
    public void testEditarProgramaComIdVazio(){
        var response = programaService.atualizarProgramaComIdVazio()
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().contentType(ContentType.JSON);
        JSONFailureResponse jsonFailureResponse = response.extract().as(JSONFailureResponse.class);
        assertThat(jsonFailureResponse.getError(), equalTo("Not Found"));
        assertThat(jsonFailureResponse.getPath(), equalTo("/programa/update/"));
    }
    @Test
    @DisplayName("Falha ao editar programa com id inexistente")
    @Story("Editar programa")
    @Description("Falha ao editar programa com id inexistente")
    public void testEditarProgramaComIdInexistente(){
        var response = programaService.atualizarProgramaComIdInexistente()
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().contentType(ContentType.JSON);
        JSONFailureResponse jsonFailureResponse = response.extract().as(JSONFailureResponse.class);
    }
//    endregion
//    region DESATIVAR PROGRAMA
    @Test
    @DisplayName("Desativar programa com sucesso")
    @Story("Desativar programa")
    @Description("Desativar programa com sucesso")
    public void testDesativarPrograma(){
        ProgramaModel programaCriado = programaService.criarPrograma(programaValido)
                .then()
                    .statusCode(HttpStatus.SC_CREATED)
                .extract().as(ProgramaModel.class);
        var response = programaService.desativarPrograma(programaCriado)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        response.assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Falha ao desativar programa com letras no id")
    @Story("Desativar programa")
    @Description("Falha ao desativar programa com letras no id")
    public void testDesativarProgramaComLetrasNoId(){
        var response = programaService.desativarProgramaComLetrasNoId()
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @Test
    @DisplayName("Falha ao desativar programa com id inexistente")
    @Story("Desativar programa")
    @Description("Falha ao desativar programa com id inexistente")
    public void testDesativarProgramaComIdErrado(){
        var response = programaService.desativarProgramaComIdInexistente()
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }
//    endregion
//    region DELETAR PROGRAMA
    @Test
    @DisplayName("Deletar programa com sucesso")
    @Story("Deletar programa")
    @Description("Deletar programa com sucesso")
    public void testDeletarPrograma(){
        ProgramaModel programaCriado = programaService.criarPrograma(programaValido)
                .then()
                    .statusCode(HttpStatus.SC_CREATED)
                .extract().as(ProgramaModel.class);
        var response = programaService.deletarPrograma(programaCriado)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        response.assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Falha ao deletar programa com letras no id")
    @Story("Deletar programa")
    @Description("Falha ao deletar programa com letras no id")
    public void testDeletarProgramaComLetrasNoId(){
        var response = programaService.deletarProgramaComLetrasNoId()
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }
    @Test
    @DisplayName("Falha ao deletar programa com id inexistente")
    @Story("Deletar programa")
    @Description("Falha ao deletar programa com id inexistente")
    public void testDeletarProgramaComIdInexistente(){
        var response = programaService.deletarProgramaComIdInexistente()
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }
//    endregion
}