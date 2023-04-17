package test;

import base.BaseTest;
import dataFactory.AcompanhamentoDataFactory;
import dataFactory.AvaliacaoDataFactory;
import dataFactory.ProgramaDataFactory;
import dataFactory.TrilhaDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import model.AcompanhamentoModel;
import model.AvaliacaoModel;
import model.ProgramaModel;
import model.TrilhaModel;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import service.AcompanhamentoService;
import service.ProgramaService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static test.TrilhaTest.programaCriado;

public class AcompanhamentoTest extends BaseTest {
    AcompanhamentoService acompanhamentoService = new AcompanhamentoService();
    AcompanhamentoModel acompanhamentoModel = new AcompanhamentoModel();

//region CRIAR ACOMPANHAMENTO
    @Test
    @DisplayName("Criar um acompanhamento com sucesso")
    @Story("Criar um acompanhamento")
    @Description("Criar um acompanhamento com sucesso")
    public void testCadastrarAcompanhamento(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(1346);
        var response = acompanhamentoService.criarAcompanhamento(acompanhamentoModel)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(AcompanhamentoModel.class);
        assertThat(acompanhamentoModel.getTitulo(), equalTo(response.getTitulo()));
        assertThat(acompanhamentoModel.getDataInicio(), equalTo(response.getDataInicio()));
        assertThat(acompanhamentoModel.getDescricao(), equalTo(response.getDescricao()));
        AcompanhamentoModel id = AcompanhamentoDataFactory.deletarAcompanhamentoPorId(response.getIdAcompanhamento());
        acompanhamentoService.deletarAcompanhamentoPeloId(id)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        System.out.println(response);
    }

