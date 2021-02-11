package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * класс для работы на странице с подробной инфорацией о рецепте
 */
public class RecipePage extends Page{
    public RecipePage(WebDriver driver) {
        super(driver);
    }

    /**
     * опрееление локатора названия рецепта
     */
    @FindBy(xpath = "//h1[contains(@class,'title')]")
    private WebElement titleRecipe;


    @Step("Проверяем название рецепта")
    public boolean checkTitle(String title) {
        return titleRecipe.getText().equals(title);
    }

    /**
     * метод возвращает Id рецепта
     */
    public String getRecipeID(){
        String url = driver.getCurrentUrl();
        return url.substring(url.lastIndexOf("/") + 1);
    }
}
