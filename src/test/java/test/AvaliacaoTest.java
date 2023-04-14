package test;

import base.BaseTest;
import dataFactory.*;
import io.restassured.http.ContentType;
import model.*;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.*;

import static test.TrilhaTest.programaCriado;


public class AvaliacaoTest extends BaseTest {
    AvaliacaoModel avaliacaoModel = new AvaliacaoModel();
    AvaliacaoService avaliacaoService = new AvaliacaoService();
    AvaliacaoDataFactory avaliacaoDataFactory = new AvaliacaoDataFactory();
    private static TrilhaService trilhaService;
    private static AcompanhamentoService acompanhamentoService;
    private static ProgramaService programaService;
    private static EstagiarioService estagiarioService;
   EstagiarioModel estagiarioCriado;
   AcompanhamentoModel acompanhamentoCriado;

    @BeforeAll
    public static void criarPreRequisitos() {
        ProgramaModel programaCriado = programaService.criarPrograma(ProgramaDataFactory.gerarProgramaValido())
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(ProgramaModel.class);
        TrilhaModel trilhaCriada = trilhaService.adicionarTrilha(TrilhaDataFactory.gerarTrilhaValida(programaCriado))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(TrilhaModel.class);
        AcompanhamentoModel acompanhamentoCriado = acompanhamentoService.criarAcompanhamento(AcompanhamentoDataFactory.gerarAcompanhamentoValido(programaCriado))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(AcompanhamentoModel.class);
        EstagiarioModel estagiarioCriado = estagiarioService.criarEstagiario(EstagiarioDataFactory.gerarEstagiarioValido(trilhaCriada))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(EstagiarioModel.class);
    }

    @Test
    public void criarAvaliacao(){
        avaliacaoModel = AvaliacaoDataFactory.gerarAvaliacaoValida(acompanhamentoCriado, estagiarioCriado);
        var response = avaliacaoService.criarAvaliacao(avaliacaoModel)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(AcompanhamentoModel.class);
    }




}
