package service;

import io.restassured.response.Response;
import model.EstagiarioModel;
import model.ProgramaModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class EstagiarioService {
    // region POST Estagiario
    public Response criarEstagiario(EstagiarioModel estagiario) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(estagiario)
                        .when()
                        .post("/estagiario/create");
    }

    // endregion
// region PUT Estagiario
    public Response atualizarEstagiario(Integer idEstagiario, EstagiarioModel estagiarioNovo) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(estagiarioNovo)
                        .pathParam("idEstagiario", idEstagiario)
                        .when()
                        .put("/estagiario/update/{idEstagiario}");
    }

    public Response atualizarEstagiario(EstagiarioModel estagiarioAntigo, EstagiarioModel estagiarioNovo) {
        return atualizarEstagiario(estagiarioAntigo.getIdEstagiario(), estagiarioNovo);
    }

    // endregion
// region GET Estagiario
    public Response buscarEstagiarioPorIdEstagiario(Integer idEstagiario) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idEstagiario", idEstagiario)
                        .when()
                        .get("/estagiario/get-by-id/{idEstagiario}");
    }

    public Response buscarEstagiarioPorIdEstagiario(EstagiarioModel estagiario) {
        return buscarEstagiarioPorIdEstagiario(estagiario.getIdEstagiario());
    }

    public Response buscarEstagiariosPorPrograma(Integer idPrograma, Integer pagina, Integer tamanho) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", idPrograma)
                        .queryParam("pagina", pagina)
                        .queryParam("tamanho", tamanho)
                        .when()
                        .get("/estagiario/list-by-programa/{idPrograma}");
    }

    // endregion
//region LISTAR TODOS OS ESTAGIARIOS
    public Response buscarPorListarTodosEstagiarios(Integer pagina, Integer tamanho) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina", pagina)
                        .queryParam("tamanho", tamanho)
                        .when()
                        .get("/estagiario/list-all");
    }

    //endregion
//region RECUPERA OS DADOS DO DASHBOARD
    public Response recuperarDadosDoDashboard(Integer idPrograma, Integer pagina, Integer tamanho) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", idPrograma)
                        .queryParam("pagina", pagina)
                        .queryParam("tamanho", tamanho)
                        .when()
                        .get("/estagiario/list-by-programa/{idPrograma}");
    }

    //endregion
// region DELETE Estagiario
    public Response deletarEstagiarioPorIdEstagiario(Integer idEstagiario) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idEstagiario", idEstagiario)
                        .when()
                        .delete("/estagiario/delete/{idEstagiario}");
    }

    public Response deletarEstagiario(EstagiarioModel estagiario) {
        return deletarEstagiarioPorIdEstagiario(estagiario.getIdEstagiario());
    }

    //Desativar Estagiario
    public Response desativarEstagiarioPorIdEstagiario(Integer idEstagiario) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idEstagiario", idEstagiario)
                        .when()
                        .delete("/estagiario/deactivate/{idEstagiario}");
    }

    public Response desativarEstagiario(EstagiarioModel estagiario) {
        return desativarEstagiarioPorIdEstagiario(estagiario.getIdEstagiario());
    }
// endregion
}
