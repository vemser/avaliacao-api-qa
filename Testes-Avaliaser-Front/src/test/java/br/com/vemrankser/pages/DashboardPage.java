package br.com.vemrankser.pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class DashboardPage extends BasePage {



    public static final By btnAtividades =
            By.cssSelector("main > div > div > a:nth-child(4)");

    public static final By btnPerfil =
            By.cssSelector("main > div > div > a:nth-child(5)");

    public static final By btnConfiguracoes =
            By.cssSelector("main > div > div > a:nth-child(5)");







    @Step("Clicar botão atividades.")
    public void clicarBtnAtividades() {

        click(btnAtividades);
    }

    @Step("Clicar botão perfil.")
    public void clicarBtnPerfil() {

        click(btnPerfil);
    }

    @Step("Clicar botão configurações.")
    public void clicarBtnConfiguracoes() {

        click(btnConfiguracoes);
    }




}
