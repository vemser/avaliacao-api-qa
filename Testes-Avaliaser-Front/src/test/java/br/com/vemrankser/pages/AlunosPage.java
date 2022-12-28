package br.com.vemrankser.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class AlunosPage extends BasePage{

    public static By btnFiltroTrilha =
            By.xpath("//*[@id=\"select-atividade\"]");

    public static By selectPrimerioItemFiltroTrilha =
            By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[2]");

    public static By campoTrilhaDoPrimeiroItemDoFiltroTrilha =
            By.xpath("//*[@id=\"root\"]/main/div[3]/section/div[2]/div[1]/div/div[2]/p[2]");

    public static By btnVincularAluno =
            By.id("botao-vincula-aluno");

    public static By campoLoginVincularAluno =
            By.id("nome-vincula-aluno");

    public static By btnSelectTrilha =
            By.id("idTrilha");

    public static By primeiraOpcaoListaTrilha =
            By.xpath("//*[@id=\"menu-idTrilha\"]/div[3]/ul/li[1]");

    public static By segundaOpcaoListaTrilha =
            By.xpath("//*[@id=\"menu-idTrilha\"]/div[3]/ul/li[2]");

    public static By toastAlunoVinculado =
            By.xpath("//*[@id=\"2\"]/div[1]/div[2]");

    public static By btnvincularTrilhaComAluno =
            By.id("button-vincula-aluno");



    @Step("Pressionar botao ESC")
    public void pressionarBotaoEsc() {

        Actions pressionarEsc = new Actions(driver);
        pressionarEsc.sendKeys(Keys.ESCAPE).perform();
    }
    @Step("Clicar botão filtro por trilha.")
    public void clicarBtnFiltroTrilha() {

        click(btnFiltroTrilha);
    }

    @Step("Clicar botao adicionar.")
    public void clicarBtnAdicionarTrilhaComAluno() {

        click(btnvincularTrilhaComAluno);
    }

    @Step("Clicar primerio item da lista filtro por trilha.")
    public void clicarPrimeiroItemDoFiltroTrilha() {

        click(selectPrimerioItemFiltroTrilha);
    }

    @Step("Clicar botao adicionar.")
    public void clicarBtnVincularAluno() {

        click(btnVincularAluno);
    }

    @Step("Preencher campo login.")
    public String preencherCampoLogin(String text) {

        sendKeys(campoLoginVincularAluno, text);
        return text;
    }

    @Step("Clicar select trilha.")
    public void clicarSelectTrilha() {

        click(btnSelectTrilha);
    }

    @Step("Clicar primeira opcao vincular trilha.")
    public void clicarPrimeiraOpcaoSelectTrilhaVincular() {

        click(primeiraOpcaoListaTrilha);
    }

    @Step("Clicar segunda opcao vincular trilha.")
    public void clicarSegundaOpcaoSelectTrilhaVincular() {

        click(segundaOpcaoListaTrilha);
    }

    @Step("Buscar nome primerio item da lista filtro por trilha.")
    public String buscarNomePrimeiroItemDaListaFiltroTrilha() {

        return getInnerText(selectPrimerioItemFiltroTrilha);
    }

    @Step("Buscar nome primerio item da lista filtrada por trilha.")
    public String buscarTrilhaDoAlunoNaListagem() {

        return getText(campoTrilhaDoPrimeiroItemDoFiltroTrilha);
    }

    @Step("Buscar mensagem do toast aluno vinculado.")
    public String buscarCampoToastVincucao() {

        return getInnerText(toastAlunoVinculado);
    }
}
