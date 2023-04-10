package test;

import base.BaseTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import service.TrilhaService;

public class TrilhaTest extends BaseTest{
    TrilhaService trilhaService = new TrilhaService();
    @Test
    public void testCadastrarTrilha(){
        var response = trilhaService.adicionarTrilha()
                        .then()
                        .statusCode(HttpStatus.SC_CREATED);
        response.log().all();
    }
    @Test
    public void testAtualizarTrilha(){
        var response = trilhaService.atualizarTrilha()
                .then()
                .statusCode(HttpStatus.SC_OK);
        response.log().all();
    }
    @Test
    public void testBuscarTrilha(){
        var response = trilhaService.buscarTodasAsTrilhas()
                .then()
                .statusCode(HttpStatus.SC_OK);
        response.log().all();
    }


    @Test
    public void testDeletarTrilhaPeloId(){
        var response = trilhaService.deletarTrilhaIdTrilha()
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        response.log().all();
    }
}
