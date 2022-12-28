package br.com.vemrankser.utils;

import br.com.vemrankser.pages.LoginPage;
import br.com.vemrankser.pages.UsuarioPage;
import br.com.vemrankser.steps.BaseSteps;
import net.datafaker.Faker;

import java.util.HashMap;
import java.util.Locale;

public class Utils extends BaseSteps {

    private static LoginPage loginPage = new LoginPage();

    static UsuarioPage usuarioPage = new UsuarioPage();


    public static Faker faker = new Faker(new Locale("pt-BR"));

    public static void logar() {

        loginPage.preencherCampoEmail(Manipulation.getProp().getProperty("prop.email"));
        loginPage.preencherCampoSenha(Manipulation.getProp().getProperty("prop.password"));
        loginPage.clicarBtnEntrar();
    }

    public static HashMap<String, String> cadastrarUsuarioCoordenador() {

        HashMap<String, String> params = new HashMap<>();

        params.put("Login", faker.name().firstName()+"."+faker.name().lastName());
        params.put("Email", faker.internet().emailAddress());

        usuarioPage.clicarBtnCadastrarUsuario();
        usuarioPage.preencherCampoNome(Utils.faker.name().firstName());
        usuarioPage.preencherCampoLogin(params.get("Login"));
        usuarioPage.preencherCampoEmail(params.get("Email"));
        usuarioPage.preencherCampoSenha(Utils.faker.internet().password());
        usuarioPage.preencherCampoCidade(Utils.faker.address().cityName());
        usuarioPage.selecionarPerfilCoordenador();
        usuarioPage.preencherCampoEspecialidade("Diretor");
        usuarioPage.clicarBtnAdicionar();

        return params;
    }

    public static HashMap<String, String> cadastrarUsuarioAluno() {

        HashMap<String, String> params = new HashMap<>();

        params.put("Login", faker.name().firstName()+"."+faker.name().lastName());
        params.put("Email", faker.internet().emailAddress());



        usuarioPage.clicarBtnCadastrarUsuario();
        usuarioPage.preencherCampoNome(Utils.faker.name().firstName());
        usuarioPage.preencherCampoLogin(params.get("Login"));
        usuarioPage.preencherCampoEmail(params.get("Email"));
        usuarioPage.preencherCampoSenha(Utils.faker.internet().password());
        usuarioPage.preencherCampoCidade(Utils.faker.address().cityName());
        usuarioPage.selecionarPerfilAluno();
        usuarioPage.preencherCampoEspecialidade("Diretor");
        usuarioPage.clicarBtnAdicionar();

        return params;
    }
}
