package br.com.vemrankser.steps;

import br.com.vemrankser.browserHandler.Elements;
import br.com.vemrankser.pages.LoginPage;
import br.com.vemrankser.utils.Utils;
import br.com.vemrankser.utils.Manipulation;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


@Epic("Login Tests")
public class LoginSteps extends BaseSteps{

    LoginPage loginPage = new LoginPage();


    @Test
    @Feature("Login")
    @Story("Deve realizar ação com sucesso.")
    public void testeDeveLogarComSucesso() throws InterruptedException {

        Utils.logar();
        Thread.sleep(3000);
        Assert.assertNotEquals(Elements.getUrl(), "https://vemrankser.vercel.app/");
    }

    @Test
    @Feature("Login")
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetonarMensagemErroPadraoAoPreencherCampoEmailNaoCadastrado() {

        loginPage.preencherCampoEmail(Utils.faker.internet().emailAddress());
        loginPage.preencherCampoSenha(Manipulation.getProp().getProperty("prop.password"));
        loginPage.clicarBtnEntrar();

        Assert.assertEquals(loginPage.buscarMensagemErroToastLogin(), "Houve algum erro, por favor tente novamente!");

    }

    @Test
    @Feature("Login")
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetonarMensagemErroPadraoAoPreencherCampoSenhaIncorreta() {

        loginPage.preencherCampoEmail(Manipulation.getProp().getProperty("prop.email"));
        loginPage.preencherCampoSenha(Utils.faker.internet().password());
        loginPage.clicarBtnEntrar();

        Assert.assertEquals(loginPage.buscarMensagemErroToastLogin(), "Houve algum erro, por favor tente novamente!");

    }

    @Test
    @Feature("Login")
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetonarMensagemErroPadraoAoPreencherCampoSenhaVazio() {

        loginPage.preencherCampoEmail(Manipulation.getProp().getProperty("prop.email"));
        loginPage.preencherCampoSenha(StringUtils.EMPTY);
        loginPage.clicarBtnEntrar();

        Assert.assertEquals(loginPage.buscarMensagemErroInputSenha(), "Por favor, digite sua senha");

    }

    @Test
    @Feature("Login")
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetonarMensagemErroPadraoAoPreencherCampoEmailVazio() {

        loginPage.preencherCampoEmail(StringUtils.EMPTY);
        loginPage.preencherCampoSenha(Manipulation.getProp().getProperty("prop.password"));
        loginPage.clicarBtnEntrar();

        Assert.assertEquals(loginPage.buscarMensagemErroInputEmail(), "Por favor, digite seu email");

    }

    @Test
    @Feature("Login")
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetonarMensagemErroPadraoAoPreencherCampoEmailInvalido() {

        loginPage.preencherCampoEmail(Utils.faker.name().firstName());
        loginPage.preencherCampoSenha(Manipulation.getProp().getProperty("prop.password"));
        loginPage.clicarBtnEntrar();

        Assert.assertEquals(loginPage.buscarMensagemErroInputEmail(), "Por favor, digite um email válido");

    }
}