package steps;

import helpers.AllureEdit;
import helpers.CustomUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import pages.*;

import java.util.List;
import java.util.Map;

public class Steps {
    private final WebDriver driver;

    public Steps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Произвести поиск в гугл по запросу {request}")
    public void findGoogle(String request) {
        driver.get("https://www.google.ru/search?q=" + request);
    }

    @Step("Перейти на страницу {url}")
    public void goPage(String url) {
        driver.get(url);
        //AllureEdit.setLastStepName("Перейти на страницу " + driver.getTitle());
    }

    @Step("Произвести поиск по запросу")
    public void find(String request, FindPage page) {
        page.getFindField().sendKeys(request);
        page.getFindButton().click();
    }

    @Step("Шаг 1. Проверка наличия имени: {name}")
    public void checkContainsName(List<Map<String, Object>> resultSearch, String name, WebDriver driver) {
        CustomUtils.getScreen(driver);
        Assertions.assertTrue(resultSearch.stream().anyMatch(x -> x.get("NAME_PAGE").toString().contains(name)));
    }

    @Step("Шаг 2. Перейдём по ссылке с текстом {textTitle}")
    public void goPageText(GooglePageWithSearch googleWithSearch, String textTitle) {
        Assertions.assertTrue(googleWithSearch.goPage(textTitle), "Страница " + textTitle + " не найдена");
        CustomUtils.print("Текущая страница" + driver.getTitle());
    }

    @Step("Шаг 3. Проверка курса {moneyType}")
    public void checkCourse(String moneyType, OpenPage openPage) {
        CustomUtils.getScreen(openPage.getDriver(), openPage.getExchangeRates());
        Assertions.assertTrue(
                Double.parseDouble(
                        openPage.getCollectExchangeRates().stream()
                                .filter(x -> x.get("Валюта обмена").contains(moneyType))
                                .findFirst()
                                .get().get("Банк покупает").replace(",", "."))
                        <
                        Double.parseDouble(
                                openPage.getCollectExchangeRates().stream()
                                        .filter(x -> x.get("Валюта обмена").contains(moneyType))
                                        .findFirst()
                                        .get().get("Банк продает").replace(",", "."))
        );
    }

}
