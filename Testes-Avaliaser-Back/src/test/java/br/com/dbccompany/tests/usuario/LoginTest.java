package br.com.dbccompany.tests.usuario;

import br.com.dbccompany.client.LoginClient;
import br.com.dbccompany.client.UsuarioClient;
import br.com.dbccompany.data.changeless.UsuarioData;
import br.com.dbccompany.data.factory.LoginDataFactory;
import br.com.dbccompany.data.factory.UsuarioDataFactory;
import br.com.dbccompany.model.Login;
import br.com.dbccompany.model.Usuario;
import br.com.dbccompany.tests.base.BaseTest;
import br.com.dbccompany.utils.Utils;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.apache.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Login Tests")
@Feature("Usuario")
@DisplayName("Login")
public class LoginTest extends BaseTest {

    LoginClient loginClient = new LoginClient();

    UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    @Story("Deve realizar login com sucesso")
    public void testeDeveRealizarLoginComSucesso() {

        Login loginValido = LoginDataFactory.loginValido();
        loginClient.logar(Utils.convertLoginToJson(loginValido))
            .then()
                .statusCode(HttpStatus.SC_OK)
        ;
    }

    @Test
    @Story("Deve retornar usuario logado")
    public void testeDeveRetornarUsuarioLogado() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
        usuarioClient.cadastrar(UsuarioData.ALUNO,
                        Utils.convertUsusarioToJson(usuarioNovo));


        loginClient.usuarioLogado(usuarioNovo.getEmail(), usuarioNovo.getSenha())
            .then()
                .statusCode(HttpStatus.SC_OK)
            ;
    }

    @Test
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetornarErroPadraoAoTentarLogarComSenhaInvalida() {

        Login loginSenhaInvalida = LoginDataFactory.loginSenhaInvalida();
        loginClient.logar(Utils.convertLoginToJson(loginSenhaInvalida))
            .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
        ;
    }

    @Test
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetornarErroPadraoAoTentarLogarComEmailInvalido() {

        Login loginEmailInvalida = LoginDataFactory.loginUsuarioInvalido();
        loginClient.logar(Utils.convertLoginToJson(loginEmailInvalida))
            .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
        ;
    }

    @Test
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetornarErroPadraoAoTentarLogarComEmailSenhaVazios() {

        Login loginEmailSenhaVazios = LoginDataFactory.loginEmailSenhaVazios();
        loginClient.logar(Utils.convertLoginToJson(loginEmailSenhaVazios))
            .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
        ;
    }
}
