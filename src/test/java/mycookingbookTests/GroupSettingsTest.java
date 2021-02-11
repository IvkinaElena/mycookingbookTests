package mycookingbookTests;

import helpers.Auth;
import helpers.CleanTestsData;
import helpers.ReportListener;
import helpers.WebDriverProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import pages.GroupSettingsPage;
import pages.RecipesPage;
import parametrs.Parameters;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * проверка сценариев на странице Настройка групп
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GroupSettingsTest {
    public WebDriver driver;
    public GroupSettingsPage groupSettingsPage;
    public RecipesPage recipesPage;

    @RegisterExtension
    public ReportListener reportListener = new ReportListener();

    @BeforeAll
    public void setup() {
        driver = WebDriverProvider.createDriver();
        Auth.authorization(driver);
        reportListener.setDriver(driver);
        groupSettingsPage = new GroupSettingsPage(driver);
        recipesPage = new RecipesPage(driver);
        recipesPage.clickSettingsGroupHref();
    }

    @Test
    @Description(value = "Проверка добавления новой группы")
    public void testAddGroup(){
        String testTitleGroup = groupSettingsPage.testTitle();
        groupSettingsPage
                .inputNameGroup(testTitleGroup)
                .clickAddBtn();
        assertTrue(groupSettingsPage.assertCreateNewGroup(testTitleGroup));
    }

    @Test
    @Description(value = "Проверка удаления группы")
    public void testDeleteGroup(){
        String newGroup = groupSettingsPage.createGroup();
        groupSettingsPage.deleteGroup(newGroup);
        assertFalse(groupSettingsPage.assertCreateNewGroup(newGroup));
    }


    @AfterAll
    public void tearDown() {
        groupSettingsPage.deleteAllGroups();
        driver.quit();
    }
}
