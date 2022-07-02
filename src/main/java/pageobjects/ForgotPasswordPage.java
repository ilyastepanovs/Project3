package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {
    @FindBy(how = How.XPATH,using = "//a[text() = 'Войти']")
    private SelenideElement loginButton;

    @Step("Click Login Button")
    public void clickLoginButton(){
        loginButton.click();
    }
}
