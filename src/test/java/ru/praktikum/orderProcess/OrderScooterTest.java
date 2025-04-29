package ru.praktikum.orderProcess;

//Импортируем необходимые пакеты

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.*;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)

public class OrderScooterTest extends BaseTest {
    private OrderFormsFill orderFormsFill;
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

    // Конструктор с параметрами для 2й  и 3 стр
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


    // Параметры для 2 и 3 стр. Для кого самокат
    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {"Вера", "Веровна", "ул. Ленина, 1", "Октябрьская", "12345677890", "воскресенье, 13-е апреля 2025 г.", "двое суток", "чёрный жемчуг", "Спасибо"},
                {"Иван", "Иванов", "ул. Пушкина, 10", "Сокольники", "89998887766", "21.04.2025", "пятеро суток", "серая безысходность", "Спасибо"}
        });
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
        driver.get(mainPage.URL_PAGE);
        mainPage = new MainPage(driver);
        orderFormsFill = new OrderFormsFill(driver);
    }

    @Test
    public void testDoYouWantToOrderTop() {

        mainPage.clickOrderScooterTopButton(); //кликаем на заказать
        orderFormsFill.completeOrder(name, lastName, address, metroStation, phoneNumber,
                date, periodOfRent, colour, comment);

        // Добавляем ассерт для проверки текста заголовка "Заказ оформлен"
        String actualOrderSuccessText = wantToOrder.getOrderSuccessText();
        String expectedOrderSuccessText = "Заказ оформлен";
        assertEquals("Текст 'Заказ оформлен' не совпадает",
                expectedOrderSuccessText, actualOrderSuccessText);
    }

    @Test
    public void testDoYouWantToOrderButtom() {

        mainPage.clickOrderScooterBottomButton(); //кликаем на заказать
        orderFormsFill.completeOrder(name, lastName, address, metroStation, phoneNumber,
                date, periodOfRent, colour, comment);

        // Добавляем ассерт для проверки текста заголовка "Заказ оформлен"
        String actualOrderSuccessText = wantToOrder.getOrderSuccessText();
        String expectedOrderSuccessText = "Заказ оформлен";
        assertEquals("Текст 'Заказ оформлен' не совпадает",
                expectedOrderSuccessText, actualOrderSuccessText);
    }

}