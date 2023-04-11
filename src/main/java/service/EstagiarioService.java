package service;

import io.restassured.response.Response;
import model.EstagiarioModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class EstagiarioService {
    // region POST Estagiario
    public Response criarEstagiario(EstagiarioModel estagiario){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(estagiario)
            .when()
                .post("/estagiario/create");
        return response;
    }
    // endregion
    // region DELETE Estagiario
    public Response deletarEstagiarioPorIdEstagiario(Integer idEstagiario){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idEstagiario", idEstagiario)
            .when()
                .delete("/estagiario/delete/{idEstagiario}");
        return response;
    }
    public Response deletarEstagiario(EstagiarioModel estagiario){
        return deletarEstagiarioPorIdEstagiario(estagiario.getIdEstagiario());
    }
    //Desativar Estagiario
    public Response desativarEstagiarioPorIdEstagiario(Integer idEstagiario){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idEstagiario", idEstagiario)
            .when()
                .delete("/estagiario/deactivate/{idEstagiario}");
        return response;
    }
    public Response desativarEstagiario(EstagiarioModel estagiario){
        return desativarEstagiarioPorIdEstagiario(estagiario.getIdEstagiario());
    }
    // endregion
}
