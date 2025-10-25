package Runner;

import Utility.Screenshot;
import Utility.ScreenshotPDFGenerator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        String featureName = getFeatureNameFromScenario(scenario);
        Screenshot.startScenario(featureName, scenario.getName());
        System.out.println("▶ Starting Scenario: " + featureName + " → " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            ScreenshotPDFGenerator.createPdfFromScreenshots(
                    Screenshot.getCurrentScenarioFolder(),
                    Screenshot.getScenarioScreenshots().get(Screenshot.getCurrentScenario())
            );
            System.out.println("📄 PDF created for scenario: " + scenario.getName());
        } catch (Exception e) {
            System.out.println("⚠️ PDF generation failed: " + e.getMessage());
        }
    }

    private String getFeatureNameFromScenario(Scenario scenario) {
        String[] raw = scenario.getUri().toString().split("/");
        String featureFile = raw[raw.length - 1];
        return featureFile.replace(".feature", "");
    }
}
