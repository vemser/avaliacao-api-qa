package br.com.dbccompany.data.factory;

import br.com.dbccompany.model.Login;
import br.com.dbccompany.model.LoginBuilder;
import br.com.dbccompany.utils.Manipulation;
import br.com.dbccompany.utils.Utils;

import org.apache.commons.lang3.StringUtils;

public class LoginDataFactory {

    public static Login loginValido() {
        return login();
    }

    public static Login loginSenhaInvalida() {

        Login loginSenhaInvalida = loginValido();
        loginSenhaInvalida.setSenha(Utils.faker.number().digits(6));

        return loginSenhaInvalida;
    }

    public static Login loginUsuarioInvalido() {

        Login loginUsuarioInvalido = loginValido();
        loginUsuarioInvalido.setEmail(Utils.faker.internet().emailAddress());

        return loginUsuarioInvalido;
    }

    public static Login loginEmailSenhaVazios() {

        Login loginEmailSenhaVazios = loginValido();
        loginEmailSenhaVazios.setEmail(StringUtils.EMPTY);
        loginEmailSenhaVazios.setSenha(StringUtils.EMPTY);

        return loginEmailSenhaVazios;
    }

    private static Login login() {

        Login login = new LoginBuilder().
                email(Manipulation.getProp().getProperty("prop.email")).
                senha(Manipulation.getProp().getProperty("prop.password")).
                build();

        return login;
    }
}
