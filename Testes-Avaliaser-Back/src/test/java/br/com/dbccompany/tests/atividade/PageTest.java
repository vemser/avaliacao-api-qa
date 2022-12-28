package br.com.dbccompany.tests.atividade;

import br.com.dbccompany.client.*;
import br.com.dbccompany.data.changeless.AtividadeData;
import br.com.dbccompany.data.changeless.UsuarioData;
import br.com.dbccompany.data.factory.AtividadeDataFactory;
import br.com.dbccompany.data.factory.ModuloDataFactory;
import br.com.dbccompany.data.factory.TrilhaDataFactory;
import br.com.dbccompany.data.factory.UsuarioDataFactory;
import br.com.dbccompany.dto.*;
import br.com.dbccompany.model.Atividade;
import br.com.dbccompany.model.Modulo;
import br.com.dbccompany.model.Trilha;
import br.com.dbccompany.model.Usuario;
import br.com.dbccompany.tests.base.BaseTest;
import br.com.dbccompany.utils.Utils;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.apache.http.HttpStatus;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


@Epic("Page Tests")
@Feature("Atividade")
@DisplayName("Listar Atividade")
public class PageTest extends BaseTest{

    AtividadeClient atividadeClient = new AtividadeClient();

    TrilhaClient trilhaClient = new TrilhaClient();

    ModuloClient moduloClient = new ModuloClient();

    PageClient pageClient = new PageClient();

    UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    @Story("Deve listar com sucesso")
    public void testeDeveRetornarListaDeAtividadeNaTrilhaStatusPendente() {

        Modulo novoModulo = ModuloDataFactory.novoModulo();
        ModuloDTO moduloCadastrato = moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .extract().as(ModuloDTO.class);

        Trilha novatrilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(novatrilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        Atividade novaAtividade = AtividadeDataFactory.novaAtividade(
                moduloCadastrato.getIdModulo(), trilhaCadastrada);

        AtividadeDTO cadastroAtividade = atividadeClient.cadastrar(moduloCadastrato.getIdModulo(),
                        trilhaCadastrada.getIdTrilha(), Utils.converterAtividadeToJson(novaAtividade))
                .then()
                .extract().as(AtividadeDTO.class);

        PageDTO atividadeStatusConcluidos = pageClient
                .listarAtividadeStatus("0", "1", trilhaCadastrada.getIdTrilha(), AtividadeData.PENDENTE)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(PageDTO.class);

        TodosDTO atividadeBuscada = atividadeStatusConcluidos.getElementos().get(0);

        Assertions.assertEquals(atividadeBuscada.getIdAtividade(), cadastroAtividade.getIdAtividade());
        Assertions.assertEquals(atividadeBuscada.getNomeInstrutor(), cadastroAtividade.getNomeInstrutor());
        Assertions.assertEquals(atividadeBuscada.getTitulo(), cadastroAtividade.getTitulo());
        Assertions.assertEquals(atividadeBuscada.getPesoAtividade(), cadastroAtividade.getPesoAtividade());
    }

    @Test
    @Story("Deve listar com sucesso")
    public void testeDeveRetornarListaDeAtividadeNaTrilhaStatusConcluida() {

        Modulo novoModulo = ModuloDataFactory.novoModulo();
        ModuloDTO moduloCadastrato = moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .extract().as(ModuloDTO.class);

        Trilha novatrilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(novatrilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        Atividade novaAtividade = AtividadeDataFactory.novaAtividade(
                moduloCadastrato.getIdModulo(), trilhaCadastrada);

        AtividadeDTO cadastroAtividade = atividadeClient.cadastrar(moduloCadastrato.getIdModulo(),
                        trilhaCadastrada.getIdTrilha(), Utils.converterAtividadeToJson(novaAtividade))
                .then()
                .extract().as(AtividadeDTO.class);

        AtividadeDTO atividadeBuscada = atividadeClient.entregarAtividade(cadastroAtividade.getIdAtividade())
                .then()
                .extract().as(AtividadeDTO.class);

        PageDTO atividadeStatusConcluidos = pageClient
                .listarAtividadeStatus("0", "1", trilhaCadastrada.getIdTrilha(), AtividadeData.CONCLUIDA)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(PageDTO.class);

        TodosDTO primeiraAtividadeLista = atividadeStatusConcluidos.getElementos().get(0);

        Assertions.assertEquals(primeiraAtividadeLista.getIdAtividade(), atividadeBuscada.getIdAtividade());
        Assertions.assertEquals(primeiraAtividadeLista.getNomeInstrutor(), atividadeBuscada.getNomeInstrutor());
        Assertions.assertEquals(primeiraAtividadeLista.getTitulo(), atividadeBuscada.getTitulo());
        Assertions.assertEquals(primeiraAtividadeLista.getPesoAtividade(), atividadeBuscada.getPesoAtividade());
    }

    @Test
    @Story("Deve listar com sucesso")
    public void testeDeveListarTodasAsAtividadesPorPagina() {

        PageDTO atividadePorPagina = pageClient.listarPaginadoAtividades("0", "1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(PageDTO.class);

        Assert.assertNotNull(atividadePorPagina);
    }

    @Test
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetornarMensagemDeErroAoListarAtividadesPorPaginaNegativa() {

        ResponseDTO responseServer = pageClient.listarPaginadoAtividades("-1", "1")
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .extract().as(ResponseDTO.class);

        Assert.assertEquals("Internal Server Error", responseServer.getError());
    }

    @Test
    @Story("Deve listar com sucesso")
    public void testeDeveBuscarAtividadePorId() {

        Modulo novoModulo = ModuloDataFactory.novoModulo();
        ModuloDTO moduloCadastrato = moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .extract().as(ModuloDTO.class);

        Trilha novatrilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(novatrilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        Atividade novaAtividade = AtividadeDataFactory.novaAtividade(
                moduloCadastrato.getIdModulo(), trilhaCadastrada);

        AtividadeDTO cadastroAtividade = atividadeClient.cadastrar(moduloCadastrato.getIdModulo(),
                        trilhaCadastrada.getIdTrilha(), Utils.converterAtividadeToJson(novaAtividade))
                .then()
                .extract().as(AtividadeDTO.class);

        AtividadeDTO atividadeBuscada = atividadeClient.buscarAtividadePorId(cadastroAtividade.getIdAtividade())
                .then()
                .extract().as(AtividadeDTO.class);

        Assertions.assertEquals(cadastroAtividade.getIdAtividade(), atividadeBuscada.getIdAtividade());
        Assertions.assertEquals(cadastroAtividade.getNomeInstrutor(), atividadeBuscada.getNomeInstrutor());
        Assertions.assertEquals(cadastroAtividade.getTitulo(), atividadeBuscada.getTitulo());
        Assertions.assertEquals(cadastroAtividade.getPesoAtividade(), atividadeBuscada.getPesoAtividade());
        Assertions.assertTrue(cadastroAtividade.getDataCriacao().contains(atividadeBuscada.getDataCriacao().substring(0, 11)));
        Assertions.assertTrue(cadastroAtividade.getDataEntrega().contains(atividadeBuscada.getDataEntrega().substring(0, 11)));
    }

    @Test
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetornarErroAoBuscarAtividadePorIdNegativo() {

        ResponseDTO atividadeBuscada = atividadeClient.buscarAtividadePorId(Utils.faker.number().negative())
                .then()
                .extract().as(ResponseDTO.class);

        Assertions.assertEquals(atividadeBuscada.getStatus(), HttpStatus.SC_BAD_REQUEST);
        Assertions.assertEquals(atividadeBuscada.getMessage(), "Atividade não encontrada.");
    }

    @Test
    @Story("Deve listar com sucesso")
    public void testeDeveListarTrilhaPorTrilhaEModulo() {

        Modulo novoModulo = ModuloDataFactory.novoModulo();
        ModuloDTO moduloCadastrato = moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .extract().as(ModuloDTO.class);

        Trilha novatrilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(novatrilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        Atividade novaAtividade = AtividadeDataFactory.novaAtividade(
                moduloCadastrato.getIdModulo(), trilhaCadastrada);

        atividadeClient.cadastrar(moduloCadastrato.getIdModulo(),
                        trilhaCadastrada.getIdTrilha(), Utils.converterAtividadeToJson(novaAtividade))
                .then()
                .extract().as(AtividadeDTO.class);

        PageDTO listaAtividadeBuscada = pageClient.listarAtividadePorTrilhaModulo
                ("0", "1", trilhaCadastrada.getIdTrilha(), moduloCadastrato.getIdModulo(), AtividadeData.PENDENTE)
                .then()
                .extract().as(PageDTO.class);

        List<TodosDTO> primeiraAtiviadeNaLista = listaAtividadeBuscada.getElementos();

        Assertions.assertNotNull(primeiraAtiviadeNaLista);
    }

    @Test
    @Story("Deve listar com sucesso")
    public void testeDeveListarAtividadeNoMuralInstrutor() {

        Modulo novoModulo = ModuloDataFactory.novoModulo();
        ModuloDTO moduloCadastrato = moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .extract().as(ModuloDTO.class);

        Trilha novatrilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(novatrilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        Atividade novaAtividade = AtividadeDataFactory.novaAtividade(
                moduloCadastrato.getIdModulo(), trilhaCadastrada);

        atividadeClient.cadastrar(moduloCadastrato.getIdModulo(),
                        trilhaCadastrada.getIdTrilha(), Utils.converterAtividadeToJson(novaAtividade))
                .then()
                .extract().as(AtividadeDTO.class);

        PageDTO listaAtividadeBuscada = pageClient.listarMuralInstrutor
                        ("0", "1", trilhaCadastrada.getIdTrilha())
                .then()
                .extract().as(PageDTO.class);

        List<TodosDTO> primeiraAtiviadeNaLista = listaAtividadeBuscada.getElementos();

        Assertions.assertNotNull(primeiraAtiviadeNaLista);
    }

    @Test
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetornarMensagemDeErroAoListarMuralInstrutorComIdNegativo() {


        ResponseDTO responseServer = pageClient.listarMuralInstrutor
                        ("0", "1", Utils.faker.number().negative())
                .then()
                .extract().as(ResponseDTO.class);

        Assertions.assertEquals(responseServer.getStatus(), HttpStatus.SC_BAD_REQUEST);
        Assertions.assertEquals(responseServer.getMessage(), "Sem atividades no mural do instrutor");
    }

    @Test
    @Story("Deve listar com sucesso")
    public void testeDeveListarAtividadePendenteNoMuralAluno() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();

        UsuarioDTO usuarioCadastrado = usuarioClient.cadastrar(UsuarioData.INSTRUTOR,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(UsuarioDTO.class)
                ;

        Modulo novoModulo = ModuloDataFactory.novoModulo();
        ModuloDTO moduloCadastrato = moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .extract().as(ModuloDTO.class);

        Trilha novatrilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(novatrilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        Atividade novaAtividade = AtividadeDataFactory.novaAtividade(
                moduloCadastrato.getIdModulo(), trilhaCadastrada);

        atividadeClient.cadastrar(moduloCadastrato.getIdModulo(),
                        trilhaCadastrada.getIdTrilha(), Utils.converterAtividadeToJson(novaAtividade))
                .then()
                .log().all()
                .extract().as(AtividadeDTO.class);

        PageDTO listaAtividadeBuscada = pageClient.listarMuralAluno
                        ("0", "1", AtividadeData.PENDENTE, usuarioCadastrado.getIdUsuario())
                .then()
                .extract().as(PageDTO.class);

        List<TodosDTO> primeiraAtiviadeNaLista = listaAtividadeBuscada.getElementos();

        Assertions.assertNotNull(primeiraAtiviadeNaLista);
    }

    @Test
    @Story("Deve listar com sucesso")
    public void testeDeveListarAtividadeConcluidoNoMuralAluno() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();

        UsuarioDTO usuarioCadastrado = usuarioClient.cadastrar(UsuarioData.ALUNO,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(UsuarioDTO.class)
                ;

        Modulo novoModulo = ModuloDataFactory.novoModulo();
        ModuloDTO moduloCadastrato = moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .extract().as(ModuloDTO.class);

        Trilha novatrilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(novatrilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        Atividade novaAtividade = AtividadeDataFactory.novaAtividade(
                moduloCadastrato.getIdModulo(), trilhaCadastrada);

        AtividadeDTO cadastroAtividade = atividadeClient.cadastrar(moduloCadastrato.getIdModulo(),
                        trilhaCadastrada.getIdTrilha(), Utils.converterAtividadeToJson(novaAtividade))
                .then()
                .extract().as(AtividadeDTO.class);

        PageDTO listaAtividadeBuscada = pageClient.listarMuralAluno
                        ("0", "1", AtividadeData.PENDENTE, usuarioCadastrado.getIdUsuario())
                .then()
                .extract().as(PageDTO.class);

        atividadeClient.entregarAtividade(cadastroAtividade.getIdAtividade())
                .then()
                .extract().as(AtividadeDTO.class);

        List<TodosDTO> primeiraAtiviadeNaLista = listaAtividadeBuscada.getElementos();

        Assertions.assertNotNull(primeiraAtiviadeNaLista);
    }

    @Test
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetornarErroAoListarMuralInstrutor() {


        ResponseDTO responseServer = pageClient.listarMuralInstrutor
                        ("0", "1", Utils.faker.number().negative())
                .then()
                .extract().as(ResponseDTO.class);

        Assertions.assertEquals(responseServer.getStatus(), HttpStatus.SC_BAD_REQUEST);
        Assertions.assertEquals(responseServer.getMessage(), "Sem atividades no mural do instrutor");
    }

    @Test
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetornarMensagemDeErroAoListarMuralAlunoComIdNegativo() {


        PageDTO responseServer = pageClient.listarMuralAluno
                        ("0", "1", AtividadeData.PENDENTE, Utils.faker.number().negative())
                .then()
                .extract().as(PageDTO.class);

        Assertions.assertTrue(responseServer.getElementos().isEmpty());
    }
}
