package br.com.dbccompany.client;

import br.com.dbccompany.data.changeless.*;
import br.com.dbccompany.specs.AuthSpecs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PageClient {

    public Response listarUsuarios(String pagina, String tamanho, String sort) {

        return
                given()
                        .log().all()
                        .spec(AuthSpecs.requestSpecAdmin())
                        .queryParam(PageData.PAGINA, pagina)
                        .queryParam(PageData.TAMANHO, tamanho)
                        .queryParam(PageData.SORT, sort)
                .when()
                        .get(PageData.LISTAR_USUARIOS)
                ;
    }

    public Response listarUsuariosSemAuth(String pagina, String tamanho, String sort, String nome) {

        return
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .queryParam(PageData.PAGINA, pagina)
                        .queryParam(PageData.TAMANHO, tamanho)
                        .queryParam(PageData.SORT, sort)
                        .queryParam(PageData.NOME, nome)
                .when()
                        .get(PageData.LISTAR_USUARIOS)
                ;
    }

    public Response listarUsuarioPorNome(String nome) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(PageData.NOME, nome)
                .when()
                    .get(PageData.BUSCAR_NOME)
                ;
    }

    public Response listarTrilhaComUsuarios(String pagina, String tamanho, Integer idTrilha) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(PageData.PAGINA, pagina)
                    .queryParam(PageData.TAMANHO, tamanho)
                    .queryParam(TrilhaData.ID_TRILHA, idTrilha)
                .when()
                    .get(TrilhaData.LISTAR_TRILHAS_COM_USUARIOS)
                ;
    }

    public Response listarTrilhaPorNome(String nome) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(PageData.NOME, nome)
                .when()
                    .get(TrilhaData.LISTAR_TRILHA_POR_NOME)
                ;
    }

    public Response listarTrilhaPorRanking(Integer idTrilha) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(TrilhaData.ID_TRILHA, idTrilha)
                .when()
                    .get(TrilhaData.LISTAR_RANKING_TRILHA)
                ;
    }

    public Response listarTrilhaPorEdicao(Integer nome) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(TrilhaData.EDICAO, nome)
                .when()
                    .get(TrilhaData.LISTAR_TRILHA_POR_EDICAO)
                ;
    }

    public Response listarAlunosPorPagina(String pagina, String tamanho) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(PageData.PAGINA, pagina)
                    .queryParam(PageData.TAMANHO, tamanho)
                .when()
                    .get(PageData.LISTAR_ALUNOS)
                ;
    }

    public Response buscarAlunoPorLogin(String login) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(UsuarioData.LOGIN, login)
                .when()
                    .get(UsuarioData.BUSCAR_ALUNO)
                ;
    }

    public Response buscarListaDeTrilhaDoAluno(String pagina, String tamanho, String nome, Integer idTrilha) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(PageData.PAGINA, pagina)
                    .queryParam(PageData.TAMANHO, tamanho)
                    .queryParam(PageData.NOME, nome)
                    .queryParam(TrilhaData.ID_TRILHA, idTrilha)
                .when()
                    .get(PageData.LISTAR_ALUNOS_TRILHA)
                ;
    }

    public Response buscarUsuarioPorId(Integer id) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(PageData.ID, id)
                .when()
                    .get(UsuarioData.BUSCAR_ID_USUARIO)
                ;
    }

    public Response buscarTrilhaPorId(Integer idTrilha) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(TrilhaData.ID_TRILHA, idTrilha)
                .when()
                    .get(TrilhaData.BUSCAR_ID_TRILHA)
                ;
    }

    public Response listaTodosModulos() {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                .when()
                    .get(ModuloData.LISTA_TODOS_MODULOS)
                ;
    }

    public Response buscaModuloPorId(Integer idModulo) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(ModuloData.ID_MODULO, idModulo)
                .when()
                    .get(ModuloData.BUSCA_MODULO_IDMODULO)
                ;
    }

    public Response buscaModuloPorIdNumeroFloat(float idModulo) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(ModuloData.ID_MODULO, idModulo)
                .when()
                    .get(ModuloData.BUSCA_MODULO_IDMODULO)
                ;
    }

    public Response listarPaginadoAtividades(String pagina, String tamanho) {

        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(PageData.PAGINA, pagina)
                    .queryParam(PageData.TAMANHO, tamanho)
                .when()
                    .get(AtividadeData.LISTAR_PAGINADO)
                ;
    }

    public Response listarAtividadeStatus(String pagina, String tamanho, Integer idTrilha, String atividadeStatus) {
        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(PageData.PAGINA, pagina)
                    .queryParam(PageData.TAMANHO, tamanho)
                    .queryParam(TrilhaData.ID_TRILHA, idTrilha)
                    .queryParam(AtividadeData.ATIVIDADE_STATUS, atividadeStatus)
                .when()
                    .get(AtividadeData.LISTAR_STATUS)
                ;
    }

    public Response listarAtividadePorTrilhaModulo(String pagina, String tamanho, Integer idTrilha, Integer idModulo, String atividadeStatus) {
        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(PageData.PAGINA, pagina)
                    .queryParam(PageData.TAMANHO, tamanho)
                    .queryParam(TrilhaData.ID_TRILHA, idTrilha)
                    .queryParam(ModuloData.ID_MODULO, idModulo)
                    .queryParam(AtividadeData.ATIVIDADE_STATUS, atividadeStatus)
                .when()
                    .get(AtividadeData.LISTAR_ATIVIDADE_POR_TRILHA_MODULO)
                ;
    }

    public Response listarMuralInstrutor(String pagina, String tamanho, Integer idTrilha) {
        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(PageData.PAGINA, pagina)
                    .queryParam(PageData.TAMANHO, tamanho)
                    .queryParam(TrilhaData.ID_TRILHA, idTrilha)
                .when()
                    .get(AtividadeData.LISTAR_MURAL_INSTRUTOR)
                ;
    }

    public Response listarMuralAluno(String pagina, String tamanho, String atividadeStatus, Integer idUsuario) {
        return
                given()
                    .log().all()
                    .spec(AuthSpecs.requestSpecAdmin())
                    .queryParam(PageData.PAGINA, pagina)
                    .queryParam(PageData.TAMANHO, tamanho)
                    .queryParam(AtividadeData.ATIVIDADE_STATUS, atividadeStatus)
                    .queryParam(UsuarioData.ID_USUARIO, idUsuario)
                .when()
                    .get(AtividadeData.LISTAR_MURAL_ALUNO)
                ;
    }
}
