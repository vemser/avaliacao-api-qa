package br.com.vemrankser.steps;


import br.com.vemrankser.pages.AtividadesPage;
import br.com.vemrankser.pages.ComponentsPage;

import br.com.vemrankser.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;



public class AtividadeSteps extends BaseSteps{

    AtividadesPage atividadesPage = new AtividadesPage();
    ComponentsPage componentsPage = new ComponentsPage();
    @Test
    @Feature("Atividade")
    @Story("Deve retornar mensagem erro.")
    public void testeDeveRetornarMensagemDeErroAoTentarCriarAtiviadeSemModulo() throws InterruptedException {

        Utils.logar();

        componentsPage.clicarBtnAtividade();
        atividadesPage.clicarBtnNovaAtividade();
        atividadesPage.clicarToastErros();
        atividadesPage.clicarBtnTrilhaAtividade();
        atividadesPage.clicarPrimeiroSelectTrilhaAtividade();
        atividadesPage.preencherCampoTitulo(Utils.faker.company().name());
        atividadesPage.preencherCampoDescricao(Utils.faker.lorem().characters(100));
        atividadesPage.preencherCampoCalenario("12122012");

        atividadesPage.clicarBtnPesoAtividade();
        atividadesPage.clicarPrimeiroSelectPesoAtividade();
        atividadesPage.clicarBtnAdicionarAtividade();

        Assert.assertTrue(atividadesPage.buscarTextoCampoModulo().contains("idModulo"));
    }

    @Test
    @Feature("Atividade")
    @Story("Deve retornar mensagem erro.")
    public void testeDeveRetornarMensagemDeErroAoTentarCriarAtiviadeComDataInvalida() throws InterruptedException {

        Utils.logar();

        componentsPage.clicarBtnAtividade();
        atividadesPage.clicarBtnNovaAtividade();
        atividadesPage.clicarToastErros();
        atividadesPage.clicarBtnTrilhaAtividade();
        atividadesPage.clicarPrimeiroSelectTrilhaAtividade();
        atividadesPage.preencherCampoTitulo(Utils.faker.company().name());
        atividadesPage.preencherCampoDescricao(Utils.faker.lorem().characters(100));
        atividadesPage.preencherCampoCalenario("12120000");

        atividadesPage.clicarBtnPesoAtividade();
        atividadesPage.clicarPrimeiroSelectPesoAtividade();
        atividadesPage.clicarBtnAdicionarAtividade();

        Assert.assertEquals(atividadesPage.buscarTextoCampodata(), "Date inválida, por favor digite outra data");
    }
}