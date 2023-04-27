package service;

import io.restassured.response.Response;
import model.ProgramaModel;
import specs.SetupsRequestSpecification;
import static io.restassured.RestAssured.given;
public class ProgramaService {

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

//    endregion
//    region LISTAR PROGRAMAS

    public Response buscarProgramas(Integer pagina, Integer tamanho){
        Response response =
                given()
                .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina", pagina)
                        .queryParam("tamanho", tamanho)
                        .when()
                        .get("/programa/list-all");
        return response;
    }
//endregion
//region LISTAR PROGRAMAS COM TRILHA

    public Response buscarProgramasComTrilha(Integer pagina, Integer tamanho){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina", pagina)
                        .queryParam("tamanho", tamanho)
                        .when()
                        .get("/programa/list-with-trilhas");
        return response;
    }
//endregion
//    region BUSCAR PROGRAMA PELO ID

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
//region BUSCAR PROGRAMA ABERTO
    public Response buscarProgramaAberto(){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
            .when()
                    .get("/programa/get-open");
        return response;
    }
//endregion
//    region ATUALIZAR PROGRAMA
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

    public Response deletarPrograma(){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", 45)
                .when()
                        .delete("/programa/delete/{idPrograma}");
        return response;
    }
    public Response deletarPrograma(Integer programa){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", programa)
                .when()
                        .delete("/programa/delete/{idPrograma}");
        return response;
    }
//    endregion
}