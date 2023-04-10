package test;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.ProgramaService;
import utils.Autenticacao;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProgramaTest extends BaseTest {
    ProgramaService programaService = new ProgramaService();
    @Test
    public void testAdicionarPrograma(){
        var response = programaService.criarPorgrama()
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        response.assertThat().statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void testBuscarTodosProgramas(){
        var response = programaService.buscarProgramas()
            .then()
                .statusCode(HttpStatus.SC_OK);
        response.assertThat().statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void testEditarPrograma(){
        var response = programaService.atualizarPrograma()
                .then()
                .statusCode(HttpStatus.SC_OK);
        response.assertThat().statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void testDeletarPrograma(){
        var response = programaService.desativarPrograma()
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.assertThat().statusCode(HttpStatus.SC_OK);
    }

}
