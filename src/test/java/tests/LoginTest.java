package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.*;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Step("Выполнение входа через UI с email: {email}")
    private void performUILogin(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Step("Проверка успешного входа (появление кнопки 'Оформить заказ')")
    private void verifySuccessfulLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Оформить заказ']")));
        MainPage mainPage = new MainPage(driver);
        assertTrue("Кнопка оформления заказа не появилась", mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка входа через главную кнопку")
    public void loginViaMainButtonTest() {
        String email = "user" + System.currentTimeMillis() + "@test.com";
        String password = "123456";
        createUserViaApi(email, password, "TestUser");

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForMainPageLoad();
        mainPage.clickLoginButton();

        performUILogin(email, password);
        verifySuccessfulLogin();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка входа через личный кабинет")
    public void loginViaPersonalAccountTest() {
        String email = "user" + System.currentTimeMillis() + "@test.com";
        String password = "123456";
        createUserViaApi(email, password, "TestUser");

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForMainPageLoad();
        mainPage.clickPersonalAccount();

        performUILogin(email, password);
        verifySuccessfulLogin();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через ссылку 'Войти' на странице регистрации")
    public void loginViaRegisterFormTest() {
        String email = "user" + System.currentTimeMillis() + "@test.com";
        String password = "123456";
        createUserViaApi(email, password, "TestUser");

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForMainPageLoad();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForPageLoad();
        registerPage.clickLoginLink();

        performUILogin(email, password);
        verifySuccessfulLogin();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через ссылку 'Войти' на странице восстановления пароля")
    public void loginViaForgotPasswordFormTest() {
        String email = "user" + System.currentTimeMillis() + "@test.com";
        String password = "123456";
        createUserViaApi(email, password, "TestUser");

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForMainPageLoad();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.waitForPageLoad();
        forgotPage.clickLoginLink();

        performUILogin(email, password);
        verifySuccessfulLogin();
    }
}