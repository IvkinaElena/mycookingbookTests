package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import parametrs.Parameters;
import java.util.List;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * класс для работы на основной странице со списком рецептов
 */
public class RecipesPage extends Page{

    public RecipesPage(WebDriver driver) {
        super(driver);
    }

    /**
     * определение локатора кнопки для добавления нового рецепта
     */
    @FindBy(xpath = "//*[text()='Добавить рецепт']")
    private WebElement addRecipeBtn;

    /**
     * определение локатора кнопки сворачивания левой панели
     */
    @FindBy(xpath = "//div[contains(@class,'burger')]")
    private WebElement panelFoldingBtn;

    /**
     * определение локатора панели с фильтрами
     */
    @FindBy(xpath = "//*[contains(text(), 'ФИЛЬТРЫ')]")
    private WebElement filterPanel;

    /**
     * определение локатора кнопки Добавить группу
     */
    @FindBy(xpath = "//button[text()='+ Добавить группу']")
    private WebElement addGroupBtn;

    /**
     * определение локатора текстового поля для ввода названия группы
     */
    @FindBy(id="title")
    private WebElement titleGroupField;

    /**
     * определение локатора кнопки Добавить для группы
     */
    @FindBy(xpath = "//button[text()='Добавить']")
    private WebElement addBtn;

    /**
     * определяем список локаторов групп пользователя
     */
    @FindBy(xpath="//div[contains(@class, 'list__item')]")
    private List<WebElement> listGroups;

    /**
     * определение локатора кнопки Настроить для перехода в настрйоки групп
     */
    @FindBy(xpath = "//a[text()='Настроить']")
    private WebElement settingsGroupHref;

    /**
     * определение локатора кнопки с логотипом
     */
    @FindBy(xpath="//div[contains(@class, 'header__logo')]")
    private WebElement logoBtn;

    /**
     * опрееление локатора аватара профиля
     */
    @FindBy(xpath = "//div[contains(@class, 'profile__avatar')]")
    private WebElement profileAvatar;

    /**
     * Определение локатора кнопки Выйти из профиля
     */
    @FindBy(xpath = "//div[text() = 'Выйти']")
    private WebElement outBtn;

    /**
     * Определение локатора ссылки в Профиль
     */
    @FindBy(xpath = "//a[text() = 'Профиль']")
    private WebElement profileLink;

    /**
     * Определение локатора названия фильтра по категории
     */
    @FindBy(xpath = "//div[contains(text(), 'Категория блюд')]")
    private WebElement categoryFilter;

    /**
     * Определение локатора одной из категорий блюд (супы и бульоны)
     */
    @FindBy(xpath = "//span[contains(text(), 'Супы')]")
    private WebElement categoryItem;

    /**
     * Определение локатора названия фильтра по кухне
     */
    @FindBy(xpath = "//div[contains(text(), 'Кухни')]")
    private WebElement countryFilter;

    /**
     * Определение локатора одной из кухни(Абхазская)
     */
    @FindBy(xpath = "//span[contains(text(), 'Абхазская')]")
    private WebElement countryItem;

    /**
     * Определение локатора названия фильтра по характеристике
     */
    @FindBy(xpath = "//div[contains(text(), 'Характеристики')]")
    private WebElement characteristicsFilter;

    /**
     * Определение локатора одной из характеристики (Мясное блюдо)
     */
    @FindBy(xpath = "//span[contains(text(), 'Мясное')]")
    private WebElement characteristicsItem;

    /**
     * Определение локатора названия фильтра по времени приема пищи
     */
    @FindBy(xpath = "//div[contains(text(), 'Время приема')]")
    private WebElement timeToEatFilter;

    /**
     * Определение локатора одного из времени приема пищи (Завтрак)
     */
    @FindBy(xpath = "//span[contains(text(), 'Завтрак')]")
    private WebElement timeToEatItem;

    /**
     * метод для перехода на основную страницу
     */
    public void openMainPage() {
        driver.get(Parameters.BASE_URL);
    }

