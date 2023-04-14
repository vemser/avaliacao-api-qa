package service;

import io.restassured.response.Response;
import model.ModuloModel;
import model.TrilhaModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class ModuloService {
    public Response adicionarModulo(ModuloModel model) {
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(model)
            .when()
                .post("/modulo/create");
        return response;
    }
}
