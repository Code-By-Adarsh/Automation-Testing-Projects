package tests;

import java.time.Duration;
import java.util.Random;
import base.BaseTest;
import listener.BaseListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SauceDemo.LoginPage;
import pages.SauceDemo.ProductsPage;

@Listeners(BaseListener.class)
public class LoginPageTest extends BaseTest {
    Random rand = new Random();
    SoftAssert softAssert = new SoftAssert();
    public static void wait1(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void standardUserLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),"User did not land on Product page.");
    }

    @Test
    public void lockedUserLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        System.out.println("Error Message: "+loginPage.getErrorDisplayed());
        String error = loginPage.getErrorDisplayed();
        Assert.assertEquals(error,"Epic sadface: Sorry, this user has been locked out.","Error message not displayed.");
    }

    @Test
    public void problemUserLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        loginPage.enterUsername("problem_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl,"https://www.saucedemo.com/inventory.html","User did not land on Product page.");
        Assert.assertTrue(productsPage.areImagesBroken(),"Expected broken images for problem_user, but all images loaded fine!");
    }

    @Test
    public void performaneceGlitchUserLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("performance_glitch_user");
        loginPage.enterPassword("secret_sauce");
        long startTime = System.currentTimeMillis();
        loginPage.clickLogin();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productPageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        long endTime = System.currentTimeMillis();
        long TimeTake = endTime-startTime;
        System.out.println("Time take to load Product page: "+ TimeTake+" ms.");
        Assert.assertFalse(TimeTake<3,"Expected: Product page available after 3 seconds but Product page available under the 3 seconds.");
    }

    @Test
    public void errorUserLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        loginPage.enterUsername("error_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        Assert.assertTrue(productsPage.areAddToCartButtonsNotClickable(),"Expected: Some add to cart button not work properly But all button works properly.");
    }

    @Test()
    public void visualUserLoginTest(){
        //Will be done in Project Page Testing.
        throw new SkipException("Skipping visual_user test for now.");
    }

    @Test
    public void blankBothUserTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        System.out.println("Error Message: "+loginPage.getErrorDisplayed());
        String error = loginPage.getErrorDisplayed();
        Assert.assertEquals(error,"Epic sadface: Username is required","Error message not displayed.");
    }

    @Test
    public void blankBothPasswordTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        System.out.println("Error Message: "+loginPage.getErrorDisplayed());
        String error = loginPage.getErrorDisplayed();
        Assert.assertEquals(error,"Epic sadface: Password is required","Error message not displayed.");
    }
}
