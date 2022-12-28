package br.com.dbccompany.data.factory;

import br.com.dbccompany.client.PageClient;
import br.com.dbccompany.dto.PageDTO;
import br.com.dbccompany.dto.TodosDTO;
import br.com.dbccompany.model.Usuario;
import br.com.dbccompany.model.UsuarioBuilder;
import br.com.dbccompany.utils.Utils;

import java.util.List;

public class UsuarioDataFactory {

    static PageClient pageClient = new PageClient();
    public static Usuario usuarioValido() {
        return novoUsuario();
    }

    public static Usuario modificarEmailDoUsuario(Usuario usuario, String atualizacaoEmail) {

        usuario.setEmail(atualizacaoEmail);
        return usuario;
    }

    public static Usuario modificarSenhaDoUsuario(Usuario usuario, String atualizacaoSenha) {

        usuario.setSenha(atualizacaoSenha);
        return usuario;
    }

    public static Usuario usuarioComEmailRepetido() {

        Usuario usuarioComEmailRepetido = usuarioValido();
        PageDTO lista = pageClient.listarUsuarios("0","1", "nome")
                .then()
                .log().all()
                .extract().as(PageDTO.class)
                ;
        List<TodosDTO> usuarioRepetido = lista.getElementos();
        usuarioComEmailRepetido.setEmail(usuarioRepetido.get(0).getEmail());

        return usuarioComEmailRepetido;
    }

    private static Usuario novoUsuario() {

        Usuario usuario = new UsuarioBuilder().
                foto("").
                nome(Utils.faker.name().fullName()).
                login(Utils.faker.name().firstName()+"."+Utils.faker.name().lastName()).
                email(Utils.faker.internet().emailAddress()).
                senha(Utils.faker.password().toString()).
                statusUsuario(1).
                tipoPerfil(2).
                cidade(Utils.faker.address().cityName()).
                especialidade("QA").
                build();

        return usuario;
    }
}
