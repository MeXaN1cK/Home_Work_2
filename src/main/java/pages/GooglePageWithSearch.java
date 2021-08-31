package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GooglePageWithSearch {
    private String selectorSearchItems="//div[@class='g' and not(@data-hveid)]";
    private String selectorURL = ".//a[@href]";
    private String selectorNamePage = ".//h3";
    private String selectorDiscriprion = ".//div[@class='IsZvec']";

    private WebDriver driver;

    private List<WebElement> searchItems = new ArrayList<>();
    private List<Map<String,Object>> collectResults = new ArrayList<>();

    public GooglePageWithSearch(WebDriver driver) {
        this.driver = driver;
        searchItems = driver.findElements(By.xpath(selectorSearchItems));
    }

    public List<Map<String,Object>> getSearchResults() {
        for (WebElement result : searchItems)
            collectResults.add(Map.of(
                    "WEB_ELEMENT", result,
                    "URL", result.findElement(By.xpath(selectorURL)).getAttribute("href"),
                    "NAME_PAGE", result.findElement(By.xpath(selectorNamePage)).getText(),
                    "DISCRIPTION", result.findElement(By.xpath(selectorDiscriprion)).getText()
            ));
        return collectResults;
    }

    public boolean goPage(String namePage){
        WebElement pageLink = (WebElement) collectResults.stream()
                .filter(x->x.get("NAME_PAGE").toString().contains(namePage))
                .findFirst()
                .get().get("WEB_ELEMENT");
        pageLink.findElement(By.xpath(selectorURL)).click();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String tab : tabs){
            driver.switchTo().window(tab);
            if(driver.getTitle().contains(namePage))
                return true;
        }
        return false;
    }

}
