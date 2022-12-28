//package br.com.dbccompany.tests.usuario;
//
//import br.com.dbccompany.model.Usuario;
//import br.com.dbccompany.tests.base.BaseTest;
//import br.com.dbccompany.utils.Utils;
//
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
//import io.qameta.allure.Story;
//
//import org.apache.http.HttpStatus;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Epic("Page Tests")
//@Feature("Usuario")
//@DisplayName("Listar Usuario")
//public class PageTest extends BaseTest {
//
//    PageClient pageClient = new PageClient();
//    UsuarioClient usuarioClient = new UsuarioClient();
//
//    TrilhaClient trilhaClient = new TrilhaClient();
//
//    @Test
//    @Story("Deve os elementos com sucesso")
//    public void testeDeveListarTodosUsuarios() {
//
//        PageDTO lista = pageClient.listarUsuarios("0","1", "nome")
//                .then()
//                .statusCode(HttpStatus.SC_OK)
//                .extract().as(PageDTO.class)
//        ;
//
//        assertAll("lista",
//                () -> assertEquals("0", lista.getPagina()),
//                () -> assertEquals("1", lista.getTamanho()),
//                () -> assertNotNull(lista.getElementos())
//        );
//    }
//
//    @Test
//    @Story("Deve os elementos com sucesso")
//    public void testeDeveRetornarFotoDoUsuario() throws IOException {
//
//        Usuario usuario = UsuarioDataFactory.usuarioValido();
//        TodosDTO usuarioResponse = usuarioClient.cadastrar(UsuarioData.GESTAO,
//                        Utils.convertUsusarioToJson(usuario))
//                .then()
//                .statusCode(HttpStatus.SC_CREATED)
//                .extract().as(TodosDTO.class)
//                ;
//
//        usuarioClient.atualizarFoto(usuarioResponse.getIdUsuario());
//
//        usuarioClient.buscarFoto(usuarioResponse.getIdUsuario())
//                .then()
//                .statusCode(HttpStatus.SC_OK)
//                ;
//    }
//
//    @Test
//    @Story("Deve retornar erro ao listar")
//    public void testeDeveRetornarErroAoListarUsuariosSemEstarLogado() {
//
//        pageClient.listarUsuariosSemAuth("0","0", "nome", "nome")
//                .then()
//                .statusCode(HttpStatus.SC_FORBIDDEN)
//        ;
//    }
//
//    @Test
//    @Story("Deve retornar erro ao listar")
//    public void testeDeveRetornarErroAoListarUsuariosComTamanhoListaZerado() {
//
//        pageClient.listarUsuarios("0","0", "nome")
//                .then()
//                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
//        ;
//    }
//
//    @Test
//    @Story("Deve os elementos com sucesso")
//    public void testeDeveRetornarUsuarioBuscadoPorNome() {
//
//        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
//
//        usuarioClient.cadastrar(UsuarioData.ALUNO,
//                Utils.convertUsusarioToJson(usuarioNovo));
//
//
//        TodosDTO[] listaPorNome = pageClient.listarUsuarioPorNome(usuarioNovo.getNome())
//                .then()
//                .extract().as(TodosDTO[].class);
//
//        Assertions.assertEquals(listaPorNome[0].getNome(), usuarioNovo.getNome());
//        Assertions.assertEquals(listaPorNome[0].getEmail(), usuarioNovo.getEmail());
//        Assertions.assertEquals(listaPorNome[0].getStatusUsuario(), usuarioNovo.getStatusUsuario());
//        Assertions.assertEquals(listaPorNome[0].getTipoPerfil(), usuarioNovo.getTipoPerfil());
//        Assertions.assertEquals(listaPorNome[0].getLogin(), usuarioNovo.getLogin());
//        Assertions.assertEquals(listaPorNome[0].getCidade(), usuarioNovo.getCidade());
//        Assertions.assertEquals(listaPorNome[0].getEspecialidade(), usuarioNovo.getEspecialidade());
//    }
//
//    @Test
//    @Story("Deve retornar erro ao listar")
//    public void testeDeveRetornarErroAoListarUsuariosInexistentePorNome() {
//
//        TodosDTO[] listaPorNome = pageClient.listarUsuarioPorNome(Utils.faker.name().firstName())
//                .then()
//                .extract().as(TodosDTO[].class);
//
//        assertNotNull(listaPorNome);
//    }
//
//    @Test
//    @Story("Deve os elementos com sucesso")
//    public void testeDeveRetonarAlunosPorLogin() {
//
//        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
//
//        usuarioClient.cadastrar(UsuarioData.ALUNO,
//                Utils.convertUsusarioToJson(usuarioNovo));
//
//        UsuarioDTO ususarioBuscado = pageClient.buscarAlunoPorLogin(usuarioNovo.getLogin())
//                        .then()
//                                .statusCode(HttpStatus.SC_OK)
//                                .extract().as(UsuarioDTO.class)
//                        ;
//
//        Assertions.assertEquals(ususarioBuscado.getNome(), usuarioNovo.getNome());
//        Assertions.assertEquals(ususarioBuscado.getEmail(), usuarioNovo.getEmail());
//        Assertions.assertEquals(ususarioBuscado.getStatusUsuario(), usuarioNovo.getStatusUsuario());
//        Assertions.assertEquals(ususarioBuscado.getTipoPerfil(), usuarioNovo.getTipoPerfil());
//        Assertions.assertEquals(ususarioBuscado.getLogin(), usuarioNovo.getLogin());
//        Assertions.assertEquals(ususarioBuscado.getCidade(), usuarioNovo.getCidade());
//        Assertions.assertEquals(ususarioBuscado.getEspecialidade(), usuarioNovo.getEspecialidade());
//    }
//
//    @Test
//    @Story("Deve retornar erro ao listar")
//    public void testeDeveRetonarErroPadraoAoBuscarAlunoPorLoginInexistente() {
//
//        ResponseDTO responseServer = pageClient.buscarAlunoPorLogin(Utils.faker.name().firstName())
//                .then()
//                .extract().as(ResponseDTO.class)
//                ;
//
//        Assertions.assertEquals(responseServer.getStatus(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
//    }
//
//    @Test
//    @Story("Deve os elementos com sucesso")
//    public void testeDeveRetornarListaDeAlunos() {
//
//        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
//
//        usuarioClient.cadastrar(UsuarioData.ALUNO,
//                Utils.convertUsusarioToJson(usuarioNovo));
//
//        PageDTO listaAlunos = pageClient.listarAlunosPorPagina("0", "10")
//                .then()
//                .extract().as(PageDTO.class)
//                ;
//
//        Assertions.assertTrue(listaAlunos.getElementos().size() > 0);
//    }
//
//    @Test
//    @Story("Deve retornar erro ao listar")
//    public void testeDeveRetornarErroAoListarAlunosPorPaginaNegativa() {
//
//        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
//
//        usuarioClient.cadastrar(UsuarioData.ALUNO,
//                Utils.convertUsusarioToJson(usuarioNovo));
//
//        pageClient.listarAlunosPorPagina("-1", "10")
//                .then()
//                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
//                ;
//    }
//
//    @Test
//    @Story("Deve os elementos com sucesso")
//    public void testeDeveRetornarListaDeTrilhaDoAluno() {
//
//        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
//
//        UsuarioDTO usuarioNovoCadastrado =  usuarioClient.cadastrar(UsuarioData.ALUNO,
//                Utils.convertUsusarioToJson(usuarioNovo))
//                .then()
//                .extract().as(UsuarioDTO.class);
//
//        Trilha trilha =  TrilhaDataFactory.novaTrilha();
//        TrilhaDTO trilhaResponse = trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilha))
//                .then()
//                .extract().as(TrilhaDTO.class)
//                ;
//        Trilha vincularTrilha = TrilhaDataFactory.vincularTrilha(trilhaResponse.getIdTrilha(), usuarioNovo.getLogin());
//        trilhaClient.vincularTrilhaAluno(trilhaResponse.getIdTrilha(), usuarioNovo.getLogin(), Utils.convertTrilhaToJson(vincularTrilha));
//
//        PageDTO listaDeTrilhasAluno = pageClient.buscarListaDeTrilhaDoAluno("0", "1", usuarioNovoCadastrado.getNome(), trilhaResponse.getIdTrilha())
//                .then()
//                .statusCode(HttpStatus.SC_OK)
//                .extract().as(PageDTO.class);
//
//        Assertions.assertEquals(listaDeTrilhasAluno.getElementos().get(0).getIdUsuario(), usuarioNovoCadastrado.getIdUsuario());
//        Assertions.assertEquals(listaDeTrilhasAluno.getElementos().get(0).getNome(), usuarioNovoCadastrado.getNome());
//        Assertions.assertEquals(listaDeTrilhasAluno.getElementos().get(0).getEmail(), usuarioNovoCadastrado.getEmail());
//        Assertions.assertEquals(listaDeTrilhasAluno.getElementos().get(0).getStatusUsuario(), usuarioNovoCadastrado.getStatusUsuario());
//        Assertions.assertEquals(listaDeTrilhasAluno.getElementos().get(0).getTipoPerfil(), usuarioNovoCadastrado.getTipoPerfil());
//        Assertions.assertEquals(listaDeTrilhasAluno.getElementos().get(0).getIdTrilha(), trilhaResponse.getIdTrilha());
//        Assertions.assertEquals(listaDeTrilhasAluno.getElementos().get(0).getEdicao(), trilhaResponse.getEdicao());
//    }
//
//
//    @Test
//    @Story("Deve os elementos com sucesso")
//    public void testeDeveRetornarUsuarioBuscadoPorId() {
//
//        Usuario usuarioNovo =  UsuarioDataFactory.usuarioValido();
//
//        UsuarioDTO usuarioResponse = usuarioClient.cadastrar(UsuarioData.ALUNO,
//                Utils.convertUsusarioToJson(usuarioNovo))
//                        .then()
//                            .extract().as(UsuarioDTO.class);
//
//        UsuarioDTO usuarioBuscado = pageClient.buscarUsuarioPorId(usuarioResponse.getIdUsuario())
//                .then()
//                .statusCode(HttpStatus.SC_OK)
//                .extract().as(UsuarioDTO.class);
//
//        Assertions.assertEquals(usuarioBuscado.getNome(), usuarioResponse.getNome());
//        Assertions.assertEquals(usuarioBuscado.getEmail(), usuarioResponse.getEmail());
//        Assertions.assertEquals(usuarioBuscado.getStatusUsuario(), usuarioResponse.getStatusUsuario());
//        Assertions.assertEquals(usuarioBuscado.getTipoPerfil(), usuarioResponse.getTipoPerfil());
//        Assertions.assertEquals(usuarioBuscado.getLogin(), usuarioResponse.getLogin());
//        Assertions.assertEquals(usuarioBuscado.getCidade(), usuarioResponse.getCidade());
//        Assertions.assertEquals(usuarioBuscado.getEspecialidade(), usuarioResponse.getEspecialidade());
//    }
//
//    @Test
//    @Story("Deve retornar erro ao listar")
//    public void testeDeveRetornarErroAoBuscarUsuarioPorIdNegativo() {
//
//        ResponseDTO responseServer = pageClient.buscarUsuarioPorId(Utils.faker.number().negative())
//                .then()
//                .extract().as(ResponseDTO.class);
//
//        Assertions.assertEquals(responseServer.getStatus(), HttpStatus.SC_BAD_REQUEST);
//    }
//}
