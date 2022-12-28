package br.com.vemrankser.steps;

import br.com.vemrankser.browserHandler.Elements;
import br.com.vemrankser.pages.ComponentsPage;
import br.com.vemrankser.pages.UsuarioPage;
import br.com.vemrankser.utils.Utils;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class UsuarioSteps extends BaseSteps {
    UsuarioPage usuarioPage = new UsuarioPage();

    ComponentsPage componentsPage = new ComponentsPage();

    @Test
    @Feature("Adminstrador")
    @Story("Deve criar usuario com sucesso.")
    public void testeDeveCadastrarUsuarioCompletoTipoGestaoComSucesso() {

        Utils.logar();
        usuarioPage.clicarBtnCadastrarUsuario();
        usuarioPage.preencherCampoNome(Utils.faker.name().firstName());
        usuarioPage.preencherCampoLogin(Utils.faker.name().firstName()+"."+Utils.faker
                .name().lastName());
        usuarioPage.preencherCampoEmail(Utils.faker.internet().emailAddress());
        usuarioPage.preencherCampoSenha(Utils.faker.internet().password());
        usuarioPage.preencherCampoCidade(Utils.faker.address().cityName());
        usuarioPage.selecionarPerfilGestao();
        usuarioPage.preencherCampoEspecialidade("Gestão de Pessoas");
        usuarioPage.clicarBtnAdicionar();

        Assert.assertEquals(usuarioPage.buscarCampoToastContaCadastrada(), "Pessoa cadastrada com sucesso!");
    }

    @Test
    @Feature("Adminstrador")
    @Story("Deve retornar mensagem de erro.")
    public void testeDeveRetornarMensagemErroAoCadastrarNovoUsuarioComLoginExistenteNoBancoDeDados() {

        Utils.logar();

        usuarioPage.clicarBtnCadastrarUsuario();
        usuarioPage.preencherCampoNome(Utils.faker.name().firstName());
        usuarioPage.preencherCampoLogin(Utils.faker.name().firstName()+"."+Utils.faker
                .name().lastName());
        usuarioPage.preencherCampoSenha(Utils.faker.internet().password());
        usuarioPage.preencherCampoCidade(Utils.faker.address().cityName());
        usuarioPage.selecionarPerfilCoordenador();
        usuarioPage.preencherCampoEspecialidade("Diretor");
        usuarioPage.clicarBtnAdicionar();

        Assert.assertTrue( 1 == 0);
    }

    @Test
    @Feature("Adminstrador")
    @Story("Deve criar usuario com sucesso.")
    public void testeDeveCadastrarUsuarioCompletoTipoAlunoComSucesso() {

        Utils.logar();

        usuarioPage.clicarBtnCadastrarUsuario();
        usuarioPage.preencherCampoNome(Utils.faker.name().firstName());
        usuarioPage.preencherCampoLogin(Utils.faker.name().firstName()+"."+Utils.faker
                .name().lastName());
        usuarioPage.preencherCampoEmail(Utils.faker.internet().emailAddress());
        usuarioPage.preencherCampoSenha(Utils.faker.internet().password());
        usuarioPage.preencherCampoCidade(Utils.faker.address().cityName());
        usuarioPage.selecionarPerfilAluno();
        usuarioPage.preencherCampoEspecialidade("Back-End");
        usuarioPage.clicarBtnAdicionar();

        Assert.assertEquals(usuarioPage.buscarCampoToastContaCadastrada(), "Pessoa cadastrada com sucesso!");
    }

    @Test
    @Feature("Adminstrador")
    @Story("Deve criar usuario com sucesso.")
    public void testeDeveCadastrarUsuarioCompletoTipoInstrutorComSucesso() {

        Utils.logar();

        componentsPage.clicarBtnUsuario();

        usuarioPage.clicarBtnCadastrarUsuario();
        usuarioPage.preencherCampoNome(Utils.faker.name().firstName());
        usuarioPage.preencherCampoLogin(Utils.faker.name().firstName()+"."+Utils.faker
                .name().lastName());
        usuarioPage.preencherCampoEmail(Utils.faker.internet().emailAddress());
        usuarioPage.preencherCampoSenha(Utils.faker.internet().password());
        usuarioPage.preencherCampoCidade(Utils.faker.address().cityName());
        usuarioPage.selecionarPerfilInstrutor();
        usuarioPage.preencherCampoEspecialidade("QA");
        usuarioPage.clicarBtnAdicionar();

        Assert.assertEquals(usuarioPage.buscarCampoToastContaCadastrada(), "Pessoa cadastrada com sucesso!");
    }

    @Test
    @Feature("Adminstrador")
    @Story("Deve criar usuario com sucesso.")
    public void testeDeveCadastrarUsuarioCompletoTipoCoordenadorComSucesso() {

        Utils.logar();

        componentsPage.clicarBtnUsuario();

        usuarioPage.clicarBtnCadastrarUsuario();
        usuarioPage.preencherCampoNome(Utils.faker.name().firstName());
        usuarioPage.preencherCampoLogin(Utils.faker.name().firstName()+"."+Utils.faker
                .name().lastName());
        usuarioPage.preencherCampoEmail(Utils.faker.internet().emailAddress());
        usuarioPage.preencherCampoSenha(Utils.faker.internet().password());
        usuarioPage.preencherCampoCidade(Utils.faker.address().cityName());
        usuarioPage.selecionarPerfilCoordenador();
        usuarioPage.preencherCampoEspecialidade("Diretor");
        usuarioPage.clicarBtnAdicionar();

        Assert.assertEquals(usuarioPage.buscarCampoToastContaCadastrada(), "Pessoa cadastrada com sucesso!");
    }

    @Test
    @Feature("Adminstrador")
    @Story("Deve criar usuario com sucesso.")
    public void testeDeveCadastrarNovoUsuarioApenasPreenchendoOsCamposObrigatorios() {

        Utils.logar();

        componentsPage.clicarBtnUsuario();

        usuarioPage.clicarBtnCadastrarUsuario();
        usuarioPage.preencherCampoNome(Utils.faker.name().firstName());
        usuarioPage.preencherCampoLogin(Utils.faker.name().firstName()+"."+Utils.faker
                .name().lastName());
        usuarioPage.preencherCampoEmail(Utils.faker.internet().emailAddress());
        usuarioPage.preencherCampoSenha(Utils.faker.internet().password());
        usuarioPage.preencherCampoCidade(Utils.faker.address().cityName());
        usuarioPage.selecionarPerfilCoordenador();
        usuarioPage.clicarBtnAdicionar();

        Assert.assertEquals(usuarioPage.buscarCampoToastContaCadastrada(), "Pessoa cadastrada com sucesso!");
    }



    @Test
    @Feature("Adminstrador")
    @Story("Deve retornar mensagem de erro.")
    public void testeDeveRetornarMensagemErroAoCadastrarNovoUsuarioComEmailExistenteNoBancoDeDados() {

        Utils.logar();
        componentsPage.clicarBtnUsuario();
        HashMap<String, String> usuarioCriado = Utils.cadastrarUsuarioCoordenador();

        usuarioPage.clicarBtnCadastrarUsuario();
        usuarioPage.preencherCampoNome(Utils.faker.name().firstName());
        usuarioPage.preencherCampoLogin(Utils.faker.name().firstName()+"."+Utils.faker
                .name().lastName());
        usuarioPage.preencherCampoEmail(usuarioCriado.get("Email"));
        usuarioPage.preencherCampoSenha(Utils.faker.internet().password());
        usuarioPage.preencherCampoCidade(Utils.faker.address().cityName());
        usuarioPage.selecionarPerfilCoordenador();
        usuarioPage.preencherCampoEspecialidade("Diretor");
        usuarioPage.clicarBtnAdicionar();

        Assert.assertEquals(usuarioPage.buscarMsgErroCadastro(), "Ocorreu algum erro, por favor tente novamente!");
    }

    @Test
    @Feature("Adminstrador")
    @Story("Deve retornar mensagem de erro.")
    public void testeDeveRetornarMensagemDeErroAoCadastrarUsuarioComCamposObrigatoriosVazios() {

        Utils.logar();

        componentsPage.clicarBtnUsuario();
        usuarioPage.clicarBtnCadastrarUsuario();
        usuarioPage.selecionarPerfilCoordenador();
        usuarioPage.preencherCampoEspecialidade("Diretor");
        usuarioPage.clicarBtnAdicionar();

        Assert.assertEquals(usuarioPage.buscarMsgErroCampoNome(), "Por favor, digite o nome");
        Assert.assertEquals(usuarioPage.buscarMsgErroCampoLogin(), "Por favor, digite o login");
        Assert.assertEquals(usuarioPage.buscarMsgErroCampoEmail(), "Por favor, digite o email");
        Assert.assertEquals(usuarioPage.buscarMsgErroCampoSenha(), "Por favor, digite uma senha");
        Assert.assertEquals(usuarioPage.buscarMsgErroCampoCidade(), "Por favor, digite a cidade");
    }

    @Test
    @Feature("Adminstrador")
    @Story("Deve retornar mensagem de erro.")
    public void testeDeveRetornarMensagemDeErroAoCadastrarUsuarioComTodosCamposVazios() {

        Utils.logar();

        componentsPage.clicarBtnUsuario();
        usuarioPage.clicarBtnCadastrarUsuario();
        usuarioPage.clicarBtnAdicionar();

        Assert.assertEquals(usuarioPage.buscarMsgErroCampoNome(), "Por favor, digite o nome");
        Assert.assertEquals(usuarioPage.buscarMsgErroCampoLogin(), "Por favor, digite o login");
        Assert.assertEquals(usuarioPage.buscarMsgErroCampoEmail(), "Por favor, digite o email");
        Assert.assertEquals(usuarioPage.buscarMsgErroCampoSenha(), "Por favor, digite uma senha");
        Assert.assertEquals(usuarioPage.buscarMsgErroCampoCidade(), "Por favor, digite a cidade");
    }

    @Test
    @Feature("Adminstrador")
    @Story("Deve retornar mensagem de erro.")
    public void testeDeveRetornarMensagemDeErroAoEditarUsuarioPreenchandoTodosOsCamposVazios() {

        Utils.logar();

        componentsPage.clicarBtnUsuario();
        usuarioPage.clicarBtnEditarUsuario();

        usuarioPage.preencherCampoNome(StringUtils.EMPTY);
        usuarioPage.preencherCampoLogin(StringUtils.EMPTY);
        usuarioPage.preencherCampoEmail(StringUtils.EMPTY);
        usuarioPage.preencherCampoCidade(StringUtils.EMPTY);
        usuarioPage.preencherCampoEspecialidade(StringUtils.EMPTY);
        usuarioPage.clicarBtnSalvar();

        Assert.assertEquals(usuarioPage.buscarMsgErroCampoNomeEditar(), "Por favor, digite o nome");
        Assert.assertEquals(usuarioPage.buscarMsgErroCampoLoginEditar(), "Por favor, digite o login");
        Assert.assertEquals(usuarioPage.buscarMsgErroCampoEmailEditar(), "Por favor, digite o email");
        Assert.assertEquals(usuarioPage.buscarMsgErroCampoCidadeEditar(), "Por favor, digite a cidade");
    }

    @Test
    @Feature("Adminstrador")
    @Story("Deve fazer a acao esperada.")
    public void testeDeveRetornarAPaginaDeUsuariosAoClicarNoBotaoCancelar() {

        Utils.logar();

        componentsPage.clicarBtnUsuario();
        usuarioPage.clicarBtnCadastrarUsuario();
        usuarioPage.clicarBtnCancelar();

        Assert.assertEquals(Elements.getUrl(), "http://vemser-dbc.dbccompany.com.br:39000/vemser/vemrankser-front/usuarios");
    }

    @Test
    @Feature("Adminstrador")
    @Story("Deve fazer a acao esperada.")
    public void testeDeveVerificarAcaoDosSelectsAtivoEInativo() {

        Utils.logar();
        componentsPage.clicarBtnUsuario();
        usuarioPage.clicarBtnEditarUsuario();

        usuarioPage.selectRadioInvativo();
        Assert.assertTrue(usuarioPage.buscarEstadoRadioInativo());

        usuarioPage.selectRadioAtivo();
        Assert.assertTrue(usuarioPage.buscarEstadoRadioAtivo());
    }
}