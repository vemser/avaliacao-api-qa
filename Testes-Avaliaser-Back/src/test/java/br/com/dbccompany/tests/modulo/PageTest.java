package br.com.dbccompany.tests.modulo;

import br.com.dbccompany.client.ModuloClient;
import br.com.dbccompany.client.PageClient;
import br.com.dbccompany.data.factory.ModuloDataFactory;
import br.com.dbccompany.dto.ModuloDTO;
import br.com.dbccompany.model.Modulo;
import br.com.dbccompany.tests.base.BaseTest;
import br.com.dbccompany.utils.Utils;

import io.qameta.allure.Story;

import org.apache.http.HttpStatus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PageTest extends BaseTest {

    ModuloClient moduloClient = new ModuloClient();

    PageClient pageClient = new PageClient();

    @Test
    @Story("Deve listar os elementos com sucesso")
    public void testeDeveListarTodosOsModulos() {

        Modulo novoModulo = ModuloDataFactory.novoModulo();
        moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo));

        ModuloDTO[] lista =  pageClient.listaTodosModulos()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(ModuloDTO[].class);

        Assertions.assertNotNull(lista);
    }

    @Test
    @Story("Deve listar os elementos com sucesso")
    public void testeDeveListarModuloPorId() {

        Modulo novoModulo = ModuloDataFactory.novoModulo();
        ModuloDTO moduloCadastrado = moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .extract().as(ModuloDTO.class);

        ModuloDTO moduloBuscado =  pageClient.buscaModuloPorId(moduloCadastrado.getIdModulo())
                .then()
                .extract().as(ModuloDTO.class);

        Assertions.assertEquals(moduloBuscado.getNome(), moduloCadastrado.getNome());
        Assertions.assertEquals(moduloBuscado.getIdModulo(), moduloCadastrado.getIdModulo());
        Assertions.assertEquals(moduloBuscado.getDataInicio(), moduloCadastrado.getDataInicio().substring(0, 19));
        Assertions.assertEquals(moduloBuscado.getDataFim(), moduloCadastrado.getDataFim().substring(0, 19));
    }

    @Test
    @Story("Deve retornar mensagem de erro")
    public void testeDeveRetornarMensagemDeErroAoBuscarIdModuloComNumeroFloat() {

        pageClient.buscaModuloPorIdNumeroFloat(Utils.faker.random().nextFloat())
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                ;
    }
}
