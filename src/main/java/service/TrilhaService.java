package service;

import dataFactory.TrilhaDataFactory;
import io.restassured.response.Response;
import model.ProgramaModel;
import model.TrilhaModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;
//region ADICIONAR TRILHA
public class TrilhaService {
    public Response adicionarTrilha(TrilhaModel trilha){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(trilha)
            .when()
                .post("/trilha/create");
        return response;
    }
//endregion
//region    ATUALIZAR TRILHA
    public Response atualizarTrilha(TrilhaModel trilhaAntiga, TrilhaModel trilhaModelNovo){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(trilhaModelNovo)
                .pathParam("idTrilha",trilhaAntiga.getIdTrilha())
            .when()
                .put("/trilha/update/{idTrilha}");
        return response;
    }
//endregion

    public Response buscarTrilhaPorIdTrilha(TrilhaModel trilha){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idTrilha", trilha.getIdTrilha())
                        .when()
                        .get("/trilha/get-by-id/{idTrilha}");
        return response;
    }











//    public Response buscarTrilhaPage(){
//        Response response =
//            given()
//                .spec(SetupsRequestSpecification.requestSpecification())
//                .queryParam("page", 0)
//                .queryParam("size", 5)
//            .when()
//                .get("/trilha/lista-trilha-page");
//        return response;
//    }

    public Response deletarTrilhaIdTrilha(TrilhaModel trilha){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idTrilha", trilha.getIdTrilha())
            .when()
                .delete("/trilha/delete/{idTrilha}");
        return response;
    }
}

