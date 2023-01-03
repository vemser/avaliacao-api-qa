package br.com.dbccompany.tests.trilha;

import br.com.dbccompany.client.TrilhaClient;
import br.com.dbccompany.data.factory.ProgramaDataFactory;
import br.com.dbccompany.data.factory.TrilhaDataFactory;
import br.com.dbccompany.dto.ProgramaDTO;
import br.com.dbccompany.dto.TrilhaDTO;
import br.com.dbccompany.model.Programa;
import br.com.dbccompany.model.Trilha;
import br.com.dbccompany.tests.base.BaseTest;
import br.com.dbccompany.utils.Utils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Cadastro Tests")
@Feature("Trilha")
@DisplayName("Cadastro Trilha")
public class CadastrarTest extends BaseTest {

    TrilhaClient trilhaClient = new TrilhaClient();

    @Test
    @Story("Deve cadastrar trilha com sucesso")
    public void testeDeveCadastrarTrilhaValidaComSucesso() {

        TrilhaDTO trilhaCadastrada = null;
        try {
            Trilha trilhaNova = TrilhaDataFactory.trilhaValida();
            trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilhaNova))
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(TrilhaDTO.class);

            Assertions.assertEquals(trilhaCadastrada.getNome(), trilhaNova.getNome());
            Assertions.assertEquals(trilhaCadastrada.getDescricao(), trilhaNova.getDescricao());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            trilhaClient.deletar(trilhaCadastrada.getIdTrilha())
                    .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        }
    }

    @Test
    @Story("Deve cadastrar trilha com sucesso")
    public void testeDeveCadastrarTrilhaComDescricaoVaziaComSucesso() {

        TrilhaDTO trilhaCadastrada = null;
        try {
            Trilha trilhaNova = TrilhaDataFactory.trilhaDescricaoVazio();
            trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilhaNova))
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(TrilhaDTO.class);

            Assertions.assertEquals(trilhaCadastrada.getNome(), trilhaNova.getNome());
            Assertions.assertEquals(trilhaCadastrada.getDescricao(), trilhaNova.getDescricao());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            trilhaClient.deletar(trilhaCadastrada.getIdTrilha())
                    .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        }
    }

    @Test
    @Story("Deve retornar erro ao cadastrar trilha")
    public void testeDeveRetornarMensagemErroPadraoAoCadastrarTrilhaSemNome() {


        Trilha trilhaNova = TrilhaDataFactory.trilhaComNomeVazio();
        trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilhaNova))
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
