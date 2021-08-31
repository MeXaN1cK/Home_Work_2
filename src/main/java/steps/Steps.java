package steps;

import helpers.AllureEdit;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import pages.*;

public class Steps {
    private final WebDriver driver;

    public Steps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перейти на страницу {url}")
    public void goPage(String url) {
        driver.get(url);
        AllureEdit.setLastStepName("Перейти на страницу " + driver.getTitle());
    }

    @Step("Произвести поиск по запросу")
    public void find(String request, FindPage page) {
        page.getFindField().sendKeys(request);
        page.getFindButton().click();
    }
}
