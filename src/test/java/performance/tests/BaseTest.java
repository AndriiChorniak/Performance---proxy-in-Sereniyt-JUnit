package performance.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import performance.tests.steps.SetupSteps;

import static net.thucydides.core.annotations.ClearCookiesPolicy.BeforeEachTest;

/**
 * Created by andriy.chornyak on 11/20/2018.
 */

@RunWith(SerenityRunner.class)
public abstract class BaseTest {

    @Managed(uniqueSession = true, clearCookies = BeforeEachTest)
    protected WebDriver driver;

    @Steps
    public SetupSteps test;

}
