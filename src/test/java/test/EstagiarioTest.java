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

    }
    @BeforeEach
    public void criarEstagiarioValido() {
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
        estagiarioService.criarEstagiario(estagiarioValido)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .body("message", equalTo("CPF deve ser apenas númerico!"))
                ;
        ;
    }
    @ParameterizedTest(name = "{index} - E-mail: {0}")
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
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(jsonFailureResponse.getErrors().contains("emailPessoal: Endereço de e-mail inválido"));
    }
    @ParameterizedTest(name = "{index} - E-mail: {0}")
    @DisplayName("Criar estagiário com Email Corporativo inválido")
    @Story("Criar estagiário")
    @Description("Criar estagiário com Email Corporativo inválido")
    @MethodSource("dataFactory.EstagiarioDataFactory#provideEmailsInvalidos")
    public void testCriarEstagiarioComEmailCorporativoInvalido(String emailInvalido) {
        estagiarioValido.setEmailCorporativo(emailInvalido);
        estagiarioService.criarEstagiario(estagiarioValido)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON)
                .body("errors", Matchers.hasItem("emailCorporativo: Endereço de e-mail inválido"))
            ;
    }
    // endregion
    // region Buscar estagiário
    @Nested
    class BuscarEstagiario {
        @BeforeAll
        public static void setupEstagiario() {
            estagiarioValido = EstagiarioDataFactory.gerarEstagiarioValido(trilhaCriada);
            estagiarioCriado = estagiarioService.criarEstagiario(estagiarioValido)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .contentType(ContentType.JSON)
                    .extract().as(EstagiarioModel.class);
        }
        @AfterAll
        public static void limparEstagiario() {
            estagiarioService.deletarEstagiario(estagiarioCriado)
                    .then()
                    .statusCode(HttpStatus.SC_OK);
        }
        @Test
        @DisplayName("Buscar estagiário por ID com sucesso")
        @Story("Buscar estagiário")
        @Description("Buscar estagiário por ID com sucesso")
        public void testBuscarEstagiarioPorIdComSucesso() {
            EstagiarioModel estagiario = estagiarioService.buscarEstagiarioPorIdEstagiario(estagiarioCriado)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .contentType(ContentType.JSON)
                    .extract()
                    .as(EstagiarioModel.class);
            Assertions.assertEquals(estagiarioCriado.getIdEstagiario(), estagiario.getIdEstagiario());
            Assertions.assertEquals(estagiarioCriado.getIdTrilha(), estagiario.getIdTrilha());
            Assertions.assertEquals(estagiarioCriado.getNome(), estagiario.getNome());
            Assertions.assertEquals(estagiarioCriado.getCpf(), estagiario.getCpf());
            Assertions.assertEquals(estagiarioCriado.getEmailPessoal(), estagiario.getEmailPessoal());
            Assertions.assertEquals(estagiarioCriado.getEmailCorporativo(), estagiario.getEmailCorporativo());
            Assertions.assertEquals(estagiarioCriado.getTelefone(), estagiario.getTelefone());
            Assertions.assertEquals(estagiarioCriado.getDataNascimento(), estagiario.getDataNascimento());
            Assertions.assertEquals(estagiarioCriado.getEstado(), estagiario.getEstado());
            Assertions.assertEquals(estagiarioCriado.getCidade(), estagiario.getCidade());
            Assertions.assertEquals(estagiarioCriado.getCurso(), estagiario.getCurso());
            Assertions.assertEquals(estagiarioCriado.getInstituicaoEnsino(), estagiario.getInstituicaoEnsino());
            Assertions.assertEquals(estagiarioCriado.getLinkedin(), estagiario.getLinkedin());
            Assertions.assertEquals(estagiarioCriado.getGithub(), estagiario.getGithub());
            Assertions.assertEquals(estagiarioCriado.getObservacoes(), estagiario.getObservacoes());
            Assertions.assertEquals(estagiarioCriado.getStatus(), estagiario.getStatus());
            Assertions.assertEquals(estagiarioCriado.getAtivo(), estagiario.getAtivo());
        }
        @Test
        @DisplayName("Buscar estagiário por ID inexistente")
        @Story("Buscar estagiário")
        @Description("Buscar estagiário por ID inexistente")
        public void testBuscarEstagiarioPorIdInexistente() {
            jsonFailureResponse = estagiarioService.buscarEstagiarioPorIdEstagiario(999999999)
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND)
                    .contentType(ContentType.JSON)
                    .extract()
                    .as(JSONFailureResponse.class);
            Assertions.assertEquals("Estagiário não encontrado.", jsonFailureResponse.getMessage());
        }
        @ParameterizedTest(name = "{index} - ID inválido: {0}")
        @DisplayName("Buscar estagiário por ID inválido")
        @Story("Buscar estagiário")
        @Description("Buscar estagiário por ID inválido")
        @MethodSource("dataFactory.GeralDataFactory#provideIdsInvalidos")
        public void testBuscarEstagiarioPorIdInvalido(String idInvalido) {
            estagiarioService.buscarEstagiarioPorIdEstagiarioInvalido(idInvalido)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
        }
        @ParameterizedTest(name = "{index} - Página: {0} - Tamanho: {1}")
        @DisplayName("Buscar estagiário por programa")
        @Story("Buscar estagiário")
        @Description("Buscar estagiário por programa")
        @MethodSource("dataFactory.GeralDataFactory#providePaginasETamanhosDePaginaValidos")
        public void testBuscarEstagiarioPorPrograma(int pagina, int tamanhoPagina) {
            estagiarioService.buscarEstagiariosPorPrograma(programaCriado, pagina, tamanhoPagina)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .contentType(ContentType.JSON)
                    .body("pagina", Matchers.is(pagina))
                    .body("tamanho", Matchers.is(tamanhoPagina))
                    .body("totalElementos", Matchers.greaterThanOrEqualTo(0))
            ;
        }

        @ParameterizedTest(name = "{index} - Página: {0} - Tamanho: {1}")
        @DisplayName("Buscar estagiário por programa com página e tamanho inválidos")
        @Story("Buscar estagiário")
        @Description("Buscar estagiário por programa com página e tamanho inválidos")
        @MethodSource("dataFactory.GeralDataFactory#providePaginasETamanhosDePaginaInvalidos")
        public void testBuscarEstagiarioPorProgramaComPaginasETamanhosInvalidos(String pagina, String tamanhoPagina) {
            estagiarioService.buscarEstagiariosPorProgramaQueryInvalida(programaCriado, pagina, tamanhoPagina)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
            ;
        }
        @ParameterizedTest(name = "{index} - Página: {0} - Tamanho: {1}")
        @DisplayName("Buscar por lista de todos estagiários")
        @Story("Buscar estagiário")
        @Description("Buscar por lista de todos estagiários")
        @MethodSource("dataFactory.GeralDataFactory#providePaginasETamanhosDePaginaValidos")
        public void testBuscarPorListarTodosEstagiarios(int pagina, int tamanhoPagina) {
            estagiarioService.buscarPorListarTodosEstagiarios(pagina, tamanhoPagina)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .contentType(ContentType.JSON)
                    .body("pagina", Matchers.is(pagina))
                    .body("tamanho", Matchers.is(tamanhoPagina))
                    .body("totalElementos", Matchers.greaterThanOrEqualTo(0))
            ;
        }
        @ParameterizedTest(name = "{index} - Página: {0} - Tamanho: {1}")
        @DisplayName("Buscar por lista de todos estagiários com página e tamanho inválidos")
        @Story("Buscar estagiário")
        @Description("Buscar por lista de todos estagiários com página e tamanho inválidos")
        @MethodSource("dataFactory.GeralDataFactory#providePaginasETamanhosDePaginaInvalidos")
        public void testBuscarPorListarTodosEstagiariosComPaginasETamanhosInvalidos(String pagina, String tamanhoPagina) {
            estagiarioService.buscarPorListarTodosEstagiariosQueryInvalida(pagina, tamanhoPagina)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
            ;
        }
    }

    // endregion
    // region Atualizar estagiário
    @Nested
    class AtualizarEstagiario{
    @BeforeAll
    public static void setupEstagiario() {
        estagiarioValido = EstagiarioDataFactory.gerarEstagiarioValido(trilhaCriada);
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
                .extract()
                .as(EstagiarioModel.class);
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
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertEquals("Estagiário inexistente ou inativo.", jsonFailureResponse.getMessage());
    }
    @ParameterizedTest(name = "{index} - E-mail: {0}")
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
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(jsonFailureResponse.getErrors().contains("emailPessoal: Endereço de e-mail inválido"));
    }
    @ParameterizedTest(name = "{index} - E-mail: {0}")
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
                .extract()
                .as(JSONFailureResponse.class);
        Assertions.assertTrue(jsonFailureResponse.getErrors().contains("emailCorporativo: Endereço de e-mail inválido"));
    }
    @ParameterizedTest(name = "{index} - CPF: {0}")
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
                .extract()
                .as(JSONFailureResponse.class);
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
    public void testDeletarEstagiarioInexistente() {
        estagiarioCriado = retornarEstagiarioCriado();
        estagiarioService.deletarEstagiario(estagiarioCriado)
            .then()
                .statusCode(HttpStatus.SC_OK);
        estagiarioService.deletarEstagiario(estagiarioCriado)
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
    @Test
    @DisplayName("Desativar estagiário")
    @Story("Deletar estagiário")
    @Description("Desativar estagiário")
    public void testDesativarEstagiario() {
        estagiarioCriado = retornarEstagiarioCriado();
        estagiarioService.desativarEstagiario(estagiarioCriado)
            .then()
                .statusCode(HttpStatus.SC_OK);
        estagiarioService.buscarEstagiarioPorIdEstagiario(estagiarioCriado.getIdEstagiario())
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("ativo", equalTo(false));
        estagiarioService.deletarEstagiario(estagiarioCriado)
            .then()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    @DisplayName("Desativar estagiário já desativado")
    @Story("Deletar estagiário")
    @Description("Desativar estagiário já desativado")
    public void testDesativarEstagiarioJaDesativado() {
        estagiarioCriado = retornarEstagiarioCriado();
        estagiarioService.desativarEstagiario(estagiarioCriado)
            .then()
                .statusCode(HttpStatus.SC_OK);
        estagiarioService.desativarEstagiario(estagiarioCriado)
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        estagiarioService.buscarEstagiarioPorIdEstagiario(estagiarioCriado.getIdEstagiario())
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("ativo", equalTo(false));
        estagiarioService.deletarEstagiario(estagiarioCriado)
            .then()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    @DisplayName("Desativar estagiário por ID inexistente")
    @Story("Deletar estagiário")
    @Description("Desativar estagiário por ID inexistente")
    public void testDesativarEstagiarioInexistente() {
        estagiarioCriado = retornarEstagiarioCriado();
        estagiarioService.deletarEstagiario(estagiarioCriado)
            .then()
                .statusCode(HttpStatus.SC_OK);
        estagiarioService.desativarEstagiario(estagiarioCriado)
            .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
    // endregion
}
