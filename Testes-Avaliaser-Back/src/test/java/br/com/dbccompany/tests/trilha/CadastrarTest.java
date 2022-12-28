package br.com.dbccompany.tests.trilha;

import br.com.dbccompany.client.TrilhaClient;
import br.com.dbccompany.client.UsuarioClient;
import br.com.dbccompany.data.changeless.UsuarioData;
import br.com.dbccompany.data.factory.TrilhaDataFactory;
import br.com.dbccompany.data.factory.UsuarioDataFactory;
import br.com.dbccompany.dto.ResponseDTO;
import br.com.dbccompany.dto.TrilhaDTO;
import br.com.dbccompany.dto.TodosDTO;
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


@Epic("Cadastro Tests")
@Feature("Trilha")
@DisplayName("Cadastro Trilha")
public class CadastrarTest extends BaseTest {

    TrilhaClient trilhaClient = new TrilhaClient();
    UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    @Story("Deve cadastrar com sucesso")
    public void testeDeveCadastrarTrilhaComSucesso() {

        Trilha trilha =  TrilhaDataFactory.novaTrilha();

        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilha))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(TrilhaDTO.class)
                ;

        Assertions.assertEquals(trilha.getNome(), trilhaCadastrada.getNome());
        Assertions.assertEquals(trilha.getEdicao(), trilhaCadastrada.getEdicao());
        Assertions.assertEquals(trilha.getAnoEdicao(), trilhaCadastrada.getAnoEdicao());
    }

    @Test
    @Story("Deve cadastrar com sucesso")
    public void testeDeveVincularTrilhaAoAlunoComSucesso() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();

        usuarioClient.cadastrar(UsuarioData.ALUNO,Utils.convertUsusarioToJson(usuarioNovo));
        Trilha trilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaResponse = trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;
        Trilha vincularTrilha = TrilhaDataFactory.vincularTrilha(trilhaResponse.getIdTrilha(), usuarioNovo.getLogin());
        trilhaClient.vincularTrilhaAluno(trilhaResponse.getIdTrilha(), usuarioNovo.getLogin(), Utils.convertTrilhaToJson(vincularTrilha))
                .then()
                .statusCode(HttpStatus.SC_OK)
                ;
    }

    @Test
    @Story("Deve retornar mensagem de erro ao cadastrar")
    public void testeDeveRetornarErroAoVincularTrilhaAAlunoInexistente() {

        Trilha trilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaResponse = trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        String nomeAleatorio = Utils.faker.name().fullName();
        Trilha vincularTrilha = TrilhaDataFactory.vincularTrilha(trilhaResponse.getIdTrilha(), nomeAleatorio);
        ResponseDTO responseBody = trilhaClient.vincularTrilhaAluno(
                        trilhaResponse.getIdTrilha(), nomeAleatorio, Utils.convertTrilhaToJson(vincularTrilha))
                .then()
                .extract().as(ResponseDTO.class)
        ;

        Assertions.assertEquals((int)responseBody.getStatus(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @Story("Deve retornar mensagem de erro ao cadastrar")
    public void testeDeveRetornarErroAoVincularTrilhaIdNegativoAoAluno() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
        usuarioClient.cadastrar(UsuarioData.ALUNO,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .extract().as(TodosDTO.class)
                ;

        Integer numeroNegativo = Utils.faker.number().negative();
        Trilha vincularTrilha = TrilhaDataFactory.vincularTrilha(numeroNegativo, usuarioNovo.getLogin());
        ResponseDTO responseBody = trilhaClient.vincularTrilhaAluno(numeroNegativo, usuarioNovo.getLogin(), Utils.convertTrilhaToJson(vincularTrilha))
                .then()
                .extract().as(ResponseDTO.class)
                ;

        Assertions.assertEquals((int)responseBody.getStatus(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Story("Deve retornar mensagem de erro ao cadastrar")
    public void testeDeveRetornarErroAoCadastrarTrilhaSemAnoEdicao() {

        Trilha trilhaNova = TrilhaDataFactory.novaTrilha();
        Trilha trilhaSemAnoEdicao = TrilhaDataFactory.retirarEdicaoDaTrilha(trilhaNova);

        ResponseDTO responseServer = trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilhaSemAnoEdicao))
                .then()
                .extract().as(ResponseDTO.class)
        ;

        Assertions.assertEquals(responseServer.getStatus(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @Story("Deve cadastrar com sucesso")
    public void testeDeveVincularTrilhaAoInstrutorComSucesso() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();

        usuarioClient.cadastrar(UsuarioData.INSTRUTOR,Utils.convertUsusarioToJson(usuarioNovo));

        Trilha trilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaResponse = trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        Trilha vincularTrilha = TrilhaDataFactory.vincularTrilha(trilhaResponse.getIdTrilha(), usuarioNovo.getLogin());
        trilhaClient.vincularTrilhaInstrutor(trilhaResponse.getIdTrilha(),
                        usuarioNovo.getLogin(), Utils.convertTrilhaToJson(vincularTrilha))
                .then()
                .statusCode(HttpStatus.SC_OK)
                ;
    }

    @Test
    @Story("Deve retornar mensagem de erro ao cadastrar")
    public void testeDeveRetornarErroAoVincularTrilhaIdNegativoAoInstrutor() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
        usuarioClient.cadastrar(UsuarioData.INSTRUTOR,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .extract().as(TodosDTO.class)
        ;

        Integer numeroNegativo = Utils.faker.number().negative();
        Trilha vincularTrilha = TrilhaDataFactory.vincularTrilha(numeroNegativo, usuarioNovo.getLogin());
        ResponseDTO responseBody = trilhaClient.vincularTrilhaAluno(numeroNegativo, usuarioNovo.getLogin(), Utils.convertTrilhaToJson(vincularTrilha))
                .then()
                .extract().as(ResponseDTO.class)
                ;

        Assertions.assertEquals((int)responseBody.getStatus(), HttpStatus.SC_BAD_REQUEST);
    }
}
