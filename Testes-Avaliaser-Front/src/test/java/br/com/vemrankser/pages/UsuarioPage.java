package br.com.vemrankser.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UsuarioPage extends BasePage{

    private static final By inputNome =
            By.id("nome");
    private static final By inputLogin =
            By.id("login");
    private static final By inputEmail =
            By.id("email");
    private static final By inputSenha =
            By.id("senha");
    private static final By inputCidade =
            By.id("cidade");
    private static final By btnTipoPerfil =
            By.id("tipoPerfil");
    private static final By btnGestaoPessoa =
            By.cssSelector("#menu-tipoPerfil > div > ul > li:nth-child(1)");
    private static final By btnCoordenador =
            By.cssSelector("#menu-tipoPerfil > div > ul > li:nth-child(2)");
    private static final By btnInstrutor =
            By.cssSelector("#menu-tipoPerfil > div > ul > li:nth-child(3)");
    private static final By btnAluno =
            By.cssSelector("#menu-tipoPerfil > div > ul > li:nth-child(4)");
    private static final By inputEspecialidade =
            By.id("especialidade");
    private static final By btnCadastraUsuario =
            By.id("botao-adiciona-usuario");
    private static final By btnAdicionarUsuario =
            By.id("button-adiciona-usuario");
    private static final By btnCancelar =
            By.id("button-cancela-usuario");
    private static final By msgErroCadastro =
            By.xpath("//*[@id=\"2\"]/div[1]/div[2]");
    private static final By msgErroCampoNome =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/span[1]");
    private static final By msgErroCampoLogin =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/span[2]");
    private static final By msgErroCampoEmail =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/span[3]");
    private static final By msgErroCampoSenha =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/span[4]");
    private static final By msgErroCampoCidade =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/span[5]");
    private static final By btnEditarUsuario =
            By.xpath("//*[@id=\"root\"]/main/div[3]/section/div[2]/div/div/div[2]/div[2]/div/div/div/div[1]/div[5]");
    private static final By selectRadioAtivo =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/div[7]/div/label[1]/span[1]/input");
    private static final By selectRadioInvativo =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/div[7]/div/label[2]/span[1]/input");
    private static final By btnSalvarEditarUsuario =
            By.xpath("//*[@id=\"button-edita-usuario\"]");
    private static final By campoErroNomeEditar =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/span[1]");
    private static final By campoErroLoginEditar =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/span[2]");
    private static final By campoErroEmailEditar =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/span[3]");
    private static final By campoErroCidadeEditar =
            By.xpath("//*[@id=\"root\"]/main/div[3]/form/span[4]");

    public static final By msgContaCadastrada =
            By.cssSelector("#\\31  > div.Toastify__toast-body > div:nth-child(2)");


    @Step("Preencher campo nome.")
    public String preencherCampoNome(String input) {

        sendKeys(inputNome, input);
        return input;
    }

    @Step("Preencher campo login.")
    public String preencherCampoLogin(String input) {

        sendKeys(inputLogin, input);
        return input;
    }

    @Step("Preencher campo email.")
    public String preencherCampoEmail(String input) {

        sendKeys(inputEmail, input);
        return input;
    }

    @Step("Preencher campo senha.")
    public String preencherCampoSenha(String input) {

        sendKeys(inputSenha, input);
        return input;
    }

    @Step("Preencher campo cidade.")
    public String preencherCampoCidade(String input) {

        sendKeys(inputCidade, input);
        return input;
    }

    @Step("Preencher campo especialidade.")
    public String preencherCampoEspecialidade(String input) {

        sendKeys(inputEspecialidade, input);
        return input;
    }

    @Step("Selecionar tipo de perfil Gestao.")
    public void selecionarPerfilGestao() {

        click(btnTipoPerfil);
        click(btnGestaoPessoa);
    }

    @Step("Selecionar tipo de perfil Coordenador.")
    public void selecionarPerfilCoordenador() {

        click(btnTipoPerfil);
        click(btnCoordenador);
    }

    @Step("Selecionar tipo de perfil Instrutor.")
    public void selecionarPerfilInstrutor() {

        click(btnTipoPerfil);
        click(btnInstrutor);
    }

    @Step("Fechar toast conta cadastrada.")
    public void fecharToastContaCadastrada() {

        click(msgContaCadastrada);
    }

    @Step("Selecionar tipo de perfil Aluno.")
    public void selecionarPerfilAluno() {

        click(btnTipoPerfil);
        click(btnAluno);
    }

    @Step("Clicar botao cadastrar usuario.")
    public void clicarBtnCadastrarUsuario() {

        click(btnCadastraUsuario);
    }

    @Step("Clicar botao adicionar usuario.")
    public void clicarBtnAdicionar() {

        click(btnAdicionarUsuario);
    }

    @Step("Clicar botao cancelar.")
    public void clicarBtnCancelar() {

        click(btnCancelar);
    }

    @Step("Clicar botao de editar usuario.")
    public void clicarBtnEditarUsuario() {

        click(btnEditarUsuario);
    }

    @Step("Selecionar botao usuario ativo.")
    public void selectRadioAtivo() {

        click(selectRadioAtivo);
    }

    @Step("Selecionar botao usuario inativo.")
    public void selectRadioInvativo() {

        click(selectRadioInvativo);
    }

    @Step("Clicar botao salvar na edicao do usuario.")
    public void clicarBtnSalvar() {

        click(btnSalvarEditarUsuario);
    }

    @Step("Buscar mensagem erro ao cadastrar.")
    public String buscarMsgErroCadastro() {

       return getInnerText(msgErroCadastro);
    }

    @Step("Buscar mensagem erro ao cadastrar.")
    public String buscarMsgErroCampoNome() {

        return getText(msgErroCampoNome);
    }

    @Step("Buscar mensagem erro ao cadastrar.")
    public String buscarMsgErroCampoLogin() {

        return getText(msgErroCampoLogin);
    }

    @Step("Buscar mensagem erro ao cadastrar.")
    public String buscarMsgErroCampoEmail() {

        return getText(msgErroCampoEmail);
    }

    @Step("Buscar mensagem erro ao cadastrar.")
    public String buscarMsgErroCampoSenha() {

        return getText(msgErroCampoSenha);
    }

    @Step("Buscar mensagem erro ao editar usuario.")
    public String buscarMsgErroCampoCidade() {

        return getText(msgErroCampoCidade);
    }

    @Step("Buscar mensagem erro ao editar usuario.")
    public String buscarMsgErroCampoNomeEditar() {

        return getText(campoErroNomeEditar);
    }

    @Step("Buscar mensagem erro ao editar usuario.")
    public String buscarMsgErroCampoLoginEditar() {

        return getText(campoErroLoginEditar);
    }

    @Step("Buscar mensagem erro ao editar usuario.")
    public String buscarMsgErroCampoEmailEditar() {

        return getText(campoErroEmailEditar);
    }

    @Step("Buscar mensagem erro ao cadastrar.")
    public String buscarMsgErroCampoCidadeEditar() {

        return getText(campoErroCidadeEditar);
    }

    @Step("Buscar campo toast conta cadastrada.")
    public String buscarCampoToastContaCadastrada() {

        wait.until(ExpectedConditions.visibilityOf(element(msgContaCadastrada)));
        return getText(msgContaCadastrada);
    }

    @Step("Verificar radio Ativo esta selecionado.")
    public Boolean buscarEstadoRadioAtivo() {

        return isSelected(selectRadioAtivo);
    }

    @Step("Verificar radio inativo esta selecionado.")
    public Boolean buscarEstadoRadioInativo() {

        return isSelected(selectRadioInvativo);
    }
}
