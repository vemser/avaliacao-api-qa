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
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.EstagiarioService;
import service.ProgramaService;
import service.TrilhaService;

public class EstagiarioTest extends BaseTest {
    //     region Pre-requisitos
    private static ProgramaService programaService = new ProgramaService();
    private static TrilhaService trilhaService = new TrilhaService();
    private static EstagiarioService estagiarioService = new EstagiarioService();
    private static ProgramaModel programaCriado;
    private static TrilhaModel trilhaCriada;
    private static EstagiarioModel estagiarioValido;
    private static EstagiarioModel estagiarioCriado;
    private static JSONFailureResponse jsonFailureResponse;
    @BeforeAll
    public static void criarPreRequisitos() {
        programaCriado = programaService.criarPrograma(ProgramaDataFactory.gerarProgramaValido())
                .then()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(ProgramaModel.class);
        trilhaCriada = trilhaService.adicionarTrilha(TrilhaDataFactory.gerarTrilhaValida(programaCriado))
                .then()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(TrilhaModel.class);
        estagiarioValido = EstagiarioDataFactory.gerarEstagiarioValido(trilhaCriada);
    }
    @AfterAll
    public static void limparDadosCriados() {
        trilhaService.deletarTrilhaIdTrilha(trilhaCriada)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        programaService.deletarPrograma(programaCriado)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
//        estagiarioService.deletarEstagiario(estagiarioCriado)
//                .then();
    }
    //     endregion
    //     region Criar estagiário
    @Test
    @DisplayName("Criar estagiário com sucesso")
    @Story("Criar estagiário")
    @Description("Criar estagiário com sucesso")
    public void testCriarEstagiarioComSucesso() {
        estagiarioCriado = retornarEstagiarioCriado();
        Assertions.assertEquals(estagiarioValido.getIdTrilha(), estagiarioCriado.getIdTrilha());
        Assertions.assertEquals(estagiarioValido.getNome(), estagiarioCriado.getNome());
        Assertions.assertEquals(estagiarioValido.getCpf(), estagiarioCriado.getCpf());
        Assertions.assertEquals(estagiarioValido.getEmailPessoal(), estagiarioCriado.getEmailPessoal());
        Assertions.assertEquals(estagiarioValido.getEmailCorporativo(), estagiarioCriado.getEmailCorporativo());
        Assertions.assertEquals(estagiarioValido.getTelefone(), estagiarioCriado.getTelefone());
        Assertions.assertEquals(estagiarioValido.getDataNascimento(), estagiarioCriado.getDataNascimento());
        Assertions.assertEquals(estagiarioValido.getEstado(), estagiarioCriado.getEstado());
        Assertions.assertEquals(estagiarioValido.getCidade(), estagiarioCriado.getCidade());
        Assertions.assertEquals(estagiarioValido.getCurso(), estagiarioCriado.getCurso());
        Assertions.assertEquals(estagiarioValido.getInstituicaoEnsino(), estagiarioCriado.getInstituicaoEnsino());
        Assertions.assertEquals(estagiarioValido.getLinkedin(), estagiarioCriado.getLinkedin());
        Assertions.assertEquals(estagiarioValido.getGithub(), estagiarioCriado.getGithub());
        Assertions.assertEquals(estagiarioValido.getObservacoes(), estagiarioCriado.getObservacoes());
        Assertions.assertEquals(estagiarioValido.getStatus(), estagiarioCriado.getStatus());
        Assertions.assertTrue(estagiarioCriado.getIdEstagiario() > 0);
        Assertions.assertNotNull(estagiarioCriado.getAtivo());
        estagiarioService.deletarEstagiario(estagiarioCriado)
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }
    @Test
    @DisplayName("Criar estagiário com CPF com ponto e hífen")
    @Story("Criar estagiário")
    @Description("Criar estagiário com CPF com ponto e hífen")
    public void testCriarEstagiarioComCpfComPontoEVirgula() {
        estagiarioValido.setCpf(EstagiarioDataFactory.gerarCpfComPontoEHifen());
        jsonFailureResponse = estagiarioService.criarEstagiario(estagiarioValido)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .contentType(ContentType.JSON)
                    .extract().as(JSONFailureResponse.class);
        Assertions.assertTrue(jsonFailureResponse.getErrors().contains("cpf: invalid Brazilian individual taxpayer registry number (CPF)"));
    }
    @ParameterizedTest
    @DisplayName("Criar estagiário com Email Pessoal inválido")
    @Story("Criar estagiário")
    @Description("Criar estagiário com Email Pessoal inválido")
    @MethodSource("dataFactory.EstagiarioDataFactory#provideEmailsInvalidos")
    public void testCriarEstagiarioComEmailPessoalInvalido(String emailInvalido) {
        estagiarioValido.setEmailPessoal(emailInvalido);
        jsonFailureResponse = estagiarioService.criarEstagiario(estagiarioValido)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .contentType(ContentType.JSON)
                    .extract().as(JSONFailureResponse.class);
        Assertions.assertTrue(jsonFailureResponse.getErrors().contains("emailPessoal: Endereço de e-mail inválido"));
    }
    @ParameterizedTest
    @DisplayName("Criar estagiário com Email Corporativo inválido")
    @Story("Criar estagiário")
    @Description("Criar estagiário com Email Corporativo inválido")
    @MethodSource("dataFactory.EstagiarioDataFactory#provideEmailsInvalidos")
    public void testCriarEstagiarioComEmailCorporativoInvalido(String emailInvalido) {
        estagiarioValido.setEmailCorporativo(emailInvalido);
        jsonFailureResponse = estagiarioService.criarEstagiario(estagiarioValido)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .contentType(ContentType.JSON)
                    .extract().as(JSONFailureResponse.class);
        Assertions.assertTrue(jsonFailureResponse.getErrors().contains("emailPessoal: Endereço de e-mail inválido"));
    }
    // endregion
    // region Deletar estagiário
    @Test
    @DisplayName("Deletar estagiário com sucesso")
    @Story("Deletar estagiário")
    @Description("Deletar estagiário com sucesso")
    public void testDeletarEstagiarioComSucesso() {
        estagiarioCriado = retornarEstagiarioCriado();
        estagiarioService.deletarEstagiario(estagiarioCriado)
                .then()
                    .statusCode(HttpStatus.SC_OK);
    }
    @Test
    @DisplayName("Deletar estagiário inexistente")
    @Story("Deletar estagiário")
    @Description("Deletar estagiário inexistente")
    public void testDeletarEstagiarioinexistente() {
        estagiarioCriado = retornarEstagiarioCriado();
        estagiarioService.deletarEstagiario(estagiarioCriado)
                .then()
                    .statusCode(HttpStatus.SC_OK);
        estagiarioService.deletarEstagiario(estagiarioCriado)
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    private static EstagiarioModel retornarEstagiarioCriado() {
        return estagiarioService.criarEstagiario(estagiarioValido)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .contentType(ContentType.JSON)
                    .extract().as(EstagiarioModel.class);
    }
}
