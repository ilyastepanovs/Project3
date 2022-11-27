import api.client.UserClient;
import com.codeborne.selenide.Configuration;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.Before;
import pageobjects.*;
import io.qameta.allure.junit4.DisplayName;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import utils.DataGenerator;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class ProfilePageTest {
    UserClient userClient = new UserClient();
    User user;
    String accessToken;

    @Before
    public void startBrowser(){
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        System.setProperty("chromeoptions.args",
                "--headless, --disable-gpu, --no-sandbox, ---allow-insecure-localhost, --disable-dev-shm-usage"); user = DataGenerator.getRandomUser();
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
    @DisplayName("Logout Check")
    public void logoutCheckPage(){
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();
        constructorPage.clickProfileButton();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickLogoutButton();
        Assert.assertTrue(loginPage.isLoginPageHeaderDisplayed());
    }

    @Test
    @DisplayName("Check Transition to Profile Page")
    public void checkTransitionToProfilePage(){
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();
        constructorPage.clickProfileButton();
        ProfilePage profilePage = page(ProfilePage.class);
        Assert.assertTrue(profilePage.isLogoutButtonDisplayed());
    }
}