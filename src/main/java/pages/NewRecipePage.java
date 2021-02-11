package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import parametrs.Parameters;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * класс для работы на странице создания нового рецепта
 */
public class NewRecipePage extends Page{
    public NewRecipePage(WebDriver driver) {
        super(driver);
    }

    /**
     * опрееление локатора кнопки Сохранить
     */
    @FindBy(xpath = "//button[text()='Сохранить']")
    private WebElement saveBtn;

    /**
     * определение локатора заголовка страницы
     */
    @FindBy(xpath = "//div[contains(@class, 'header_title')]")
    private WebElement addRecipeTitle;

    /**
     * определение локатора поля для ввода названия рецепта
     */
    @FindBy(id = "title")
    private WebElement titleField;

    /**
     * определение локатора поля для ввода ссылки на источник
     */
    @FindBy(id = "link")
    private WebElement linkField;

    /**
     * определение локатора выпадащего списка с категорией
     */
    @FindBy(xpath = "//*[contains(text(), 'категорию')]")
    private WebElement categorySelect;

    /**
     * определяем список локаторов элементов выпаадающего списка
     */
    @FindBy(xpath = "//div/li")
    private List<WebElement> itemsList;

    /**
     * опрееляем локатор текста ошибки о незаполненном поле Название
     */
    @FindBy(xpath = "//div[text() = 'Поле \"Название\" обязательно к заполнению']")
    private WebElement errorMessageNameNotFilled;

    /**
     * опрееляем локатор текста ошибки о незаполненном поле Название
     */
    @FindBy(xpath = "//div[text() = 'Поле \"Категория\" обязательно к заполнению']")
    private WebElement errorMessageCategoryNotFilled;

    /**
     * опрееляем локатор текста ошибки о незаполненном поле Ссылка
     */
    @FindBy(xpath = "//div[text() = 'Поле \"Ссылка на источник\" обязательно к заполнению']")
    private WebElement errorMessageLinkNotFilled;

    /**
     * определяем локатор кнопки Отмена
     */
    @FindBy(xpath = "//button[text() = 'Отмена']")
    private WebElement cancelBtn;

    /**
     * определение локатора выпадащего списка с географией блюда
     */
    @FindBy(xpath = "//*[contains(text(), 'кухню')]")
    private WebElement countrySelect;

    /**
     * опрееление списка локаторов чекбоксов времени приёма пищи
     */
    @FindBy(xpath = "//input[@name='checkbox']/following::span[1]")
    private List<WebElement> timeToEatList;

    /**
     * определение списка локаторов характеристик блюда
     */
    @FindBy(xpath = "//div[contains(@class, 'icons')]/div")
    private List<WebElement> characteristicsList;

    /**
     * определение локатора поля для загрузки иконки блюда
     */
    @FindBy(id = "avatar")
    private WebElement avatarInput;

    /**
     * определяем локатор текстового поля Описание
     */
    @FindBy(id = "description")
    private WebElement descriptionInput;

    /**
     *метод для генерации уникального названия рецепта за счет добавления текущей даты и времени
     * @return строку с уникальным значением
     */
    public String testTitle() {
        String title = "Recipe" + new SimpleDateFormat("dd-MM-yyyy hh-mm").format(new Date());
        return title;
    }

    @Step("Нажимаем кнопку Сохранить")
    public void clickSaveBtn(){
        saveBtn.click();
    }

    @Step("Проверяем, что открыта страница добавления рецепта")
    public boolean isOpenNewRecipePage() {
        return  addRecipeTitle.getText().equals("Добавить рецепт");
    }

    @Step("Заполняем название рецепта")
    public NewRecipePage inputTitle(String title) {
        titleField.sendKeys(title);
        return this;
    }

    @Step("Заполняем поле ссылки на источник")
    public NewRecipePage inputLink(String link) {
        linkField.sendKeys(link);
        return this;
    }

    @Step("Открываем выпадающий список для выбора категории")
    public NewRecipePage clickCategorySelect() {
        //пришлось добавить явное ожидание, т.к. не успевает загрузиться список категорий
        //что вызывает ошибку Cannot read property 'map'of null - белый экран
        try {
            MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
        categorySelect.click();
        return this;
    }

    @Step("Выбираем категорию")
    public NewRecipePage clickCategoryItem () {
        WebElement testItemCategory = getListItem();
        testItemCategory.click();
        return this;
    }

    @Step("Проверка сообщения о необходимости заполнить поле Название")
    public boolean errorNameNotFilled(){
        return errorMessageNameNotFilled.isDisplayed();
    }

    @Step("Проверка сообщения о необходимости заполнить поле Категория")
    public boolean errorCategoryNotFilled(){
        return errorMessageCategoryNotFilled.isDisplayed();
    }

    @Step("Проверка сообщения о необходимости заполнить поле Ссылка")
    public boolean errorLinkNotFilled(){
        return errorMessageLinkNotFilled.isDisplayed();
    }

    @Step("Нажимаем кнопку Отмена")
    public NewRecipePage clickCancelBtn() {
        cancelBtn.click();
        return this;
    }

    @Step("Открываем выпадающий список с кухнями мира ")
    public NewRecipePage clickCountrySelect() {
        countrySelect.click();
        return this;
    }

    @Step("Выбираем кухню")
    public NewRecipePage clickCountryItem () {
        WebElement testItemCountry = getListItem();
        testItemCountry.click();
        return this;
    }

    /**
     *метод для опрееления случайного элемента из выпадающего списка
     * @return локатор случайного элемента из выпадающего списка
     */
    public WebElement getListItem() {
        Random random = new Random();
        if (itemsList.size() == 0) {
            waitForElementVisibility(itemsList.get(0));
        }
        int num = random.nextInt(itemsList.size());
        return itemsList.get(num);
    }

    @Step("Выставляем все чекбоксы времени приёма пищи")
    public NewRecipePage clickAllCheckboxTimeToEat(){
        for (WebElement elem: timeToEatList) {
            elem.click();
        }
        return this;
    }

    @Step("Выбираем все характеристики блюда")
    public NewRecipePage clickAllСharacteristics(){
        for (WebElement elem: characteristicsList) {
            elem.click();
        }
        return this;
    }

    @Step("Загружаем иконку для рецепта")
    public NewRecipePage uploadAvatar() {
        File avatar = new File("src/main/resources/image.jpg");
        avatarInput.sendKeys(avatar.getAbsolutePath());
        return this;
    }

    @Step("Заполняем Описание")
    public  NewRecipePage inputDescription() {
        descriptionInput.sendKeys(Parameters.TEST_DESCRIPTION);
        return this;
    }
}
