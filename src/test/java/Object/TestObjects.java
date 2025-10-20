package Object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestObjects {

    WebDriver driver;
    public TestObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


     @FindBy(xpath = "//button[text()='Continue shopping']")
     public WebElement continuebutton ;

    @FindBy(id = "twotabsearchtextbox")
    public WebElement Search ;

    @FindBy(xpath = "((//div[@role='listitem'])[2]//a)[1]")
    public WebElement seconditem ;




}
