package ru.praktikum;

import org.openqa.selenium.By; // импортировали класс By
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver; //импортировали класс Webdriver
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration; // для новой версии Selenium при ожидании нужно теперь писать Duration.ofSeconds(...)
import java.util.List;

public class WantToOrder {
    WebDriver driver; //2. Объявили переменную/добавили поле driver

    public WantToOrder(WebDriver driver) {
        this.driver = driver; //3. конструктор для передачи Webdriver
    }

    private By yesButton = By.xpath("//button[text()='Да']");

    public void clickYesButton() {
        WebElement order = driver.findElement(yesButton); // ищет кнопку "Да все привыкли"
        order.click();
// Ждём в этом методе появления след стр "Заказ оформлен"
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[text()='Заказ оформлен']")));
    }
}
