package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * класс для работы на странице авторизации
 */
public class LoginPage extends Page{

    /**
     * определение локатора поля ввода логина
     */
    @FindBy(id = "email")
    private WebElement loginField;
    /**
     * определение локатора поля ввода пароля
     */
    @FindBy(id = "password")
    private WebElement passwordField;
    /**
     * определение локатора кнопки входа в аккаунт
     */
    @FindBy(xpath = "//*[contains(text(), 'Войти')]")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Вводим логин")
    public LoginPage inputLogin(String login) {
        loginField.sendKeys(login);
        return this;
    }
    @Step("Вводим пароль")
    public LoginPage inputPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }
    @Step("Нажимаем Войти для авторизации")
    public LoginPage clickLoginBtn() {
        loginBtn.click();
        return this;
    }

}
