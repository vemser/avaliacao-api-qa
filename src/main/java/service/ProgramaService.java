package service;

import io.restassured.response.Response;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class ProgramaService {
    public Response criarPorgrama(){
        Response response =
            given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .body("""
                               {
                                "nome": "VemSer 11ed",
                                "descricao": "Programa de formação profissional Vem Ser DBC 11º edição.",
                                "dataInicio": "2023-10-23",
                                "dataFim": "2023-12-23",
                                "status": "ABERTO"
                              }
                            """)
                .when()
                    .post("/programa/create");
        return response;
    }

    public Response buscarProgramas(){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .queryParam("pagina", 0)
                .queryParam("tamanho", 1)
            .when()
                .get("/programa/list-all");
        return response;
    }
    public Response atualizarPrograma(){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body("""
                        {
                          "nome": "VemSer 11ed",
                          "descricao": "Programa de formação profissional Vem Ser DBC 11º edição.",
                          "dataInicio": "2024-02-23",
                          "dataFim": "2024-06-23",
                          "status": "ABERTO"
                        }
                        """)
                .pathParam("idPrograma", 12)
            .when()
                .put("/programa/update/{idPrograma}");
        return response;
    }
    public Response desativarPrograma(){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", 14)
            .when()
                .delete("/programa/deactivate/{idPrograma}");
        return response;
    }
}
