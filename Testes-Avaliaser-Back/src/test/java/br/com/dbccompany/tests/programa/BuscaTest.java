package br.com.dbccompany.tests.programa;

import br.com.dbccompany.client.ProgramaClient;
import br.com.dbccompany.data.factory.ProgramaDataFactory;
import br.com.dbccompany.dto.PaginacaoDTO;
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

@Epic("Busca Tests")
@Feature("Programa")
@DisplayName("Busca Programa")
public class BuscaTest extends BaseTest {

    ProgramaClient programaClient = new ProgramaClient();

    @Test
    @Story("Deve buscar programa com sucesso")
    public void testeDeveListarProgramasComSucesso() {


        ProgramaDTO programaCadastrado = null;
        try {
            Programa programaNovo = ProgramaDataFactory.programaValido();
            programaCadastrado = programaClient.cadastrar(Utils.convertProgramaToJson(programaNovo))
                    .then()
                    .log().all()
                    .extract().as(ProgramaDTO.class);

            PaginacaoDTO listaDeProgramas = programaClient.listarProgramas(0, 1)
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(PaginacaoDTO.class);

            Assertions.assertTrue(listaDeProgramas.getElementos().size() == 1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            programaClient.deletar(programaCadastrado.getIdPrograma())
                    .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        }
    }

    @Test
    @Story("Deve retornar mensagem de erro padrao")
    public void testeDeveRetonarMensagemDeErroPadraoAoListarComSizeMenorQueZero() {

        programaClient.listarProgramas(0, -1)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    @Story("Deve retornar mensagem de erro padrao")
    public void testeDeveRetonarMensagemDeErroPadraoAoListarComPageMenorQueZero() {

        programaClient.listarProgramas(-1, 0)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    @Story("Deve buscar programa com sucesso")
    public void testeDeveListarProgramasPorNomeComSucesso() {


        ProgramaDTO programaCadastrado = null;
        try {
            Programa programaNovo = ProgramaDataFactory.programaValido();
            programaCadastrado = programaClient.cadastrar(Utils.convertProgramaToJson(programaNovo))
                    .then()
                    .log().all()
                    .extract().as(ProgramaDTO.class);

            PaginacaoDTO listaDeProgramasPorNome = programaClient.listarProgramasPorNome(0, 1, programaCadastrado.getNome())
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(PaginacaoDTO.class);

            Assertions.assertTrue(listaDeProgramasPorNome.getElementos().size() == 1);
            Assertions.assertEquals(listaDeProgramasPorNome.getElementos().get(0).getNome(), programaCadastrado.getNome());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            programaClient.deletar(programaCadastrado.getIdPrograma())
                    .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        }
    }

    @Test
    @Story("Deve buscar programa com sucesso")
    public void testeDeveBuscarProgramaPorIdExistenteComSucesso() {


        ProgramaDTO programaCadastrado = null;
        try {
            Programa programaNovo = ProgramaDataFactory.programaValido();
            programaCadastrado = programaClient.cadastrar(Utils.convertProgramaToJson(programaNovo))
                    .then()
                    .log().all()
                    .extract().as(ProgramaDTO.class);

            ProgramaDTO programaBuscado = programaClient.buscarProgramaPorId(programaCadastrado.getIdPrograma())
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(ProgramaDTO.class);

            Assertions.assertEquals(programaBuscado.getIdPrograma(), programaCadastrado.getIdPrograma());
            Assertions.assertEquals(programaBuscado.getNome(), programaCadastrado.getNome());
            Assertions.assertEquals(programaBuscado.getDescricao(), programaCadastrado.getDescricao());
            Assertions.assertEquals(programaBuscado.getSituacao(), programaCadastrado.getSituacao());
            Assertions.assertEquals(programaBuscado.getDataInicio(), programaCadastrado.getDataInicio());
            Assertions.assertEquals(programaBuscado.getDataFim(), programaCadastrado.getDataFim());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            programaClient.deletar(programaCadastrado.getIdPrograma())
                    .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        }
    }

    @Test
    @Story("Deve retornar mensagem de erro padrao")
    public void testeDeveRetornarMensagemErroPadraoAoBuscarPorIdProgramaNegativo() {

        programaClient.buscarProgramaPorId(Utils.faker.number().negative())
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
