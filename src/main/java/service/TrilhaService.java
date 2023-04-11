package service;

import dataFactory.TrilhaDataFactory;
import io.restassured.response.Response;
import model.ProgramaModel;
import model.TrilhaModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

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
    public Response atualizarTrilha(){

        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body("""
                                {
                                  "idPrograma": 1,
                                  "nome": "Backend",
                                  "descricao": "Especialidade com java."
                                }
                            """)
                .pathParam("idTrilha",1)
            .when()
                .put("/trilha/update/{idTrilha}");
        return response;
    }
    public Response buscarTodasAsTrilhas(){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", 12)
                .queryParam("pagina", 0)
                .queryParam("tamanho", 1)
            .when()
                .get("/trilha/list-by-programa/{idPrograma}");
        return response;
    }
    public Response buscarTrilhaPage(){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .queryParam("page", 0)
                .queryParam("size", 5)
            .when()
                .get("/trilha/lista-trilha-page");
        return response;
    }

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

