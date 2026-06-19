package tests;

import api.client.UserClient;
import api.model.User;
import config.WebDriverFactory;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;
    protected UserClient userClient;
    protected User user;
    protected String accessToken;

    @Step("Инициализация драйвера и API-клиента")
    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.education-services.ru/");
        userClient = new UserClient();
    }

    @Step("Закрытие драйвера и удаление пользователя")
    @After
    public void tearDown() {
        if (accessToken != null && !accessToken.isEmpty()) {
            userClient.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Создание пользователя через API с email: {email}")
    protected void createUserViaApi(String email, String password, String name) {
        user = new User(email, password, name);
        Response response = userClient.createUser(user);
        accessToken = userClient.getAccessToken(response);
    }

    @Step("Логин пользователя через API для получения токена (используется после UI-регистрации)")
    protected void loginUserViaApi(String email, String password) {
        User loginUser = new User(email, password, null);
        Response response = userClient.loginUser(loginUser);
        accessToken = userClient.getAccessToken(response);
    }
}