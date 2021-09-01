package tests.banks;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import tests.BaseTests;

import java.util.List;
import java.util.Map;

public class Tests extends BaseTests {

    @Feature("Проверка курса валют")
    @DisplayName("Проверка курса валют cо степами")
    @ParameterizedTest(name="{displayName} {arguments}")
    @Tag("EUR, USD")
    @CsvSource({"EUR","USD"})
    public void testOpenWithPage(String value) {
        steps.findGoogle("открытие");
        GooglePageWithSearch googlePageWithSearch = new GooglePageWithSearch(chromeDriver);
        List<Map<String,Object>> resultSearch = googlePageWithSearch.getSearchResults();
        steps.checkContainsName(resultSearch, "Банк Открытие: Частным клиентам", chromeDriver);
        steps.goPageText(googlePageWithSearch, "Банк Открытие");
        OpenPage openPage = new OpenPage( chromeDriver);
        List<Map<String,String>> collectExchangeRates = openPage.getCollectExchangeRates();
        steps.checkCourse(value,openPage);
    }

    @Test
    public void testSberbank() {
        steps.goPage("https://www.sberbank.ru/ru/quotes/currencies");
        SberbankExchangePage page = new SberbankExchangePage();
        String euroBuy1;
        String dollarBuy1;
        euroBuy1 = page.getExchangeBuy("Евро", "1");
        dollarBuy1 = page.getExchangeBuy("Доллар США", "1");
        System.out.println(euroBuy1 + "\n" + dollarBuy1);
    }

    @Test
    public void testTinkoff(){
        steps.goPage("https://www.tinkoff.ru/about/exchange/");
        TinkoffPage page = new TinkoffPage();
        WebDriverWait wait = new WebDriverWait(chromeDriver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*/div[@class='a2KQOa b2KQOa']//self::*//child::*[contains(text(),'Для всех')]/parent::*/parent::*")));
        System.out.println(page.getCourseDouble(""));
    }
}
