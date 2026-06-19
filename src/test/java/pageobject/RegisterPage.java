package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath(".//a[text()='Войти']");
    private final By errorMessage = By.xpath(".//p[contains(@class, 'error')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод имени: {name}")
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке «Зарегистрироваться»")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Клик по ссылке «Войти»")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Получение текста сообщения об ошибке")
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    @Step("Ожидание загрузки страницы регистрации")
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(registerButton));
    }
}