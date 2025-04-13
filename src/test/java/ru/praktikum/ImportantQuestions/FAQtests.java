package ru.praktikum.ImportantQuestions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.praktikum.MainPage;

import java.time.Duration;

public class FAQtests {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // Для Chrome (раскомментировать для использования)
        ChromeOptions chromeOptions = new ChromeOptions(); // Для Chrome (раскомментировать для использования)
        chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage"); // Для Chrome (раскомментировать для использования)
        driver = new ChromeDriver(chromeOptions);// Для Chrome (раскомментировать для использования)

        //WebDriverManager.firefoxdriver().setup(); //Для Firefox (закомментировать, если не используется)
        //FirefoxOptions firefoxOptions = new FirefoxOptions(); //Для Firefox (закомментировать, если не используется)
        //driver = new FirefoxDriver(firefoxOptions); //Для Firefox (закомментировать, если не используется)

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void dropdownAAnswerTest1() {
        mainPage.clickAcceptCookie();
        mainPage.checkDropdownAnswers(0, mainPage.ANSWER_1);
    }

    @Test
    public void dropdownAAnswerTest2() {
        mainPage.clickAcceptCookie();
        mainPage.checkDropdownAnswers(1, mainPage.ANSWER_2);
    }

    @Test
    public void dropdownAAnswerTest3() {
        mainPage.clickAcceptCookie();
        mainPage.checkDropdownAnswers(2, mainPage.ANSWER_3);
    }

    @Test
    public void dropdownAAnswerTest4() {
        mainPage.clickAcceptCookie();
        mainPage.checkDropdownAnswers(3, mainPage.ANSWER_4);
    }

    @Test
    public void dropdownAAnswerTest5() {
        mainPage.clickAcceptCookie();
        mainPage.checkDropdownAnswers(4, mainPage.ANSWER_5);
    }

    @Test
    public void dropdownAAnswerTest6() {
        mainPage.clickAcceptCookie();
        mainPage.checkDropdownAnswers(5, mainPage.ANSWER_6);
    }

    @Test
    public void dropdownAAnswerTest7() {
        mainPage.clickAcceptCookie();
        mainPage.checkDropdownAnswers(5, mainPage.ANSWER_7);
    }

    @Test
    public void dropdownAAnswerTest8() {
        mainPage.clickAcceptCookie();
        mainPage.checkDropdownAnswers(7, mainPage.ANSWER_8);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}