package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * класс для работы на странице настроек профиля
 */

public class ProfileSettingsPage extends Page{

    public ProfileSettingsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * опредделение локатора заголовка страницы Настройки профиля
     */
    @FindBy(xpath = "//h1[text() = 'Информация профиля']")
    private WebElement profileSettingsTitle;

    @Step("Проверям отображение страницы Настройки профиля")
    public boolean profileSettingsTitleIsExists(){
        return profileSettingsTitle.isDisplayed();
    }
}
