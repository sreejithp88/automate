package Runner;

import Base.Base;
import Utility.Screenshot;
import Utility.ScreenshotPDFGenerator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.List;

public class Hooks extends Base{

    @Before
    public void beforeScenario(Scenario scenario) {
        String featureName = getFeatureNameFromScenario(scenario);
        Screenshot.startScenario(featureName, scenario.getName());
        System.out.println("▶ Starting Scenario: " + featureName + " → " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            List<String> screenshots = Screenshot.getScenarioScreenshots().get(Screenshot.getCurrentScenario());
            if (screenshots != null && !screenshots.isEmpty()) {
                ScreenshotPDFGenerator.createPdfFromScreenshots(
                        Screenshot.getCurrentScenarioFolder(), screenshots);
                System.out.println("📄 PDF created for scenario: " + scenario.getName());
            }
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
