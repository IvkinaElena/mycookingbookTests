package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * базовый класс страницы
 */
public class Page {

    public WebDriver driver;

    public Page(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Page waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (NoSuchElementException e){
            System.out.println("Элемент не найден");
        }
        return this;
    }

}
