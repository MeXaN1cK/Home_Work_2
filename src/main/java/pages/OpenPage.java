package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenPage implements ExchangePage {
    @FieldName("Курс обмена")
    @FindBy(xpath = "//*[@class='main-page-exchange main-page-info__card']")
    private WebElement exchangeRates;

    @FindBy(xpath = "//*[@class='main-main-page-info__card']")
    private WebElement exchangeRateError;


    @FindAll(@FindBy(xpath = "//*[@class='main-page-exchange main-page-info__card']//tbody/tr[contains(@class,'header')]/td"))
    private List<WebElement> tableHeaders;

    @FindAll(@FindBy(xpath = "//*[@class='main-page-exchange main-page-info__card']//tbody/tr[contains(@class,'row')]"))
    private List<WebElement> tableRows;

    private WebDriver driver;

    private List<Map<String,String>> collectExchangeRates = new ArrayList<>();

    private String mainURL="https://www.open.ru/";

    public OpenPage(WebDriver driver) {
        this.driver=driver;
        if(!driver.getTitle().contains("Открытие"))
            driver.get(mainURL);
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return true;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getExchangeRates() {
        return exchangeRates;
    }

    public List<Map<String, String>> getCollectExchangeRates() {
        for(int i= 0; i<tableRows.size();++i){
            Map<String,String> collectRow=new HashMap<>();
            for (int j=0;j<tableHeaders.size();++j){
                collectRow.put(
                        tableHeaders.get(j).getText(),
                        tableRows.get(i).findElement(By.xpath("./td["+(j+1)+"]")).getText()
                );
            }
            collectExchangeRates.add(collectRow);
        }

        return collectExchangeRates;
    }

    @Override
    public void preAction() {

    }

    @Override
    public Double getCourseDouble(String course) {
        return Double.parseDouble(course.replace(",", ".").trim());
    }
}
