package performance.tests.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;
import performance.utils.property.PropertyUtils;

/**
 * Created by andriy.chornyak on 11/20/2018.
 */
public class SetupSteps  extends ScenarioSteps {

    @Step("Open front page")
    public void openFrontPage() {
        String frontPageUrl = PropertyUtils.ENDPOINT_PROPERTY.getProperty("front.page");
        getDriver().navigate().to(frontPageUrl);
    }

    @Step("Init driver")
    public void initDriver() {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
    }
}
