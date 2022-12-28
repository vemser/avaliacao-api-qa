package br.com.vemrankser.steps;

import br.com.vemrankser.pages.AlunosPage;
import br.com.vemrankser.pages.ComponentsPage;
import br.com.vemrankser.pages.UsuarioPage;
import br.com.vemrankser.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AlunosSteps extends BaseSteps{

    AlunosPage alunosPage = new AlunosPage();

    ComponentsPage componentsPage = new ComponentsPage();

    UsuarioPage usuarioPage = new UsuarioPage();

    @Test
    @Feature("Alunos")
    @Story("Deve fazer a acao esperada.")
    public void testeDeveFiltarAlunosPorTrilhaComSucesso() throws InterruptedException {

        Utils.logar();
        componentsPage.clicarBtnUsuario();
        Utils.cadastrarUsuarioCoordenador();

        usuarioPage.fecharToastContaCadastrada();
        componentsPage.clicarBtnAlunos();

        alunosPage.buscarTrilhaDoAlunoNaListagem();
        alunosPage.clicarBtnFiltroTrilha();
        String itemSelecionado = alunosPage.buscarNomePrimeiroItemDaListaFiltroTrilha();
        alunosPage.clicarPrimeiroItemDoFiltroTrilha();
        Thread.sleep(1000);
        String trilhaDoAluno = alunosPage.buscarTrilhaDoAlunoNaListagem();


        Assert.assertTrue(trilhaDoAluno.contains(itemSelecionado));
    }

    @Test
    @Feature("Alunos")
    @Story("Deve fazer a acao esperada.")
    public void testeDeveVincularAlunoNaTrilha() {

        Utils.logar();
        componentsPage.clicarBtnUsuario();
        HashMap<String, String> contaCadastrada = Utils.cadastrarUsuarioAluno();

        usuarioPage.fecharToastContaCadastrada();
        componentsPage.clicarBtnAlunos();

        alunosPage.clicarBtnVincularAluno();
        alunosPage.preencherCampoLogin(contaCadastrada.get("Login"));
        alunosPage.clicarSelectTrilha();
        alunosPage.clicarPrimeiraOpcaoSelectTrilhaVincular();
        alunosPage.clicarSegundaOpcaoSelectTrilhaVincular();
        alunosPage.pressionarBotaoEsc();
        alunosPage.clicarBtnAdicionarTrilhaComAluno();
        String mensagemRetorno = alunosPage.buscarCampoToastVincucao();

        Assert.assertEquals(mensagemRetorno, "Aluno vinculado com sucesso!");
    }

    @Test
    @Feature("Alunos")
    @Story("Deve retornar mensagem de erro.")
    public void testeDeveRetornarMensagemDeErroAoVincularCoordenadorAUmaTrilha() {

        Utils.logar();
        componentsPage.clicarBtnUsuario();
        HashMap<String, String> contaCadastrada = Utils.cadastrarUsuarioCoordenador();

        usuarioPage.fecharToastContaCadastrada();
        componentsPage.clicarBtnAlunos();

        alunosPage.clicarBtnVincularAluno();
        alunosPage.preencherCampoLogin(contaCadastrada.get("Login"));
        alunosPage.clicarSelectTrilha();
        alunosPage.clicarPrimeiraOpcaoSelectTrilhaVincular();
        alunosPage.clicarSegundaOpcaoSelectTrilhaVincular();
        alunosPage.pressionarBotaoEsc();
        alunosPage.clicarBtnAdicionarTrilhaComAluno();
        String mensagemRetorno = alunosPage.buscarCampoToastVincucao();

        Assert.assertEquals(mensagemRetorno, "Houve algum erro, por favor recarregue a página");
    }

    @Test
    @Feature("Alunos")
    @Story("Deve retornar mensagem de erro.")
    public void testeDeveRetornarMensagemDeErroAoVincularAlunoInexistenteAUmaTrilha() {

        Utils.logar();

        componentsPage.clicarBtnAlunos();

        alunosPage.clicarBtnVincularAluno();
        alunosPage.preencherCampoLogin(Utils.faker.name().firstName());
        alunosPage.clicarSelectTrilha();
        alunosPage.clicarPrimeiraOpcaoSelectTrilhaVincular();
        alunosPage.clicarSegundaOpcaoSelectTrilhaVincular();
        alunosPage.pressionarBotaoEsc();
        alunosPage.clicarBtnAdicionarTrilhaComAluno();
        alunosPage.clicarBtnAdicionarTrilhaComAluno();
        String mensagemRetorno = alunosPage.buscarCampoToastVincucao();

        Assert.assertEquals(mensagemRetorno, "Houve algum erro, por favor recarregue a página");
    }
}