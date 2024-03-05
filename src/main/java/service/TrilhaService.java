package service;

import io.restassured.response.Response;
import model.TrilhaModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class TrilhaService {
//region ADICIONAR TRILHA

    public Response adicionarTrilha(TrilhaModel trilha) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(trilha)
                .when()
                        .post("/trilha/create")
                ;
    }

    //endregion
//region    ATUALIZAR TRILHA
    public Response atualizarTrilha(TrilhaModel trilhaAntiga, TrilhaModel trilhaModelNovo) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(trilhaModelNovo)
                        .pathParam("idTrilha", trilhaAntiga.getIdTrilha())
                        .when()
                        .put("/trilha/update/{idTrilha}");
    }

    public Response atualizarTrilhaComLink(TrilhaModel trilhaAntiga) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body("""
                                {
                                  "link": "https://meet.google.com/abc-defg-hij"
                                }
                                """)
                        .pathParam("idTrilha", trilhaAntiga.getIdTrilha())
                .when()
                        .put("/trilha/update-link/{idTrilha}");
    }

    //endregion
//region  BUSCAR TRILHAS POR ID
    public Response buscarTrilhaPorIdTrilha(Integer trilha) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idTrilha", trilha)
                        .when()
                        .get("/trilha/get-by-id/{idTrilha}");
    }

    //endregion
//region    DESATIVAR TRILHA POR ID DA TRILHA
    public Response deletarTrilhaIdTrilha(TrilhaModel trilha) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idTrilha", trilha.getIdTrilha())
                .when()
                        .delete("/trilha/delete/{idTrilha}");
    }

    //endregion
//region    DELETAR TRILHA POR ID DA TRILHA
    public Response desativarTrilhaIdTrilha(TrilhaModel trilha) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idTrilha", trilha.getIdTrilha())
                        .when()
                        .delete("/trilha/deactivate/{idTrilha}");
    }

    //endregion
//region BUSCAR TODAS AS TRILHAS
    public Response buscarTrilhaPage(Integer programa) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", programa)
                        .queryParam("pagina", 0)
                        .queryParam("tamanho", 5)
                        .when()
                        .get("/trilha/list-by-programa/{idPrograma}");
    }

    //endregion
//region ADICIONAR TRILHA COM MODULO
    public Response adicionarTrilhaComModulo(TrilhaModel trilha) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(trilha)
                        .when()
                        .post("/trilha/create-with-modulos");
    }

    //endregion
//    region LISTAR TRILHAS MODELO
    public Response buscarTrilhaModelo(Integer pagina, Integer tamanho) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina", pagina)
                        .queryParam("tamanho", tamanho)
                        .when()
                        .get("/trilha/list-models");
    }

    public Response buscarTrilhaModeloInvalido(String pagina, String tamanho) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina", pagina)
                        .queryParam("tamanho", tamanho)
                        .when()
                        .get("/trilha/list-models");
    }
//    endregion
}

