package br.com.vemrankser.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage{

    private static final By inputEmail =
            By.id("email");
    private static final By inputPasswrod =
            By.id("senha");
    private static final By btnLogin =
            By.id("botao-logar");
    private static final By campoMensagemErroToast =
            By.cssSelector("#\\31  > div.Toastify__toast-body > div:nth-child(2)");
    private static final By campoMensagemErroInputEmail =
            By.xpath("//*[@id=\"root\"]/div[2]/div[2]/form/span");
    private static final By campoMensagemErroInputSenha =
            By.xpath("//*[@id=\"root\"]/div[2]/div[2]/form/span");

    @Step("Preencher campo endereço.")
    public String preencherCampoEmail(String email) {

        String emailRetorno = email;
        sendKeys(inputEmail, email);

        return emailRetorno;
    }

    @Step("Preencher campo senha.")
    public String preencherCampoSenha(String senha) {

        String senhaRetorno = senha;
        sendKeys(inputPasswrod, senha);

        return senhaRetorno;
    }

    @Step("Clicar botao entrar.")
    public void clicarBtnEntrar() {

        click(btnLogin);
    }

    @Step("Buscar mensagem de erro no toast")
    public String buscarMensagemErroToastLogin() {

        return getInnerText(campoMensagemErroToast);
    }

    @Step("Buscar mensagem de erro no input email")
    public String buscarMensagemErroInputEmail() {

        return getText(campoMensagemErroInputEmail);
    }

    @Step("Buscar mensagem de erro no input senha")
    public String buscarMensagemErroInputSenha() {

        return getText(campoMensagemErroInputSenha);
    }


}