    @Test
    @DisplayName("Falha ao criar um acompanhamento sem id do programa")
    @Story("Criar um acompanhamento")
    @Description("Falha ao criar um acompanhamento sem id do programa")
    public void testCadastrarAcompanhamentoSemId(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoSemId(programaCriado);
        acompanhamentoService.criarAcompanhamento(acompanhamentoModel)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .contentType(ContentType.JSON);
    }
//endregion
//region ATUALIZAR ACOMPANHAMENTO
    @Test
    public void atualizarAcompanhamento() {
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(1346);
        AcompanhamentoModel criarAcompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
        AcompanhamentoModel extrairId = AcompanhamentoDataFactory.deletarAcompanhamentoPorId(criarAcompanhamento.getIdAcompanhamento());
        AcompanhamentoModel acompanhamentoAlterado = AcompanhamentoDataFactory.alterarAcompanhamento(criarAcompanhamento.getIdAcompanhamento());
        acompanhamentoService.atualizarAcompanhamento(acompanhamentoAlterado, criarAcompanhamento)
                .then()
                .statusCode(HttpStatus.SC_OK);
        acompanhamentoService.deletarAcompanhamentoPeloId(extrairId)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }
    @Test
    public void atualizarAcompanhamentoSemId() {
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(1346);
        AcompanhamentoModel criarAcompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
        AcompanhamentoModel extrairId = AcompanhamentoDataFactory.deletarAcompanhamentoPorId(criarAcompanhamento.getIdAcompanhamento());
        AcompanhamentoModel acompanhamentoAlterado = AcompanhamentoDataFactory.alterarAcompanhamentoSemId(000000000000);
        var response = acompanhamentoService.atualizarAcompanhamento(acompanhamentoAlterado, criarAcompanhamento)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        acompanhamentoService.deletarAcompanhamentoPeloId(extrairId)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.log().all();
    }
//endregion
//region LISTAR ACOMPANHAMENTOS POR PROGRAMA
    @Test
    @DisplayName("Listar acompanhamento pelo id do programa")
    @Story("Listar acompanhamento")
    @Description("Listar acompanhamento pelo id do programa")
    public void testBuscarAcompanhamentoPorIdDePrograma(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(1346);
        AcompanhamentoModel idPrograma = AcompanhamentoDataFactory.buscarAcompanhamentoPorId(acompanhamentoModel.getIdPrograma());
        acompanhamentoService.buscarAcompanhamentoPeloId(idPrograma)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
    }
    @Test
    @DisplayName("Falha ao listar acompanhamento com numero de id invalido")
    @Story("Listar acompanhamento")
    @Description("Falha ao listar acompanhamento com numero de id invalido")
    public void testBuscarProgramaPorIdDePrograma(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(00000000000000000);
        acompanhamentoService.buscarAcompanhamentoPeloId(acompanhamentoModel)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region BUSCAR TODOS ACOMPANHAMENTOS
    @ParameterizedTest(name = "{index} - Página: {0} - Tamanho: {1}")
    @DisplayName("Buscar todos os acompanhamentos")
    @Story("Buscar trilhas")
    @Description("Buscar todos os acompanhamentos")
    @MethodSource("dataFactory.GeralDataFactory#providePaginasETamanhosDePaginaValidos")
    public void testBuscarTodosAcompanhamentos(int pagina, int tamanhoPagina) {
        acompanhamentoService.buscarAcompanhamentoPorPaginas(pagina, tamanhoPagina)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("pagina", Matchers.is(pagina))
                .body("tamanho", Matchers.is(tamanhoPagina))
                .body("totalElementos", Matchers.greaterThanOrEqualTo(0))
        ;
    }
    @Test
    @DisplayName("Falha ao buscar todos os acompanhamentos por nao informar o numero de paginas e tamanho")
    @Story("Buscar trilhas")
    @Description("Falha ao buscar todos os acompanhamentos por nao informar o numero de paginas e tamanho")
    public void testBuscarTodosAcompanhamentosSemPagina() {
        acompanhamentoService.buscarAcompanhamentoSemInformarPaginas()
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//region BUSCAR ACOMPANHAMENTO PELO ID DO ACOMPANHAMENTO
    @Test
    @DisplayName("Buscar acompanhamento por id do programa")
    @Story("Buscar acompanhamento por id")
    @Description("Buscar acompanhamento por id do programa")
    public void testBuscarAcompanhamentoPorIdDoPrograma(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(1346);
        AcompanhamentoModel acompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
        AcompanhamentoModel idAcompanhamento = AcompanhamentoDataFactory.buscarAcompanhamentoPorIdDoAcompanhamento(acompanhamento.getIdAcompanhamento());
        acompanhamentoService.buscarAcompanhamentoPeloIdDoAcompanhamento(idAcompanhamento)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
        acompanhamentoService.desativarAcompanhamentoPeloId(idAcompanhamento)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Falha ao buscar acompanhamento com id do acompanhamento inexistente")
    @Story("Buscar acompanhamento por id")
    @Description("Falha ao buscar acompanhamento com id do acompanhamento inexistente")
    public void testBuscarAcompanhamentoSemId(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(1347);
        AcompanhamentoModel acompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
        AcompanhamentoModel idAcompanhamento = AcompanhamentoDataFactory.buscarAcompanhamentoComIdInvalido(acompanhamento.getIdAcompanhamento());
        var resp = acompanhamentoService.buscarAcompanhamentoPeloIdDoAcompanhamento(idAcompanhamento)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        acompanhamentoService.desativarAcompanhamentoPeloId(idAcompanhamento)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
//endregion
//region DESATIVAR ACOMPANHAMENTO
    @Test
    @DisplayName("Desativar um acompanhamento com sucesso")
    @Story("Deletar um acompanhamento")
    @Description("Desativar um acompanhamento com sucesso")
    public void testDesativatAcompanhamento(){
        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(1346);
        AcompanhamentoModel acompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
        AcompanhamentoModel idAcompanhamento = AcompanhamentoDataFactory.deletarAcompanhamentoPorId(acompanhamento.getIdAcompanhamento());
        acompanhamentoService.desativarAcompanhamentoPeloId(idAcompanhamento)
            .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    @DisplayName("Falha em deletar um acompanhamento com id inexistente")
    @Story("Deletar um acompanhamento")
    @Description("Falha em deletar um acompanhamento com id inexistente")
    public void testDesativarAcompanhamentoIdInvalido(){
        acompanhamentoModel = AcompanhamentoDataFactory.deletarAcompanhamentocomIdErrado(00000000);
        var rep = acompanhamentoService.desativarAcompanhamentoPeloId(acompanhamentoModel)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        rep.log().all();
    }
//endregion
//region DELETAR ACOMPANHAMENTO
//    @Test
//    @DisplayName("Deletar um acompanhamento com sucesso")
//    @Story("Deletar um acompanhamento")
//    @Description("Deletar um acompanhamento com sucesso")
//    public void testDeletarAcompanhamento(){
//        acompanhamentoModel = AcompanhamentoDataFactory.gerarAcompanhamentoValido(1346);
//        AcompanhamentoModel acompanhamento = acompanhamentoService.criarAcompanhamento(acompanhamentoModel).then().extract().as(AcompanhamentoModel.class);
//        AcompanhamentoModel idAcompanhamento = AcompanhamentoDataFactory.deletarAcompanhamentoPorId(acompanhamento.getIdAcompanhamento());
//        acompanhamentoService.deletarAcompanhamentoPeloId(idAcompanhamento)
//            .then()
//                .statusCode(HttpStatus.SC_NO_CONTENT);
//    }
//    @Test
//    @DisplayName("Falha em deletar um acompanhamento com id inexistente")
//    @Story("Deletar um acompanhamento")
//    @Description("Falha em deletar um acompanhamento com id inexistente")
//    public void testDeletarAcompanhamentoIdInvalido(){
//        acompanhamentoModel = AcompanhamentoDataFactory.desativarAcompanhamentocomIdErrado(00000000);
//        acompanhamentoService.desativarAcompanhamentoPeloId(acompanhamentoModel)
//            .then()
//                .statusCode(HttpStatus.SC_BAD_REQUEST);
//    }
//endregion
}
