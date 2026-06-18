package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.MainPage;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверка, что при клике на «Булки» активен соответствующий таб")
    public void switchToBunsTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForMainPageLoad();
        mainPage.clickConstructorSection("Булки");
        Thread.sleep(300);
        String active = mainPage.getActiveTabText();
        assertEquals("Булки", active);
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверка, что при клике на «Соусы» активен соответствующий таб")
    public void switchToSaucesTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForMainPageLoad();
        mainPage.clickConstructorSection("Соусы");
        Thread.sleep(300);
        String active = mainPage.getActiveTabText();
        assertEquals("Соусы", active);
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверка, что при клике на «Начинки» активен соответствующий таб")
    public void switchToFillingsTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForMainPageLoad();
        mainPage.clickConstructorSection("Начинки");
        Thread.sleep(300);
        String active = mainPage.getActiveTabText();
        assertEquals("Начинки", active);
    }
}