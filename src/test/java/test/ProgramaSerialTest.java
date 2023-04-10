package test;

import base.BaseTest;
import io.restassured.response.Response;
import model.ProgramaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class ProgramaSerialTest extends BaseTest {
    @Test
    public void testSerializar(){
        //Criar um programaModel com dados válidos e serializar
        ProgramaModel programaModel = new ProgramaModel("Programa KTeste", "Programa de teste", "2023-05-01", "2023-08-01", "ABERTO");
        //Requisição para criar um programa
        Response response =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(programaModel)
                        .when()
                        .post("/programa/create");
        //Validar o status code
        response.then().statusCode(HttpStatus.SC_CREATED);
        //Deserializar a resposta
        ProgramaModel programaModelResponse = response.as(ProgramaModel.class);
        //Validar se o nome do programa é igual ao nome do programaModel
        Assertions.assertEquals(programaModel.getNome(), programaModelResponse.getNome());
        System.out.println(programaModelResponse);
        //Deletar o programa
        Response responseDel =
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idPrograma", programaModelResponse.getIdPrograma())
                        .when()
                        .delete("/programa/delete/{idPrograma}");
        responseDel.then().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
