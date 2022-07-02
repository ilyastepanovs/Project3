package pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.Condition;

public class ConstructorPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site";

    @FindBy(how = How.XPATH,using = "//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement profileButton;

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement sauceTab;

    @FindBy(how = How.XPATH,using = ".//span[text()='Начинки']")
    private SelenideElement fillingTab;

    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement bunHeader;

    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement sauceHeader;

    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement fillingHeader;

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement createOrderButton;

    @Step("click login Button")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("click Profile Button")
    public void clickProfileButton() {
        profileButton.click();
    }

    @Step("Check is Create order button is displayed")
    public boolean isCreateOrderButtonExist(){
        createOrderButton.shouldBe(Condition.visible);
        return createOrderButton.isDisplayed();
    }

    @Step("click Bun Tab")
    public void clickBunTab() {
        bunTab.click();
    }

    @Step("click Sauce Tab")
    public void clickSauceTab() {
        sauceTab.click();
    }

    @Step("click Filling Tab")
    public void clickFillingTab() {
        fillingTab.click();
    }

    @Step("Check is bun section is displayed")
    public boolean isHeaderBunDisplayed(){
        return bunHeader.isDisplayed();
    }

    @Step("Check is sauce section is displayed")
    public boolean isSauceDisplayed(){
        return sauceHeader.isDisplayed();
    }

    @Step("Check is Filling section is displayed")
    public boolean isHeaderFillingDisplayed(){
        return fillingHeader.isDisplayed();
    }
}