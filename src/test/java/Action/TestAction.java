package Action;

import Base.Base;
import Utility.Screenshot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import Object.TestObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import TestData.amazdata;
import Utility.JsonReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class  TestAction extends Base {
//    WebDriver driver = new ChromeDriver();

    TestObjects testObjects ;
    amazdata amazdata = JsonReader.getData();
//    public  TestAction() {
//    driver = new ChromeDriver();
//    testObjects = new TestObjects(driver);
//    }


    public void Gotoamazon() throws InterruptedException {
        if(driver == null){
            Base.initializeDriver();
        }
//
        testObjects = new TestObjects(driver);
        driver.get(amazdata.getUrl());
        Thread.sleep(5000);
        Screenshot.TakeScreenshots(System.getProperty("currentFeature"),"HOME_PAGE");

    }

    public void searchproduct(String product) throws InterruptedException {
//    driver.findElement(By.id("twotabsearchtextbox"));
        Thread.sleep(10000);
        try {
            if (testObjects.continuebutton.isDisplayed()) {
                testObjects.continuebutton.click();
            }
        } catch (Exception e) {
            System.out.println("Continue shopping button not found — skipping...");
        }
        testObjects.Search.sendKeys(amazdata.getSearchProduct());
        Screenshot.TakeScreenshots(System.getProperty("currentFeature"),"Product_Search");
        Thread.sleep(1010);
        testObjects.Search.sendKeys(Keys.ENTER);
    }

    public void selectproductgettext() throws InterruptedException {
        Thread.sleep(5000);
        Screenshot.TakeScreenshots(System.getProperty("currentFeature"),"Search_Result");
        String mainwindow = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement secondItem = wait.until(ExpectedConditions.elementToBeClickable(testObjects.seconditem));

// scroll into view if needed
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", secondItem);
        secondItem.click();
//        testObjects.seconditem.click();
        Thread.sleep(10000);
        Screenshot.TakeScreenshots(System.getProperty("currentFeature"),"Product_Selected");
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> windows = handles.iterator();
//    if (windows.size() > 1) {
//        driver.switchTo().window(windows.get(1));
//    }
        String childwindow = windows.next();
        if (childwindow.equals(mainwindow)) {
            childwindow = windows.next();
        }
        driver.switchTo().window(childwindow);
        String title = driver.getTitle();
        System.out.print(title);
    }
}


