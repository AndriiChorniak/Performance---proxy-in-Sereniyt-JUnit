package performance.tests.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by andriy.chornyak on 11/20/2018.
 */
public class SetupSteps  extends ScenarioSteps {

    public static final Properties property;

    static {
        FileInputStream fileInputStream;
        property = new Properties();

        try {
            fileInputStream = new FileInputStream("src/test/resources/endpoints.properties");
            property.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Property file is missing");
        }
    }

    @Step("Open front page")
    public void openFrontPage() {
        String frontPageUrl = property.getProperty("front.page");
        getDriver().navigate().to(frontPageUrl);
    }

    @Step("Init driver")
    public void initDriver() {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
    }
}
