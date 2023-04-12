package test;

import base.BaseTest;
import dataFactory.ProgramaDataFactory;
import dataFactory.TrilhaDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import model.JSONFailureResponse;
import model.ProgramaModel;
import model.TrilhaModel;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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
    @DisplayName("Criar uma trilha com sucesso")
    @Story("Criar uma trilha")
    @Description("Criar uma trilha com sucesso")
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
    }
    @Test
    @DisplayName("Falha ao criar uma trilha sem id")
    @Story("Criar uma trilha")
    @Description("Falha ao criar uma trilha sem id")
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
    @DisplayName("Falha ao criar uma trilha com status da trilha inexistente")
    @Story("Criar uma trilha")
    @Description("Falha ao criar uma trilha com status da trilha inexistente")
    public void testAdicionarTrilhaComLetrasNoId(){
        TrilhaModel trilhaComLetrasNoCampoId = TrilhaDataFactory.gerarTrilhaComStatusInexistente(programaCriado.getIdPrograma());
       JSONFailureResponse response = trilhaService.adicionarTrilha(trilhaComLetrasNoCampoId)
            .then()
                 .statusCode(HttpStatus.SC_BAD_REQUEST)
                 .contentType(ContentType.JSON).extract().as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessages().contains("status: Campo inválido!"));
    }