    /**
     * Метод проверки наличия кнопки для добавления нового рецепта на странице
     */
    public boolean addRecipeBtnIsExists(){
        return  addRecipeBtn.isDisplayed();
    }

    @Step("Нажимаем на элемент для сворачивания/разворачивания левой панели")
    public RecipesPage clickPanelFoldingBtn() {
        panelFoldingBtn.click();
        return this;
    }

    @Step("Проверяем, что левая панель свернута/развернута")
    public boolean filterPanelIsExists(){
        try {
            SECONDS.sleep(1);
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
        return  filterPanel.isDisplayed();
    }

    @Step("Нажимаем кнопку + Добавить группу")
    public RecipesPage clickAddGroupBtn() {
        addGroupBtn.click();
        return this;
    }

    @Step("Вводим название группы")
    public RecipesPage inputTitleGroup(String title) {
        titleGroupField.sendKeys(title);
        return this;
    }
    @Step("Нажимаем кнопку Добавить для создания новой группы")
    public RecipesPage clickAddBtn() {
        addBtn.click();
        return this;
    }

    @Step("Проверяем отображение новой группы")
    public boolean newGroupIsDisplayed(String title) {
        for (WebElement elem : listGroups) {
            if (elem.getText().equals(title));
                return true;
        }
        return false;
    }

    @Step("Нажимаем кнопку Настроить для перехода в настройки групп")
    public void clickSettingsGroupHref() {
        //Здесь пришлось отправлять щелчок прямо на элемент
        //т.к. он иногда временно перекрывался другим
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", settingsGroupHref);
    }

    @Step("Нажимаем на логотип для возврата в основную страницу")
    public RecipesPage clickLogo() {
        logoBtn.click();
        return this;
    }

    @Step("Нажимаем кнопку Добавить рецепт")
    public void clickAddRecipe() {
        waitForElementVisibility(addRecipeBtn);
        // Здесь пришлось организовать специальное ожидание с игнорированием
        // StaleElementReferenceException, потому что искомый элемент подменяется
        // каким-то виртуальным элементом при рендеринге (соответственно, старый элемент
        // теряется и становится недоступен)
        new WebDriverWait(driver, 5)
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver d) -> {
                    addRecipeBtn.click();
                    return true;
                });
    }

    @Step("Нажимаем на аватар пользователя")
    public RecipesPage clickProfileAvatar(){
        profileAvatar.click();
        return this;
    }

    @Step("Нажимаем Выйти из профиля")
    public void clickOutBtn() {
        outBtn.click();
    }

    @Step("Нажимаем ссылку для перехода в натсройки профиля")
    public  void clickProfileLink() {
        profileLink.click();
    }

    @Step("Нажимаем на фильтр Категория блюд")
    public void clickCategoryFilter() {
        categoryFilter.click();
    }

    @Step("Проверяем, что список с Категориями развернут")
    public boolean categoryItemIsExists() {
       return categoryItem.isDisplayed();
    }

    @Step("Нажимаем на фильтр Кухни")
    public void clickCountryFilter() {
        countryFilter.click();
    }

    @Step("Проверяем, что список Кухни развернут/свернут")
    public boolean countryItemIsExists() {
        return countryItem.isDisplayed();
    }

    @Step("Нажимаем на фильтр Характеристики")
    public void clickCharacteristicsFilter() {
        characteristicsFilter.click();
    }

    @Step("Проверяем, что список Характеристики развернут/свернут")
    public boolean characteristicsItemIsExists() {
        return characteristicsItem.isDisplayed();
    }

    @Step("Нажимаем на фильтр Время приема пищи")
    public void clickTimeToEatFilter() {
        timeToEatFilter.click();
    }

    @Step("Проверяем, что список Время приема пищи развернут/свернут")
    public boolean timeToEatItemIsExists() {
        return timeToEatItem.isDisplayed();
    }
}
