package br.com.vemrankser.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class AtividadesPage extends BasePage{


    public static By btnNovaAtividade =
            By.id("botao-nova-atividade");
    public static By btnGerencia =
            By.id("botao-notas-atividade");
    public static By campoTituloAtividade =
            By.id("titulo-cadastra-atividade");
    public static By campoDescricaoAtiidade =
            By.id("descricao-cadastra-atividade");
    public static By btnTrilhaAtividade =
            By.id("checkbox-trilha");
    public static By btnModuloAtividade =
            By.id("cadastra-atividade-modulo");
    public static By btnPesoAtividade =
            By.id("cadastra-atividade-peso");
    public static By calendarioAtividade =
            By.id("textfield-data");
    public static By btnAdicionar =
            By.id("botao-adiciona-atividade");
    public static By selectPrimeiroItemTrilha =
            By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[1]/div/span");
    public static By selectPrimeiroItemModulo =
            By.xpath("//*[@id=\"menu-idModulo\"]/div[3]/ul/li[1]");
    public static By selectPrimeiroItemPesoAtividade =
            By.xpath("//*[@id=\"menu-pesoAtividade\"]/div[3]/ul/li[1]");

    public static By msgErroCampoModulo =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/span");
    public static By toastError =
            By.xpath("//*[@id=\"1\"]/div[1]/div[2]");

    public static By msgErroCampoData =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/span[2]");

    @Step("Clicar botão nova atividade.")
    public void clicarBtnNovaAtividade() {

        click(btnNovaAtividade);
    }

    @Step("Clicar botão gerenciar.")
    public void clicarBtnGerenciar() {

        click(btnGerencia);
    }

    @Step("Clicar botão adicionar atividade.")
    public void clicarBtnAdicionarAtividade() {

        click(btnAdicionar);
    }

    @Step("Clicar toast erro.")
    public void clicarToastErros() {

        click(toastError);
    }

    @Step("Clicar primeiro select trilha atividade.")
    public void clicarPrimeiroSelectTrilhaAtividade() {

        click(selectPrimeiroItemTrilha);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    @Step("Clicar primeiro select modulo atividade.")
    public void clicarPrimeiroSelectModuloAtividade() {

        click(selectPrimeiroItemModulo);
    }

    @Step("Clicar primeiro select peso atividade.")
    public void clicarPrimeiroSelectPesoAtividade() {

        click(selectPrimeiroItemPesoAtividade);
    }

    @Step("Preencher campo titulo.")
    public String preencherCampoTitulo(String titulo) {

        sendKeys(campoTituloAtividade, titulo);
        return titulo;
    }


    @Step("Preencher campo descrição.")
    public String preencherCampoDescricao(String descricao) {

        sendKeys(campoDescricaoAtiidade, descricao);
        return descricao;
    }

    @Step("Preencher campo calendario.")
    public void preencherCampoCalenario(String data) {

        sendKeys(calendarioAtividade, data);
    }

    @Step("Clicar select trilha.")
    public void clicarBtnTrilhaAtividade() {

        click(btnTrilhaAtividade);
    }

    @Step("Clicar select modulo.")
    public void clicarBtnModuloAtividade() {

        click(btnModuloAtividade);
    }

    @Step("Clicar select peso.")
    public void clicarBtnPesoAtividade() {

        click(btnPesoAtividade);
    }

    @Step("Buscar mensagem erro campo modulo")
    public String buscarTextoCampoModulo() {

        return  getText(msgErroCampoModulo);
    }

    @Step("Buscar mensagem erro campo data")
    public String buscarTextoCampodata() {

        return  getText(msgErroCampoData);
    }
}
