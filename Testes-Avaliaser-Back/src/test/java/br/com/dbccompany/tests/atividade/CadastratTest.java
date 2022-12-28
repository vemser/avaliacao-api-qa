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

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.apache.http.HttpStatus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Cadastro Tests")
@Feature("Atividade")
@DisplayName("Cadastro Atividade")
public class CadastratTest extends BaseTest {

    ModuloClient moduloClient = new ModuloClient();

    TrilhaClient trilhaClient = new TrilhaClient();

    AtividadeClient atividadeClient = new AtividadeClient();

    @Test
    @Story("Deve cadastrar com sucesso")
    public void testeDeveCadastrarNovaAtividade() {

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

        Assertions.assertEquals(cadastroAtividade.getTitulo(), novaAtividade.getTitulo());
        Assertions.assertEquals(cadastroAtividade.getPesoAtividade(), novaAtividade.getPesoAtividade());
        Assertions.assertTrue(cadastroAtividade.getDataCriacao().contains(novaAtividade.getDataCriacao().substring(0, 11)));
        Assertions.assertTrue(cadastroAtividade.getDataEntrega().contains(novaAtividade.getDataEntrega().substring(0, 11)));
    }

    @Test
    @Story("Deve retornar mensagem erro")
    public void testeDeveRetornarMensagemErroAoCadastrarAtividadeComModuloInexistente() {

        Trilha novatrilha =  TrilhaDataFactory.novaTrilha();
        TrilhaDTO trilhaCadastrada = trilhaClient.cadastrar(Utils.convertTrilhaToJson(novatrilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        Atividade novaAtividade = AtividadeDataFactory.novaAtividade(0, trilhaCadastrada);

        ResponseDTO cadastroAtividade = atividadeClient.cadastrar(0,
                        trilhaCadastrada.getIdTrilha(), Utils.converterAtividadeToJson(novaAtividade))
                .then()
                .extract().as(ResponseDTO.class);

        Assertions.assertEquals(cadastroAtividade.getStatus(), HttpStatus.SC_BAD_REQUEST);
        Assertions.assertEquals(cadastroAtividade.getMessage(), "Modulo não encontrado.");
    }

    @Test
    @Story("Deve retornar mensagem erro")
    public void testeDeveRetornarMensagemErroAoCadastrarAtividadeComTrilhaInexistente() {

        Modulo novoModulo = ModuloDataFactory.novoModulo();
        ModuloDTO moduloCadastrato = moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .extract().as(ModuloDTO.class);

        Atividade novaAtividade = AtividadeDataFactory.novaAtividade(moduloCadastrato.getIdModulo(), new TrilhaDTO());
        novaAtividade.setTrilha(null);

        ResponseDTO cadastroAtividade = atividadeClient.cadastrar(moduloCadastrato.getIdModulo(),
                        0, Utils.converterAtividadeToJson(novaAtividade))
                .then()
                .extract().as(ResponseDTO.class);

        Assertions.assertEquals(cadastroAtividade.getStatus(), HttpStatus.SC_BAD_REQUEST);
        Assertions.assertEquals(cadastroAtividade.getMessage(), "Trilha não encontrada.");
    }
}
