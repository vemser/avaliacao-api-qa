package test;

import base.BaseTest;
import dataFactory.ProgramaDataFactory;
import dataFactory.TrilhaDataFactory;
import io.restassured.http.ContentType;
import model.JSONFailureResponse;
import model.ProgramaModel;
import model.TrilhaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import service.ProgramaService;
import service.TrilhaService;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class TrilhaTest extends BaseTest{
    public static ProgramaService programaService = new ProgramaService();
    public static ProgramaModel programaCriado;
    TrilhaModel trilhaModel;
    TrilhaService trilhaService = new TrilhaService();

//    region BEFORE
    @BeforeAll
    public static void criarPrograma(){

        programaCriado = programaService.criarPrograma(ProgramaDataFactory.gerarProgramaValido())
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(ProgramaModel.class);
    }
    @AfterAll
    public static void deletarPrograma(){
        programaService.deletarPrograma(programaCriado)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
//    endregion
//    region CRIAR TRILHA
    @Test
    public void testCadastrarTrilha(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        var response = trilhaService.adicionarTrilha(trilhaModel)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON);
                TrilhaModel trilhaResponse = response.extract().as(TrilhaModel.class);
            assertThat(trilhaModel.getNome(), equalTo(trilhaResponse.getNome()));
            assertThat(trilhaModel.getDescricao(), equalTo(trilhaResponse.getDescricao()));
            assertThat(trilhaModel.getStatus(), equalTo(trilhaResponse.getStatus()));
            assertThat(trilhaResponse.getIdTrilha(), equalTo(trilhaResponse.getIdTrilha()));
            trilhaService.deletarTrilhaIdTrilha(trilhaResponse)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.log().all();
    }
    @Test
    public void testAdicionarTrilhaSemId(){
        TrilhaModel trilhaComCampoNomeVazio = TrilhaDataFactory.gerarTrilhaSemId();
        var response = trilhaService.adicionarTrilha(trilhaComCampoNomeVazio)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract().as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("idPrograma: must not be null"));
    }
    @Test
    public void testAdicionarTrilhaComLetrasNoId(){
        TrilhaModel trilhaComLetrasNoCampoId = TrilhaDataFactory.gerarTrilhaComLetrasNoId(programaCriado.getIdPrograma());
       JSONFailureResponse response = trilhaService.adicionarTrilha(trilhaComLetrasNoCampoId)
            .then()
                 .statusCode(HttpStatus.SC_BAD_REQUEST)
                 .contentType(ContentType.JSON).extract().as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessages().contains("status: Campo inválido!"));
    }

//    endregion
//    region ATUALIZAR TRILHAS
    @Test
    public void testAtualizarTrilha(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
        TrilhaModel trilhaAlterada = TrilhaDataFactory.atualizarTrilhaValida(trilhaCriada.getIdPrograma());
        var response = trilhaService.atualizarTrilha(trilhaCriada, trilhaAlterada)
                .then()
                    .statusCode(HttpStatus.SC_OK);
        response.log().all();
        TrilhaModel trilhaResponse = response.extract().as(TrilhaModel.class);
        assertThat(trilhaAlterada.getNome(), equalTo(trilhaResponse.getNome()));
        assertThat(trilhaAlterada.getDescricao(), equalTo(trilhaResponse.getDescricao()));
        assertThat(trilhaAlterada.getStatus(), equalTo(trilhaResponse.getStatus()));
        assertThat(trilhaAlterada.getIdPrograma(), equalTo(trilhaResponse.getIdPrograma()));
        //Deletar trilha
        trilhaService.deletarTrilhaIdTrilha(trilhaCriada)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    public void testAtualizarTrilhaSemId(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
        TrilhaModel trilhaAlterada = TrilhaDataFactory.atualizarTrilhaSemId();
        var response = trilhaService.atualizarTrilha(trilhaCriada, trilhaAlterada)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract().as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("idPrograma: must not be null"));
    }
    @Test
    public void testAtualizarTrilhaSemBody(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
        TrilhaModel trilhaAlterada = TrilhaDataFactory.atualizarTrilhSemCorpo();
        var response = trilhaService.atualizarTrilha(trilhaCriada, trilhaAlterada)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract().as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("idPrograma: must not be null"));
        Assertions.assertTrue(response.getErrors().contains("status: must not be null"));
        Assertions.assertTrue(response.getErrors().contains("nome: Nome não pode estar vazio!"));
    }
//    endregion

//    region BUSCAR TRILHA POR ID
@Test
public void testBuscarTrilhaPorIdTrilha(){
    trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
    TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
    var response = trilhaService.buscarTrilhaPorIdTrilha(trilhaCriada)
            .then()
                .statusCode(HttpStatus.SC_OK);
    TrilhaModel trilhaResponse = response.extract().as(TrilhaModel.class);
    assertThat(trilhaCriada.getNome(), equalTo(trilhaResponse.getNome()));

    //Deletar trilha
    trilhaService.deletarTrilhaIdTrilha(trilhaCriada)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
//    TrilhaModel trilhaResponse = response.extract().as(TrilhaModel.class)
}
//    endregion
//    region BUSCAR TODAS TRILHAS
//    @Test
//    public void testBuscarTrçilha(){
//        var response = trilhaService.buscarTodasAsTrilhas()
//                .then()
//                .statusCode(HttpStatus.SC_OK);
//        response.log().all();
//    }
//    endregion
//    region DESATIVAR
//    endregion
//    region DELETAR TRILHA
    @Test
    public void testDeletarTrilhaPeloId(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel)
                .then()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(TrilhaModel.class);
         var response = trilhaService.deletarTrilhaIdTrilha(trilhaCriada)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
         response.log().all();
    }
//    endregion
}
