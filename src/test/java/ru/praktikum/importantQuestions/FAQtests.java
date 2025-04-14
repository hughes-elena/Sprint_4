package ru.praktikum.importantQuestions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.praktikum.BaseTest;
import ru.praktikum.MainPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class FAQtests extends BaseTest {

    private final int index;
    private final String expectedAnswer;
    private MainPage mainPage;

    public FAQtests(int index, String expectedAnswer) {
        this.index = index;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {0, MainPage.ANSWER_COST},
                {1, MainPage.ANSWER_ONE_SCOOTER},
                {2, MainPage.ANSWER_RENT_START},
                {3, MainPage.ANSWER_ORDER_TODAY},
                {4, MainPage.ANSWER_PROLONG_RETURN},
                {5, MainPage.ANSWER_CHARGE},
                {6, MainPage.ANSWER_CANCEL_ORDER},
                {7, MainPage.ANSWER_DELIVER_FAR}
        });
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        driver.get(mainPage.URL_PAGE);
    }

    @Test
    public void checkDropdownAnswer() {

        mainPage.checkDropdownAnswers(index, expectedAnswer); //Кликаем на стрелку по индексу
        String actualAnswer = mainPage.getAnswerTextByIndex(index);// Получаем фактический текст ответа из метода в MainPage
        assertEquals("Текст ответа не соответствует ожидаемому для вопроса #" + index,
                expectedAnswer, actualAnswer); // Проверяем соответствие текста

    }
}