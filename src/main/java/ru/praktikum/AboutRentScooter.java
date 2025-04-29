package ru.praktikum;

//Проверить полностью позит.заказ самоката от начала до конца:
// ШАГИ:
// Импортировать необходимые классы import org....
// 1. Объявляю переменную WebDriver  WebDriver driver
// 2. Создаю конструктор для передачи переменной WebDriver: this.driver=driver
// 3. После этого я создаю ЛОКАТОРЫ (т.е. элементы, какие мне надо проверить:
// 3.1. Локатор поля Когда привезти самокат (КАЛЕНДАРЬ) private By...= By....
// 3.2. Локатор поля Срок аренды (ВЫПАДАЮЩ СПИСОК) private By...= By....
// 3.3. Локатор поля Цвет самоката (ЧЕКБОКС) private By... = By...
// 3.3.1 Локатор для Чекбокса чёрный жемчуг
// 3.3.2 Локатор для Чекбокса серая безысходность
// 3.4. Локатор поля Комментарий (ТЕКСТ) private By... = By...
// 3.5. Локатор кнопки ЗАКАЗАТЬ + ожидание появления нов стр.
// 3.6. Локатор кнопки НАЗАД + ожидание появления нов стр.
// 4. создаю МЕТОДЫ public void {} для этих локаторов(элементов) PS все методы POM -совершают действия с элементами и выполняют проверки:


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AboutRentScooter {
    WebDriver driver;

    public AboutRentScooter(WebDriver driver) {
        this.driver = driver;
    }

    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By dateInCalendar = By.className("react-datepicker__month-container");
    private final By lengthField = By.xpath("//div[@class='Dropdown-placeholder' and text()='* Срок аренды']");
    private final By rentalPeriodOptions = By.xpath("//div[@class='Dropdown-option']");
    private final By blackColourCheckboxField = By.id("black");
    private final By greyColourCheckboxField = By.id("grey");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By goBackButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i']");
    private final By toWhom = By.xpath("//div[text()='Для кого самокат']");
    private final By wantToOrder = By.xpath("//*[text()='Хотите оформить заказ?']");

    private void waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void selectDateFromCalendar(String date) {
        driver.findElement(dateField).click();
        // Ждём появления календаря
        waitForElement(dateInCalendar); // Ждем появления попера Календарь

        // Формируем локатор для конкретной даты
        String day = date.split("\\.")[0];
        String dayLocator = String.format(
                "//div[contains(@class, 'react-datepicker__day') " +
                        "and contains(@aria-label, '%s') " +
                        "and not(contains(@class, 'disabled'))]",
                day);

        // Ждем и кликаем
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(dayLocator)))
                .click();
    }

    public void selectRentalPeriod(String period) {
        driver.findElement(lengthField).click();
        waitForElement(rentalPeriodOptions);

// Find the option with the matching text and click it
        driver.findElement(By.xpath(String.format("//div[@class='Dropdown-option' and contains(text(), '%s')]", period)))
                .click();
    }

    public void selectColour(String colour) {
        if (colour.equalsIgnoreCase("чёрный жемчуг")) {
            driver.findElement(blackColourCheckboxField).click();
        } else if (colour.equalsIgnoreCase("серая безысходность")) {
            driver.findElement(greyColourCheckboxField).click();
        }
    }

    public void enterComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(wantToOrder));
    }

    public void clickGoBackButton() {
        driver.findElement(goBackButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(toWhom));
    }
}