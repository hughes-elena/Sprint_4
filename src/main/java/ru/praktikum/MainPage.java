package ru.praktikum;

import org.openqa.selenium.By; // импортировали класс By
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver; //импортировали класс Webdriver
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration; // для новой версии Selenium при ожидании нужно теперь писать Duration.ofSeconds(...)
import java.util.List;

//На главной странице нам необходимо проверить:
// ТОЛЬКО Выпадающий список в разделе «Вопросы о важном». В нем надо:
// 1. проверить: что при КЛИКЕ на выпадающую стрелочку:
// 2.открывается соответствующий текст.
// ШАГИ:
// Импортировать необходимые классы import org....
// 1. для начала я создаю Java class главной стр, где находятся эти элементы(п1.п.2) И я сейчас в нем.
// 2. Объявляю переменную WebDriver  WebDriver driver
// 3. Создаю конструктор для передачи переменной WebDriver: this.driver=driver
// 4. После этого я создаю ЛОКАТОРЫ (т.е. элементы, какие мне надо проверить:
// 4.1. Локатор по раскрывающейся стрелочки private By...= By....
// 4.2. Локатор по тексту, кот. раскрывается после нажатия на стрелку private By... = By...
// 5. создаю МЕТОДЫ public void {} для этих локаторов(элементов) PS все методы POM -совершают действия с элементами и выполняют проверки:
//5.1. Метод по локатору п.4.1: Метод, который нажимает на стрелочку
//5.2 Создаю ожидание, чтобы текст точно появился и тест не выдал ошибку new WebDriverWait
//5.3. Метод по локатору п.4.2: Метод, который проверяет, что появился текст
//* Со след задания, нужно в этом классе создать:
//1. Локатор для кнопки "Заказать" в правом верхнем углу стр + ожидание загрузки след стр и
//2.Локатор для кнопки "Заказать" внизу стр + ожидание загрузки след стр и
//3. Два отдельных Метода для этих кнопок Заказать

//1. создали page object - класс для главной страницы MainPage
public class MainPage {
    public static final String URL_PAGE = "https://qa-scooter.praktikum-services.ru/";
    WebDriver driver; //2. Объявили переменную/добавили поле driver

    public MainPage(WebDriver driver) {
        this.driver = driver; //3. конструктор для передачи Webdriver
    }

    // ЛОКАТОРЫ private By:
    private final By allDropdownArrows = By.xpath("//div[@class='accordion__button']"); // Все  выпад.стрелочки
    private final String dropdownTextByIndex = "//div[@id='accordion__panel-%d']/p"; // Шаблон для текста ответа по индексу
    //Локаторы Для ЗАКАЗА Создаю локаторы для кнопок "Заказать" (для след задания):
    private final By orderScooterTop = By.xpath("(//button[text()='Заказать'])[1]"); //верхняя кнопка Заказать
    private final By orderScooterBottom = By.xpath("(//button[text()='Заказать'])[2]"); //нижняя кнопка Заказать

    //Константы для текстов ответов
    public static final String ANSWER_COST = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String ANSWER_ONE_SCOOTER = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String ANSWER_RENT_START = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String ANSWER_ORDER_TODAY = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String ANSWER_PROLONG_RETURN = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String ANSWER_CHARGE = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String ANSWER_CANCEL_ORDER = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String ANSWER_DELIVER_FAR = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    // Создаем массив ожидаемых ответов для удобного доступа по индексу
    String[] expectedAnswers = {
            ANSWER_COST, ANSWER_ONE_SCOOTER, ANSWER_RENT_START, ANSWER_ORDER_TODAY,
            ANSWER_PROLONG_RETURN, ANSWER_CHARGE, ANSWER_CANCEL_ORDER, ANSWER_DELIVER_FAR
    };

    // 5.1 Метод для нажатия выпадающей стрелочки и появления текста после нажатия:
    public void checkDropdownAnswers(int index, String expectedText) {
        By arrowLocator = By.id("accordion__heading-" + index);
        By answerLocator = By.id("accordion__panel-" + index);

        WebElement arrow = driver.findElement(arrowLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", arrow);
        arrow.click();
        ;

        String actualText = driver.findElement(answerLocator).getText();

        if (actualText.equals(expectedText)) {
            System.out.println("Стрелка #" + (index + 1) + ": Текст соответствует ожидаемому");
        } else {
            System.out.println("Стрелка #" + (index + 1) + ": Текст не соответствует ожидаемому");
        }

    }

    public String getAnswerTextByIndex(int index) {
        By answerLocator = By.id("accordion__panel-" + index);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator)).getText();
    }

    // Метод для нажатия верхней кнопки "Заказать" и ожидания появления страницы заказа
    public void clickOrderScooterTopButton() {
        WebElement order = driver.findElement(orderScooterTop); // ищет кнопку "Заказать"
        order.click(); // кликаем по ней
        // Ждём в этом методе появления след стр "Для кого самокат"(стр оформления заказа)
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[text()='Для кого самокат']")));
    }

    // Метод для нажатия нижней кнопки "Заказать" и ожидания появления страницы заказа
    public void clickOrderScooterBottomButton() {
        WebElement order = driver.findElement(orderScooterBottom); // ищет кнопку "Заказать"
        order.click(); // кликаем по ней
        // Ждём в этом методе появления след стр "Для кого самокат"(стр оформления заказа)
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[text()='Для кого самокат']")));
    }
}
