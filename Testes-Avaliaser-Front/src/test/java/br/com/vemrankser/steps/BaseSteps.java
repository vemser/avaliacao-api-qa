package br.com.vemrankser.steps;

import br.com.vemrankser.browserHandler.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseSteps extends Browser {

    @BeforeMethod
    public void abrirNavegador() {
        browserUp("http://vemser-dbc.dbccompany.com.br:39000/vemser/vemrankser-front");
    }

    @AfterMethod
    public void fecharNavegador() {
        browserDown();
    }
}