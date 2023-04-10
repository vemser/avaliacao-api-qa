package test;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import service.ProgramaService;

public class ProgramaTest extends BaseTest {
    ProgramaService programaService = new ProgramaService();
//    region TESTES DE CRIAR PROGRAMA!
    @Test
    public void testAdicionarPrograma(){
        var response = programaService.criarPrograma()
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        response.assertThat().contentType(ContentType.JSON);
        response.assertThat().statusCode(HttpStatus.SC_CREATED);
        response.log().all();
    }
    @Test
    public void testAdicionarProgramaComErro(){
        var response = programaService.criarProgramaSemNome()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
        response.log().all();
    }
    @Test
    public void testCriarProgramaComDataAbaixoDaAtual(){
        var response = programaService.criarProgramaComDataAbaixoDaAtual()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
        response.log().all();
    }
//    endregion
//    region TESTES DE BUSCAR PROGRAMA

    @Test
    public void testBuscarTodosProgramas(){
        var response = programaService.buscarProgramas()
            .then()
                .statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.JSON);
        response.assertThat().statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void testBuscarTodosProgramasSemPagina(){
        var response = programaService.buscarProgramasSemNumeroDePaginas()
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.log().all();
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @Test
    public void testBuscarTodosProgramasSemTamanho(){
        var response = programaService.buscarProgramasSemTamanho()
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.log().all();
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
//endregion
//    region EDITAR PROGRAMA
    @Test
    public void testEditarPrograma(){
        var response = programaService.atualizarPrograma()
                .then()
                .statusCode(HttpStatus.SC_OK);
        response.assertThat().contentType(ContentType.JSON);
        response.assertThat().statusCode(HttpStatus.SC_OK);
        response.log().all();
    }
    @Test
    public void testEditarProgramaSemId(){
        var response = programaService.atualizarProgramaSemInformarId()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().contentType(ContentType.JSON);
        response.log().all();
    }
    @Test
    public void testEditarProgramaComIdErrado(){
        var response = programaService.atualizarProgramaComIdErrado()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().contentType(ContentType.JSON);
        response.log().all();
    }
//    endregion
//    region DESATIVAR PROGRAMA
    @Test
    public void testDesativarPrograma(){
        var response = programaService.desativarPrograma()
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
        response.log().all();
    }
    @Test
    public void testDesativarProgramaSemId(){
        var response = programaService.desativarProgramaComLetrasNoId()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
        response.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
        response.log().all();
    }
    @Test
    public void testDesativarProgramaComIdErrado(){
        var response = programaService.desativarProgramaComIdErrado()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.log().all();
    }
//    endregion
//    region DELETAR PROGRAMA
@Test
public void testDeletarPrograma(){
    var response = programaService.deletarPrograma()
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    response.assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    response.log().all();
}
    @Test
    public void testDeletarProgramaComLetrasNoId(){
        var response = programaService.deletarProgramaComLetrasNoId()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.log().all();
    }
    @Test
    public void testDeletarProgramaComIdErrado(){
        var response = programaService.deletarProgramaComIdErrado()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        response.assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
        response.log().all();
    }
//    endregion
}
