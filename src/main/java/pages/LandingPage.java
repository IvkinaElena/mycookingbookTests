package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.qameta.allure.Step;

/**
 * класс для работы на стартовой странице сайта
 */
public class LandingPage  extends Page {

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Определение локатора для кнопки входа
     */
    @FindBy(xpath = "//*[contains(text(), 'Войти')]")
    private WebElement loginBtn;

    @Step("Нажимаем кнопку Войти на главной странице")
    public void clickEnterBtn() {
        loginBtn.click();
    }

    @Step("Проверяем, что отображается кнопка Войти")
    public boolean loginBtnIsExists(){
        return loginBtn.isDisplayed();
    }
}
