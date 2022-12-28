package br.com.vemrankser.pages;

import br.com.vemrankser.browserHandler.Elements;
import org.openqa.selenium.By;

public class BasePage extends Elements {

    public static void click(By by) {

        waitElement(by);
        element(by).click();
    }

    public static void sendKeys(By by, String texto) {

        waitElement(by);
        element(by).clear();
        element(by).sendKeys(texto);
    }

    public static String getText(By by) {

        waitElement(by);
        return element(by).getText();
    }

    public static String getInnerText(By by) {

        waitElement(by);
        return element(by).getAttribute("innerText");
    }


    public static boolean isSelected(By by) {

        waitElement(by);
        return element(by).isSelected();
    }
}