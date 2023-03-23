import model.MainPage;
import model.OrderPage;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static model.MainPage.driver;
import static model.OrderPage.*;

@RunWith(Parameterized.class)
public class MakeToOrder {

        private final By orderButton;
        private final By metroStation;

        public MakeToOrder(By orderButton, By metroStation) {
            this.orderButton = orderButton;
            this.metroStation = metroStation;
        }
        @Parameterized.Parameters
        public static Object[][] getIdButton() {

            return new Object[][]{
                    // orderButton - кнопка заказа, metroStation - станция метро
                    {OrderPage.getButtonOrderUp(), OrderPage.getMetroMolodezhnaySelect()}, //метро молодежная(50), верхняя кнопка заказать
                    {OrderPage.getButtonOrderDown(), OrderPage.getMetroVodniyStadion()}, //метро водный стадион(23),нижняя кнопка заказать
            };
        }
        @Test
        public void checkMakeOrder() {

            //Settings for Chrome(для теста под хром, раскомментировать и закоментировать для Firefox)
                /* ChromeOptions options = new ChromeOptions();
                  options.addArguments("--remote-allow-origins=*");
                  WebDriver driver = new ChromeDriver(options);
                  MainPage mainPage = new MainPage(driver);*/

            //Settings for Firefox
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                WebDriver driver = new FirefoxDriver();
                MainPage mainPage = new MainPage(driver);

                OrderPage orderPage = new OrderPage();

            //Открытие главной страницы и переход на страницу оформления заказа
            goToOrderScooter(orderButton);
            //Заполнение информации "Для кого" и переход на страницу "Про аренду"
            String name = "Пётр";
            String surname = "Петров";

            String adress = "Ул. Мира, д.50, кв. 73";
            String phoneNumber = "89007778080";
            orderPage.inputBasicInformation(metroStation, name, surname, adress, phoneNumber);
            //Заполнение информации "Про аренду" и оформление заказа
            String comment= "Домофон не работает";
            orderPage.inputInfoAboutRent(comment);
            //Подтверждение заказа
            orderPage.confirmOrder();
            //Получение окна с подтверждением заказа
            orderPage.getTextOrderProcessed();
            //Просмотр информации о заказе
            orderPage.getInfoAboutOrder();


        }
      @After
        public void tearDown() {
            driver.quit();
        }


}

