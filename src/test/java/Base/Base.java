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

            // Headless only in CI or if environment variable is set
            if (System.getenv("CI") != null || System.getProperty("headless") != null) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }

            // Set large window size to avoid zero-size elements
            options.addArguments("--window-size=1920,1080");

            // Use unique user data dir to avoid session conflicts in CI
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
