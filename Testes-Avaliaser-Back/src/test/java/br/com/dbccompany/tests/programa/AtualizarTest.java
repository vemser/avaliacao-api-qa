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
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Atualizar Tests")
@Feature("Programa")
@DisplayName("Atualizar Programa")
public class AtualizarTest extends BaseTest {

    ProgramaClient programaClient = new ProgramaClient();

    @Test
    @Story("Deve atualizar usuário com sucesso")
    public void testeDeveAtualizarNomeDoProgramaComSucesso() {


        ProgramaDTO programaCadastrado = null;
        try {
            Programa programaNovo = ProgramaDataFactory.programaValido();
            programaCadastrado = programaClient.cadastrar(Utils.convertProgramaToJson(programaNovo))
                    .then()
                    .log().all()
                    .extract().as(ProgramaDTO.class);

            ProgramaDataFactory.modificarNomePrograma(programaNovo, "VemSer 12ed");
            ProgramaDTO programaAtualizado = programaClient.atualizar(Utils.convertProgramaToJson(programaNovo), programaCadastrado.getIdPrograma())
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(ProgramaDTO.class);

            Assertions.assertEquals(programaNovo.getNome(), programaAtualizado.getNome());
        } catch (Exception e) {
            e.printStackTrace();
        }

        programaClient.deletar(programaCadastrado.getIdPrograma())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Story("Deve atualizar usuário com sucesso")
    public void testeDeveAtualizarSituacaoDoProgramaComSucesso() {


        ProgramaDTO programaCadastrado = null;
        try {
            Programa programaNovo = ProgramaDataFactory.programaValido();
            programaCadastrado = programaClient.cadastrar(Utils.convertProgramaToJson(programaNovo))
                    .then()
                    .log().all()
                    .extract().as(ProgramaDTO.class);

            ProgramaDataFactory.modificarSituacaoPrograma(programaNovo, "FECHADO");
            ProgramaDTO programaAtualizado = programaClient.atualizar(Utils.convertProgramaToJson(programaNovo), programaCadastrado.getIdPrograma())
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(ProgramaDTO.class);

            Assertions.assertEquals(programaNovo.getSituacao(), programaAtualizado.getSituacao());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            programaClient.deletar(programaCadastrado.getIdPrograma())
                    .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        }
    }

    @Test
    @Story("Deve atualizar usuário com sucesso")
    public void testeDeveAtualizarDescricaoDoProgramaComSucesso() {

        ProgramaDTO programaCadastrado = null;
        try {
            Programa programaNovo = ProgramaDataFactory.programaValido();
            programaCadastrado = programaClient.cadastrar(Utils.convertProgramaToJson(programaNovo))
                    .then()
                    .log().all()
                    .extract().as(ProgramaDTO.class);

            ProgramaDataFactory.modificarDescricaoPrograma(programaNovo, "Descricao Nova");
            ProgramaDTO programaAtualizado = programaClient.atualizar(Utils.convertProgramaToJson(programaNovo), programaCadastrado.getIdPrograma())
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(ProgramaDTO.class);
            Assertions.assertEquals(programaNovo.getDescricao(), programaAtualizado.getDescricao());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            programaClient.deletar(programaCadastrado.getIdPrograma())
                    .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        }
    }

    @Test
    @Story("Deve atualizar usuário com sucesso")
    public void testeDeveAtualizarDataInicioDoProgramaComSucesso() {


        ProgramaDTO programaCadastrado = null;
        try {
            Programa programaNovo = ProgramaDataFactory.programaValido();
            programaCadastrado = programaClient.cadastrar(Utils.convertProgramaToJson(programaNovo))
                    .then()
                    .log().all()
                    .extract().as(ProgramaDTO.class);

            ProgramaDataFactory.modificarDataInicioPrograma(programaNovo, 2);
            ProgramaDTO programaAtualizado = programaClient.atualizar(Utils.convertProgramaToJson(programaNovo), programaCadastrado.getIdPrograma())
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(ProgramaDTO.class);

            Assertions.assertEquals(programaNovo.getDataInicio(), programaAtualizado.getDataInicio());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            programaClient.deletar(programaCadastrado.getIdPrograma())
                    .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        }
    }

    @Test
    @Story("Deve atualizar usuário com sucesso")
    public void testeDeveAtualizarDataFimDoProgramaComSucesso() {


        ProgramaDTO programaCadastrado = null;
        try {
            Programa programaNovo = ProgramaDataFactory.programaValido();
            programaCadastrado = programaClient.cadastrar(Utils.convertProgramaToJson(programaNovo))
                    .then()
                    .log().all()
                    .extract().as(ProgramaDTO.class);

            ProgramaDataFactory.modificarDataFimPrograma(programaNovo, 2);
            ProgramaDTO programaAtualizado = programaClient.atualizar(Utils.convertProgramaToJson(programaNovo), programaCadastrado.getIdPrograma())
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(ProgramaDTO.class);

            Assertions.assertEquals(programaNovo.getDataFim(), programaAtualizado.getDataFim());
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
    public void testeDeveRetornarMensagemDeErroAoAtualizarDataInicioComMesMaiorQueDoze() {

        ProgramaDTO programaCadastrado = null;
        try {
            Programa programaNovo = ProgramaDataFactory.programaValido();
            programaCadastrado = programaClient.cadastrar(Utils.convertProgramaToJson(programaNovo))
                    .then()
                    .log().all()
                    .extract().as(ProgramaDTO.class);

            ProgramaDataFactory.modificarProgramaDataInicioMesMaiorQueDoze(programaNovo);
            programaClient.atualizar(Utils.convertProgramaToJson(programaNovo), programaCadastrado.getIdPrograma())
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);

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
    public void testeDeveRetornarMensagemDeErroAoAtualizarDataInicioMaiorQueDataFim() {


        ProgramaDTO programaCadastrado = null;
        try {

            Programa programaNovo = ProgramaDataFactory.programaValido();
            programaCadastrado = programaClient.cadastrar(Utils.convertProgramaToJson(programaNovo))
                    .then()
                    .log().all()
                    .extract().as(ProgramaDTO.class);
            ProgramaDataFactory.modificarDataInicioPrograma(programaNovo, 4);

            programaClient.atualizar(Utils.convertProgramaToJson(programaNovo), programaCadastrado.getIdPrograma())
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
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
    public void testeDeveRetornarMensagemDeErroAoAtualizarNomeProgramaParaVazio() {


        ProgramaDTO programaCadastrado = null;
        try {

            Programa programaNovo = ProgramaDataFactory.programaValido();
            programaCadastrado = programaClient.cadastrar(Utils.convertProgramaToJson(programaNovo))
                    .then()
                    .log().all()
                    .extract().as(ProgramaDTO.class);

            ProgramaDataFactory.modificarNomePrograma(programaNovo, StringUtils.EMPTY);
            programaClient.atualizar(Utils.convertProgramaToJson(programaNovo), programaCadastrado.getIdPrograma())
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            programaClient.deletar(programaCadastrado.getIdPrograma())
                    .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        }
    }
}
