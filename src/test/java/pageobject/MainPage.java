package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private final By loginButton = By.xpath("//button[contains(text(),'Войти')]");
    private final By personalAccountLink = By.xpath("//*[@id='root']/div/header/nav/a/p");
    private final By constructorBuns = By.xpath("//div[contains(@class, 'tab_tab')]//span[text()='Булки']/..");
    private final By constructorSauces = By.xpath("//div[contains(@class, 'tab_tab')]//span[text()='Соусы']/..");
    private final By constructorFillings = By.xpath("//div[contains(@class, 'tab_tab')]//span[text()='Начинки']/..");
    private final By activeTab = By.xpath("//div[contains(@class, 'current')]//span");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickPersonalAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(personalAccountLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickConstructorSection(String section) {
        By locator;
        switch (section) {
            case "Булки": locator = constructorBuns; break;
            case "Соусы": locator = constructorSauces; break;
            case "Начинки": locator = constructorFillings; break;
            default: throw new IllegalArgumentException("Неизвестный раздел: " + section);
        }
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public String getActiveTabText() {
        return driver.findElement(activeTab).getText();
    }

    public void waitForMainPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    public boolean isOrderButtonDisplayed() {
        try {
            return driver.findElement(By.xpath("//button[text()='Оформить заказ']")).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}