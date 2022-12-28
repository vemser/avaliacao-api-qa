package br.com.dbccompany.tests.trilha;

import br.com.dbccompany.client.PageClient;
import br.com.dbccompany.client.TrilhaClient;
import br.com.dbccompany.client.UsuarioClient;
import br.com.dbccompany.data.changeless.UsuarioData;
import br.com.dbccompany.data.factory.TrilhaDataFactory;
import br.com.dbccompany.data.factory.UsuarioDataFactory;
import br.com.dbccompany.dto.PageDTO;
import br.com.dbccompany.dto.ResponseDTO;
import br.com.dbccompany.dto.TodosDTO;
import br.com.dbccompany.dto.TrilhaDTO;
import br.com.dbccompany.model.Trilha;
import br.com.dbccompany.model.Usuario;
import br.com.dbccompany.tests.base.BaseTest;
import br.com.dbccompany.utils.Utils;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.apache.http.HttpStatus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Page Tests")
@Feature("Trilha")
@DisplayName("Listar Trilha")
public class PageTest extends BaseTest {

    PageClient pageClient = new PageClient();

    TrilhaClient trilhaClient = new TrilhaClient();

    UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    @Story("Deve listar os elementos com sucesso")
    public void testeDeveListarPorTrilhaComAlunos() {

        Trilha trilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        PageDTO lista = pageClient.listarTrilhaComUsuarios("0","1", trilhaCadastrada.getIdTrilha())
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(PageDTO.class)
                ;

        List<TodosDTO> trilhaBuscada = lista.getElementos();

        assertAll("lista",
                () -> assertEquals("0", lista.getPagina()),
                () -> assertEquals("1", lista.getTamanho()),
                () -> assertEquals(trilhaBuscada.get(0).getIdTrilha(), trilhaCadastrada.getIdTrilha()),
                () -> assertEquals(trilhaBuscada.get(0).getEdicao(), trilhaCadastrada.getEdicao()),
                () -> assertEquals(trilhaBuscada.get(0).getNome(), trilhaCadastrada.getNome())
        );
    }

    @Test
    @Story("Deve retornar lista vazia")
    public void testeDeveRetornarListaVaziaAoBuscarPorTrilhaInexistenteComAlunos() {

        PageDTO lista = pageClient.listarTrilhaComUsuarios("0","1", 0)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(PageDTO.class)
                ;

        Assertions.assertTrue(lista.getElementos().isEmpty());
    }

    @Test
    @Story("Deve listar os elementos com sucesso")
    public void testeDeveRetornarListaDeTrilhaPorNome() {

        Trilha trilha =  TrilhaDataFactory.novaTrilha();
        trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilha));

        TrilhaDTO[] lista = pageClient.listarTrilhaPorNome(trilha.getNome())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(TrilhaDTO[].class)
                ;

        Assertions.assertTrue(lista.length > 0);
    }

    @Test
    @Story("Deve retornar lista vazia")
    public void testeDeveRetornarErroAoBuscarListaDeTrilhaPorNomeInexistente() {

        ArrayList lista = pageClient.listarTrilhaPorNome(Utils.faker.random().hex())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(ArrayList.class)
                ;

        Assertions.assertTrue(lista.isEmpty());
    }

    @Test
    @Story("Deve listar os elementos com sucesso")
    public void testeDeveRetornarListaPorRanking() {

        Trilha trilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilha))
                .then()
                .extract().as(TrilhaDTO.class);

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
        usuarioClient.cadastrar(UsuarioData.ALUNO,
                Utils.convertUsusarioToJson(usuarioNovo));

        Trilha vincularTrilha = TrilhaDataFactory.vincularTrilha(trilhaCadastrada.getIdTrilha(), usuarioNovo.getLogin());
        trilhaClient.vincularTrilhaAluno(trilhaCadastrada.getIdTrilha(), usuarioNovo.getLogin(), Utils.convertTrilhaToJson(vincularTrilha))
                .then()
                .statusCode(HttpStatus.SC_OK)
                ;

        TrilhaDTO[] lista = pageClient.listarTrilhaPorRanking(trilhaCadastrada.getIdTrilha())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(TrilhaDTO[].class)
                ;

        Assertions.assertTrue(lista.length > 0);
    }

    @Test
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetornarErroAoBuscarListaDeTrilhaPorIdNegativo() {

        ResponseDTO lista = pageClient.listarTrilhaPorRanking(Utils.faker.number().negative())
                .then()
                .extract().as(ResponseDTO.class)
                ;

        Assertions.assertEquals(lista.getStatus(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Story("Deve listar os elementos com sucesso")
    public void testeDeveRetornarListaDeTrilhaPorEdicao() {

        Trilha trilha =  TrilhaDataFactory.novaTrilha();
        trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilha));

        TrilhaDTO[] lista = pageClient.listarTrilhaPorEdicao(trilha.getEdicao())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(TrilhaDTO[].class)
                ;

        Assertions.assertTrue(lista.length > 0);
    }

    @Test
    @Story("Deve retornar lista vazia")
    public void testeDeveRetornarVazioAoBuscarListaDeTrilhaPorEdicaoNegativo() {

        ArrayList lista = pageClient.listarTrilhaPorEdicao(Utils.faker.number().negative())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(ArrayList.class)
                ;

        Assertions.assertTrue(lista.isEmpty());
    }

    @Test
    @Story("Deve listar os elementos com sucesso")
    public void testeDeveRetornarTrilhaBuscadaPorId() {

        Trilha trilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        TrilhaDTO trilhaBuscada = pageClient.buscarTrilhaPorId(trilhaCadastrada.getIdTrilha())
                .then()
                .extract().as(TrilhaDTO.class);

        Assertions.assertEquals(trilhaBuscada.getNome(), trilhaCadastrada.getNome());
        Assertions.assertEquals(trilhaBuscada.getEdicao(), trilhaCadastrada.getEdicao());
        Assertions.assertEquals(trilhaBuscada.getAnoEdicao(), trilhaCadastrada.getAnoEdicao());
    }

    @Test
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetornarErroAoBuscarTrilhaPorIdNegativo() {

        ResponseDTO responseServer = pageClient.buscarTrilhaPorId(Utils.faker.number().negative())
                .then()
                .extract().as(ResponseDTO.class);

        Assertions.assertEquals(responseServer.getStatus(), HttpStatus.SC_BAD_REQUEST);
    }
}
