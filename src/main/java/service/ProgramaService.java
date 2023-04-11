package service;

import dataFactory.ProgramaDataFactory;
import io.restassured.response.Response;
import model.ProgramaModel;
import specs.SetupsRequestSpecification;
import static io.restassured.RestAssured.given;

public class ProgramaService {
    ProgramaModel programaModel = new ProgramaModel("Programa KTeste", "Programa de teste", "2023-05-01", "2023-08-01", "ABERTO");

//    region CRIAR PROGRAMA
    public Response criarPrograma(ProgramaModel programa) {
        Response response =
            given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .body(programa)
                .when()
                    .post("/programa/create");
        return response;
    }
    public Response criarPrograma(){
        return criarPrograma(programaModel);
    }

    public Response criarProgramaValido(){
        return criarPrograma(ProgramaDataFactory.gerarProgramaValido());
    }

    public Response criarProgramaSemNome(){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body("""
                               {
                                "nome": "",
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
    public Response criarProgramaComDatasAbaixoDaAtual(){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(ProgramaDataFactory.gerarProgramaComDataAbaixoDaAtual())
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
                .queryParam("tamanho", 10)
            .when()
                .get("/programa/list-all");
        return response;
    }
    public Response buscarProgramasNumeroPaginaVazio(){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .queryParam("pagina", "")
                .queryParam("tamanho", 10)
            .when()
                .get("/programa/list-all");
        return response;
    }
    public Response buscarProgramasTamanhoVazio(){
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
//    region BUSCAR PROGRAMA PELO ID
public Response buscarProgramaComLetrasNoId(){
    Response response =
            given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .pathParam("idPrograma", "saxsaxaxasxsaa")
            .when()
                    .get("/programa/get-by-id/{idPrograma}");
    return response;
}
    public Response buscarProgramaSemId(){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", "")
                        .when()
                        .get("/programa/get-by-id/{idPrograma}");
        return response;
    }
    public Response buscarPrograma(){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", 12)
                        .when()
                        .get("/programa/get-by-id/{idPrograma}");
        return response;
    }
    public Response buscarPrograma(ProgramaModel programa){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", programa.getIdPrograma())
                .when()
                        .get("/programa/get-by-id/{idPrograma}");
        return response;
    }
    //    endregion
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
    public Response atualizarPrograma(ProgramaModel programaExistente, ProgramaModel programaNovo){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(programaNovo)
                .pathParam("idPrograma", programaExistente.getIdPrograma())
            .when()
                .put("/programa/update/{idPrograma}");
        return response;
    }
    public Response atualizarProgramaComIdInexistente(){
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
    public Response atualizarProgramaComIdVazio(){
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
    public Response desativarProgramaComIdInexistente(){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", 19987000)
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
    public Response desativarPrograma(ProgramaModel programa){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", programa.getIdPrograma())
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
    public Response deletarProgramaComIdInexistente(){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", 2552500)
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
    public Response deletarPrograma(ProgramaModel programa){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", programa.getIdPrograma())
                .when()
                        .delete("/programa/delete/{idPrograma}");
        return response;
    }
//    endregion
}