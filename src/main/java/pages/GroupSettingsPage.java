package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * класс для работы на странице настроек групп
 */
public class GroupSettingsPage extends Page{

    public GroupSettingsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * определение локатора заголовка страницы "Настройка групп"
     */
    @FindBy(xpath = "//h1[text()='Настройка групп']")
    private WebElement titlePage;

    /**
     * определение локатора поля для ввода названия группы
     */
    @FindBy(id = "add_group")
    private WebElement addGroupInput;

    /**
     * определение локатора кнопки Добавить для создания группы
     */
    @FindBy(xpath = "//button[text()='Добавить']")
    private WebElement addGroupBtn;

    /**
     * определяем список локаторов групп пользователя
     */
    @FindBy(xpath="//span[contains(@class, 'title')]")
    private List<WebElement> listGroups;

    /**
     * определяем xpath иконки удаления группы
     * @param title - название группы, которую нужно удалить
     * @return xpath
     */
    private String deleteIconXpath(String title) {
        String xpath = String.format("//span[text()='%s']/../div/div[2][contains(@class,'icon')]", title);
        return xpath;
    }

    @Step("Проверяем отображение заголовка страницы 'Настройка групп'")
    public boolean titlePageIsExists() {
        return titlePage.isDisplayed();
    }

    /**
     *метод для генерации уникального названия рецепта за счет добавления текущей даты и времени
     * @return строку с уникальным значением
     */
    public String testTitle() {
        String title = "Group" + new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date());
        return title;
    }

    @Step("Вводим название новой группы")
    public GroupSettingsPage inputNameGroup (String title){
        addGroupInput.sendKeys(title);
        return this;
    }

    @Step("Нажимаем кнопку добавить")
    public void clickAddBtn() {
        addGroupBtn.click();
    }

    @Step("Проверяем отображение новой группы")
    public boolean assertCreateNewGroup(String title) {
        driver.navigate().refresh();
        if (listGroups.size() != 0) {
            for (WebElement elem : listGroups) {
                if (elem.getText().equals(title)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * метод создает группу
     * @return название группы
     */
    public String createGroup() {
        String nameGroup = testTitle();
        inputNameGroup(nameGroup);
        clickAddBtn();
        return nameGroup;
    }

    @Step("Удаляет группу по названию")
    public void deleteGroup(String title) {
        for (WebElement elem : listGroups) {
            if (elem.getText().equals(title)) {
                WebElement deleteIcon = driver.findElement(By.xpath(deleteIconXpath(title)));
                deleteIcon.click();
            }
        }
    }

    /**
     * мето удаляет все группы
     */
    public void deleteAllGroups() {
        for (WebElement elem : listGroups) {
                WebElement deleteIcon = driver.findElement(By.xpath(deleteIconXpath(elem.getText())));
                deleteIcon.click();
        }

    }

}
