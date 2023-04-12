package service;

import io.restassured.response.Response;
import model.TrilhaModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;
public class TrilhaService {
//region ADICIONAR TRILHA

    public Response adicionarTrilha(TrilhaModel trilha) {
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
    public Response atualizarTrilha(TrilhaModel trilhaAntiga, TrilhaModel trilhaModelNovo) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(trilhaModelNovo)
                        .pathParam("idTrilha", trilhaAntiga.getIdTrilha())
                        .when()
                        .put("/trilha/update/{idTrilha}");
        return response;
    }

    //endregion
//region  BUSCAR TRILHAS POR ID
    public Response buscarTrilhaPorIdTrilha(TrilhaModel trilha) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idTrilha", trilha.getIdTrilha())
                        .when()
                        .get("/trilha/get-by-id/{idTrilha}");
        return response;
    }

    //endregion
//region    DESATIVAR TRILHA POR ID DA TRILHA
    public Response deletarTrilhaIdTrilha(TrilhaModel trilha) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idTrilha", trilha.getIdTrilha())
                        .when()
                        .delete("/trilha/delete/{idTrilha}");
        return response;
    }

//endregion
//region    DELETAR TRILHA POR ID DA TRILHA
    public Response desativarTrilhaIdTrilha(TrilhaModel trilha) {
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idTrilha", trilha.getIdTrilha())
            .when()
                .delete("/trilha/deactivate/{idTrilha}");
        return response;
    }
//endregion
//region BUSCAR TODAS AS TRILHAS
    public Response buscarTrilhaPage(TrilhaModel programa){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", programa.getIdPrograma())
                .queryParam("pagina", 0)
                .queryParam("tamanho", 2)
            .when()
                .get("/trilha/list-by-programa/{idPrograma}");
        return response;
    }
//endregion
//region ADICIONAR TRILHA COM MODULO
public Response adicionarTrilhaComModulo(TrilhaModel trilha) {
    Response response =
            given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .body(trilha)
                    .when()
                    .post("/trilha/create-with-modulos");
    return response;
}
//endregion
}

