package test;

import base.BaseTest;
import dataFactory.AcompanhamentoDataFactory;
import dataFactory.ProgramaDataFactory;
import dataFactory.TrilhaDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import model.AcompanhamentoModel;
import model.ProgramaModel;
import model.TrilhaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.AcompanhamentoService;
import service.ProgramaService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static test.TrilhaTest.programaCriado;

public class AcompanhamentoTest extends BaseTest {
    AcompanhamentoService acompanhamentoService = new AcompanhamentoService();
    AcompanhamentoModel acompanhamentoModel = new AcompanhamentoModel();
    static ProgramaService programaService = new ProgramaService();
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
    @Test
    @DisplayName("Criar uma trilha com sucesso")
    @Story("Criar uma trilha")
    @Description("Criar uma trilha com sucesso")
    public void testCadastrarAcompanhamento(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(programaCriado);
        var response = acompanhamentoService.criarAcompanhamento(acompanhamentoModel)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON);
        response.log().all();
//        TrilhaModel trilhaResponse = response.extract().as(TrilhaModel.class);
//        assertThat(trilhaModel.getNome(), equalTo(trilhaResponse.getNome()));
//        assertThat(trilhaModel.getDescricao(), equalTo(trilhaResponse.getDescricao()));
//        assertThat(trilhaModel.getStatus(), equalTo(trilhaResponse.getStatus()));
//        assertThat(trilhaResponse.getIdTrilha(), equalTo(trilhaResponse.getIdTrilha()));
//        trilhaService.deletarTrilhaIdTrilha(trilhaResponse)
//                .then()
//                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

}
