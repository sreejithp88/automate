package Utility;

import Base.Base;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Screenshot extends Base {

    private static final String baseFolder = System.getProperty("user.dir") + "/src/test/java/Screenshot/";
    private static final Map<String, List<String>> scenarioScreenshots = new HashMap<>();
    private static String currentFeature = "";
    private static String currentScenario = "";

    // Called by Hooks before each scenario
    public static void startScenario(String featureName, String scenarioName) {
        currentFeature = featureName.replaceAll("[^a-zA-Z0-9]", "_");
        currentScenario = scenarioName.replaceAll("[^a-zA-Z0-9]", "_");
        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());

        String folderPath = baseFolder + currentFeature + "/" + currentScenario + timestamp + "/";
        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdirs();

        scenarioScreenshots.put(currentScenario, new ArrayList<>());
    }

    // Take screenshot and store in current scenario folder
    public static String TakeScreenshots(String screenshotName) {
        if (driver == null) return null;

        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
        String fileName = (screenshotName + "_" + timestamp + ".png").replaceAll("[^a-zA-Z0-9_.]", "_");

        String folderPath = baseFolder + currentFeature + "/" + currentScenario + "/";
        String filePath = folderPath + fileName;

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(filePath));

            scenarioScreenshots.get(currentScenario).add(filePath);
            System.out.println("✅ Screenshot saved: " + filePath);
        } catch (IOException e) {
            System.out.println("❌ Error saving screenshot: " + e.getMessage());
        }

        return filePath;
    }

    public static Map<String, List<String>> getScenarioScreenshots() {
        return scenarioScreenshots;
    }

    public static String getCurrentFeature() {
        return currentFeature;
    }

    public static String getCurrentScenario() {
        return currentScenario;
    }

    public static String getCurrentScenarioFolder() {
        return baseFolder + currentFeature + "/" + currentScenario + "/";
    }
}
