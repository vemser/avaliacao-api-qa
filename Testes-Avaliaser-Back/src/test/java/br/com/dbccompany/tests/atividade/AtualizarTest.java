package br.com.dbccompany.tests.atividade;

import br.com.dbccompany.client.AtividadeClient;
import br.com.dbccompany.client.ModuloClient;
import br.com.dbccompany.client.TrilhaClient;
import br.com.dbccompany.data.factory.AtividadeDataFactory;
import br.com.dbccompany.data.factory.ModuloDataFactory;
import br.com.dbccompany.data.factory.TrilhaDataFactory;
import br.com.dbccompany.dto.AtividadeDTO;
import br.com.dbccompany.dto.ModuloDTO;
import br.com.dbccompany.dto.ResponseDTO;
import br.com.dbccompany.dto.TrilhaDTO;
import br.com.dbccompany.model.Atividade;
import br.com.dbccompany.model.Modulo;
import br.com.dbccompany.model.Trilha;
import br.com.dbccompany.tests.base.BaseTest;
import br.com.dbccompany.utils.Utils;

import io.qameta.allure.Story;

import org.apache.http.HttpStatus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AtualizarTest extends BaseTest {

    AtividadeClient atividadeClient = new AtividadeClient();

    ModuloClient moduloClient = new ModuloClient();

    TrilhaClient trilhaClient = new TrilhaClient();

    @Test
    @Story("Deve atualizar com sucesso")
    public void testeDeveEntregarAtividadeDoAlunoComSucesso() {

        Modulo novoModulo = ModuloDataFactory.novoModulo();
        ModuloDTO moduloCadastrato = moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .extract().as(ModuloDTO.class);

        Trilha novatrilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(novatrilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        Atividade novaAtividade = AtividadeDataFactory.novaAtividade(
                moduloCadastrato.getIdModulo(), trilhaCadastrada);

        AtividadeDTO cadastroAtividade = atividadeClient.cadastrar(moduloCadastrato.getIdModulo(),
                        trilhaCadastrada.getIdTrilha(), Utils.converterAtividadeToJson(novaAtividade))
                .then()
                .extract().as(AtividadeDTO.class);

        AtividadeDTO atividadeBuscada = atividadeClient.entregarAtividade(cadastroAtividade.getIdAtividade())
                .then()
                .extract().as(AtividadeDTO.class);

        Assertions.assertEquals(cadastroAtividade.getPesoAtividade(), atividadeBuscada.getPesoAtividade());
        Assertions.assertEquals(cadastroAtividade.getTitulo(), atividadeBuscada.getTitulo());
        Assertions.assertEquals(cadastroAtividade.getNomeInstrutor(), atividadeBuscada.getNomeInstrutor());
    }

    @Test
    @Story("Deve retornar msg de erro.")
    public void testeDeveRetornarErroAoEntregarAtividadeComIdNegativo() {


        ResponseDTO atividadeBuscada = atividadeClient.entregarAtividade(Utils.faker.number().negative())
                .then()
                .log().all()
                .extract().as(ResponseDTO.class);

        Assertions.assertEquals(atividadeBuscada.getStatus(), HttpStatus.SC_BAD_REQUEST);
        Assertions.assertEquals(atividadeBuscada.getMessage(), "Atividade não encontrada.");
    }
}
