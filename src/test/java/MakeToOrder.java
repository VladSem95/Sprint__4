import model.OrderPage;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class MakeToOrder {

        private final By orderButton;
        private final By metroStation;
        private final String name;
        private final String surname;
        private final String adress;
        private final String phoneNumber;
        public MakeToOrder(By orderButton, By metroStation, String name, String surname, String adress, String phoneNumber) {
            this.orderButton = orderButton;
            this.metroStation = metroStation;
            this.name = name;
            this.surname = surname;
            this.adress = adress;
            this.phoneNumber = phoneNumber;
        }
        @Parameterized.Parameters
        public static Object[][] getTestParameters() {

            return new Object[][]{
                    // orderButton - кнопка заказа, metroStation - станция метро
                    {OrderPage.getButtonOrderUp(), OrderPage.getMetroMolodezhnaySelect(),"Пётр", "Петров", "Ул. Мира, д.50, кв. 73", "89007778080"}, //метро молодежная(50), верхняя кнопка заказать
                    {OrderPage.getButtonOrderDown(), OrderPage.getMetroVodniyStadion(), "Иван", "Иванов", "Ул. Ленина, д.50, кв. 55", "89001775550"}, //метро водный стадион(23),нижняя кнопка заказать
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
                OrderPage orderPage = new OrderPage(driver);

            //Открытие главной страницы и переход на страницу оформления заказа
            orderPage.goToOrderScooter(orderButton, driver);
            //Заполнение информации "Для кого" и переход на страницу "Про аренду"
            orderPage.inputBasicInformation(metroStation, name, surname, adress, phoneNumber, driver);
            //Заполнение информации "Про аренду" и оформление заказа
            String comment= "Домофон не работает";
            orderPage.inputInfoAboutRent(comment, driver);
            //Подтверждение заказа
            orderPage.confirmOrder(driver);
            //Получение окна с подтверждением заказа
            orderPage.getTextOrderProcessed(driver);
            //Просмотр информации о заказе
            MatcherAssert.assertThat(orderPage.getTextOrderProcessed(driver),
                    containsString("Заказ оформлен"));
            driver.quit();

        }
}

