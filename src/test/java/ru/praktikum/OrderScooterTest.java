package ru.praktikum;

//Импортируем необходимые пакеты

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.MainPage;
import ru.praktikum.ToWhomScooter;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class OrderScooterTest {

    private WebDriver driver;
    private MainPage mainPage; // Поле для MainPage
    private ToWhomScooter toWhomScooter; // Поле для ToWhomScooter
    private AboutRentScooter aboutRentScooter; // Поле для AboutRentScooter
    private WantToOrder wantToOrder; // Поле для WantToOrder

    // Для 2й стр Для кого самокат
    private final String name; //Для 2й стр Для кого самокат
    private final String lastName; //Для 2й стр Для кого самокат
    private final String address; //Для 2й стр Для кого самокат
    private final String metroStation; //Для 2й стр Для кого самокат
    private final String phoneNumber; //Для 2й стр Для кого самокат

    // Для 3й стр Про аренду
    private final String date; // Для 3й стр Про аренду
    private final String periodOfRent; // Для 3й стр Про аренду
    private final String colour; // Для 3й стр Про аренду
    private final String comment; // Для 3й стр Про аренду

    // Конструктор с параметрами для 2й стр Для кого самокат
    public OrderScooterTest(String name, String lastName, String address, String metroStation,
                            String phoneNumber,
                            String date, String periodOfRent,
                            String colour, String comment) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;

        this.date = date;
        this.periodOfRent = periodOfRent;
        this.colour = colour;
        this.comment = comment;
    }


    // Параметры для 2q стр. Для кого самокат
    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {"Вера", "Веровна", "ул. Ленина, 1", "Октябрьская", "12345677890", "воскресенье, 13-е апреля 2025 г.", "двое суток", "чёрный жемчуг", "Спасибо"},
                {"Иван", "Иванов", "ул. Пушкина, 10", "Сокольники", "89998887766", "21.04.2025", "пятеро суток", "серая безысходность", "Спасибо"}
        });
    }

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
        toWhomScooter = new ToWhomScooter(driver);
        aboutRentScooter = new AboutRentScooter(driver);
        wantToOrder = new WantToOrder(driver);
    }


    @Test
    public void testDoYouWantToOrder() {
        mainPage.clickAcceptCookie(); //принимаем куки
        mainPage.clickOrderScooterTopButton(); //кликаем на заказать
        toWhomScooter.setNameField(name);//заполняем поля на 2й стр
        toWhomScooter.setLastNameField(lastName);//заполняем поля на 2й стр
        toWhomScooter.setAddressField(address);//заполняем поля на 2й стр
        toWhomScooter.setMetroStationField(metroStation);//заполняем поля на 2й стр
        toWhomScooter.setPhoneNumberField(phoneNumber);//заполняем поля на 2й стр
        toWhomScooter.clickNextButton(); // кликаем Далее

        aboutRentScooter.selectDateFromCalendar(date);
        aboutRentScooter.selectRentalPeriod(periodOfRent);
        aboutRentScooter.selectColour(colour);
        aboutRentScooter.enterComment(comment);
        aboutRentScooter.clickOrderButton();

        wantToOrder.clickYesButton();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Закрытие браузера
        }
    }
}