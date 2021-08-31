package pages;

import helpers.PageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SberbankExchangePage implements Page,ExchangePage {

    @FindBy(xpath = "//table[@class = 'rates-table-component']//tbody//tr[2]/td[2]")
    private WebElement pageLoadSign;

    @FindBy(xpath = "//table[@class = 'rates-table-component']//tbody//tr[2]/td[3]")
    private WebElement pageTestSign;


    @FindBy(xpath = "//table[@class = 'rates-table-component']")
    private WebElement exchangeTable;

    public SberbankExchangePage() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return PageUtils.isElementTextContains(pageLoadSign, "от 1");
    }

    public String getExchangeBuy(String moneyType, String number) {
        return exchangeTable.findElement(By.xpath(".//tr/td/div[contains(text(), '" + moneyType + "')]/self::*[contains(text(), '" +
                number + "')]/parent::*/following-sibling::td[2]")).getText();
    }

    public void testMethod() {
        System.out.println(pageTestSign.getText());
    }

    @Override
    public void preAction() {

    }

    @Override
    public Double getCourseDouble(String course) {
        return Double.parseDouble(course.replace(",", ".").trim());
    }
}
