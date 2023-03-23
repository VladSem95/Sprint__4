package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static model.MainPage.*;

public class OrderPage {

    //Локаторы для страницы "Для кого самокат"
    private final static By BUTTON_ORDER_UP = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']"); // кнопка заказать вверху страницы
    private final static By BUTTON_ORDER_DOWN = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    private final static By NAME_INPUT_FIELD = By.cssSelector("div > input[placeholder='* Имя']"); // поле Имя
    private final static By SURNAME_INPUT_FIELD = By.cssSelector("div > input[placeholder='* Фамилия']"); //поле Фамилия
    private final static By ADDRESS_FIELD = By.cssSelector("div > input[placeholder='* Адрес: куда привезти заказ']"); //поле Адрес
    private final static By METRO_STATION_FIELD = By.cssSelector("div > input[placeholder='* Станция метро']"); //поле Станция метро
    private final static By METRO_MOLODEZHNAY_SELECT = By.xpath(".//li[@data-index='50']"); // локатор для метро Молодежная
    private final static By METRO_VODNIY_STADION = By.xpath(".//li[@data-index='23']"); //локатор для метро Водный стадион
    private final static By PHONE_NUMBER_FIELD = By.cssSelector("div > input[placeholder='* Телефон: на него позвонит курьер']"); //поле телефон
    private final static By BUTTON_FURTHER = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[text() ='Далее']"); //Кнопка Далее

    private final static By WHEN_TO_BRING_FIELD = By.cssSelector("div > input[placeholder = '* Когда привезти самокат']"); // Поле когда привезти самокат
    private final static By DATEPIKER = By.xpath(".//div[@class = 'react-datepicker']"); //Календарь
    private final static By CALENDARS_DAY = By.xpath(".//div[@class = 'react-datepicker__week']/div[@class = 'react-datepicker__day react-datepicker__day--028']"); //выбор дня текущего месяца
    private final static By RENTAL_PERIOD_FIELD = By.className("Dropdown-arrow");
    private final static By ONE_DAY = By.xpath(".//div[@class='Dropdown-option'][text() = 'сутки']");

    private final static By BLACK_PEARL = By.id("black");
    private final static By COMMENT_FOR_COURIER = By.cssSelector("div > input[placeholder = 'Комментарий для курьера']");
    private final static By BUTTON_ORDER_FINAL = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Заказать']");
    private final static By BUTTON_CONFIRM_ORDER = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Да']");
    private final static By BUTTON_VIEW_ORDER = By.xpath(".//div[@class = 'Order_NextButton__1_rCA']/button[text() = 'Посмотреть статус']");

    private final static By TEXT_ORDER_PROCESSED = By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ']");


    public OrderPage() {
    }

    //метожы для получения кнопок
    public static By getButtonOrderUp() {
        return BUTTON_ORDER_UP;
    }
    public static By getButtonOrderDown() {
        return BUTTON_ORDER_DOWN;
    }

    public static By getMetroMolodezhnaySelect() {
        return METRO_MOLODEZHNAY_SELECT;
    }

    public static By getMetroVodniyStadion() {
        return METRO_VODNIY_STADION;
    }



    public static void goToOrderScooter(By orderButton) {
        driver.get(returnUrl());
        driver.findElement(By.id("rcc-confirm-button")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(orderButton));//ожидание для кнопки
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButton));
        driver.findElement(orderButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.elementToBeClickable(NAME_INPUT_FIELD));
    }
    //Заполнение первой страницы заказа
    public static void inputBasicInformation(By metroStation, String name, String surname, String adress, String phoneNumber) {
        driver.findElement(NAME_INPUT_FIELD).sendKeys(name);
        driver.findElement(SURNAME_INPUT_FIELD).click();
        driver.findElement(SURNAME_INPUT_FIELD).sendKeys(surname);
        driver.findElement(ADDRESS_FIELD).click();
        driver.findElement(ADDRESS_FIELD).sendKeys(adress);
        driver.findElement(METRO_STATION_FIELD).click();
        driver.findElement(metroStation).click(); //выбор метро
        driver.findElement(PHONE_NUMBER_FIELD).click();
        driver.findElement(PHONE_NUMBER_FIELD).sendKeys(phoneNumber);
        driver.findElement(BUTTON_FURTHER).click();
    }
    //Заполнение второй страницы заказа
    public static void inputInfoAboutRent(String comment) {
        driver.findElement(WHEN_TO_BRING_FIELD).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(DATEPIKER));
        driver.findElement(CALENDARS_DAY).click();
        driver.findElement(RENTAL_PERIOD_FIELD).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(RENTAL_PERIOD_FIELD));
        driver.findElement(ONE_DAY).click();
        driver.findElement(BLACK_PEARL).click();
        driver.findElement(COMMENT_FOR_COURIER).click();
        driver.findElement(COMMENT_FOR_COURIER).sendKeys(comment);
        driver.findElement(BUTTON_ORDER_FINAL).click();
    }
    public static void confirmOrder() {
        driver.findElement(BUTTON_CONFIRM_ORDER).click();
    }

    public static void getTextOrderProcessed() {
        driver.findElement(TEXT_ORDER_PROCESSED);
    }
    public static void getInfoAboutOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(BUTTON_VIEW_ORDER));
        driver.findElement(BUTTON_VIEW_ORDER).click();
    }



}
