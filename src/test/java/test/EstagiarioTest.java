package test;

import base.BaseTest;
import dataFactory.EstagiarioDataFactory;
import dataFactory.ProgramaDataFactory;
import dataFactory.TrilhaDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import model.EstagiarioModel;
import model.JSONFailureResponse;
import model.ProgramaModel;
import model.TrilhaModel;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.EstagiarioService;
import service.ProgramaService;
import service.TrilhaService;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EstagiarioTest extends BaseTest {
    private static EstagiarioService estagiarioService = new EstagiarioService();


//         region Criar estagiário
    @Test
    @DisplayName("Criar estagiário com sucesso")
    public void testCriarEstagiarioComSucesso() {
        EstagiarioModel estagiarioCriado = EstagiarioDataFactory.gerarEstagiarioValido();
        var response = estagiarioService.criarEstagiario(estagiarioCriado)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(EstagiarioModel.class);
        Assertions.assertEquals(response.getIdTrilha(), estagiarioCriado.getIdTrilha());
        Assertions.assertEquals(response.getNome(), estagiarioCriado.getNome());
        Assertions.assertEquals(response.getCpf(), estagiarioCriado.getCpf());
        Assertions.assertEquals(response.getEmailPessoal(), estagiarioCriado.getEmailPessoal());
        Assertions.assertEquals(response.getEmailCorporativo(), estagiarioCriado.getEmailCorporativo());
        estagiarioService.deletarEstagiario(response)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Criar estagiário com CPF com ponto e hífen")
    public void testCriarEstagiarioComCpfComPontoEVirgula() {
        EstagiarioModel criarEstagiario = EstagiarioDataFactory.gerarEstagiarioValido();
        criarEstagiario.setCpf(EstagiarioDataFactory.gerarCpfComPontoEHifen());
        estagiarioService.criarEstagiario(criarEstagiario)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .body("message", equalTo("CPF deve ser apenas númerico!"));
    }

    @Test
    @DisplayName("Criar estagiário com Email Pessoal inválido")
    public void testCriarEstagiarioComEmailPessoalInvalido() {
        EstagiarioModel criarEstagiario = EstagiarioDataFactory.gerarEstagiarioValido();
        criarEstagiario.setEmailPessoal(EstagiarioDataFactory.gerarEmailInvalido());
        var response = estagiarioService.criarEstagiario(criarEstagiario)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .extract().as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getErrors().contains("emailPessoal: Endereço de e-mail inválido"));
    }
// endregion
// region BUSCAR ESTAGIARIO PELO ID DO ESTAGIARIO

    @Test
    @DisplayName("Buscar estagiário por ID com sucesso")
    public void testBuscarEstagiarioPorIdComSucesso() {
        EstagiarioModel estagiario = estagiarioService.buscarEstagiarioPorIdEstagiario(468)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().as(EstagiarioModel.class);
        Assertions.assertEquals(468, estagiario.getIdEstagiario());
        Assertions.assertEquals(644, estagiario.getIdTrilha());
        Assertions.assertEquals("Dra. Miguel Hernandes da Rosa", estagiario.getNome());
    }

    @Test
    @DisplayName("Buscar estagiário por ID inexistente")
    public void testBuscarEstagiarioPorIdInexistente() {
        var response = estagiarioService.buscarEstagiarioPorIdEstagiario(999999999)
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .contentType(ContentType.JSON)
                .extract().as(JSONFailureResponse.class);
        Assertions.assertEquals("Estagiário não encontrado.", response.getMessage());
    }

    //endregion
//region BUSCAR ESTAGIARIO COM ID DO PROGRAMA
    @Test
    @DisplayName("Buscar estagiário por programa")
    public void testBuscarEstagiarioPorPrograma() {
        var response = estagiarioService.buscarEstagiariosPorPrograma(1346, 0, 5)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("totalElementos", Matchers.greaterThanOrEqualTo(0));
    }

    @Test
    @DisplayName("Buscar estagiário por programa com página e tamanho inválidos")
    public void testBuscarEstagiarioPorProgramaComPaginasETamanhosInvalidos() {
        var response = estagiarioService.buscarEstagiariosPorPrograma(1346, -000000, -9999999)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertEquals("listAllByPrograma.size: must be greater than or equal to 1", response.getMessage());

    }

    // endregion
//region LISTAR ESTAGIARIOS
    @Test
    @DisplayName("Listar todo os estagiarios")
    public void testListarTodosOsEstagiarios() {
        var response = estagiarioService.buscarPorListarTodosEstagiarios( 0, 5)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("totalElementos", Matchers.greaterThanOrEqualTo(0));
    }
    @Test
    @DisplayName("Erro ao listar estagiarios com dados incorretos")
    public void testListarTodosOsEstagiariosComErro() {
        var response = estagiarioService.buscarPorListarTodosEstagiarios( -32, 5)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listAll.page: must be greater than or equal to 0"));

    }
//endregion
//region RECUPERAR DADOS DO DASHBOARD
    @Test
    @DisplayName("Recupera os dados que alimentam o dashbaord")
    public void testRecuperarDadosDoDashboardPeloIdDoPrograma() {
       estagiarioService.recuperarDadosDoDashboard(1346, 0, 5)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("totalElementos", Matchers.greaterThanOrEqualTo(0));
    }
    @Test
    @DisplayName("Erro ao tentar recuperar os dados do dashboard por inserir os dados inválidos")
    public void testRecuperarDadosDoDashboardInvalido() {
        var response = estagiarioService.recuperarDadosDoDashboard(-1346, -000000, -9999999)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("listAllByPrograma.size: must be greater than or equal to 1"));

    }
