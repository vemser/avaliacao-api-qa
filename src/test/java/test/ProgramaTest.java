package test;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
    public void testCriarProgramaComDataAbaixoDaAtual(){
        var response = programaService.criarProgramaComDataAbaixoDaAtual()
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
        response.log().all();
    }
//    endregion
//    region TESTES DE BUSCAR PROGRAMA

    @Test
    public void testBuscarTodosProgramas(){
        var response = programaService.buscarProgramas()
            .then()
                .statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.JSON);
        response.assertThat().statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void testBuscarTodosProgramasSemPagina(){
        var response = programaService.buscarProgramasSemNumeroDePaginas()
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.log().all();
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @Test
    public void testBuscarTodosProgramasSemTamanho(){
        var response = programaService.buscarProgramasSemTamanho()
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.log().all();
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//    region TESTES DE BUSCAR PROGRAMA POR ID

    @Test
    public void testBuscarPorId(){
        var response = programaService.buscarPrograma()
                .then()
                .statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.JSON);
        response.assertThat().statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void testBuscarProgramaSemId(){
        var response = programaService.buscarProgramaSemId()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.log().all();
    }
    @Test
    public void testBuscarProgramaComIdInexistente(){
        var response = programaService.buscarProgramaComLetrasNoId()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
        response.log().all();
    }
//endregion
//    region EDITAR PROGRAMA
    @Test
    public void testEditarPrograma(){
        var response = programaService.atualizarPrograma()
                .then()
                .statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.JSON);
        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.log().all();
    }
    @Test
    public void testEditarProgramaSemId(){
        var response = programaService.atualizarProgramaSemInformarId()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().contentType(ContentType.JSON);
        response.log().all();
    }
    @Test
    public void testEditarProgramaComIdErrado(){
        var response = programaService.atualizarProgramaComIdErrado()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().contentType(ContentType.JSON);
        response.log().all();
    }
//    endregion
//    region DESATIVAR PROGRAMA
    @Test
    public void testDesativarPrograma(){
        var response = programaService.desativarPrograma()
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
        response.log().all();
    }
    @Test
    public void testDesativarProgramaSemId(){
        var response = programaService.desativarProgramaComLetrasNoId()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
        response.log().all();
    }
    @Test
    public void testDesativarProgramaComIdErrado(){
        var response = programaService.desativarProgramaComIdErrado()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.log().all();
    }
//    endregion
//    region DELETAR PROGRAMA
@Test
public void testDeletarPrograma(){
    var response = programaService.deletarPrograma()
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    response.assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    response.log().all();
}
    @Test
    public void testDeletarProgramaComLetrasNoId(){
        var response = programaService.deletarProgramaComLetrasNoId()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.log().all();
    }
    @Test
    public void testDeletarProgramaComIdErrado(){
        var response = programaService.deletarProgramaComIdErrado()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.log().all();
    }
//    endregion
}