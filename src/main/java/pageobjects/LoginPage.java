package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.Condition;

public class LoginPage {
    @FindBy(how = How.XPATH, using = "//label[text() ='Email']//following::input[1]")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = "//label[text() ='Пароль']//following::input[1]")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private SelenideElement registerLink;

    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private SelenideElement forgotPasswordLink;

    @FindBy(how = How.XPATH, using = ".//h2[text()='Вход']")
    private SelenideElement loginPageHeader;

    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement profileButton;

    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement logoutButton;

    @Step("set Email")
    public void setEmail(String email){
        emailField.setValue(email);
    }

    @Step("set Password")
    public void setPassword(String password){
        passwordField.setValue(password);
    }

    @Step("click Register Link")
    public void clickRegisterLink(){
        registerLink.click();

    }

    @Step("Click Login Button")
    public void clickLoginButton(){
        loginButton.click();
    }

    @Step("click Forgot Password Link")
    public void clickForgotPasswordLink(){
        forgotPasswordLink.click();
    }

    @Step("Check is Profile PageOpened")
    public boolean isProfilePageOpened(){
        profileButton.click();
        return logoutButton.shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Check is Login PageHeader Displayed")
    public boolean isLoginPageHeaderDisplayed(){
        return loginPageHeader.shouldBe(Condition.visible).isDisplayed();
    }
}
