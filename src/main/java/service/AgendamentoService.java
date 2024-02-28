package service;

import io.restassured.response.Response;
import model.AgendamentoModel;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class AgendamentoService {
    //region CADASTRAR AGENDAMENTO
    public Response cadastraragendamento(AgendamentoModel corpo) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(corpo)
                        .when()
                        .post("/agendamento/create");
    }

    //endregion
//region ATUALIZAR AGENDAMENTO
    public Response atualizarAgendamento(AgendamentoModel agendamentoAntigo, AgendamentoModel agendamentoNovo) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(agendamentoAntigo)
                        .pathParam("idAgendamento", agendamentoNovo.getIdAgendamento())
                        .when()
                        .put("/agendamento/update/{idAgendamento}");
    }

    //endregion
//region LISTAR AGENDAMENTOS
    public Response buscarAgendamentoPorPaginas(Integer pagina, Integer tamanho) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .queryParam("pagina", pagina)
                        .queryParam("tamanho", tamanho)
                        .when()
                        .get("/agendamento/list-all");
    }

    //endregion
//region BUSCAR AGENDAMENTO PELO ID
    public Response buscarAgendamentoPorIdDoAgendamento(AgendamentoModel idAgendamento) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAgendamento", idAgendamento.getIdAgendamento())
                        .when()
                        .get("/agendamento/get-by-id/{idAgendamento}");
    }

    public Response buscarAgendamentoPorIdDoAcompanhamento(Integer idAcompanhamento) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", idAcompanhamento)
                        .when()
                        .get("/agendamento/list-all-available-horarios/{idAcompanhamento}");
    }

    //endregion
//region LISTAR HORARIOS DE AGENDAMENTO DISPONIVEL
    public Response listarhorariosDispiniveis(Integer idAcompanhamento) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAcompanhamento", idAcompanhamento)
                        .when()
                        .get("/agendamento/list-all-available-horarios/{idAcompanhamento}");
    }

    //endregion
//region DELETAR AGENDAMENTO
    public Response deletaragendamento(AgendamentoModel idAgendamento) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAgendamento", idAgendamento.getIdAgendamento())
                        .when()
                        .delete("/agendamento/delete/{idAgendamento}");
    }
//endregion
}
