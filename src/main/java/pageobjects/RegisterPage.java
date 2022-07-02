package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.Condition;

public class RegisterPage {

    @FindBy(how = How.XPATH,using = "//label[text() ='Имя']//following::input[1]")
    private SelenideElement nameField;

    @FindBy(how = How.XPATH,using = "//label[text() ='Email']//following::input[1]")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH,using = "//input[@type=\"password\"]")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement invalidPasswordError;

    @Step("Set Name")
    public void setName(String name){
        nameField.setValue(name);
    }

    @Step("Set Password")
    public void setPassword(String password){
        passwordField.setValue(password);
    }

    @Step("Set Email")
    public void setEmail(String email){
        emailField.setValue(email);
    }

    @Step("click Register Button")
    public void clickRegisterButton(){
        registerButton.click();
    }

    @Step("click Login Button")
    public void clickLoginButton(){
        loginButton.click();
    }

    @Step("Check is Invalid Password Error Displayed")
    public boolean isInvalidPasswordErrorDisplayed(){
        invalidPasswordError.should(Condition.visible);
        return invalidPasswordError.shouldBe(Condition.visible).isDisplayed();
    }
}
