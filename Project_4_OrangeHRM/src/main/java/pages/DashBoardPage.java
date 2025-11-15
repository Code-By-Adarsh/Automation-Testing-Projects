package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashBoardPage {
    private WebDriver driver;

    private By dashboardHeading = By.xpath("//h6[text()='Dashboard']");

    public DashBoardPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean visibilityOfHeading(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeading));
        return driver.findElement(dashboardHeading).isDisplayed();
    }
}
