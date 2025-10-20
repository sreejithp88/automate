package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
    public static WebDriver driver;

//    public static void initializeDriver() {
//        if (driver == null) {
//            driver = new ChromeDriver();
//            driver.manage().window().maximize();
//        }
//    }

    public static void initializeDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();

            // Headless mode for CI
            options.addArguments("--headless=new");  // Chrome 111+ uses "new" headless mode
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");

            // Unique profile to avoid user-data-dir conflict
            options.addArguments("--user-data-dir=/tmp/chrome-" + System.currentTimeMillis());

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
