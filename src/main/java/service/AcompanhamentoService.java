package service;

import io.restassured.response.Response;
import model.AcompanhamentoModel;
import model.ProgramaModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class AcompanhamentoService {
    public Response criarAcompanhamento(AcompanhamentoModel acompanhamento) {
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(acompanhamento)
                        .when()
                        .post("/acompanhamento/create");
        return response;
    }
}
