package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

    @Step("Переключение на раздел конструктора: {section}")
    private void switchToSection(String section) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickConstructorSection(section);
    }

    @Step("Проверка активного раздела: ожидаем {expected}")
    private void verifyActiveTab(String expected) {
        By activeTabLocator = By.xpath("//div[contains(@class, 'current')]//span");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.textToBe(activeTabLocator, expected));
        MainPage mainPage = new MainPage(driver);
        String active = mainPage.getActiveTabText();
        assertEquals("Неверный активный раздел", expected, active);
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверка, что при клике на «Булки» активен соответствующий таб")
    public void switchToBunsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForMainPageLoad();
        switchToSection("Булки");
        verifyActiveTab("Булки");
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверка, что при клике на «Соусы» активен соответствующий таб")
    public void switchToSaucesTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForMainPageLoad();
        switchToSection("Соусы");
        verifyActiveTab("Соусы");
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверка, что при клике на «Начинки» активен соответствующий таб")
    public void switchToFillingsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForMainPageLoad();

        switchToSection("Соусы");
        verifyActiveTab("Соусы");

        switchToSection("Начинки");
        verifyActiveTab("Начинки");
    }
}