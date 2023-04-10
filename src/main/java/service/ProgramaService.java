package service;

import io.restassured.response.Response;
import specs.SetupsRequestSpecification;
import static io.restassured.RestAssured.given;

public class ProgramaService {

//    region CRIAR PROGRAMA
    public Response criarPrograma(){
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

    public Response criarProgramaSemNome(){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body("""
                               {
                                "nome": ,
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
    public Response criarProgramaComDataAbaixoDaAtual(){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body("""
                               {
                                "nome": "VemSer 11ed",
                                "descricao": "Programa de formação profissional Vem Ser DBC 11º edição.",
                                "dataInicio": "2021-10-21",
                                "dataFim": "2021-12-21",
                                "status": "ABERTO"
                              }
                            """)
                        .when()
                        .post("/programa/create");
        return response;
    }
//    endregion
//    region BUSCAR PROGRAMA

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
    public Response buscarProgramasSemNumeroDePaginas(){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .queryParam("pagina", "")
                .queryParam("tamanho", 1)
            .when()
                .get("/programa/list-all");
        return response;
    }
    public Response buscarProgramasSemTamanho(){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina", 0)
                        .queryParam("tamanho", "")
                        .when()
                        .get("/programa/list-all");
        return response;
    }

//endregion
//    region ATUALIZAR PROGRAMA
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
    public Response atualizarProgramaComIdErrado(){
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
                .pathParam("idPrograma", 123546879)
            .when()
                .put("/programa/update/{idPrograma}");
        return response;
    }
    public Response atualizarProgramaSemInformarId(){
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
                .pathParam("idPrograma", "")
            .when()
                .put("/programa/update/{idPrograma}");
        return response;
    }
//    endregion
//    region DESATIVAR PROGRAMA
    public Response desativarProgramaComLetrasNoId(){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", "saxsaxaxasxsaa")
            .when()
                .delete("/programa/deactivate/{idPrograma}");
        return response;
    }
    public Response desativarProgramaComIdErrado(){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", 19987)
            .when()
                .delete("/programa/deactivate/{idPrograma}");
        return response;
    }
    public Response desativarPrograma(){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", 19)
            .when()
                .delete("/programa/deactivate/{idPrograma}");
        return response;
    }
//    endregion
//    region DELETAR PROGRAMA
public Response deletarProgramaComLetrasNoId(){
    Response response =
            given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .pathParam("idPrograma", "ushuahus")
                    .when()
                    .delete("/programa/dealete/{idPrograma}");
    return response;
}
    public Response deletarProgramaComIdErrado(){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", 25525)
                        .when()
                        .delete("/programa/delete/{idPrograma}");
        return response;
    }
    public Response deletarPrograma(){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", 45)
                        .when()
                        .delete("/programa/delete/{idPrograma}");
        return response;
    }
//    endregion
}
