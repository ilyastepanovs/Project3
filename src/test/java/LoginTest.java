import api.client.UserClient;
import com.codeborne.selenide.Configuration;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.Before;
import pageobjects.ForgotPasswordPage;
import pageobjects.RegisterPage;
import pageobjects.ConstructorPage;
import pageobjects.LoginPage;
import io.qameta.allure.junit4.DisplayName;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Test;
import utils.DataGenerator;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    UserClient userClient = new UserClient();
    User user;
    String accessToken;

    @Before
    public void startBrowser(){
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
        System.setProperty("chromeoptions.args",
                "--headless, --disable-gpu, --no-sandbox, ---allow-insecure-localhost, --disable-dev-shm-usage");
        user = DataGenerator.getRandomUser();
        ValidatableResponse createResponse = userClient.userRegistration(user);
        accessToken = createResponse.extract().path("accessToken");
        user.setAccessToken(accessToken);
    }

    @After
    public void tearDown() {
        if ( accessToken != null){
            userClient.userDeleting(user);
        }
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Check Login From Constructor Page")
    public void checkLoginFromConstructorPage(){
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();
        assertTrue(loginPage.isProfilePageOpened());
    }

    @Test
    @DisplayName("Check Login By Profile Button")
    public void checkLoginByProfileButton(){
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickProfileButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();
        assertTrue(loginPage.isProfilePageOpened());
    }

    @Test
    @DisplayName("Check Login From By Register Button")
    public void checkLoginFromByRegisterButton(){
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickProfileButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();
        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.clickLoginButton();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();
        assertTrue(loginPage.isProfilePageOpened());
    }

    @Test
    @DisplayName("Check Login From Forgot Password Page")
    public void checkLoginFromForgotPasswordPage(){
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickProfileButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickForgotPasswordLink();
        ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);
        forgotPasswordPage.clickLoginButton();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();
        assertTrue(loginPage.isProfilePageOpened());
    }
}
