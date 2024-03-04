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
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TrilhaTest extends BaseTest {
    TrilhaService trilhaService = new TrilhaService();

    //region CRIAR TRILHA
    @Test
    @DisplayName("Criar uma trilha com sucesso")
    public void testCadastrarTrilha() {
        TrilhaModel criarTrilha = TrilhaDataFactory.gerarTrilhaValida();

        var response = trilhaService.adicionarTrilha(criarTrilha)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(TrilhaModel.class);

        assertThat(criarTrilha.getNome(), equalTo(response.getNome()));
        assertThat(criarTrilha.getDescricao(), equalTo(response.getDescricao()));
        assertThat(criarTrilha.getStatus(), equalTo(response.getStatus()));

//        TrilhaModel deletar = TrilhaDataFactory.buscarTrilhaComId(response.getIdTrilha());
//        trilhaService.deletarTrilhaIdTrilha(response)
//                .then()
//                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Falha ao criar uma trilha sem id do programa")
    public void testAdicionarTrilhaSemIdDoPrograma() {
        TrilhaModel trilhaComCampoNomeVazio = TrilhaDataFactory.gerarTrilhaSemId();
        var response = trilhaService.adicionarTrilha(trilhaComCampoNomeVazio)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONFailureResponse.class);

        Assertions.assertEquals("[idPrograma: must be greater than or equal to 1]", response.getErrors().toString());
    }

    @Test
    @DisplayName("Falha ao criar uma trilha com status da trilha inexistente")
    public void testAdicionarTrilhaSemStatusDaTrilha() {
        TrilhaModel trilhaComLetrasNoCampoId = TrilhaDataFactory.gerarTrilhaComStatusInexistente(1909876);
        JSONFailureResponse response = trilhaService.adicionarTrilha(trilhaComLetrasNoCampoId)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessages().contains("status: Campo inválido!"));
    }

    //    endregion
//region CRIAR TRILHAS COM MODULO
    @Test
    @DisplayName("Criar uma trilha com modulo com sucesso")
    public void testCadastrarTrilhaComModulo() {
        TrilhaModel trilhaModel = TrilhaDataFactory.gerarTrilhaValidaComModulo(1);

        TrilhaModel response = trilhaService.adicionarTrilhaComModulo(trilhaModel)
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(TrilhaModel.class)
        ;

        assertThat(trilhaModel.getNome(), equalTo(response.getNome()));
        assertThat(trilhaModel.getDescricao(), equalTo(response.getDescricao()));
        assertThat(trilhaModel.getStatus(), equalTo(response.getStatus()));
        assertThat(response.getIdTrilha(), equalTo(response.getIdTrilha()));

        //  trilhaService.deletarTrilhaIdTrilha(response);
    }

    @Test
    @DisplayName("Falha ao criar trilha com modulo Sem ID")
    public void testCadastrarTrilhaComModuloSemId() {
        TrilhaModel trilhaModel = TrilhaDataFactory.gerarTrilhaValidaComModuloSemInformarOId();
        var response = trilhaService.adicionarTrilhaComModulo(trilhaModel)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONFailureResponse.class);

        Assertions.assertEquals("[idPrograma: must be greater than or equal to 1]", response.getErrors().toString());
    }

    //endregion
//    region ATUALIZAR TRILHAS
    @Test
    @DisplayName("Atualizar uma trilha com sucesso")
    public void testAtualizarTrilha() {
        TrilhaModel criarTrilha = TrilhaDataFactory.gerarTrilhaValida();
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(criarTrilha).then().extract().as(TrilhaModel.class);
        var response = trilhaService.atualizarTrilha(trilhaCriada, criarTrilha)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(TrilhaModel.class);
        assertThat(criarTrilha.getNome(), equalTo(response.getNome()));
        assertThat(criarTrilha.getDescricao(), equalTo(response.getDescricao()));
        assertThat(criarTrilha.getStatus(), equalTo(response.getStatus()));
        assertThat(criarTrilha.getIdPrograma(), equalTo(response.getIdPrograma()));
        System.out.println(response);

//        trilhaService.deletarTrilhaIdTrilha(trilhaCriada)
//                .then()
//                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Falha ao atualizar uma trilha sem Id do programa")

    public void testAtualizarTrilhaSemId() {
        TrilhaModel criarTrilha = TrilhaDataFactory.gerarTrilhaValida();
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(criarTrilha).then().extract().as(TrilhaModel.class);
        TrilhaModel trilhaAlterada = TrilhaDataFactory.atualizarTrilhaSemId();
        var response = trilhaService.atualizarTrilha(trilhaCriada, trilhaAlterada)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONFailureResponse.class);

        Assertions.assertEquals("[idPrograma: must be greater than or equal to 1]", response.getErrors().toString());
    }

    @Test
    @DisplayName("Falha ao atualizar uma trilha sem Body")
    @Story("Atualizar uma trilha")
    @Description("Falha ao atualizar uma trilha sem Body")
    public void testAtualizarTrilhaSemBody() {
        TrilhaModel criarTrilha = TrilhaDataFactory.gerarTrilhaValida();
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(criarTrilha).then().extract().as(TrilhaModel.class);
        TrilhaModel trilhaAlterada = TrilhaDataFactory.atualizarTrilhSemCorpo();
        var response = trilhaService.atualizarTrilha(trilhaCriada, trilhaAlterada)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("[idPrograma: must be greater than or equal to 1]"));
        Assertions.assertTrue(response.getErrors().contains("status: must not be null"));
        Assertions.assertTrue(response.getErrors().contains("nome: Nome não pode estar vazio!"));
    }

    //endregion,
