package br.com.dbccompany.tests.modulo;

import br.com.dbccompany.client.ModuloClient;
import br.com.dbccompany.client.TrilhaClient;
import br.com.dbccompany.data.factory.ModuloDataFactory;
import br.com.dbccompany.data.factory.TrilhaDataFactory;
import br.com.dbccompany.dto.ModuloDTO;
import br.com.dbccompany.dto.TrilhaDTO;
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
@Feature("Modulo")
@DisplayName("Cadastro Modulo")
public class CadastrarTest extends BaseTest {

    ModuloClient moduloClient = new ModuloClient();

    TrilhaClient trilhaClient = new TrilhaClient();


    @Test
    @Story("Deve cadastrar com sucesso")
    public void testeDeveCadastrarModuloComSucesso() {

        Modulo novoModulo = ModuloDataFactory.novoModulo();

        ModuloDTO moduloCadastrado = moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(ModuloDTO.class)
        ;

        String dataInicio = moduloCadastrado.getDataInicio();
        String dataFim = moduloCadastrado.getDataFim();

        Assertions.assertEquals(moduloCadastrado.getNome(), novoModulo.getNome());
        Assertions.assertTrue(novoModulo.getDataInicio().contains(dataInicio.substring(0, 11)));
        Assertions.assertTrue(novoModulo.getDataFim().contains(dataFim.substring(0, 11)));
    }

    @Test
    @Story("Deve retornar mensagem errro.")
    public void testeDeveRetornarErroAoCadastrarModuloComDataNoFormatoErrado() {

        Modulo novoModulo = ModuloDataFactory.moduloDataFormatoErrado();

        moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                ;
    }

    @Test
    @Story("Deve retornar mensagem errro.")
    public void testeDeveRetornarErroAoCadastrarModuloSemNome() {

        Modulo novoModulo = ModuloDataFactory.moduloSemNome();

        moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
        ;
    }

    @Test
    @Story("Deve cadastrar com sucesso.")
    public void testeDeveVincularModuloATrilha() {

        Modulo novoModulo = ModuloDataFactory.novoModulo();
        Trilha trilha =  TrilhaDataFactory.novaTrilha();

        ModuloDTO moduloCadastrado = moduloClient.cadastrar(Utils.convertModuloToJson(novoModulo))
                .then()
                .extract().as(ModuloDTO.class)
                ;

        TrilhaDTO trilhaCadastrado = trilhaClient.cadastrar(Utils.convertTrilhaToJson(trilha))
                .then()
                .extract().as(TrilhaDTO.class)
                ;

        Modulo vinculacaoModuloTrilha = ModuloDataFactory.vincularModuloComTrilha(moduloCadastrado.getIdModulo(), trilhaCadastrado.getIdTrilha());

        ModuloDTO responseServer = moduloClient.vincularModuloETrilha
                        (moduloCadastrado.getIdModulo(), trilhaCadastrado.getIdTrilha(), Utils.convertModuloToJson(vinculacaoModuloTrilha))
                .then()
                .extract().as(ModuloDTO.class);

        Assertions.assertEquals(responseServer.getNome(), moduloCadastrado.getNome());
        Assertions.assertEquals(responseServer.getIdModulo(), moduloCadastrado.getIdModulo());
    }

    @Test
    @Story("Deve retornar mensagem errro.")
    public void testeDeveRetornarMensagemErroAoTentarVincularModuloComIdNegativoAUmaTrilhaComIdNegativo() {

        Integer idModuloNegativo = Utils.faker.number().negative();
        Integer idTrilhaNegativo = Utils.faker.number().negative();

        Modulo vinculacaoModuloTrilha = ModuloDataFactory.vincularModuloComTrilha(idModuloNegativo, idTrilhaNegativo);

        moduloClient.vincularModuloETrilha
                        (idModuloNegativo, idTrilhaNegativo, Utils.convertModuloToJson(vinculacaoModuloTrilha))
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
        ;
    }
}
