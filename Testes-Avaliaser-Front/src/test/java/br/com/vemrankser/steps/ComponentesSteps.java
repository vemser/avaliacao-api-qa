package br.com.vemrankser.steps;

import br.com.vemrankser.browserHandler.Elements;
import br.com.vemrankser.pages.ComponentsPage;

import br.com.vemrankser.utils.Utils;
import io.qameta.allure.Feature;

import io.qameta.allure.Story;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ComponentesSteps extends BaseSteps{

    ComponentsPage componentsPage = new ComponentsPage();

    @Test
    @Feature("Componente Menu lateral")
    @Story("Deve realizar ação com sucesso.")
    public void testeDeveRealizarLogoutComSucesso() throws InterruptedException {

        Utils.logar();
        Thread.sleep(3000);
        componentsPage.clicarBtnSairDaConta();
        Assert.assertEquals(Elements.getUrl(), "http://vemser-dbc.dbccompany.com.br:39000/vemser/vemrankser-front/");
    }

    @Test
    @Feature("Componente Menu lateral")
    @Story("Deve realizar ação com sucesso.")
    public void testeDeveSerRedirecionadoParaPaginaDashboard() {

        Utils.logar();
        componentsPage.clicarBtnDashboard();
        Assert.assertEquals(Elements.getUrl(), "http://vemser-dbc.dbccompany.com.br:39000/vemser/vemrankser-front/dashboard");
    }

    @Test
    @Feature("Componente Menu lateral")
    @Story("Deve realizar ação com sucesso.")
    public void testeDeveSerRedirecionadoParaPaginaAlunos() {

        Utils.logar();
        componentsPage.clicarBtnAlunos();
        Assert.assertEquals(Elements.getUrl(), "http://vemser-dbc.dbccompany.com.br:39000/vemser/vemrankser-front/alunos");
    }

    @Test
    @Feature("Componente Menu lateral")
    @Story("Deve realizar ação com sucesso.")
    public void testeDeveSerRedirecionadoParaPaginaAtividade() {

        Utils.logar();
        componentsPage.clicarBtnAtividade();
        Assert.assertEquals(Elements.getUrl(), "http://vemser-dbc.dbccompany.com.br:39000/vemser/vemrankser-front/atividades");
    }

    @Test
    @Feature("Componente Menu lateral")
    @Story("Deve realizar ação com sucesso.")
    public void testeDeveSerRedirecionadoParaPaginaPerfil() {

        Utils.logar();
        componentsPage.clicarBtnPerfil();
        Assert.assertEquals(Elements.getUrl(), "http://vemser-dbc.dbccompany.com.br:39000/vemser/vemrankser-front/perfil");
    }

    @Test
    @Feature("Componente Menu lateral")
    @Story("Deve realizar ação com sucesso.")
    public void testeDeveSerRedirecionadoParaPaginaConfiguracoes() {

        Utils.logar();
        componentsPage.clicarBtnConfiguracoes();
        Assert.assertEquals(Elements.getUrl(), "http://vemser-dbc.dbccompany.com.br:39000/vemser/vemrankser-front/configuracoes");
    }

    @Test
    @Feature("Componente de listagem")
    @Story("Deve fazer a acao esperada.")
    public void testeDeveVerificarAcaoDasSetasDireitaEsquerdaLista() {

        Utils.logar();
        componentsPage.clicarBtnUsuario();

        componentsPage.clicarBtnSetaDireitaLista();
        Assert.assertEquals(Elements.getUrl(), "http://vemser-dbc.dbccompany.com.br:39000/vemser/vemrankser-front/usuarios?pagina=2");

        componentsPage.clicarBtnSetaEsquerdaLista();
        Assert.assertEquals(Elements.getUrl(), "http://vemser-dbc.dbccompany.com.br:39000/vemser/vemrankser-front/usuarios?pagina=1");
    }
}