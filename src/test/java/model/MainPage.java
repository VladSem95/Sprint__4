package model;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    static final public String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    //локаторы для ArrowQuestions
    public final static By QUESTION_IMPORTANT1 = By.id("accordion__heading-0");
    public final static By QUESTION_IMPORTANT2 = By.id("accordion__heading-1");
    public final static By QUESTION_IMPORTANT3 = By.id("accordion__heading-2");
    public final static By QUESTION_IMPORTANT4 = By.id("accordion__heading-3");
    public final static By QUESTION_IMPORTANT5 = By.id("accordion__heading-4");
    public final static By QUESTION_IMPORTANT6 = By.id("accordion__heading-5");
    public final static By QUESTION_IMPORTANT7 = By.id("accordion__heading-6");
    public final static By QUESTION_IMPORTANT8 = By.id("accordion__heading-7");

    //локаторы для ответов на вопросы
    public final static By ANSWER_AFTER_PRESS_ARROW1 = By.xpath(".//div[@id='accordion__panel-0']/p");
    public final static By ANSWER_AFTER_PRESS_ARROW2 = By.xpath(".//div[@id='accordion__panel-1']/p");
    public final static By ANSWER_AFTER_PRESS_ARROW3 = By.xpath(".//div[@id='accordion__panel-2']/p");
    public final static By ANSWER_AFTER_PRESS_ARROW4 = By.xpath(".//div[@id='accordion__panel-3']/p");
    public final static By ANSWER_AFTER_PRESS_ARROW5 = By.xpath(".//div[@id='accordion__panel-4']/p");
    public final static By ANSWER_AFTER_PRESS_ARROW6 = By.xpath(".//div[@id='accordion__panel-5']/p");
    public final static By ANSWER_AFTER_PRESS_ARROW7 = By.xpath(".//div[@id='accordion__panel-6']/p");
    public final static By ANSWER_AFTER_PRESS_ARROW8 = By.xpath(".//div[@id='accordion__panel-7']/p");

    //текст ответов на вопросы
    public final static String ANSWER_FOR_IMPORTANT_QUESTION1 =
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public final static String ANSWER_FOR_IMPORTANT_QUESTION2 =
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public final static String ANSWER_FOR_IMPORTANT_QUESTION3 =
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня." +
                    " Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру." +
                    " Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public final static String ANSWER_FOR_IMPORTANT_QUESTION4 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public final static String ANSWER_FOR_IMPORTANT_QUESTION5 = "Пока что нет! " +
            "Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public final static String ANSWER_FOR_IMPORTANT_QUESTION6 = "Самокат приезжает к вам с полной зарядкой." +
            " Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public final static String ANSWER_FOR_IMPORTANT_QUESTION7 = "Да, пока самокат не привезли. " +
            "Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public final static String ANSWER_FOR_IMPORTANT_QUESTION8 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";


    public static WebDriver driver;
    public  MainPage(WebDriver driver) {
        this.driver = driver;

    }
    //Открытие стартовой страницы
    public static void openPage() {

        driver.get(PAGE_URL);
    }
    //Согласие на использование куки
    public static void clickCookieConfirmButton() {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }
    //Поиск вопроса
    public static WebElement findButtonArrow(By idButton) {
        WebElement buttonArrow = driver.findElement(idButton);
        return buttonArrow;
    }
    //Скролл до вопроса
    public static void scrollToButtonArrow(WebElement buttonArrow) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", buttonArrow);
    }
    //Ожидание пока элемент не станет кликабельным
    public static void waitElementToBeClickable(By idButton) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(idButton));
    }
    //Ожидание пока не прогрузится ответ после нажатия кнопки
    public static void waitLoadAnswerText(By xpathForAnswer) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(xpathForAnswer));
    }
    //Получение текста ответа и сравнение с ожидаемым ответом на вопрос
    public static void comprasionExpectedAndActualResult(By xpathForAnswer, String textAfterClickButton) {
        String textAfterPressButtonArrow = driver.findElement(xpathForAnswer).getText(); //получаем текст появившегося элемента
        Assert.assertEquals(textAfterClickButton, textAfterPressButtonArrow);
    }
}

