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
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.AcompanhamentoService;
import service.ProgramaService;

import java.lang.reflect.Type;

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
                ;
    }
//    endregion
//region CRIAR ACOMPANHAMENTO
    @Test
    @DisplayName("Criar um acompanhamento com sucesso")
    @Story("Criar um acompanhamento")
    @Description("Criar um acompanhamento com sucesso")
    public void testCadastrarAcompanhamento(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(programaCriado);
        var response = acompanhamentoService.criarAcompanhamento(acompanhamentoModel)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(AcompanhamentoModel.class);
        assertThat(acompanhamentoModel.getTitulo(), equalTo(response.getTitulo()));
        assertThat(acompanhamentoModel.getDataInicio(), equalTo(response.getDataInicio()));
        assertThat(acompanhamentoModel.getDescricao(), equalTo(response.getDescricao()));
        acompanhamentoService.deletarAcompanhamentoPeloId(response)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Falha ao criar um acompanhamento sem id do programa")
    @Story("Criar um acompanhamento")
    @Description("Falha ao criar um acompanhamento sem id do programa")
    public void testCadastrarAcompanhamentoSemId(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoSemId(programaCriado);
        var response = acompanhamentoService.criarAcompanhamento(acompanhamentoModel)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON);

    }
//endregion
//region LISTAR ACOMPANHAMENTOS POR PROGRAMA
    @Test
    @DisplayName("Listar acompanhamento pelo id do programa")
    @Story("Listar acompanhamento")
    @Description("Listar acompanhamento pelo id do programa")
    public void testBuscarAcompanhamentoPorIdDePrograma(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(programaCriado);
        AcompanhamentoModel idprograma = AcompanhamentoDataFactory.buscarAcompanhamentoPorId(acompanhamentoModel.getIdPrograma());
        var response = acompanhamentoService.buscarAcompanhamentoPeloId(idprograma)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);

    }
    @Test
    @DisplayName("Falha ao listar acompanhamento com numero de invalido")
    @Story("Listar acompanhamento")
    @Description("Falha ao listar acompanhamento com numero de invalido")
    public void testBuscarProgramaPorIdDePrograma(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(00000000000000000);
        acompanhamentoService.buscarAcompanhamentoPeloId(acompanhamentoModel)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }


//endregion
//region BUSCAR TODOS ACOMPANHAMENTOS
    @ParameterizedTest
    @DisplayName("Buscar todas trilhas")
    @Story("Buscar trilhas")
    @Description("Buscar todas trilhas")
    @MethodSource("dataFactory.GeralDataFactory#providePaginasETamanhosDePaginaValidos")
    public void testBuscarTodosAcompanhamentos(int pagina, int tamanhoPagina) {
        acompanhamentoService.buscarAcompanhamentoPorPaginas(pagina, tamanhoPagina)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("pagina", Matchers.is(pagina))
                .body("tamanho", Matchers.is(tamanhoPagina))
//                .body("totalElementos", Matchers.greaterThanOrEqualTo(0))
        ;
    }
    @Test
    @DisplayName("Buscar todas trilhas")
    @Story("Buscar trilhas")
    @Description("Buscar todas trilhas")
    public void testBuscarTodosAcompanhamentosSemPagina() {
        acompanhamentoService.buscarAcompanhamentoSemInformarPaginas()
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region BUSCAR ACOMPANHAMENTO PELO D DO ACOMPANHAMENTO
    @Test
    @DisplayName("Buscar acompanhamento por id do programa")
    @Story("Buscar acompanhamento por id")
    @Description("Buscar acompanhamento por id do programa")
    public void testBuscarAcompanhamentoPorIdDoPrograma(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(programaCriado);
        AcompanhamentoModel idprograma = AcompanhamentoDataFactory.buscarAcompanhamentoPorId(acompanhamentoModel.getIdPrograma());
        var response = acompanhamentoService.buscarAcompanhamentoPeloId(idprograma)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
        response.log().all();

    }
    @Test
    @DisplayName("Buscar acompanhamento por id do acompanhamento")
    @Story("Buscar acompanhamento por id")
    @Description("Buscar acompanhamento por id do acompanhamento")
    public void testBuscarAcompanhamentoSemId(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(programaCriado);
        AcompanhamentoModel acompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
        AcompanhamentoModel idAcompanhamento = AcompanhamentoDataFactory.buscarAcompanhamentoComIdInvalido(acompanhamento.getIdAcompanhamento());
        acompanhamentoService.buscarAcompanhamentoPeloIdDoAcompanhamento(idAcompanhamento)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                ;
    }
//endregion
//region DESATIVAR ACOMPANHAMENTO
    @Test
    @DisplayName("Deletar um acompanhamento com sucesso")
    @Story("Deletar um acompanhamento")
    @Description("Deletar um acompanhamento com sucesso")
    public void testDesativatAcompanhamento(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(programaCriado);
        AcompanhamentoModel acompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
        AcompanhamentoModel idAcompanhamento = AcompanhamentoDataFactory.buscarAcompanhamentoPorIdDoAcompanhamento(acompanhamento.getIdAcompanhamento());
        acompanhamentoService.desativarAcompanhamentoPeloId(idAcompanhamento)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Falha em desativar um acompanhamento com id inexistente")
    @Story("Deletar um acompanhamento")
    @Description("Falha em desativar um acompanhamento com id inexistente")
    public void testDesativarAcompanhamentoIdInvalido(){
        acompanhamentoModel = AcompanhamentoDataFactory.deletarAcompanhamentocomIdErrado(00000000);
        acompanhamentoService.desativarAcompanhamentoPeloId(acompanhamentoModel)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region DELETAR ACOMPANHAMENTO
    @Test
    @DisplayName("Deletar um acompanhamento com sucesso")
    @Story("Deletar um acompanhamento")
    @Description("Deletar um acompanhamento com sucesso")
    public void testDeletarAcompanhamento(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(programaCriado);
        AcompanhamentoModel acompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
        AcompanhamentoModel idAcompanhamento = AcompanhamentoDataFactory.buscarAcompanhamentoPorIdDoAcompanhamento(acompanhamento.getIdAcompanhamento());
        acompanhamentoService.deletarAcompanhamentoPeloId(idAcompanhamento)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Falha em deletar um acompanhamento com id inexistente")
    @Story("Deletar um acompanhamento")
    @Description("Falha em deletar um acompanhamento com id inexistente")
    public void testDeletarAcompanhamentoIdInvalido(){
        acompanhamentoModel = AcompanhamentoDataFactory.deletarAcompanhamentocomIdErrado(00000000);
        acompanhamentoService.desativarAcompanhamentoPeloId(acompanhamentoModel)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
}
