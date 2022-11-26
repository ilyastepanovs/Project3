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

public class ConstructorPageTest {
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
    @DisplayName("Check Transition From Profile To Constructor Page by LogoButton")
    public void checkTransitionFromProfileToConstructorPageByLogoButton() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();
        constructorPage.clickProfileButton();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickLogoButton();
        Assert.assertTrue(constructorPage.isCreateOrderButtonExist());
    }

    @Test
    @DisplayName("Check Transition From Profile To Constructor Page by Constructor Button")
    public void checkTransitionFromProfileToConstructorPageByConstructorButton() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();
        constructorPage.clickProfileButton();
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickConstructorButton();
        Assert.assertTrue(constructorPage.isCreateOrderButtonExist());
    }

    @Test
    @DisplayName("Check Bun Tab")
    public void checkBunTab() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickSauceTab();
        constructorPage.clickBunTab();
        Assert.assertTrue(constructorPage.isHeaderBunDisplayed());
    }

    @Test
    @DisplayName("Check Sauce Tab")
    public void checkSauceTab() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickSauceTab();
        Assert.assertTrue(constructorPage.isSauceDisplayed());
    }

    @Test
    @DisplayName("Check Filling Tab")
    public void checkFillingTab() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickFillingTab();
        Assert.assertTrue(constructorPage.isHeaderFillingDisplayed());
    }
}