package mycookingbookTests;

import helpers.Auth;
import helpers.WebDriverProvider;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import pages.LandingPage;
import pages.LoginPage;
import pages.RecipesPage;
import parametrs.Parameters;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * проверка авторизации и деавторизации
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthTest {
     public WebDriver driver;
     public LandingPage landingPage;
     public LoginPage loginPage;
     public RecipesPage recipesPage;

    @BeforeAll
    public void setup(){
        driver = WebDriverProvider.createDriver();
        driver.get(Parameters.BASE_URL);
        landingPage = new LandingPage(driver);
        loginPage = new LoginPage(driver);
        recipesPage= new RecipesPage(driver);
    }

    @Test
    @Description(value = "Тест для проверки авторизации")
    public void testLogin(){
        landingPage.clickEnterBtn();
        loginPage.inputLogin(Parameters.LOGIN)
                .inputPassword(Parameters.PASSWORD)
                .clickLoginBtn();

        assertTrue(recipesPage.addRecipeBtnIsExists());
        Auth.logout(driver);
    }

    @Test
    @Description(value = "Тест для проверки разлогина")
    public  void testLogout(){
        Auth.authorization(driver);
        recipesPage.clickProfileAvatar()
                    .clickOutBtn();
        assertTrue(landingPage.loginBtnIsExists());
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
