import model.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static model.MainPage.*;

@RunWith(Parameterized.class)
public class QuestionAboutImportant {

        //Добавь необходимые поля
        private final By idButton;
        private final String textAfterClickButton;
        private final By xpathForAnswer;

        public QuestionAboutImportant(By idButton, String textAfterClickButton, By xpathForElement) {
            this.idButton = idButton;
            this.textAfterClickButton = textAfterClickButton;
            this.xpathForAnswer = xpathForElement;
        }


        @Parameterized.Parameters
        public static Object[][] getTestParameters() {

            return new Object[][]{
                    // idButton - id кнопки, textAfterClickButton - текст в выпадающем списке, xpathForAnswer - хpath до элемента
                    {MainPage.getArrowQuestions1(), "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", MainPage.getAnswerAfterPressArrow1()},
                    {MainPage.getArrowQuestions2(), "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                            MainPage.getAnswerAfterPressArrow2()},
                    {MainPage.getArrowQuestions3(), "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня." +
                            " Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру." +
                            " Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", MainPage.getAnswerAfterPressArrow3()},
                    {MainPage.getArrowQuestions4(), "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", MainPage.getAnswerAfterPressArrow4()},
                    {MainPage.getArrowQuestions5(), "Пока что нет! " +
                            "Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", MainPage.getAnswerAfterPressArrow5()},
                    {MainPage.getArrowQuestions6(), "Самокат приезжает к вам с полной зарядкой." +
                            " Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", MainPage.getAnswerAfterPressArrow6()},
                    {MainPage.getArrowQuestions7(), "Да, пока самокат не привезли. " +
                            "Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", MainPage.getAnswerAfterPressArrow7()},
                    {MainPage.getArrowQuestions8(), "Да, обязательно. Всем самокатов! И Москве, и Московской области.", MainPage.getAnswerAfterPressArrow8()},
            };
        }
        @Test
        public void checkArrow_openText() {
            //Settings for Chrome
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            WebDriver driver = new ChromeDriver(options);

            //Settings for Firefox
            // FirefoxOptions options = new FirefoxOptions();

            //options.addArguments("--headless");
            //WebDriver driver = new FirefoxDriver();
            MainPage mainPage = new MainPage(driver);

            mainPage.openPage(); //открываем страницу
            mainPage.clickCookieConfirmButton() ; //принимаем кукм
            scrollToButtonArrow(mainPage.findButtonArrow(idButton));//скролим до кнопки
            waitElementToBeClickable(idButton);//ожидание кликабельности элемента
            mainPage.findButtonArrow(idButton).click(); //кликаем на вопрос
            mainPage.waitLoadAnswerText(xpathForAnswer); //ожидание текста ответа на раскрытый вопрос
            Assert.assertEquals(textAfterClickButton, mainPage.comprasionExpectedAndActualResult(xpathForAnswer,textAfterClickButton));
        }
        //Выход из браузера
        @After
        public void tearDown() {
            driver.quit();
        }
}

