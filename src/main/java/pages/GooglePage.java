package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage implements Page {

    @FieldName("Поле поиска")
    @FindBy(name = "q")
    public WebElement searchField;

    @FieldName("Кнопка поиска")
    @FindBy(name = "btnK")
    public WebElement searchButton;

    public GooglePage() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return true;
    }
}