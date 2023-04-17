package service;

import io.restassured.response.Response;
import model.AgendamentoModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class AgendamentoService {
//region CADASTRAR AGENDAMENTO
    public Response cadastraragendamento(AgendamentoModel corpo){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(corpo)
                .when()
            .post("/agendamento/create");
        return response;
    }
    public Response deletaragendamento(AgendamentoModel idAgendamento){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idAgendamento", idAgendamento.getIdAgendamento())
            .when()
                .delete("/agendamento/delete/{idAgendamento}");
        return response;
    }
//endregion
//region ATUALIZAR AGENDAMENTO
    public Response atualizarAgendamento(AgendamentoModel agendamentoAntigo, AgendamentoModel agendamentoNovo) {
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .body(agendamentoAntigo)
                .pathParam("idAgendamento", agendamentoNovo.getIdAgendamento())
            .when()
                .post("/agendamento/update/{idAgendamento}");
        return response;
    }
//endregion
    public Response buscarAgendamentoPorPaginas(Integer pagina, Integer tamanho) {
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .queryParam("pagina",pagina)
                .queryParam("tamanho",tamanho)
            .when()
                .get("/agendamento/list-all");
        return response;
    }


    public Response buscarAgendamentoPorIdDoAgendamento(AgendamentoModel idAgendamento){
        Response response =
            given()
                .spec(SetupsRequestSpecification.requestSpecification())
                .pathParam("idAgendamento", idAgendamento.getIdAgendamento())
            .when()
                .get("/agendamento/get-by-id/{idAgendamento}");
        return response;
    }
    public Response buscarAgendamentoPorIdDoAcompanhamento(AgendamentoModel idAcompanhamento){
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", idAcompanhamento.getIdAcompanhamento())
                        .when()
                        .get("/agendamento/list-all-available-horarios/{idAcompanhamento}");
        return response;
    }
}
