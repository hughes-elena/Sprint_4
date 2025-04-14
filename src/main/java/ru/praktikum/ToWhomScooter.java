package ru.praktikum;

//Проверить полностью позит.заказ самоката от начала до конца:
// ШАГИ:
// Импортировать необходимые классы import org....
// 1. Объявляю переменную WebDriver  WebDriver driver
// 2. Создаю конструктор для передачи переменной WebDriver: this.driver=driver
// 3. После этого я создаю ЛОКАТОРЫ (т.е. элементы, какие мне надо проверить:
// 3.1. Локатор *Кнопка "Заказать" на главной странице идет в другом классе - MainPage + Ожидание появл новой стр заказа
// 3.2. Локатор поля Имя private By...= By....
// 3.3. Локатор поля Фамилия private By... = By...
// 3.4. Локатор поля Адрес private By... = By...
// 3.5. Локатор поля Станц метро private By... = By... Т.к. он выпадающий, нужно еще добавить локатор для опций
// 3.6. Локатор поля Телефон private By... = By...
// 3.7. Локатор кнопки "Далее" + ожидание появления нов стр.
// 5. создаю МЕТОДЫ public void {} для этих локаторов(элементов) PS все методы POM -совершают действия с элементами и выполняют проверки:
//5.1. Метод по локатору п.3.1: Метод, который нажимает на кнопку заказать *в классе MainPage.java
//      + в MainPage.java Создаю ожидание, чтобы новая стр точно появилась и тест не выдал ошибку new WebDriverWait
//5.2. Метод по локаторам п.3.2,3,4,5,6: Метод, который ищет поле и заполняет его
//5.3.


import org.openqa.selenium.By; // импортировали класс By
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver; //импортировали класс Webdriver
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration; // для новой версии Selenium при ожидании нужно теперь писать Duration.ofSeconds(...)


public class ToWhomScooter {
    WebDriver driver; //1. Объявили переменную/добавили поле driver

    public ToWhomScooter(WebDriver driver) {
        this.driver = driver; //3. конструктор для передачи Webdriver
    }
    //Создаем локаторы п.п3.2-3.7

    private final By nameField = By.cssSelector("input[placeholder='* Имя']"); // cssSelector для поля Имя
    private final By lastNameField = By.cssSelector("input[placeholder='* Фамилия']"); // cssSelector для поля Фамилия
    private final By addressField = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']"); // cssSelector для поля Адрес
    private final By metroDropDownList = By.cssSelector("input[placeholder='* Станция метро']"); // cssSelector для поля станция Метро
    private final By phoneNumberField = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']"); // cssSelector для поля Телефон
    private final By nextButton = By.xpath("//button[text()='Далее']");//по xpath по тексту кнопки

    //Создаем методы для локаторов

    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name); // метод заполняет поля "Имя"
    }
    public void setLastNameField(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName); // метод заполняет поля "Фамилия"
    }
    public void setAddressField(String address) {
        driver.findElement(addressField).sendKeys(address); // метод заполняет поля "Адрес: куда привезти заказ"
    }
    public void setMetroStationField(String metro) {
        WebElement metroInput = driver.findElement(metroDropDownList); // метод находит поле "Метро" и сохраняет в переменную metroInput

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", metroInput);
        metroInput.click(); // метод кликает на поле "метро" и появляется выпадающий список станций
        new WebDriverWait(driver, Duration.ofSeconds(5)) //создаем ожидание в 5сек
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'select-search__select')]"))); //ждем до тех пор, пока нужная станция не появится в списке
        By metroOption = By.xpath(String.format(
                "//button[contains(@class, 'select-search__option')]//div[contains(@class, 'Order_Text__2broi') and text()='%s']",
                metro));

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(metroOption))
                .click();
    }

    public void setPhoneNumberField(String number) {
        driver.findElement(phoneNumberField).sendKeys(number); // метод заполняет поля "Номер телефона"
    }


    public void clickNextButton() {
        driver.findElement(nextButton).click(); // ищем кнопку "Далее" и кликаем по кнопке

        new WebDriverWait(driver, Duration.ofSeconds(5)) //ждем, когда появится след стр
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[text()='Про аренду']")));
    }
}


