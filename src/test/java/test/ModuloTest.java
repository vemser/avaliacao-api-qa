package test;

import base.BaseTest;
import dataFactory.ModuloDataFactory;
import io.restassured.http.ContentType;
import model.JSONFailureResponse;
import model.ModuloModel;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ModuloService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ModuloTest extends BaseTest {
//region CRIAR MODULO
    ModuloService moduloService = new ModuloService();
    ModuloModel moduloModel = new ModuloModel();
    @Test
    @DisplayName("Criar modulo com sucesso")
    public void testCriarModulo(){
        ModuloModel criarModulo = ModuloDataFactory.gerarModuloModel(1173);
        var response = moduloService.criarModulo(criarModulo)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(ModuloModel.class);
        assertThat(criarModulo.getNome(), equalTo(response.getNome()));
        assertThat(criarModulo.getDescricao(), equalTo(response.getDescricao()));
    }
    @Test
    @DisplayName("Erro ao criar modulo com id da trilha inexistente")
    public void testErroAoCriarModulo(){
        ModuloModel criarModulo = ModuloDataFactory.gerarModuloModel(123156451);
        var response = moduloService.criarModulo(criarModulo)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("Trilha inexistente ou inativa."));

    }
//endregion
//region ATUALIZAR MODULO
    @Test
    @DisplayName("Atualizar  modulo com sucesso")
        public void testAtualizarModulo(){
            ModuloModel criarModulo = ModuloDataFactory.gerarModuloModel(1173);
            ModuloModel idModulo = moduloService.criarModulo(criarModulo).then().extract().as(ModuloModel.class);
            ModuloModel moduloAlterado = ModuloDataFactory.atualizarModulo(criarModulo.getIdTrilha());
            var response = moduloService.atualizarModulo(moduloAlterado, idModulo.getIdModulo())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(ModuloModel.class);
            assertThat(moduloAlterado.getNome(), equalTo(response.getNome()));
            assertThat(moduloAlterado.getDescricao(), equalTo(response.getDescricao()));
        }
    @Test
    @DisplayName("Criar modulo com sucesso")
    public void testAtualizarModuloComIdInexistente(){
        ModuloModel criarModulo = ModuloDataFactory.gerarModuloModel(00000);
        ModuloModel moduloAlterado = ModuloDataFactory.atualizarModulo(criarModulo.getIdTrilha());
        var response = moduloService.atualizarModulo(moduloAlterado, 12345)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("idTrilha: must be greater than or equal to 1"));
    }
//endregion
//region BUSCAR MODULO PELO ID DO MODULO
    @Test
    @DisplayName("buscar modulo pelo id do modulo")
    public void testBuscarModulo(){
        ModuloModel response = moduloService.buscarModuloPeloId(63)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract()
                .as(ModuloModel.class);
        assertNotNull(response);
        Assertions.assertEquals(1173, response.getIdTrilha());
    }
    @Test
    @DisplayName("Erro ao buscar modulo pelo id do modulo ser inexistente")
    public void testErroAoBuscarModulo(){
        var response = moduloService.buscarModuloPeloId(1234561)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("Módulo não encontrado."));
    }
    @Test
    @DisplayName("Erro ao buscar modulo pelo id do modulo ser um valor negativo")
    public void testErroAoBuscarModuloIdNegativo(){
        var response = moduloService.buscarModuloPeloId(-1234561)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("getById.idModulo: must be greater than or equal to 1"));
    }
