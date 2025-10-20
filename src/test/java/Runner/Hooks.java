package Runner;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import Base.Base;
import Utility.Screenshot;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;


public class Hooks extends Base {

    // Will store the current feature name correctly
    private static String currentFeature = "UnknownFeature";

    @Before
    public void beforeScenario(Scenario scenario) {
        initializeDriver();

            String featureName = scenario.getUri().toString()
                    .substring(scenario.getUri().toString().lastIndexOf("/") + 1)
                    .replace(".feature", "");
            scenario.attach(("Feature Name: " + featureName).getBytes(), "text/plain", "Feature Info");
            System.setProperty("currentFeature", featureName);
        }




        @After
    public void afterScenario(Scenario scenario) {
        try {
            String screenshotName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");

            // Capture screenshot on failure
            if (scenario.isFailed()) {
                Screenshot.TakeScreenshots(currentFeature, screenshotName);

                // Attach screenshot in report
                byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(bytes, "image/png", screenshotName);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Screenshot capture failed: " + e.getMessage());
        } finally {
            quitDriver();
        }
    }

    // Optional: For manual step-level screenshots
    public static void takeStepScreenshot(String name) {
        Screenshot.TakeScreenshots( currentFeature, name);
    }
}
