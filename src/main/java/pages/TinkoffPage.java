package pages;

import helpers.PageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TinkoffPage implements Page,ExchangePage{

    @FindBy(xpath = "//*/div[@class='a2KQOa b2KQOa']//self::*//child::*[contains(text(),'Для всех')]")
    private WebElement pageLoadSign;

    @FindBy(xpath = "//*/div[@class='a2KQOa b2KQOa']//self::*//child::*[contains(text(),'Для всех')]/parent::*/parent::*")
    private WebElement exchangeTable;

    @Override
    public void preAction() {

    }

    @Override
    public Double getCourseDouble(String course) {
        exchangeTable.findElements(By.className("a1daNl")).forEach(x-> System.out.println(x.getText()));
        List<WebElement> exchangeValues = exchangeTable.findElements(By.className("a1daNl"));
        double bankBuy = Double.parseDouble(exchangeValues.get(0).getText().replace(",","."));
        double bankSell = Double.parseDouble(exchangeValues.get(1).getText().replace(",","."));
        return bankBuy - bankSell;
    }

    public TinkoffPage() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return PageUtils.isElementTextContains(pageLoadSign, "Для всех");
    }

}
