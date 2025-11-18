package tests;

import base.BaseTest;
import listener.BaseListener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;

import java.time.Duration;
import java.util.Set;

@Listeners(BaseListener.class)
public class DashBoardTest extends BaseTest {
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @Override
    @BeforeMethod
    public void setUp(){
        System.out.println("-----Launching Browser-----");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
    }

    @Test
    public void validPageLoadTest(){
        wait.until(ExpectedConditions.urlContains("index"));
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current Url: "+currentUrl);
        Assert.assertEquals(driver.getCurrentUrl(),currentUrl,"Page load after login did not successful.");
    }

    @Test
    public void visibleHeadingTest(){
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        Assert.assertTrue(dashBoardPage.visibilityOfHeading(),"Dashboard heading is not visible.");
    }

    @Test
    public void upgradeTest(){
        SoftAssert softAssert = new SoftAssert();
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        softAssert.assertTrue(dashBoardPage.visibilityOfUpgrade(),"Upgrade button did not appear.");
        String parentWindow = driver.getWindowHandle();
        driver.findElement(dashBoardPage.dashboardUpgrade).click();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window:allWindows){
            if (!window.equals(parentWindow)){
                driver.switchTo().window(window);
                break;
            }
        }
        wait.until(ExpectedConditions.urlContains("advanced"));
        softAssert.assertTrue(driver.getCurrentUrl().contains("advanced"),"Upgrade button not work properly.");
        softAssert.assertAll();
    }

    @Test
    public void profilePictureTest(){
        SoftAssert softAssert = new SoftAssert();
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        softAssert.assertTrue(dashBoardPage.visibilityOfProfilePicture(),"Profile picture not visible.");
        driver.findElement(dashBoardPage.profilePicture).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("About")));
        softAssert.assertTrue(driver.findElement(By.linkText("About")).isDisplayed(),"Profile picture work not properly.");
        softAssert.assertAll();
    }

    @Test
    public void visibilityOfTimeTab(){
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        Assert.assertTrue(dashBoardPage.visibilityOfTimeAtWorkTab(),"Time At Work Tab not visible.");
    }

    @Test
    public void punchInFunctionality(){
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        Assert.assertTrue(dashBoardPage.punchInButtonWork().contains("punchIn"),"PunchIn button in time at work tab not work properly.");
        System.out.println("Current url after click punch button: "+driver.getCurrentUrl());
    }

    @Test
    public void visibilityOfMyActionTab(){
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        Assert.assertTrue(dashBoardPage.visibilityOfMyActionTab(),"My Action Tab not visible.");
    }

    @Test
    public void selfReviewTest(){
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        String reviewUrl = dashBoardPage.selfReviewTab();
        Assert.assertTrue(reviewUrl.contains("performance"),"Self-review didn't open.");
        System.out.println("Self-review Page Url: "+reviewUrl);
    }

    @Test
    public void orangeHrmFooterLinkTest(){
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        String currentUrl = driver.getWindowHandle();
        dashBoardPage.footerLinkUrl();
        wait.until(ExpectedConditions.urlContains(".com"));
        Set<String> allWindows = driver.getWindowHandles();
        for (String window:allWindows){
            if (!window.equals(currentUrl)) {
                driver.switchTo().window(window);
                break;
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().contains(".com"),"Footer link not working.");
        System.out.println("Current url: "+driver.getCurrentUrl());
    }

    @Test
    public void empByLocationTest(){
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        dashBoardPage.checkEmpByLocation();
    }

    @Test
    public void empBySubUnitsTest(){
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        dashBoardPage.checkEmpBySubUnits();
    }

    @Override
    @AfterMethod
    public void tearDown(){
        System.out.println("-----Closing Browser-----");
        if (driver != null){
            driver.quit();
        }
    }
}
