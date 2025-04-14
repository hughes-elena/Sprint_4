package ru.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.MainPage;
import ru.praktikum.ToWhomScooter;
import ru.praktikum.AboutRentScooter;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
// локатор для принятия кук
    private By acceptCookie = By.xpath("//*[@id=\"rcc-confirm-button\"]"); //локатор для кнопки "да все привыкли"


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // Для Chrome (раскомментировать для использования)
        ChromeOptions chromeOptions = new ChromeOptions(); // Для Chrome (раскомментировать для использования)
        chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage"); // Для Chrome (раскомментировать для использования)
        driver = new ChromeDriver(chromeOptions);// Для Chrome (раскомментировать для использования)
        //WebDriverManager.firefoxdriver().setup(); //Для Firefox (закомментировать, если не используется)
        //FirefoxOptions firefoxOptions = new FirefoxOptions(); //Для Firefox (закомментировать, если не используется)
        //driver = new FirefoxDriver(firefoxOptions); //Для Firefox (закомментировать, если не используется)

        // Настройки драйвера
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        driver.get(MainPage.URL_PAGE); //Открытие страницы

        // Инициализация MainPage
        mainPage = new MainPage(driver);
        acceptCookies();
    }
    // Метод для принятия кук
    public void acceptCookies() {
        WebElement cookieButton = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(acceptCookie));
        cookieButton.click();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

