package helpers;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * класс для создания скриншотов при падении теста
 */
public class ReportListener implements AfterTestExecutionCallback {

    private WebDriver driver;

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            if(driver == null) {
                throw new RuntimeException("No web driver, set it somewhere");
            }
            makeScreenshot();
        }
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Attachment
    public byte[] makeScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String scrName = "target/allure-results/" + new SimpleDateFormat("dd-MM-yyyy hh-mm").format(new Date()) + ".png";
        FileUtils.copyFile(scrFile, new File(scrName));
        return  FileUtils.readFileToByteArray(scrFile);
    }
}


