package service;

import io.restassured.response.Response;
import model.ProgramaModel;
import specs.SetupsRequestSpecification;
import static io.restassured.RestAssured.given;
public class ProgramaService {

//    region CRIAR PROGRAMA
    public Response criarPrograma(ProgramaModel programa) {
        return
            given()
                    .spec(SetupsRequestSpecification.requestSpecification())
                    .body(programa)
                .when()
                    .post("/programa/create");
    }

//    endregion
//    region LISTAR PROGRAMAS

    public Response buscarProgramas(Integer pagina, Integer tamanho){
        return
                given()
                .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina", pagina)
                        .queryParam("tamanho", tamanho)
                        .when()
                        .get("/programa/list-all");
    }
//endregion
//region LISTAR PROGRAMAS COM TRILHA

    public Response buscarProgramasComTrilha(Integer pagina, Integer tamanho){
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina", pagina)
                        .queryParam("tamanho", tamanho)
                        .when()
                        .get("/programa/list-with-trilhas");
    }
//endregion
//    region BUSCAR PROGRAMA PELO ID

    public Response buscarPrograma(ProgramaModel programa){
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", programa.getIdPrograma())
                .when()
                        .get("/programa/get-by-id/{idPrograma}");
    }
    //    endregion
//region BUSCAR PROGRAMA ABERTO
    public Response buscarProgramaAberto(){
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
            .when()
                    .get("/programa/get-open");
    }
//endregion
//    region ATUALIZAR PROGRAMA
    public Response atualizarPrograma(ProgramaModel programaExistente, ProgramaModel programaNovo){
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(programaNovo)
                .pathParam("idPrograma", programaExistente.getIdPrograma())
            .when()
                .put("/programa/update/{idPrograma}");
    }
//    endregion
//    region DESATIVAR PROGRAMA
    public Response desativarProgramaComLetrasNoId(){
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", "saxsaxaxasxsaa")
            .when()
                .delete("/programa/deactivate/{idPrograma}");
    }
    public Response desativarProgramaComIdInexistente(){
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", 19987000)
            .when()
                .delete("/programa/deactivate/{idPrograma}");
    }
    public Response desativarPrograma(){
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", 19)
            .when()
                .delete("/programa/deactivate/{idPrograma}");
    }
    public Response desativarPrograma(ProgramaModel programa){
        return
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idPrograma", programa.getIdPrograma())
            .when()
                .delete("/programa/deactivate/{idPrograma}");
    }
//    endregion
//    region DELETAR PROGRAMA

    public Response deletarPrograma(){
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", 45)
                .when()
                        .delete("/programa/delete/{idPrograma}");
    }
    public Response deletarPrograma(Integer programa){
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", programa)
                .when()
                        .delete("/programa/delete/{idPrograma}");
    }
//    endregion
}