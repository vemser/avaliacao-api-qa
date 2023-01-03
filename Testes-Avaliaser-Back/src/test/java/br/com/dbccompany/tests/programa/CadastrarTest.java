package br.com.dbccompany.tests.programa;

import br.com.dbccompany.client.ProgramaClient;
import br.com.dbccompany.data.factory.ProgramaDataFactory;
import br.com.dbccompany.dto.ProgramaDTO;
import br.com.dbccompany.model.Programa;
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
@Feature("Programa")
@DisplayName("Cadastro Programa")
public class CadastrarTest extends BaseTest {

    ProgramaClient programaClient = new ProgramaClient();

    @Test
    @Story("Deve cadastrar usuário com sucesso")
    public void testeDeveCadastrarProgramaValidoComSucesso() {

        ProgramaDTO programaCadastrado = null;
        try {
            Programa programaNovo = ProgramaDataFactory.programaValido();
            programaCadastrado = programaClient.cadastrar(Utils.convertProgramaToJson(programaNovo))
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(ProgramaDTO.class);

            Assertions.assertEquals(programaNovo.getSituacao(), programaCadastrado.getSituacao());
            Assertions.assertEquals(programaNovo.getDescricao(), programaCadastrado.getDescricao());
            Assertions.assertEquals(programaNovo.getNome(), programaCadastrado.getNome());
            Assertions.assertEquals(programaNovo.getDataInicio(), programaCadastrado.getDataInicio());
            Assertions.assertEquals(programaNovo.getDataFim(), programaCadastrado.getDataFim());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            programaClient.deletar(programaCadastrado.getIdPrograma())
                    .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        }
    }

    @Test
    @Story("Deve retonar erro ao cadastrar usuário")
    public void testeDeveRetornarErroPadraoAoCadastrarProgramaComDataInicioMaiorQueDataFim() {

        Programa programaDataInicioMaiorQueDataFim = ProgramaDataFactory.programaDataInicioMaiorQueDataFim();
        programaClient.cadastrar(Utils.convertProgramaToJson(programaDataInicioMaiorQueDataFim))
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    @Story("Deve retonar erro ao cadastrar usuário")
    public void testeDeveRetornarErroPadraoAoCadastrarProgramaComDataInicioNull() {

        Programa programaDataInicioNull = ProgramaDataFactory.programaDataInicioNull();
        programaClient.cadastrar(Utils.convertProgramaToJson(programaDataInicioNull))
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    @Story("Deve retonar erro ao cadastrar usuário")
    public void testeDeveRetornarErroPadraoAoCadastrarProgramaComDataFimNull() {

        Programa programaDataFimNull = ProgramaDataFactory.programaDataFimNull();
        programaClient.cadastrar(Utils.convertProgramaToJson(programaDataFimNull))
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    @Story("Deve retonar erro ao cadastrar usuário")
    public void testeDeveRetornarErroPadraoAoCadastrarProgramaComNomeNull() {

        Programa programaNomeNull = ProgramaDataFactory.programaNomeNull();
        programaClient.cadastrar(Utils.convertProgramaToJson(programaNomeNull))
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }
}
