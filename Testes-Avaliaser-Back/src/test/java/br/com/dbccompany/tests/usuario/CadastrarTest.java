package br.com.dbccompany.tests.usuario;

import br.com.dbccompany.client.UsuarioClient;
import br.com.dbccompany.data.changeless.UsuarioData;
import br.com.dbccompany.data.factory.UsuarioDataFactory;
import br.com.dbccompany.dto.UsuarioDTO;
import br.com.dbccompany.model.Usuario;
import br.com.dbccompany.tests.base.BaseTest;
import br.com.dbccompany.utils.Utils;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.apache.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Cadastro Tests")
@Feature("Usuario")
@DisplayName("Cadastro Usuario")
public class CadastrarTest extends BaseTest {

    UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    @Story("Deve cadastrar usuário com sucesso")
    public void testeDeveCadastrarAlunoComSucesso() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();

        UsuarioDTO usuarioResponse = usuarioClient.cadastrar(UsuarioData.ALUNO,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(UsuarioDTO.class)
                ;

        assertAll("usuario",
                () -> assertEquals(usuarioNovo.getNome(), usuarioResponse.getNome()),
                () -> assertEquals(usuarioNovo.getEmail(), usuarioResponse.getEmail())
        );
    }

    @Test
    @Story("Deve cadastrar usuário com sucesso")
    public void testeDeveCadastrarInstrutorComSucesso() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();

        UsuarioDTO usuarioResponse = usuarioClient.cadastrar(UsuarioData.INSTRUTOR,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(UsuarioDTO.class)
        ;

        assertAll("usuario",
                () -> assertEquals(usuarioNovo.getNome(), usuarioResponse.getNome()),
                () -> assertEquals(usuarioNovo.getEmail(), usuarioResponse.getEmail())
        );
    }

    @Test
    @Story("Deve cadastrar usuário com sucesso")
    public void testeDeveCadastrarCooerdenadorComSucesso() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();

        UsuarioDTO usuarioResponse = usuarioClient.cadastrar(UsuarioData.COORDENADOR,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(UsuarioDTO.class)
        ;

        assertAll("usuario",
                () -> assertEquals(usuarioNovo.getNome(), usuarioResponse.getNome()),
                () -> assertEquals(usuarioNovo.getEmail(), usuarioResponse.getEmail())
        );
    }

    @Test
    @Story("Deve cadastrar usuário com sucesso")
    public void testeDeveCadastrarGestaoComSucesso() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();

        UsuarioDTO usuarioResponse = usuarioClient.cadastrar(UsuarioData.GESTAO,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(UsuarioDTO.class)
        ;

        assertAll("usuario",
                () -> assertEquals(usuarioNovo.getNome(), usuarioResponse.getNome()),
                () -> assertEquals(usuarioNovo.getEmail(), usuarioResponse.getEmail())
        );
    }

    @Test
    @Story("Deve retornar erro ao tentar cadastrar")
    public void testeDeveRetornarErroAoCadastrarNovoUsuarioComEmailExistenteNoBancoDeDados() {


        Usuario usuarioEmailExistente =  UsuarioDataFactory.usuarioComEmailRepetido();

        usuarioClient.cadastrar(UsuarioData.GESTAO,
                        Utils.convertUsusarioToJson(usuarioEmailExistente))
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                ;
    }
}