//endregion
//region BUSCAR MODULO PELO ID DA TRILHA
    @Test
    @DisplayName("Buscar modulo pelo id da trilha")
    public void testBuscarModuloPelaTrilha(){
        var response = moduloService.buscarModuloPelaTrilhaPage(1173)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("pagina", Matchers.is(0))
                .body("tamanho", Matchers.is(5))
                .body("totalElementos", Matchers.greaterThanOrEqualTo(0));
    }
    @Test
    @DisplayName("Erro ao buscar modulo, id da trilha com valor negativo")
    public void testBuscarModuloPelaTrilhaComvalorNegativo(){
        var response = moduloService.buscarModuloPelaTrilhaPage(-1173)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listAllByTrilha.idTrilha: must be greater than or equal to 1"));
    }
    @Test
    @DisplayName("Erro ao buscar modulo, id da trilha com valor zero")
    public void testBuscarModuloPelaTrilhaComValorZero(){
        var response = moduloService.buscarModuloPelaTrilhaPage(000000000000000000000)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listAllByTrilha.idTrilha: must be greater than or equal to 1"));
    }
//endregion
//region DESATIVAR MODULO
    @Test
    @DisplayName("Criar modulo com sucesso")
    public void testDesativarModulo(){
        ModuloModel criarModulo = ModuloDataFactory.gerarModuloModel(1173);
        ModuloModel idModulo = moduloService.criarModulo(criarModulo).then().extract().as(ModuloModel.class);
        moduloService.desativarModuloPeloId(idModulo.getIdModulo())
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        moduloService.buscarModuloPeloId(idModulo.getIdModulo())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("ativo", equalTo(false));
    }
    @Test
    @DisplayName("Criar modulo com sucesso")
    public void testDesativarModuloDuasVezes(){
        ModuloModel criarModulo = ModuloDataFactory.gerarModuloModel(1173);
        ModuloModel idModulo = moduloService.criarModulo(criarModulo).then().extract().as(ModuloModel.class);
        moduloService.desativarModuloPeloId(idModulo.getIdModulo());
        var response = moduloService.desativarModuloPeloId(idModulo.getIdModulo())
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("Módulo inexistente ou inativo."));
    }
    @Test
    @DisplayName("Criar modulo com sucesso")
    public void testDesativarModuloComIdNegativo(){
        var response = moduloService.desativarModuloPeloId(-45652)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("deactivate.idModulo: must be greater than or equal to 1"));
    }

//endregion

//region DELETAR MODULO
    @Test
    @DisplayName("Criar modulo com sucesso")
    public void testDeletarModulo(){
        ModuloModel criarModulo = ModuloDataFactory.gerarModuloModel(1173);
        ModuloModel idModulo = moduloService.criarModulo(criarModulo).then().extract().as(ModuloModel.class);
        moduloService.deletarModuloPeloId(idModulo.getIdModulo())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        var response = moduloService.buscarModuloPeloId(idModulo.getIdModulo())
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("Módulo não encontrado."));
    }
    @Test
    @DisplayName("Criar modulo com sucesso")
    public void testDeletarModuloDesativado(){
        ModuloModel criarModulo = ModuloDataFactory.gerarModuloModel(1173);
        ModuloModel idModulo = moduloService.criarModulo(criarModulo).then().extract().as(ModuloModel.class);
        moduloService.desativarModuloPeloId(idModulo.getIdModulo());
        moduloService.deletarModuloPeloId(idModulo.getIdModulo())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        var response = moduloService.buscarModuloPeloId(idModulo.getIdModulo())
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("Módulo não encontrado."));
    }
    @Test
    @DisplayName("Criar modulo com sucesso")
    public void testDeletarModuloDuasVezes(){
        ModuloModel criarModulo = ModuloDataFactory.gerarModuloModel(1173);
        ModuloModel idModulo = moduloService.criarModulo(criarModulo).then().extract().as(ModuloModel.class);
        moduloService.deletarModuloPeloId(idModulo.getIdModulo());
        var response = moduloService.deletarModuloPeloId(idModulo.getIdModulo())
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("Módulo não encontrado."));
    }
    @Test
    @DisplayName("Criar modulo com sucesso")
    public void testDeletarModuloComNumeroNegativo(){
        var response = moduloService.deletarModuloPeloId(-54562156)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("delete.idModulo: must be greater than or equal to 1"));
    }
//endregion

}
