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

import java.util.Arrays;

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
    private static EstagiarioModel retornarEstagiarioCriado() {
        return estagiarioService.criarEstagiario(estagiarioValido)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().as(EstagiarioModel.class);
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
    // region Atualizar estagiário
    @Nested
    class AtualizarEstagiario{
        @BeforeAll
        public static void setupEstagiario() {
            estagiarioCriado = retornarEstagiarioCriado();
        }
        @AfterAll
        public static void limparEstagiario() {
            estagiarioService.deletarEstagiario(estagiarioCriado)
                    .then()
                        .statusCode(HttpStatus.SC_OK);
        }
        @Test
        @DisplayName("Atualizar estagiário com sucesso")
        @Story("Atualizar estagiário")
        @Description("Atualizar estagiário com sucesso")
        public void testAtualizarEstagiarioComSucesso () {
            EstagiarioModel estagiarioAntigo = estagiarioCriado;
            EstagiarioModel estagiarioNovo = EstagiarioDataFactory.gerarEstagiarioAlterado(estagiarioAntigo);
            EstagiarioModel estagiarioAlterado = estagiarioService.atualizarEstagiario(estagiarioAntigo, estagiarioNovo)
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .contentType(ContentType.JSON)
                    .extract().as(EstagiarioModel.class);
            Assertions.assertEquals(estagiarioNovo.getIdTrilha(), estagiarioAlterado.getIdTrilha());
            Assertions.assertEquals(estagiarioNovo.getNome(), estagiarioAlterado.getNome());
            Assertions.assertEquals(estagiarioNovo.getCpf(), estagiarioAlterado.getCpf());
            Assertions.assertEquals(estagiarioNovo.getEmailPessoal(), estagiarioAlterado.getEmailPessoal());
            Assertions.assertEquals(estagiarioNovo.getEmailCorporativo(), estagiarioAlterado.getEmailCorporativo());
            Assertions.assertEquals(estagiarioNovo.getTelefone(), estagiarioAlterado.getTelefone());
            Assertions.assertEquals(estagiarioNovo.getDataNascimento(), estagiarioAlterado.getDataNascimento());
            Assertions.assertEquals(estagiarioNovo.getEstado(), estagiarioAlterado.getEstado());
            Assertions.assertEquals(estagiarioNovo.getCidade(), estagiarioAlterado.getCidade());
            Assertions.assertEquals(estagiarioNovo.getCurso(), estagiarioAlterado.getCurso());
            Assertions.assertEquals(estagiarioNovo.getInstituicaoEnsino(), estagiarioAlterado.getInstituicaoEnsino());
            Assertions.assertEquals(estagiarioNovo.getLinkedin(), estagiarioAlterado.getLinkedin());
            Assertions.assertEquals(estagiarioNovo.getGithub(), estagiarioAlterado.getGithub());
            Assertions.assertEquals(estagiarioNovo.getObservacoes(), estagiarioAlterado.getObservacoes());
            Assertions.assertEquals(estagiarioNovo.getStatus(), estagiarioAlterado.getStatus());
            Assertions.assertEquals(estagiarioAntigo.getIdEstagiario(), estagiarioAlterado.getIdEstagiario());
        }
        @Test
        @DisplayName("Atualizar estagiário inexistente")
        @Story("Atualizar estagiário")
        @Description("Atualizar estagiário inexistente")
        public void testAtualizarEstagiarioInexistente () {
            EstagiarioModel estagiarioNovo = EstagiarioDataFactory.copiarEstagiario(estagiarioCriado);
            jsonFailureResponse = estagiarioService.atualizarEstagiario(1, estagiarioNovo)
                    .then()
                        .statusCode(HttpStatus.SC_NOT_FOUND)
                        .contentType(ContentType.JSON)
                        .extract().as(JSONFailureResponse.class);
            Assertions.assertEquals("Estagiário inexistente ou inativo.", jsonFailureResponse.getMessage());
        }
        @ParameterizedTest
        @DisplayName("Atualizar estagiário com Email Pessoal inválido")
        @Story("Atualizar estagiário")
        @Description("Atualizar estagiário com Email Pessoal inválido")
        @MethodSource("dataFactory.EstagiarioDataFactory#provideEmailsInvalidos")
        public void testAtualizarEstagiarioComEmailPessoalInvalido (String emailInvalido){
            EstagiarioModel estagiarioNovo = EstagiarioDataFactory.copiarEstagiario(estagiarioCriado);
            estagiarioNovo.setEmailPessoal(emailInvalido);
            jsonFailureResponse = estagiarioService.atualizarEstagiario(estagiarioCriado, estagiarioNovo)
                    .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .contentType(ContentType.JSON)
                        .extract().as(JSONFailureResponse.class);
            Assertions.assertTrue(jsonFailureResponse.getErrors().contains("emailPessoal: Endereço de e-mail inválido"));
        }
        @ParameterizedTest
        @DisplayName("Atualizar estagiário com Email Corporativo inválido")
        @Story("Atualizar estagiário")
        @Description("Atualizar estagiário com Email Corporativo inválido")
        @MethodSource("dataFactory.EstagiarioDataFactory#provideEmailsInvalidos")
        public void testAtualizarEstagiarioComEmailCorporativoInvalido (String emailInvalido){
            EstagiarioModel estagiarioNovo = EstagiarioDataFactory.copiarEstagiario(estagiarioCriado);
            estagiarioNovo.setEmailCorporativo(emailInvalido);
            jsonFailureResponse = estagiarioService.atualizarEstagiario(estagiarioCriado, estagiarioNovo)
                    .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .contentType(ContentType.JSON)
                        .extract().as(JSONFailureResponse.class);
            Assertions.assertTrue(jsonFailureResponse.getErrors().contains("emailCorporativo: Endereço de e-mail inválido"));
        }
        @ParameterizedTest
        @DisplayName("Atualizar estagiário com CPF inválido")
        @Story("Atualizar estagiário")
        @Description("Atualizar estagiário com CPF inválido")
        @MethodSource("dataFactory.EstagiarioDataFactory#provideCpfInvalidos")
        public void testAtualizarEstagiarioComCpfInvalido (String cpfInvalido){
            EstagiarioModel estagiarioNovo = EstagiarioDataFactory.copiarEstagiario(estagiarioCriado);
            estagiarioNovo.setCpf(cpfInvalido);
            jsonFailureResponse = estagiarioService.atualizarEstagiario(estagiarioCriado, estagiarioNovo)
                    .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .contentType(ContentType.JSON)
                        .extract().as(JSONFailureResponse.class);
            Assertions.assertTrue(jsonFailureResponse.getErrors().contains("cpf: invalid Brazilian individual taxpayer registry number (CPF)"));
        }
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
    // endregion
}