//region ATUALIZAR TRILHA COM LINK

    @DisplayName("Atualizar uma trilha com link com sucesso")
    @Test
    public void testAtualizarTrilhaComLink() {
        TrilhaModel criarTrilha = TrilhaDataFactory.gerarTrilhaValida();
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(criarTrilha).then().log().body().extract().as(TrilhaModel.class);

        var response = trilhaService.atualizarTrilhaComLink(trilhaCriada)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(TrilhaModel.class);

        assertThat(criarTrilha.getNome(), equalTo(response.getNome()));
        assertThat(criarTrilha.getDescricao(), equalTo(response.getDescricao()));
        assertThat(criarTrilha.getStatus(), equalTo(response.getStatus()));
        assertThat(criarTrilha.getIdPrograma(), equalTo(response.getIdPrograma()));


//        trilhaService.deletarTrilhaIdTrilha(trilhaCriada)
//                .then()
//                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    //endregion
//region LISTAR TRILHAS MODELO
    @Test
    @DisplayName("Buscar todas trilhas")
    public void testBuscarTodasTrilhas() {
        trilhaService.buscarTrilhaModelo(0, 5)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("pagina", Matchers.is(0))
                .body("tamanho", Matchers.is(5))
                .body("totalElementos", Matchers.greaterThanOrEqualTo(0));
    }

    @Test
    @DisplayName("Falha uscar todas trilhas com letras")
    public void testBuscarTodasTrilhasComLetras() {
        trilhaService.buscarTrilhaModeloInvalido("error", "error")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    //endregion
//region BUSCAR TRILHAS POR ID DE PROGRAMA
    @Test
    @DisplayName("Listar todas trilhas vinculadas ao programa pelo id do programa")
    public void testBuscarProgramaPorIdDePrograma() {
        var response = trilhaService.buscarTrilhaPage(1)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().response();
        List<Map<String, Object>> trilhas = response.path("elementos");
        assertThat(trilhas.size(), greaterThan(0));
        for (Map<String, Object> trilha : trilhas) {
            assertThat(trilha.get("idPrograma"), equalTo(1));
            assertThat(trilha.get("nome"), notNullValue());
            assertThat(trilha.get("descricao"), notNullValue());
        }
    }

    @Test
    @DisplayName("Falha ao listar todas trilhas com id negativo")
    public void testBuscarProgramaPorIdDeProgramaComIdNegtivo() {
        var response = trilhaService.buscarTrilhaPage(-221346)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listAllByPrograma.idPrograma: must be greater than or equal to 1"));
    }

    //endregion
//    region BUSCAR TRILHA POR ID
    @Test
    @DisplayName("Listar trilha pelo id da trilha")
    public void testBuscarTrilhaPorIdTrilha() {
        trilhaService.buscarTrilhaPorIdTrilha(51)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nome", not(emptyString()))
                .body("descricao", not(emptyString()))
                ;
    }

    @Test
    @DisplayName("Falha ao listar trilha sem inserir um Id valido")
    public void testBuscarTrilhaPorIdTrilhaSemIdValido() {
        var response = trilhaService.buscarTrilhaPorIdTrilha(-0)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("getById.idTrilha: must be greater than or equal to 1"));
    }

    //    endregion
//region  DESATIVAR TRILHA POR ID
    @Test
    @DisplayName("Desativar trilha pelo Id")
    public void testDesativarTrilhaPorIdTrilha() {
        TrilhaModel trilhaModel = TrilhaDataFactory.gerarTrilhaValida();
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
        trilhaService.desativarTrilhaIdTrilha(trilhaCriada)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        trilhaService.deletarTrilhaIdTrilha(trilhaCriada);
    }

    @Test
    @DisplayName("Falha ao desativar trilha com id inexistente")
    public void testDesativarTrilhaPorIdTrilhaComIdInexistente() {
        TrilhaModel trilhaSemId = TrilhaDataFactory.desativarTrilhaComIdInexistente();
        trilhaService.desativarTrilhaIdTrilha(trilhaSemId)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    //endregion
//region  DELETAR TRILHA POR ID
//    @Test
//    @DisplayName("Deletar trilha pelo Id")
//    @Story("Deletar trilha")
//    @Description("Deletar trilha pelo Id")
//    public void testDeletarTrilhaPorIdTrilha() {
//        TrilhaModel trilhaModel = TrilhaDataFactory.gerarTrilhaValida();
//        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(trilhaModel).then().extract().as(TrilhaModel.class);
//
//        System.out.println(trilhaCriada.getIdTrilha());
//
//        trilhaService.deletarTrilhaIdTrilha(trilhaCriada)
//                .then()
//                .statusCode(HttpStatus.SC_NO_CONTENT);
//
////        var response = trilhaService.buscarTrilhaPorIdTrilha(trilhaCriada.getIdTrilha())
////                .then()
////                .statusCode(HttpStatus.SC_NOT_FOUND)
////                .extract()
////                .as(JSONFailureResponse.class);
//
//    }

//    @Test
//    @DisplayName("Falha ao deletar trilha com id inexistente")
//    public void testDeletarTrilhaPorIdTrilhaComIdInexistente() {
//        TrilhaModel trilhaSemId = TrilhaDataFactory.deletarTrilhaComIdInexistente();
//        var response = trilhaService.deletarTrilhaIdTrilha(trilhaSemId)
//                .then()
//                .statusCode(HttpStatus.SC_NOT_FOUND)
//                .extract()
//                .as(JSONFailureResponse.class);
//        Assertions.assertTrue(response.getMessage().contains("Trilha não encontrada."));
//    }
//endregion
}
