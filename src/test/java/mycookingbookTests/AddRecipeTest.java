package mycookingbookTests;

import helpers.*;
import helpers.WebDriverProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import pages.NewRecipePage;
import pages.RecipePage;
import pages.RecipesPage;
import parametrs.Parameters;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * проверка сценариев, связанных с созданием рецепта
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddRecipeTest {
    public WebDriver driver;
    public RecipesPage recipesPage;
    public NewRecipePage newRecipePage;
    public RecipePage recipePage;
    public CleanTestsData cleanTestsData;
    public Set<String> recipesID;

    @RegisterExtension
    public ReportListener reportListener = new ReportListener();

    @BeforeAll
    public void setup() {
        driver = WebDriverProvider.createDriver();
        Auth.authorization(driver);
        reportListener.setDriver(driver);
        recipesPage = new RecipesPage(driver);
        newRecipePage = new NewRecipePage(driver);
        recipePage = new RecipePage(driver);
        recipesID = new HashSet<>();
    }

    @Test
    @Description(value = "Тест для проверки создания рецепта с заполнением только обязательных полей")
    public void testAddRecipeWithOnlyRequiredFields() {
        recipesPage.clickAddRecipe();
        String title = newRecipePage.testTitle();
        newRecipePage
                .inputTitle(title)
                .inputLink(Parameters.LINK_RECIPE)
                .clickCategorySelect()
                .clickCategoryItem()
                .clickSaveBtn();
        assertTrue(recipePage.checkTitle(title));
        recipesID.add(recipePage.getRecipeID());
    }

    @Test
    @Description(value = "Тест для проверки появления сообщений о необходимости заполнить обязательные поля")
    public void testAddRecipeWithEmptyFields() {
        recipesPage.clickAddRecipe();
        newRecipePage.clickSaveBtn();
        assertTrue(newRecipePage.errorNameNotFilled()
                & newRecipePage.errorCategoryNotFilled()
                & newRecipePage.errorLinkNotFilled());
   }

   @Test
   @Description(value = "Тест для проверки возврата к списку рецептов при нажатии кнопки Отмена на странице создания рецепта")
   public void testClickCancel() {
       recipesPage.clickAddRecipe();
       newRecipePage.clickCancelBtn();
       assertTrue(recipesPage.addRecipeBtnIsExists());
   }

   @Test
   @Description(value = "Тест для проверки создания рецепта со всеми заполненными полями")
   public void testAddRecipeWithAllFilledFields() {
       recipesPage.clickAddRecipe();
       String title = newRecipePage.testTitle();
       newRecipePage
               .inputTitle(title)
               .inputLink(Parameters.LINK_RECIPE)
               .clickCategorySelect()
               .clickCategoryItem()
               .clickCountrySelect()
               .clickCountryItem()
               .clickAllCheckboxTimeToEat()
               .clickAllСharacteristics()
               .uploadAvatar()
               .inputDescription()
               .clickSaveBtn();
       assertTrue(recipePage.checkTitle(title));
       recipesID.add(recipePage.getRecipeID());

   }

    @AfterEach
    public void backInMainPage() {
        recipesPage.clickLogo();
    }

    @AfterAll
    public void tearDown() {
        cleanTestsData = new CleanTestsData(driver);
        cleanTestsData.deleteRecipes(recipesID);
        driver.quit();
    }
}
