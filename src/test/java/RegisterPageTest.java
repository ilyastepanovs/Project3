import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import pageobjects.*;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import api.client.UserClient;
import utils.DataGenerator;

public class RegisterPageTest {
    UserClient userClient = new UserClient();
    User user;
    String accessToken;
    String invalidPassword = RandomStringUtils.randomAlphabetic(4);

    @Before
    public void startBrowser(){
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver", "/Users/lasnavorp/Desktop/chromedriver");
        System.setProperty("chromeoptions.args",
                "--headless, --disable-gpu, --no-sandbox, ---allow-insecure-localhost, --disable-dev-shm-usage");
        user = DataGenerator.getRandomUser();
    }

    @After
    public void tearDown() {
        if ( accessToken != null){
            userClient.userDeleting(user);
        }
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Check User Registration")
    public void checkUserRegistration() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();
        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.setName(user.getFirstName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(user.getPassword());
        registerPage.clickRegisterButton();
        ValidatableResponse createResponse = userClient.userLogin(user);
        accessToken = createResponse.extract().path("accessToken");
        user.setAccessToken(accessToken);
        Assert.assertTrue(loginPage.isLoginPageHeaderDisplayed());
    }

    @Test
    @DisplayName("Check Registration with Invalid Password")
    public void checkRegistrationWithInvalidPassword() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();
        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.setName(user.getFirstName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(invalidPassword);
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.isInvalidPasswordErrorDisplayed());
    }
}
