package br.com.dbccompany.tests.usuario;

import br.com.dbccompany.client.PageClient;
import br.com.dbccompany.client.UsuarioClient;
import br.com.dbccompany.data.changeless.UsuarioData;
import br.com.dbccompany.data.factory.UsuarioDataFactory;
import br.com.dbccompany.dto.PageDTO;
import br.com.dbccompany.dto.UsuarioDTO;
import br.com.dbccompany.model.Usuario;
import br.com.dbccompany.tests.base.BaseTest;
import br.com.dbccompany.utils.Utils;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.apache.http.HttpStatus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@Epic("Atualizar Tests")
@Feature("Usuario")
@DisplayName("Atualizar Usuario")
public class AtualizarTest extends BaseTest {

    UsuarioClient usuarioClient = new UsuarioClient();
    PageClient pageClient = new PageClient();

    @Test
    @Story("Deve atualizar usuario com sucesso")
    public void testeDeveAtualizarFotoUsuarioComSucesso() {

        UsuarioDTO usuarioNovo = usuarioClient.cadastrar
                (UsuarioData.COORDENADOR, Utils.convertUsusarioToJson(UsuarioDataFactory.usuarioValido()))
                        .then()
                                .statusCode(HttpStatus.SC_CREATED)
                .log().all()
                                .extract().as(UsuarioDTO.class);

        usuarioClient.atualizarFoto(usuarioNovo.getIdUsuario())
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
        ;
    }

    @Test
    @Story("Deve atualizar usuario com sucesso")
    public void testeDeveAtualizarEmailDoUsuarioComSucesso() {

        Usuario usuario = UsuarioDataFactory.usuarioValido();

        UsuarioDTO usuarioCadastro = usuarioClient.cadastrar
                        (UsuarioData.COORDENADOR, Utils.convertUsusarioToJson(usuario))
                                        .then()
                                                .statusCode(HttpStatus.SC_CREATED)
                                                .extract().as(UsuarioDTO.class);

        Usuario usuarioEmailModificado = UsuarioDataFactory.modificarEmailDoUsuario(usuario, Utils.faker.internet().emailAddress());
        UsuarioDTO usuarioAtualizado = usuarioClient.atualizar(usuarioCadastro.getIdUsuario(), Utils.convertUsusarioToJson(usuario)
                        )
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(UsuarioDTO.class)
        ;

        Assertions.assertEquals(usuarioAtualizado.getNome(), usuarioEmailModificado.getNome());
        Assertions.assertEquals(usuarioAtualizado.getLogin(), usuarioEmailModificado.getLogin());
        Assertions.assertEquals(usuarioAtualizado.getEmail(), usuarioEmailModificado.getEmail());
        Assertions.assertEquals(usuarioAtualizado.getStatusUsuario(), usuarioEmailModificado.getStatusUsuario());
        Assertions.assertEquals(usuarioAtualizado.getCidade(), usuarioEmailModificado.getCidade());
        Assertions.assertEquals(usuarioAtualizado.getEspecialidade(), usuarioEmailModificado.getEspecialidade());
    }

    @Test
    @Story("Deve atualizar usuario com sucesso")
    public void testeDeveAtualizarSenhaDoUsuarioComSucesso() {

        Usuario usuario = UsuarioDataFactory.usuarioValido();

        UsuarioDTO usuarioCadastro = usuarioClient.cadastrar
                        (UsuarioData.COORDENADOR, Utils.convertUsusarioToJson(usuario))
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(UsuarioDTO.class);

        Usuario usuarioSenhaModificada = UsuarioDataFactory.modificarSenhaDoUsuario(usuario, "Senha_Trocado");
        UsuarioDTO usuarioAtualizado = usuarioClient.atualizar(usuarioCadastro.getIdUsuario(), Utils.convertUsusarioToJson(usuario)
                )
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(UsuarioDTO.class)
        ;

        Assertions.assertEquals(usuarioAtualizado.getNome(), usuarioSenhaModificada.getNome());
        Assertions.assertEquals(usuarioAtualizado.getLogin(), usuarioSenhaModificada.getLogin());
        Assertions.assertEquals(usuarioAtualizado.getEmail(), usuarioSenhaModificada.getEmail());
        Assertions.assertEquals(usuarioAtualizado.getStatusUsuario(), usuarioSenhaModificada.getStatusUsuario());
        Assertions.assertEquals(usuarioAtualizado.getCidade(), usuarioSenhaModificada.getCidade());
        Assertions.assertEquals(usuarioAtualizado.getEspecialidade(), usuarioSenhaModificada.getEspecialidade());
    }

    @Test
    @Story("Deve retornar mensagem de erro")
    public void testeDeveMensagemDeErrorAoAtualizarEmailPraOutroExistente() {

        Usuario usuario = UsuarioDataFactory.usuarioValido();

        UsuarioDTO usuarioCadastro = usuarioClient.cadastrar
                        (UsuarioData.COORDENADOR, Utils.convertUsusarioToJson(usuario))
                .then()
                .extract().as(UsuarioDTO.class);


        PageDTO listaUsuarios = pageClient.listarUsuarios("0","1", "nome")
                .then()
                .extract().as(PageDTO.class);
        ;

        Usuario usuarioEmailTrocado = UsuarioDataFactory.modificarEmailDoUsuario(usuario, listaUsuarios.getElementos().get(0)
                .getEmail());
        usuarioClient.atualizar(usuarioCadastro.getIdUsuario(), Utils.convertUsusarioToJson(usuarioEmailTrocado)
                )
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
        ;
    }
}
