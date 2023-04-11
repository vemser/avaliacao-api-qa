package test;

import base.BaseTest;
import dataFactory.ProgramaDataFactory;
import dataFactory.TrilhaDataFactory;
import model.ProgramaModel;
import model.TrilhaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import service.ProgramaService;
import service.TrilhaService;

public class TrilhaTest extends BaseTest{
    public static ProgramaService programaService = new ProgramaService();
    public static ProgramaModel programaCriado;
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
    TrilhaModel trilhaModel;
    TrilhaService trilhaService = new TrilhaService();
    @Test
    public void testCadastrarTrilha(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        var response = trilhaService.adicionarTrilha(trilhaModel)
                        .then()
                        .statusCode(HttpStatus.SC_CREATED);
        response.log().all();
    }
    @Test
    public void testAtualizarTrilha(){
        var response = trilhaService.atualizarTrilha()
                .then()
                .statusCode(HttpStatus.SC_OK);
        response.log().all();
    }
    @Test
    public void testBuscarTrilha(){
        var response = trilhaService.buscarTodasAsTrilhas()
                .then()
                .statusCode(HttpStatus.SC_OK);
        response.log().all();
    }


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
}
