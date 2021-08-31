package helpers.exceptions;

import drivers.WebDriverManager;
import helpers.CustomUtils;
import io.qameta.allure.Allure;

public class RuntimeExceptionWithScreenshot extends RuntimeException{
    public RuntimeExceptionWithScreenshot(String errorText) {
        Allure.attachment("Текст ошибки:", errorText);
        CustomUtils.getScreen(WebDriverManager.getCurrentDriver());
        throw new RuntimeException(errorText);
    }
}
