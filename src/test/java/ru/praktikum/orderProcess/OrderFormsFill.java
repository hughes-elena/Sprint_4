package ru.praktikum.orderProcess;


import ru.praktikum.MainPage;
import ru.praktikum.ToWhomScooter;
import ru.praktikum.AboutRentScooter;
import ru.praktikum.WantToOrder;
import org.openqa.selenium.WebDriver;

public class OrderFormsFill {
    private final MainPage mainPage;
    private final ToWhomScooter toWhomScooter;
    private final AboutRentScooter aboutRentScooter;
    private final WantToOrder wantToOrder;

    public OrderFormsFill(WebDriver driver) {
        this.mainPage = new MainPage(driver);
        this.toWhomScooter = new ToWhomScooter(driver);
        this.aboutRentScooter = new AboutRentScooter(driver);
        this.wantToOrder = new WantToOrder(driver);
    }

    //Создаю метод на полное оформление заказа от начала до конца:
    public void completeOrder(
            String name, String lastName, String address, String metroStation,
            String phoneNumber, String date, String rentalPeriod,
            String color, String comment) {
        mainPage.clickOrderScooterTopButton();
        fillCustomerInfo(name, lastName, address, metroStation, phoneNumber); //заполняем Для кого
        fillRentInfo(date, rentalPeriod, color, comment); //Заполняем Про аренду
        confirmOrder(); //Кликаем Заказать
    }

    private void fillCustomerInfo(String name, String lastName, String address,
                                  String metroStation, String phoneNumber) {
        toWhomScooter.setNameField(name);
        toWhomScooter.setLastNameField(lastName);
        toWhomScooter.setAddressField(address);
        toWhomScooter.setMetroStationField(metroStation);
        toWhomScooter.setPhoneNumberField(phoneNumber);
        toWhomScooter.clickNextButton();
    }
    private void fillRentInfo(
            String date, String rentalPeriod, String color, String comment
    ) {
        aboutRentScooter.selectDateFromCalendar(date);
        aboutRentScooter.selectRentalPeriod(rentalPeriod);
        aboutRentScooter.selectColour(color);
        aboutRentScooter.enterComment(comment);
        aboutRentScooter.clickOrderButton();
    }
    private void confirmOrder() {
        wantToOrder.clickYesButton();
    }

}




