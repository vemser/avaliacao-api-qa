package br.com.dbccompany.tests.comentario;

import br.com.dbccompany.client.ComentarioClient;
import br.com.dbccompany.client.UsuarioClient;
import br.com.dbccompany.data.changeless.ComentarioData;
import br.com.dbccompany.data.changeless.UsuarioData;
import br.com.dbccompany.data.factory.ComentarioDataFactory;
import br.com.dbccompany.data.factory.UsuarioDataFactory;
import br.com.dbccompany.dto.ComentarioDTO;
import br.com.dbccompany.dto.UsuarioDTO;
import br.com.dbccompany.model.Comentario;
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


@Epic("Cadastro Tests")
@Feature("Comentario")
@DisplayName("Cadastro Comentario")
public class CadastrarTest extends BaseTest {

    UsuarioClient usuarioClient = new UsuarioClient();

    ComentarioClient comentarioClient = new ComentarioClient();

    @Test
    @Story("Deve cadastrar com sucesso")
    public void testeDeveAdicionarComentarioPositivoAoAluno() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
        UsuarioDTO usuarioCadastrado = usuarioClient.cadastrar(UsuarioData.INSTRUTOR,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .extract().as(UsuarioDTO.class);

        Comentario novoComentario = ComentarioDataFactory.criarComentario();
        ComentarioDTO comentanrioAdicionado = comentarioClient.cadastrar(usuarioCadastrado.getIdUsuario(),
                ComentarioData.POSITIVO, Utils.converterComentarioToJson(novoComentario))
                .then()
                .extract().as(ComentarioDTO.class);

        Assertions.assertEquals(comentanrioAdicionado.getComentario(), novoComentario.getComentario());
        Assertions.assertEquals(comentanrioAdicionado.getStatusComentario(), novoComentario.getStatusComentario());
    }

    @Test
    @Story("Deve cadastrar com sucesso")
    public void testeDeveAdicionarComentarioNegativoAoAluno() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
        UsuarioDTO usuarioCadastrado = usuarioClient.cadastrar(UsuarioData.INSTRUTOR,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .extract().as(UsuarioDTO.class);

        Comentario novoComentario = ComentarioDataFactory.criarComentario();
        ComentarioDTO comentanrioAdicionado = comentarioClient.cadastrar(usuarioCadastrado.getIdUsuario(),
                        ComentarioData.NEGATIVO, Utils.converterComentarioToJson(novoComentario))
                .then()
                .extract().as(ComentarioDTO.class);

        Assertions.assertEquals(comentanrioAdicionado.getComentario(), novoComentario.getComentario());
        Assertions.assertEquals(comentanrioAdicionado.getStatusComentario(), novoComentario.getStatusComentario());
    }

    @Test
    @Story("Deve retornar mensagem errro.")
    public void testeDeveRetornarErroAoPassarFeedBackDiferenteDePositivoOuNegativo() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
        UsuarioDTO usuarioCadastrado = usuarioClient.cadastrar(UsuarioData.INSTRUTOR,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .extract().as(UsuarioDTO.class);

        Comentario novoComentario = ComentarioDataFactory.criarComentario();
        comentarioClient.cadastrar(usuarioCadastrado.getIdUsuario(),
                        Utils.faker.random().hex(), Utils.converterComentarioToJson(novoComentario))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Story("Deve retornar mensagem errro.")
    public void testeDeveRetornarErroAoPassarFeedBackAUmAlunoInxesistente() {

        Comentario novoComentario = ComentarioDataFactory.criarComentario();
        comentarioClient.cadastrar(Utils.faker.number().negative(),
                        ComentarioData.NEGATIVO, Utils.converterComentarioToJson(novoComentario))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
