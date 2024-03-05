package service;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import model.AcompanhamentoModel;
import model.AusenciaModel;
import specs.ImageRequestSpec;
import specs.SetupsRequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class AusenciaService {

    public static ValidatableResponse criarAusencia(AusenciaModel ausencia) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(ausencia)
                        .when()
                        .post("/ausencias/create")
                        .then();
    }

    public static ValidatableResponse atualizarAusencia(Integer id, AusenciaModel ausencia) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .body(ausencia)
                        .pathParam("id", id)
                        .when()
                        .put("/ausencias/update/{id}")
                        .then();
    }

    public static ValidatableResponse atualizarImagem(Integer id, File imagem, String tipoImagem) {
        return
                given()
                        .spec(ImageRequestSpec.requestSpecification())
                        .multiPart("file", imagem, tipoImagem)
                        .queryParam("idAusencia", id)
                        .when()
                        .put("/ausencias/upload")
                        .then();
    }

    public static ValidatableResponse atualizarLink(Integer id, String link) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("id", id)
                        .queryParam("link", link)
                        .when()
                        .put("/ausencias/update-link/{id}")
                        .then();
    }

    public static ValidatableResponse deletarAusencia(Integer idAusencia) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("id", idAusencia)
                        .when()
                        .delete("/ausencias/delete/{id}")
                        .then();
    }

    public static ValidatableResponse buscarImagemPorId(Integer id) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idAusencia", id)
                        .when()
                        .get("/ausencias/imagem/{idAusencia}")
                        .then();
    }

    public static ValidatableResponse buscarAusenciaPorId(Integer id) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("id", id)
                        .when()
                        .get("/ausencias/find-by-id-ausencia/{id}")
                        .then();
    }

    public static ValidatableResponse buscarAusenciaPorIdEstagiario(Integer id, Integer page, Integer size) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("idEstagiario", id)
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .when()
                        .get("/ausencias/find-all-by-id-estagiario/{idEstagiario}")
                        .then();
    }

    public static ValidatableResponse buscarTodasAusencias(Integer page, Integer size) {
        return
                given()
                        .spec(SetupsRequestSpecification.requestSpecification())
                        .pathParam("page", page)
                        .pathParam("size", size)
                        .when()
                        .get("/ausencias/find-all-ausencias/{page}/{size}")
                        .then();
    }
}
