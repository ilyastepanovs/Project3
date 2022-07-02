package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.Condition;

public class ProfilePage {

    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement logoutButton;

    @FindBy(how = How.XPATH,using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    @FindBy(how = How.XPATH,using = "//div[@class = 'AppHeader_header__logo__2D0X2']")
    private SelenideElement logoButton;

    @Step("Click Constructor Button")
    public void clickConstructorButton(){
        constructorButton.click();
    }

    @Step("Click Logout Button")
    public void clickLogoutButton(){
        logoutButton.click();
    }

    @Step("Click Logo Button")
    public void clickLogoButton(){
        logoButton.click();
    }

    @Step("Check is Logout Button Displayed")
    public boolean isLogoutButtonDisplayed() {
        logoutButton.shouldBe(Condition.visible);
        return logoutButton.isDisplayed();
    }
}
