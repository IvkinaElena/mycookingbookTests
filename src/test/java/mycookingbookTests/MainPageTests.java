package mycookingbookTests;

import helpers.Auth;
import helpers.ReportListener;
import helpers.WebDriverProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import pages.GroupSettingsPage;
import pages.NewRecipePage;
import pages.ProfileSettingsPage;
import pages.RecipesPage;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * проверка навигации на основной странице со списком рецептов
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainPageTests {
    public WebDriver driver;
    public RecipesPage recipesPage;
    public GroupSettingsPage settingsGroupPage;
    public NewRecipePage newRecipePage;
    public ProfileSettingsPage profileSettingsPage;

    @RegisterExtension
    public ReportListener reportListener = new ReportListener();

    @BeforeAll
    public void setup() {
        driver = WebDriverProvider.createDriver();
        reportListener.setDriver(driver);
        Auth.authorization(driver);
        recipesPage = new RecipesPage(driver);
        settingsGroupPage = new GroupSettingsPage(driver);
        newRecipePage = new NewRecipePage(driver);
        profileSettingsPage = new ProfileSettingsPage(driver);
    }

    @Test
    @Description(value = "Тест проверяет возможность свернуть/развернуть левую панель")
    public void testPanelFolding() {
        recipesPage.clickPanelFoldingBtn();
        assertTrue(!recipesPage.filterPanelIsExists());
        recipesPage.clickPanelFoldingBtn();
        assertTrue(recipesPage.filterPanelIsExists());
    }

    @Test
    @Description(value = "Тест проверяет создание новой группы")
    public void testAddGroup() {
        String testTitleGroup = new SimpleDateFormat("dd-MM-yyyy hh-mm").format(new Date());
        recipesPage.clickAddGroupBtn()
                   .inputTitleGroup(testTitleGroup)
                   .clickAddBtn();
        assertTrue(recipesPage.newGroupIsDisplayed(testTitleGroup));
    }

    @Test
    @Description(value = "Тест проверяет переход на страницу Настройка групп")
    public void testOpenSettingsGroup() {
        recipesPage.clickSettingsGroupHref();
        assertTrue(settingsGroupPage.titlePageIsExists());
    }

    @Test
    @Description(value = "Тест проверяет переход на страницу добавления нового ецепта")
    public void testOpenNewRecipePage() {
        recipesPage.clickAddRecipe();
        assertTrue(newRecipePage.isOpenNewRecipePage());
    }

    @Test
    @Description(value = "Тест проверяет переход на страницу натсроек профиля")
    public void testOpenProfileSettings() {
        recipesPage.clickProfileAvatar()
                    .clickProfileLink();
        assertTrue(profileSettingsPage.profileSettingsTitleIsExists());
    }

    @Test
    @Description(value = "Тест для проверки разворачивания/своачивания фильтра Категория блюд")
    public void testOpenCategoryFilter() {
        recipesPage.clickCategoryFilter();
        assertTrue(recipesPage.categoryItemIsExists());
        recipesPage.clickCategoryFilter();
        assertTrue(!recipesPage.categoryItemIsExists());
    }

    @Test
    @Description(value = "Тест для проверки разворачивания/своачивания фильтра Кухни")
    public void testOpenCountryFilter() {
        recipesPage.clickCountryFilter();
        assertTrue(recipesPage.countryItemIsExists());
        recipesPage.clickCountryFilter();
        assertTrue(!recipesPage.countryItemIsExists());
    }

    @Test
    @Description(value = "Тест для проверки разворачивания/своачивания фильтра Характеристики")
    public void testOpenCharacteristicsFilter() {
        recipesPage.clickCharacteristicsFilter();
        assertTrue(recipesPage.characteristicsItemIsExists());
        recipesPage.clickCharacteristicsFilter();
        assertTrue(!recipesPage.characteristicsItemIsExists());
    }

    @Test
    @Description(value = "Тест для проверки разворачивания/своачивания фильтра Вемя приема пищи")
    public void testOpenTimeToEatFilter() {
        recipesPage.clickTimeToEatFilter();
        assertTrue(recipesPage.timeToEatItemIsExists());
        recipesPage.clickTimeToEatFilter();
        assertTrue(!recipesPage.timeToEatItemIsExists());

    }

    @AfterEach
    public void backInMainPage() {
        recipesPage.clickLogo();
        if (!recipesPage.filterPanelIsExists()) {
            recipesPage.clickPanelFoldingBtn();
        }
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }

}