package service;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.ModuloModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class ModuloService {
//region CRIAR MODULO
    public Response criarModulo(ModuloModel corpo){
        return
                given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .body(corpo)
                .when()
                    .post("/modulo/create");
    }
//endregion
//region ATUALIZAR MODULO
    public Response atualizarModulo(ModuloModel body, Integer idModulo){
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(body)
                .pathParam("idModulo", idModulo)
            .when()
                .put("/modulo/update/{idModulo}");
    }
//endregion
//region BUSCAR MODULO PELO ID
    public Response buscarModuloPeloId(Integer idModulo){
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idModulo", idModulo)
                        .when()
                        .get("/modulo/get-by-id/{idModulo}");
    }
//endregion
//region BUSCAR MODULO PELO ID DA TRILHA
public Response buscarModuloPelaTrilhaPage(Integer trilha){
    return
        given()
            .spec(SetupsRequestSpecification.requestSpecification())
            .pathParam("idTrilha", trilha)
            .queryParam("pagina", 0)
            .queryParam("tamanho", 5)
        .when()
            .get("/modulo/list-by-trilha/{idTrilha}");
}
//endregion
//region DESATIVAR MODULO
    public Response desativarModuloPeloId(Integer idModulo){
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idModulo", idModulo)
            .when()
                .delete("/modulo/deactivate/{idModulo}");
    }
//endregion
//region DELETAR MODULO
    public Response deletarModuloPeloId(Integer idModulo){
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idModulo", idModulo)
            .when()
                .delete("/modulo/delete/{idModulo}");
    }
//endregion
}
