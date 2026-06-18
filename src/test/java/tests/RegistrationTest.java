package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации с валидными данными")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForMainPageLoad();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForPageLoad();
        String name = "User" + System.currentTimeMillis();
        String email = "user" + System.currentTimeMillis() + "@test.com";
        String password = "123456";
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();


        loginPage.waitForPageLoad();
        assertTrue("Не открылась страница входа", driver.getCurrentUrl().contains("login"));
    }

    @Test
    @DisplayName("Ошибка при коротком пароле (менее 6 символов)")
    @Description("Проверка, что при пароле длиной менее 6 символов появляется ошибка")
    public void shortPasswordErrorTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForMainPageLoad();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForPageLoad();
        registerPage.enterName("TestUser");
        registerPage.enterEmail("test@test.com");
        registerPage.enterPassword("12345"); // 5 символов
        registerPage.clickRegisterButton();

        String error = registerPage.getErrorMessage();
        assertEquals("Некорректное сообщение об ошибке", "Некорректный пароль", error);
    }
}