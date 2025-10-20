package Utility;

import Base.Base;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot extends Base {

   public static String TakeScreenshots (String featureName , String screenshotName){

       String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
       String safeFeature = (featureName == null || featureName.isEmpty()) ? "UnknownFeature" : featureName;
       String fileName = screenshotName + "_" + timestamp + ".png";
       safeFeature = safeFeature + timestamp;

       String basePath = System.getProperty("user.dir") + "/src/test/java/Screenshot/";

       // Folder specific to the feature
       String featureFolderPath = basePath + safeFeature + "/";
       File featureFolder = new File(featureFolderPath);
       if (!featureFolder.exists()) {
           featureFolder.mkdirs();
       }

       // Take screenshot
       File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       String filePath = featureFolderPath + fileName;

       try {
           FileUtils.copyFile(srcFile, new File(filePath));
           System.out.println("✅ Screenshot saved: " + filePath);
       } catch (IOException e) {
           System.out.println("❌ Failed to save screenshot: " + e.getMessage());
       }

       return filePath;
   }
}
