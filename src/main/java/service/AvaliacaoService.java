package service;

import io.restassured.response.Response;
import model.ProgramaModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

    public class AvaliacaoService {
        public Response criarAvaliacao(ProgramaModel programa) {
            Response response =
                given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .body(programa)
                .when()
                    .post("/valiacao/create");
        return response;
    }
}
