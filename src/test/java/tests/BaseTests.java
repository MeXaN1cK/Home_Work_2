package tests;

import drivers.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import steps.Steps;

public class BaseTests {

    protected WebDriver chromeDriver;
    protected Steps steps;

    @BeforeEach
    public void before() {
        WebDriverManager.initChrome();
        chromeDriver = WebDriverManager.getCurrentDriver();
        steps = new Steps(chromeDriver);
    }

    @AfterEach
    public void closeBellTest() {
        WebDriverManager.killCurrentDriver();
    }

}