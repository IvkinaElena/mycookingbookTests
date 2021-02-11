package helpers;

import org.openqa.selenium.WebDriver;
import pages.LandingPage;
import pages.LoginPage;
import pages.RecipesPage;
import parametrs.Parameters;

/**
 * в класс вынесены методы логина и логаута
 */
public class Auth {

    public static void authorization(WebDriver driver) {
        driver.get(Parameters.BASE_URL);
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        landingPage.clickEnterBtn();
        loginPage.inputLogin(Parameters.LOGIN)
                .inputPassword(Parameters.PASSWORD)
                .clickLoginBtn();
    }

    public static void logout(WebDriver driver) {
        RecipesPage recipesPage = new RecipesPage(driver);
        recipesPage.clickProfileAvatar()
                .clickOutBtn();
    }
}
