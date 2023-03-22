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


public class MakeToOrder {
    @RunWith(Parameterized.class)
    public static class TestClass {
        private final By orderButton;
        private final By metroStation;

        public TestClass(By orderButton, By metroStation) {
            this.orderButton = orderButton;
            this.metroStation = metroStation;
        }
        @Parameterized.Parameters
        public static Object[][] getIdButton() {

            return new Object[][]{
                    // orderButton - кнопка заказа, metroStation - станция метро
                    {BUTTON_ORDER_UP, METRO_MOLODEZHNAY_SELECT}, //метро молодежная(50), верхняя кнопка заказать
                    {BUTTON_ORDER_DOWN, METRO_VODNIY_STADION}, //метро водный стадион(23),нижняя кнопка заказать
            };
        }
        @Test
        public void checkMakeOrder() {

            //Settings for Chrome(для теста под хром, раскомментировать и закоментировать для Firefox)
            /*    ChromeOptions options = new ChromeOptions();
                  options.addArguments("--remote-allow-origins=*");
                  WebDriver driver = new ChromeDriver(options);
                  MainPage mainPage = new MainPage(driver); */

            //Settings for Firefox
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            WebDriver driver = new FirefoxDriver();

            MainPage mainPage = new MainPage(driver);
            OrderPage orderPage = new OrderPage();

            //Открытие главной страницы и переход на страницу оформления заказа
            orderPage.goToOrderScooter(orderButton);
            //Заполнение информации "Для кого" и переход на страницу "Про аренду"
            orderPage.inputBasicInformation(metroStation);
            //Заполнение информации "Про аренду" и оформление заказа
            orderPage.inputInfoAboutRent();
            //Подтверждение заказа
            orderPage.confirmOrder();
            //Просмотр информации о заказе
            orderPage.getInfoAboutOrder();
            //Выход из браузера
            //driver.quit();
        }
        @After
        public void tearDown() {
            driver.quit();
        }

    }
}