//endregion
// region ATUALIZAR ESTAGIARIO
    @Test
    @DisplayName("Atualizar estagiário com sucesso")
    public void testAtualizarEstagiarioComSucesso() {
        EstagiarioModel estagiarioAntigo = EstagiarioDataFactory.gerarEstagiarioValido();
        EstagiarioModel estagiarioNovo = estagiarioService.criarEstagiario(estagiarioAntigo).then().extract().as(EstagiarioModel.class);
        EstagiarioModel estagiarioAlterado = estagiarioService.atualizarEstagiario(estagiarioNovo, estagiarioAntigo)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().as(EstagiarioModel.class);
        Assertions.assertEquals(estagiarioNovo.getIdTrilha(), estagiarioAlterado.getIdTrilha());
        Assertions.assertEquals(estagiarioNovo.getNome(), estagiarioAlterado.getNome());
        Assertions.assertEquals(estagiarioNovo.getCpf(), estagiarioAlterado.getCpf());
        Assertions.assertEquals(estagiarioNovo.getEmailPessoal(), estagiarioAlterado.getEmailPessoal());
        Assertions.assertEquals(estagiarioNovo.getEmailCorporativo(), estagiarioAlterado.getEmailCorporativo());
        estagiarioService.deletarEstagiario(estagiarioAlterado)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Atualizar estagiário inexistente")
    public void testAtualizarEstagiarioInexistente() {
        EstagiarioModel estagiarioNovo = EstagiarioDataFactory.gerarEstagiarioValido();
        var response = estagiarioService.atualizarEstagiario(123, estagiarioNovo)
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .contentType(ContentType.JSON)
                .extract().as(JSONFailureResponse.class);
        Assertions.assertEquals("Estagiário inexistente ou inativo.", response.getMessage());
    }

    @Test
    @DisplayName("Atualizar estagiário com Email Corporativo inválido")
    public void testAtualizarEstagiarioComEmailCorporativoInvalido() {
        EstagiarioModel estagiarioAntigo = EstagiarioDataFactory.gerarEstagiarioValido();
        EstagiarioModel estagiarioNovo = estagiarioService.criarEstagiario(estagiarioAntigo).then().extract().as(EstagiarioModel.class);
        estagiarioAntigo.setEmailCorporativo(EstagiarioDataFactory.gerarEmailInvalido());
        estagiarioService.atualizarEstagiario(estagiarioNovo, estagiarioAntigo)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .body("errors", hasItem("emailCorporativo: Endereço de e-mail inválido, deve estar no dominio da DBC. (example@dbccompany.com.br)"));
    }

    // endregion
//region DELETAR ESTAGIARIO
    @Test
    @DisplayName("Deletar estagiário com sucesso")
    public void testDeletarEstagiarioComSucesso() {
        EstagiarioModel criarEstagiario = EstagiarioDataFactory.gerarEstagiarioValido();
        EstagiarioModel idEstagiario = estagiarioService.criarEstagiario(criarEstagiario).then().extract().as(EstagiarioModel.class);
        estagiarioService.deletarEstagiario(idEstagiario)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        estagiarioService.buscarEstagiarioPorIdEstagiario(idEstagiario)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(JSONFailureResponse.class);
    }
    @Test
    @DisplayName("Erro ao tentar deletar o estagiario duas vezes")
    public void testDeletarDuasVezes() {
        EstagiarioModel criarEstagiario = EstagiarioDataFactory.gerarEstagiarioValido();
        EstagiarioModel idEstagiario = estagiarioService.criarEstagiario(criarEstagiario).then().extract().as(EstagiarioModel.class);
        estagiarioService.deletarEstagiario(idEstagiario)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        var response = estagiarioService.deletarEstagiario(idEstagiario)
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(response.getMessage().contains("Estagiário não encontrado."));
    }
//endregion
// region DESATIVAR ESTAGIARIO
    @Test
    @DisplayName("Desativar estagiário")
    public void testDesativarEstagiario() {
        EstagiarioModel criarEstagiario = EstagiarioDataFactory.gerarEstagiarioValido();
        EstagiarioModel idEstagiario = estagiarioService.criarEstagiario(criarEstagiario).then().extract().as(EstagiarioModel.class);
        estagiarioService.desativarEstagiario(idEstagiario)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        estagiarioService.buscarEstagiarioPorIdEstagiario(idEstagiario)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("ativo", equalTo(false));
        estagiarioService.deletarEstagiario(idEstagiario)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Desativar estagiário já desativado")
    public void testDesativarEstagiarioJaDesativado() {
        EstagiarioModel criarEstagiario = EstagiarioDataFactory.gerarEstagiarioValido();
        EstagiarioModel idEstagiario = estagiarioService.criarEstagiario(criarEstagiario).then().extract().as(EstagiarioModel.class);
        estagiarioService.desativarEstagiario(idEstagiario)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        estagiarioService.desativarEstagiario(idEstagiario)
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        estagiarioService.buscarEstagiarioPorIdEstagiario(idEstagiario)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("ativo", equalTo(false));
        estagiarioService.deletarEstagiario(idEstagiario)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
// endregion
}