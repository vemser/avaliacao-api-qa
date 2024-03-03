package test;

import base.BaseTest;
import dataFactory.AusenciaDataFactory;
import model.AusenciaModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.AusenciaService;
import utils.FakerHolder;
import utils.Gerador;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AusenciaTest extends BaseTest {

    @Test
    @DisplayName("Criar ausencia com sucesso")
    public void testeCadastrarAusencia() {
        AusenciaModel request = AusenciaDataFactory.cadastrarAusencia();
        Integer idAusencia = AusenciaService.criarAusencia(request)
                .statusCode(201)
                .extract()
                .path("idAusencia");

        AusenciaModel response = AusenciaService.buscarAusenciaPorId(idAusencia)
                .statusCode(200)
                .extract()
                .as(AusenciaModel.class);

        assertAll(
                () -> Assertions.assertNotNull(response.idAusencia),
                () -> Assertions.assertEquals(request.getDiaDeFalta(), Gerador.dataComHorario(idAusencia)),
                () -> Assertions.assertEquals(request.getObservacao(), response.getObservacao()),
                () -> Assertions.assertEquals(request.getHorarioInicio(), response.getHorarioInicio()),
                () -> Assertions.assertEquals(request.getHorarioFim(), response.getHorarioFim()),
                () -> Assertions.assertEquals(request.getIdEstagiario(), response.getIdEstagiario()),
                () -> Assertions.assertNull(response.getLink())
        );

        AusenciaService.deletarAusencia(idAusencia)
                .statusCode(204);
    }

    @Test
    @DisplayName("Buscar ausencia por id com sucesso")
    public void testBuscarAusenciaPorId() {
        AusenciaModel request = AusenciaDataFactory.cadastrarAusencia();
        Integer idAusencia = AusenciaService.criarAusencia(request)
                .statusCode(201)
                .extract()
                .path("idAusencia");

        AusenciaModel response = AusenciaService.buscarAusenciaPorId(idAusencia)
                .statusCode(200)
                .extract()
                .as(AusenciaModel.class);

        assertAll(
                () -> Assertions.assertNotNull(response.idAusencia),
                () -> Assertions.assertEquals(request.getDiaDeFalta(), Gerador.dataComHorario(idAusencia)),
                () -> Assertions.assertEquals(request.getObservacao(), response.getObservacao()),
                () -> Assertions.assertEquals(request.getHorarioInicio(), response.getHorarioInicio()),
                () -> Assertions.assertEquals(request.getHorarioFim(), response.getHorarioFim()),
                () -> Assertions.assertEquals(request.getIdEstagiario(), response.getIdEstagiario()),
                () -> Assertions.assertNull(response.getLink())
        );

        AusenciaService.deletarAusencia(idAusencia)
                .statusCode(204);
    }

    @Test
    @DisplayName("Buscar imagem com sucesso")
    public void testBuscarImagemPorId() {
        AusenciaModel request = AusenciaDataFactory.cadastrarAusencia();
        Integer idAusencia = AusenciaService.criarAusencia(request)
                .statusCode(201)
                .extract()
                .path("idAusencia");

        AusenciaService.atualizarImagem(idAusencia, AusenciaDataFactory.gerarImagem(), "image/png")
                .statusCode(200)
                .extract()
                .asString();

        String responseAtualizar = AusenciaService.buscarImagemPorId(idAusencia)
                .statusCode(200)
                .extract()
                .asString();

        assertNotNull(responseAtualizar);

        AusenciaService.deletarAusencia(idAusencia)
                .statusCode(204);
    }

    @Test
    @DisplayName("Buscar ausencia pelo id do estagiario com sucesso")
    public void testBuscarAusenciaPorIdEstagiario() {
        AusenciaModel request = AusenciaDataFactory.cadastrarAusencia();
        AusenciaModel createResponse = AusenciaService.criarAusencia(request)
                .statusCode(201)
                .extract()
                .as(AusenciaModel.class);

        AusenciaModel response = AusenciaService.buscarAusenciaPorIdEstagiario(createResponse.idEstagiario, 0, 100)
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("content", AusenciaModel.class)
                .stream()
                .filter(ausencia -> ausencia.getIdAusencia().equals(createResponse.idAusencia))
                .findFirst()
                .orElse(null);

        assertAll(
                () -> Assertions.assertNotNull(response.idAusencia),
                () -> Assertions.assertEquals(request.getDiaDeFalta(), Gerador.dataComHorario(createResponse.idAusencia)),
                () -> Assertions.assertEquals(request.getObservacao(), response.getObservacao()),
                () -> Assertions.assertEquals(request.getHorarioInicio(), response.getHorarioInicio()),
                () -> Assertions.assertEquals(request.getHorarioFim(), response.getHorarioFim()),
                () -> Assertions.assertEquals(request.getIdEstagiario(), response.getIdEstagiario()),
                () -> Assertions.assertNull(response.getLink())
        );

        AusenciaService.deletarAusencia(createResponse.idAusencia)
                .statusCode(204);
    }

    @Test
    @DisplayName("Buscar todas as ausencias com sucesso")
    public void testBuscarTodasAsAusencias() {
        AusenciaModel request = AusenciaDataFactory.cadastrarAusencia();
        AusenciaModel createResponse = AusenciaService.criarAusencia(request)
                .statusCode(201)
                .extract()
                .as(AusenciaModel.class);

        List<AusenciaModel> response = AusenciaService.buscarTodasAusencias(0, 100)
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("content", AusenciaModel.class);

        assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertFalse(response.isEmpty()),
                () -> Assertions.assertTrue(response.stream().anyMatch(ausencia -> ausencia.getIdAusencia().equals(createResponse.idAusencia)))
        );

        AusenciaService.deletarAusencia(createResponse.idAusencia)
                .statusCode(204);
    }

    @Test
    @DisplayName("Deletar ausencia com sucesso")
    public void testDeletarAusenciaPorId() {
        AusenciaModel request = AusenciaDataFactory.cadastrarAusencia();
        Integer idAusencia = AusenciaService.criarAusencia(request)
                .statusCode(201)
                .extract()
                .path("idAusencia");

        AusenciaService.deletarAusencia(idAusencia)
                .statusCode(204);

        AusenciaService.buscarAusenciaPorId(idAusencia)
                .statusCode(400);
    }

    @Test
    @DisplayName("Atualizar ausencia com sucesso")
    public void testAtualizarAusencia() {
        AusenciaModel request = AusenciaDataFactory.cadastrarAusencia();
        Integer idAusencia = AusenciaService.criarAusencia(request)
                .statusCode(201)
                .extract()
                .path("idAusencia");

        AusenciaModel updateRequest = AusenciaDataFactory.cadastrarAusencia();
        AusenciaService.atualizarAusencia(idAusencia, updateRequest)
                .statusCode(200);

        AusenciaModel response = AusenciaService.buscarAusenciaPorId(idAusencia)
                .statusCode(200)
                .extract()
                .as(AusenciaModel.class);

        assertAll(
                () -> Assertions.assertNotNull(response.idAusencia),
                () -> Assertions.assertEquals(updateRequest.getDiaDeFalta(), Gerador.dataComHorario(idAusencia)),
                () -> Assertions.assertEquals(updateRequest.getObservacao(), response.getObservacao()),
                () -> Assertions.assertEquals(updateRequest.getHorarioInicio(), response.getHorarioInicio()),
                () -> Assertions.assertEquals(updateRequest.getHorarioFim(), response.getHorarioFim()),
                () -> Assertions.assertEquals(updateRequest.getIdEstagiario(), response.getIdEstagiario()),
                () -> Assertions.assertNull(response.getLink())
        );

        AusenciaService.deletarAusencia(idAusencia)
                .statusCode(204);
    }

    @Test
    @DisplayName("Atualizar imagem com sucesso")
    public void testAtualizarImagem() {
        AusenciaModel request = AusenciaDataFactory.cadastrarAusencia();
        Integer idAusencia = AusenciaService.criarAusencia(request)
                .statusCode(201)
                .extract()
                .path("idAusencia");

        AusenciaService.atualizarImagem(idAusencia, AusenciaDataFactory.gerarImagem(), "image/png")
                .statusCode(200)
                .extract()
                .asString();

        String responseAtualizar = AusenciaService.buscarImagemPorId(idAusencia)
                .statusCode(200)
                .extract()
                .asString();

        assertNotNull(responseAtualizar);

        AusenciaService.deletarAusencia(idAusencia)
                .statusCode(204);
    }

    @Test
    @DisplayName("Atualizar link com sucesso")
    public void testAtualizarLink() {
        AusenciaModel request = AusenciaDataFactory.cadastrarAusencia();
        Integer idAusencia = AusenciaService.criarAusencia(request)
                .statusCode(201)
                .extract()
                .path("idAusencia");

        AusenciaService.atualizarLink(idAusencia, "https://www.google.com")
                .statusCode(200);

        AusenciaModel response = AusenciaService.buscarAusenciaPorId(idAusencia)
                .statusCode(200)
                .extract()
                .as(AusenciaModel.class);

        assertAll(
                () -> Assertions.assertNotNull(response.idAusencia),
                () -> Assertions.assertEquals(request.getDiaDeFalta(), Gerador.dataComHorario(idAusencia)),
                () -> Assertions.assertEquals(request.getObservacao(), response.getObservacao()),
                () -> Assertions.assertEquals(request.getHorarioInicio(), response.getHorarioInicio()),
                () -> Assertions.assertEquals(request.getHorarioFim(), response.getHorarioFim()),
                () -> Assertions.assertEquals(request.getIdEstagiario(), response.getIdEstagiario()),
                () -> Assertions.assertEquals("https://www.google.com", response.getLink())
        );

        AusenciaService.deletarAusencia(idAusencia)
                .statusCode(204);
    }

    @Test
    @DisplayName("Atualizar link com URI muito longa")
    public void testAtualizarLinkComErro() {
        AusenciaModel request = AusenciaDataFactory.cadastrarAusencia();
        Integer idAusencia = AusenciaService.criarAusencia(request)
                .statusCode(201)
                .extract()
                .path("idAusencia");

        AusenciaService.atualizarLink(idAusencia, FakerHolder.instance.lorem().characters(100000))
                .statusCode(414);

        AusenciaService.deletarAusencia(idAusencia)
                .statusCode(204);
    }

    @Test
    @DisplayName("Atualizar com falta de informação")
    public void testAtualizarAusenciaComErro() {
        AusenciaModel request = AusenciaDataFactory.cadastrarAusencia();
        Integer idAusencia = AusenciaService.criarAusencia(request)
                .statusCode(201)
                .extract()
                .path("idAusencia");

        AusenciaModel updateRequest = new AusenciaModel();
        AusenciaService.atualizarAusencia(idAusencia, updateRequest)
                .statusCode(500);

        AusenciaService.deletarAusencia(idAusencia)
                .statusCode(204);
    }

    @Test
    @DisplayName("Buscar ausencia por id inexistente")
    public void testBuscarAusenciaPorIdInexistente() {
        AusenciaService.buscarAusenciaPorId(0)
                .statusCode(400);
    }
}
