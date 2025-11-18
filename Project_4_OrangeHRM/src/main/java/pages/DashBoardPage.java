package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashBoardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By dashboardHeading = By.xpath("//h6[text()='Dashboard']");
    public By dashboardUpgrade = By.className("orangehrm-upgrade-link");
    public By profilePicture = By.cssSelector("img[alt='profile picture']");
    private By timeAtWork = By.xpath("//p[text()='Time at Work']");
    private By punchInButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div[1]/div[2]/button");
    private By myAction = By.xpath("//p[text()='My Actions']");
    private By reviewTab = By.xpath("//p[text()='(1) Pending Self Review']");
    private By footerLink = By.linkText("OrangeHRM, Inc");
    private By empDistributionByLocation = By.xpath("//p[text()='Employee Distribution by Location']");
    private By empDistributionBySubUnits = By.xpath("//p[text()='Employee Distribution by Sub Unit']");

    public DashBoardPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public boolean visibilityOfHeading(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeading));
        return driver.findElement(dashboardHeading).isDisplayed();
    }

    public boolean visibilityOfUpgrade(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardUpgrade));
        return driver.findElement(dashboardUpgrade).isDisplayed();
    }

    public boolean visibilityOfProfilePicture(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(profilePicture));
        return driver.findElement(profilePicture).isDisplayed();
    }

    public boolean visibilityOfTimeAtWorkTab(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(timeAtWork));
        return driver.findElement(timeAtWork).isDisplayed();
    }

    public String punchInButtonWork(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(punchInButton));
        driver.findElement(punchInButton).click();
        wait.until(ExpectedConditions.urlContains("punchIn"));
        String currentUrl = driver.getCurrentUrl();
        return currentUrl;
    }

    public boolean visibilityOfMyActionTab(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(myAction));
        return driver.findElement(myAction).isDisplayed();
    }

    public String selfReviewTab(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(reviewTab));
        driver.findElement(reviewTab).click();
        wait.until(ExpectedConditions.urlContains("performance"));
        return driver.getCurrentUrl();
    }

    public void footerLinkUrl(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(footerLink));
        driver.findElement(footerLink).click();
    }

    public boolean checkEmpByLocation(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(empDistributionByLocation));
        return driver.findElement(empDistributionByLocation).isDisplayed();
    }

    public boolean checkEmpBySubUnits(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(empDistributionBySubUnits));
        return driver.findElement(empDistributionBySubUnits).isDisplayed();
    }
}
