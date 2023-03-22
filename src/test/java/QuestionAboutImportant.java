import model.MainPage;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static model.MainPage.*;


public class QuestionAboutImportant {

    @RunWith(Parameterized.class)
    public static class TestClass {
        //Добавь необходимые поля
        private final By idButton;
        private final String textAfterClickButton;
        private final By xpathForAnswer;

        public TestClass(By idButton, String textAfterClickButton, By xpathForElement) {
            this.idButton = idButton;
            this.textAfterClickButton = textAfterClickButton;
            this.xpathForAnswer = xpathForElement;
        }


        @Parameterized.Parameters
        public static Object[][] getIdButton() {

            return new Object[][]{
                    // idButton - id кнопки, textAfterClickButton - текст в выпадающем списке, xpathForAnswer - хpath до элемента
                    {QUESTION_IMPORTANT1, ANSWER_FOR_IMPORTANT_QUESTION1, ANSWER_AFTER_PRESS_ARROW1},
                    {QUESTION_IMPORTANT2, ANSWER_FOR_IMPORTANT_QUESTION2, ANSWER_AFTER_PRESS_ARROW2},
                    {QUESTION_IMPORTANT3, ANSWER_FOR_IMPORTANT_QUESTION3, ANSWER_AFTER_PRESS_ARROW3},
                    {QUESTION_IMPORTANT4, ANSWER_FOR_IMPORTANT_QUESTION4, ANSWER_AFTER_PRESS_ARROW4},
                    {QUESTION_IMPORTANT5, ANSWER_FOR_IMPORTANT_QUESTION5, ANSWER_AFTER_PRESS_ARROW5},
                    {QUESTION_IMPORTANT6, ANSWER_FOR_IMPORTANT_QUESTION6, ANSWER_AFTER_PRESS_ARROW6},
                    {QUESTION_IMPORTANT7, ANSWER_FOR_IMPORTANT_QUESTION7, ANSWER_AFTER_PRESS_ARROW7},
                    {QUESTION_IMPORTANT8, ANSWER_FOR_IMPORTANT_QUESTION8, ANSWER_AFTER_PRESS_ARROW8},
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
            mainPage.comprasionExpectedAndActualResult(xpathForAnswer,textAfterClickButton);

        }
        //Выход из браузера
        @After
        public void tearDown() {
            driver.quit();
        }
    }
}

