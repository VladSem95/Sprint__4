package model;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final static String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    //локаторы ArrowQuestions
    private final static By QUESTION_IMPORTANT1 = By.id("accordion__heading-0");
    private final static By QUESTION_IMPORTANT2 = By.id("accordion__heading-1");
    private final static By QUESTION_IMPORTANT3 = By.id("accordion__heading-2");
    private final static By QUESTION_IMPORTANT4 = By.id("accordion__heading-3");
    private final static By QUESTION_IMPORTANT5 = By.id("accordion__heading-4");
    private final static By QUESTION_IMPORTANT6 = By.id("accordion__heading-5");
    private final static By QUESTION_IMPORTANT7 = By.id("accordion__heading-6");
    private final static By QUESTION_IMPORTANT8 = By.id("accordion__heading-7");

    //Методы, возвращающие локаторы ArrowQuestions
    public static By getArrowQuestions1() {
        return QUESTION_IMPORTANT1;
    }

    public static By getArrowQuestions2() {
        return QUESTION_IMPORTANT2;
    }
    public static By getArrowQuestions3() {
        return QUESTION_IMPORTANT3;
    }
    public static By getArrowQuestions4() {
        return QUESTION_IMPORTANT4;
    }
    public static By getArrowQuestions5() {
        return QUESTION_IMPORTANT5;
    }
    public static By getArrowQuestions6() {
        return QUESTION_IMPORTANT6;
    }
    public static By getArrowQuestions7() {
        return QUESTION_IMPORTANT7;
    }
    public static By getArrowQuestions8() {
        return QUESTION_IMPORTANT8;
    }


    //локаторы для ответов на вопросы
    private final static By ANSWER_AFTER_PRESS_ARROW1 = By.xpath(".//div[@id='accordion__panel-0']/p");
    private final static By ANSWER_AFTER_PRESS_ARROW2 = By.xpath(".//div[@id='accordion__panel-1']/p");
    private final static By ANSWER_AFTER_PRESS_ARROW3 = By.xpath(".//div[@id='accordion__panel-2']/p");
    private final static By ANSWER_AFTER_PRESS_ARROW4 = By.xpath(".//div[@id='accordion__panel-3']/p");
    private final static By ANSWER_AFTER_PRESS_ARROW5 = By.xpath(".//div[@id='accordion__panel-4']/p");
    private final static By ANSWER_AFTER_PRESS_ARROW6 = By.xpath(".//div[@id='accordion__panel-5']/p");
    private final static By ANSWER_AFTER_PRESS_ARROW7 = By.xpath(".//div[@id='accordion__panel-6']/p");
    private final static By ANSWER_AFTER_PRESS_ARROW8 = By.xpath(".//div[@id='accordion__panel-7']/p");
    //Методы, возвращающие локаторы для ответов на вопросы
    public static By getAnswerAfterPressArrow1() {
        return ANSWER_AFTER_PRESS_ARROW1;
    }
    public static By getAnswerAfterPressArrow2() {
        return ANSWER_AFTER_PRESS_ARROW2;
    }
    public static By getAnswerAfterPressArrow3() {
        return ANSWER_AFTER_PRESS_ARROW3;
    }
    public static By getAnswerAfterPressArrow4() {
        return ANSWER_AFTER_PRESS_ARROW4;
    }
    public static By getAnswerAfterPressArrow5() {
        return ANSWER_AFTER_PRESS_ARROW5;
    }
    public static By getAnswerAfterPressArrow6() {
        return ANSWER_AFTER_PRESS_ARROW6;
    }
    public static By getAnswerAfterPressArrow7() {
        return ANSWER_AFTER_PRESS_ARROW7;
    }
    public static By getAnswerAfterPressArrow8() {
        return ANSWER_AFTER_PRESS_ARROW8;
    }

    public static String returnUrl() {
        return PAGE_URL;
    }
    private  WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;

    }
    //Открытие стартовой страницы
    public void openPage() {
        driver.get(PAGE_URL);
    }
    //Согласие на использование куки
    public  void clickCookieConfirmButton() {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }
    //Поиск вопроса
    public  WebElement findButtonArrow(By idButton) {
        WebElement buttonArrow = driver.findElement(idButton);
        return buttonArrow;
    }
    //Скролл до вопроса
    public void scrollToButtonArrow(WebElement buttonArrow) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", buttonArrow);
    }
    //Ожидание пока элемент не станет кликабельным
    public void waitElementToBeClickable(By idButton) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(idButton));
    }
    //Ожидание пока не прогрузится ответ после нажатия кнопки
    public void waitLoadAnswerText(By xpathForAnswer) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(xpathForAnswer));
    }
    //Получение текста ответа и сравнение с ожидаемым ответом на вопрос
    public String comprasionExpectedAndActualResult(By xpathForAnswer, String textAfterClickButton) {
        String textAfterPressButtonArrow = driver.findElement(xpathForAnswer).getText(); //получаем текст появившегося элемента
        return textAfterPressButtonArrow;
    }
}

