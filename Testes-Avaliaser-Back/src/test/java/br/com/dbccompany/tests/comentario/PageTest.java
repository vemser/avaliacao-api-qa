package br.com.dbccompany.tests.comentario;

import br.com.dbccompany.client.*;
import br.com.dbccompany.data.changeless.UsuarioData;
import br.com.dbccompany.client.ComentarioClient;
import br.com.dbccompany.data.factory.UsuarioDataFactory;
import br.com.dbccompany.dto.*;
import br.com.dbccompany.data.changeless.ComentarioData;
import br.com.dbccompany.data.factory.ComentarioDataFactory;
import br.com.dbccompany.model.Usuario;
import br.com.dbccompany.tests.base.BaseTest;
import br.com.dbccompany.utils.Utils;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import br.com.dbccompany.model.Comentario;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@Epic("Page Tests")
@Feature("Comentario")
@DisplayName("Listar Comentario")
public class PageTest extends BaseTest {

    UsuarioClient usuarioClient = new UsuarioClient();
    ComentarioClient comentarioClient = new ComentarioClient();

    @Test
    @Story("Deve listar os elementos com sucesso")
    public void testeDeveListarFeedbacksPositivo() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
        UsuarioDTO usuarioCadastrado = usuarioClient.cadastrar(UsuarioData.INSTRUTOR,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .extract().as(UsuarioDTO.class);

        Comentario novoComentario = ComentarioDataFactory.criarComentario();
        comentarioClient.cadastrar(usuarioCadastrado.getIdUsuario(),
                        ComentarioData.POSITIVO, Utils.converterComentarioToJson(novoComentario));

        ComentarioDTO[] listaComentarios = comentarioClient.buscarComentarioTipoFeedback(ComentarioData.POSITIVO)
                .then()
                .extract().as(ComentarioDTO[].class);

        Assertions.assertTrue(listaComentarios.length > 0);
    }

    @Test
    @Story("Deve listar os elementos com sucesso")
    public void testeDeveListarFeedbackNegativo() {

        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
        UsuarioDTO usuarioCadastrado = usuarioClient.cadastrar(UsuarioData.INSTRUTOR,
                        Utils.convertUsusarioToJson(usuarioNovo))
                .then()
                .extract().as(UsuarioDTO.class);

        Comentario novoComentario = ComentarioDataFactory.criarComentario();
        comentarioClient.cadastrar(usuarioCadastrado.getIdUsuario(),
                ComentarioData.NEGATIVO, Utils.converterComentarioToJson(novoComentario));

        ComentarioDTO[] listaComentarios = comentarioClient.buscarComentarioTipoFeedback(ComentarioData.NEGATIVO)
                .then()
                .extract().as(ComentarioDTO[].class);

        Assertions.assertTrue(listaComentarios.length > 0);
    }
    @Test
    @Story("Deve retornar mensagem de erro.")
    public void testeDeveListarFeedbackSemSerPositivoOuNegativo() {


        comentarioClient.buscarComentarioTipoFeedback(Utils.faker.random().hex())
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
