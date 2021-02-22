package helpers;

import java.util.*;

import io.restassured.http.ContentType;
import org.openqa.selenium.WebDriver;
import parametrs.Parameters;
import static io.restassured.RestAssured.given;

/**
 * класс для удаления сущностей, созданных в процессе запуска тестов
 */
public class CleanTestsData {

    private WebDriver driver;

    public  CleanTestsData(WebDriver driver){
        this.driver = driver;
    }

    /**
     * метод удаляет рецепты по списку id
     */
    public void deleteRecipes(Set listOfRecipesID){
        for(Object recipesId: listOfRecipesID) {
           given()
                   .cookies(getMyCookies())
                   .accept(ContentType.JSON)
                   //.header("Accept", "application/json")
                   .when().delete(Parameters.BASE_URL + "api/recipes/" + recipesId)
                   .then().statusCode(204);
        }
    }

    /**
     * метод для получения cookies из браузера
     * @return cookies
     */
    private Map<String, Object> getMyCookies() {
        Map<String, Object> cookies = new HashMap<>();
        cookies.put("token", driver.manage().getCookieNamed("token").getValue());
        cookies.put("userId", driver.manage().getCookieNamed("userId").getValue());
        return cookies;
    }
}