//    endregion
//    region ATUALIZAR TRILHAS
    @Test
    @DisplayName("Atualizar uma trilha com sucesso")
    @Story("Atualizar uma trilha")
    @Description("Atualizar uma trilha com sucesso")
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
    @DisplayName("Falha ao atualizar uma trilha sem ID")
    @Story("Atualizar uma trilha")
    @Description("Falha ao atualizar uma trilha sem ID")
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
    @DisplayName("Falha ao atualizar uma trilha sem Body")
    @Story("Atualizar uma trilha")
    @Description("Falha ao atualizar uma trilha sem Body")
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
//region BUSCAR TRILHAS POR ID DE PROGRAMA
    @Test
    @DisplayName("Listar todas trilhas pelo id do programa")
    @Story("Listar trilhas")
    @Description("Listar todas trilhas pelo id do programa")
    public void testBuscarProgramaPorIdDePrograma(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
        TrilhaModel idprograma = TrilhaDataFactory.buscarProgramasPorId(trilhaCriada.getIdPrograma());
        var response = trilhaService.buscarTrilhaPage(idprograma)
                .then()
                .statusCode(HttpStatus.SC_OK)
                        .contentType(ContentType.JSON);
        response.body("tamanho", equalTo(2));
        trilhaService.deletarTrilhaIdTrilha(trilhaCriada)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Falha ao listar todas trilhas com id negativo")
    @Story("Listar trilhas")
    @Description("Falha ao listar todas trilhas com id negativo")
    public void testBuscarProgramaPorIdDeProgramaComIdNegtivo(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
        TrilhaModel idprograma = TrilhaDataFactory.buscarProgramasSemId();
        var response = trilhaService.buscarTrilhaPage(idprograma)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON);
        trilhaService.deletarTrilhaIdTrilha(trilhaCriada)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

//endregion
//    region BUSCAR TRILHA POR ID
    @Test
    @DisplayName("Listar trilha pelo id")
    @Story("Listar trilha")
    @Description("Listar trilha pelo id")
    public void testBuscarTrilhaPorIdTrilha(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
        var response = trilhaService.buscarTrilhaPorIdTrilha(trilhaCriada)
                .then()
                    .statusCode(HttpStatus.SC_OK);
        TrilhaModel trilhaResponse = response.extract().as(TrilhaModel.class);
        assertThat(trilhaCriada.getNome(), equalTo(trilhaResponse.getNome()));
        assertThat(trilhaCriada.getStatus(), equalTo(trilhaResponse.getStatus()));
        assertThat(trilhaCriada.getDescricao(), equalTo(trilhaResponse.getDescricao()));
        trilhaService.deletarTrilhaIdTrilha(trilhaCriada)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Falha ao listar trilha sem o Id")
    @Story("Listar trilha")
    @Description("Falha ao listar trilha sem o Id")
    public void testBuscarTrilhaPorIdTrilhaSemId(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
        TrilhaModel trilhaSemId = TrilhaDataFactory.buscarTrilhaComIdInexistente();
        var response = trilhaService.buscarTrilhaPorIdTrilha(trilhaSemId)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        trilhaService.deletarTrilhaIdTrilha(trilhaCriada)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

//    endregion
//region  DESATIVAR TRILHA POR ID
    @Test
    @DisplayName("Desativar trilha pelo Id")
    @Story("Deletar trilha")
    @Description("Desativar trilha pelo Id")
    public void testDesativarTrilhaPorIdTrilha(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
        var response = trilhaService.desativarTrilhaIdTrilha(trilhaCriada)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Falha ao desativar trilha com id inexistente")
    @Story("Deletar trilha")
    @Description("Falha ao desativar trilha com id inexistente")
    public void testDesativarTrilhaPorIdTrilhaComIdInexistente(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
        TrilhaModel trilhaSemId = TrilhaDataFactory.desativarTrilhaComIdInexistente();
        var response = trilhaService.desativarTrilhaIdTrilha(trilhaSemId)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
//endregion
//region  DELETAR TRILHA POR ID
    @Test
    @DisplayName("Deletar trilha pelo Id")
    @Story("Deletar trilha")
    @Description("Deletar trilha pelo Id")
    public void testDeletarTrilhaPorIdTrilha(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
        var response = trilhaService.deletarTrilhaIdTrilha(trilhaCriada)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Falha ao deletar trilha com id inexistente")
    @Story("Deletar trilha")
    @Description("Falha ao deletar trilha com id inexistente")
    public void testDeletarTrilhaPorIdTrilhaComIdInexistente(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValida(programaCriado);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
        TrilhaModel trilhaSemId = TrilhaDataFactory.deletarTrilhaComIdInexistente();
        var response = trilhaService.deletarTrilhaIdTrilha(trilhaSemId)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
//endregion
//region CRIAR TRILHAS COM MODULO
    @Test
    @DisplayName("Criar uma trilha com modulo com sucesso")
    @Story("Criar uma trilha com modulo")
    @Description("Criar uma trilha com modulo com sucesso")
    public void testCadastrarTrilhaComModulo(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValidaComModulo(programaCriado.getIdPrograma());
        var response = trilhaService.adicionarTrilhaComModulo(trilhaModel)
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
    }
    @Test
    @DisplayName("Falha ao criar trilha com modulo Sem ID")
    @Story("Falha ao criar uma trilha com modulo")
    @Description("Falha ao criar trilha com modulo Sem ID")
    public void testCadastrarTrilhaComModuloSemId(){
        trilhaModel = TrilhaDataFactory.gerarTrilhaValidaComModuloSemInformarOId(programaCriado.getIdPrograma());
        var response = trilhaService.adicionarTrilhaComModulo(trilhaModel)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract().as(JSONFailureResponse.class);
            Assertions.assertTrue(response.getErrors().contains("idPrograma: must not be null"));
    }
//endregion
//region LISTAR TRILHAS MODELO
    @ParameterizedTest
    @DisplayName("Buscar todas trilhas")
    @Story("Buscar trilhas")
    @Description("Buscar todas trilhas")
    @MethodSource("dataFactory.GeralDataFactory#providePaginasETamanhosDePaginaValidos")
    public void testBuscarTodasTrilhas(int pagina, int tamanhoPagina) {
        trilhaService.buscarTrilhaModelo(pagina, tamanhoPagina)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("pagina", Matchers.is(pagina))
                .body("tamanho", Matchers.is(tamanhoPagina))
                .body("totalElementos", Matchers.greaterThanOrEqualTo(0))
        ;
    }
    @ParameterizedTest
    @DisplayName("Buscar todas trilhas")
    @Story("Buscar trilhas")
    @Description("Buscar todas trilhas")
    @MethodSource("dataFactory.GeralDataFactory#providePaginasETamanhosDePaginaInvalidos")
    public void testBuscarTodasTrilhasSemInformacoes(String pagina, String tamanhoPagina) {
        trilhaService.buscarTrilhaModeloInvalido(pagina, tamanhoPagina)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
}
