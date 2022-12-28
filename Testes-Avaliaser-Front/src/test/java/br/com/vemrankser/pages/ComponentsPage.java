package br.com.vemrankser.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ComponentsPage extends BasePage{

    public static final By btnDashboard =
            By.cssSelector("main > div > div > a:nth-child(1)");
    public static final By btnUsuarios =
            By.cssSelector("main > div > div > a:nth-child(2)");
    public static final By btnAlunos =
            By.cssSelector("main > div > div > a:nth-child(3)");
    public static final By btnAtividade =
            By.cssSelector("main > div > div > a:nth-child(4)");
    public static final By btnPerfil =
            By.cssSelector("main > div > div > a:nth-child(5)");
    public static final By btnConfiguracoes =
            By.cssSelector("main > div > div > a:nth-child(6)");

    public static final By btnSairDaConta =
            By.xpath("//*[@id=\"root\"]/main/div[2]/div[6]");
    private static final By btnSetaDiretaListaUsuarios =
            By.xpath("//*[@id=\"root\"]/main/div[3]/section/div[2]/div/nav/ul/li[9]/button");
    private static final By btnSetaEsquerdaListaUsuarios =
            By.xpath("//*[@id=\"root\"]/main/div[3]/section/div[2]/div/nav/ul/li[1]/button");


    @Step("Clicar botão dashboard.")
    public void clicarBtnDashboard() {

        click(btnDashboard);
    }

    @Step("Clicar botão usuarios.")
    public void clicarBtnUsuario() {

        click(btnUsuarios);
    }

    @Step("Clicar botão alunos.")
    public void clicarBtnAlunos() {

        click(btnAlunos);
    }

    @Step("Clicar botão atividade.")
    public void clicarBtnAtividade() {

        click(btnAtividade);
    }

    @Step("Clicar botão perfil.")
    public void clicarBtnPerfil() {

        click(btnPerfil);
    }

    @Step("Clicar botão configuracoes.")
    public void clicarBtnConfiguracoes() {

        click(btnConfiguracoes);
    }

    @Step("Clicar botao seta direita lista.")
    public void clicarBtnSetaDireitaLista() {

        click(btnSetaDiretaListaUsuarios);
    }

    @Step("Clicar botao seta esquerda lista.")
    public void clicarBtnSetaEsquerdaLista() {

        click(btnSetaEsquerdaListaUsuarios);
    }

    @Step("Clicar botão sair da conta.")
    public void clicarBtnSairDaConta() {

        click(btnSairDaConta);
    }
}
