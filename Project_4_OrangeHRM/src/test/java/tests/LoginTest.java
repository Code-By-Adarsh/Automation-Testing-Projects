package tests;

import base.BaseTest;
import listener.BaseListener;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

@Listeners(BaseListener.class)
public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        Assert.assertTrue(dashBoardPage.visibilityOfHeading(),"Dashboard not visible. \nLogin -> Failed.");
    }

    @Test
    public void invalidUsernameTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("student");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.invalidCredential));
        Assert.assertEquals(loginPage.invalidCredentialError(),"Invalid credentials","Invalid credential error did not appeared on wrong username.");
        System.out.println("Error on Invalid Username: "+loginPage.invalidCredentialError());
    }

    @Test
    public void invalidPasswordTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("Adarsh123");
        loginPage.clickLogin();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.invalidCredential));
        Assert.assertEquals(loginPage.invalidCredentialError(),"Invalid credentials","Invalid credential error did not appeared on wrong password.");
        System.out.println("Error on Invalid Password: "+loginPage.invalidCredentialError());
    }

    @Test
    public void blankUsernameTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.requiredField));
        Assert.assertEquals(loginPage.requiredError(),"Required","Required alert not appeared on blank username.");
    }

    @Test
    public void blankPasswordTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.requiredField));
        Assert.assertEquals(loginPage.requiredError(),"Required","Required alert not appeared on blank password.");
    }

    @Test
    public void bothBlankTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.requiredField));
        Assert.assertEquals(loginPage.requiredError(),"Required","Required alert not appeared on blank username and password.");
    }

    @Test
    public void caseSensitiveUsernameTest(){
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        Random rand = new Random();
        int randomValue = rand.nextInt(0,4);
        String [] username = {"admin","ADMIN","AdmiN","ADmin","admIn"};
        String Username = username[randomValue];
        loginPage.enterUsername(Username);
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        Assert.assertTrue(dashBoardPage.visibilityOfHeading(),"Dashboard not visible. \nLogin -> Failed.");
        System.out.println("Login with "+Username+" Successful.");
    }
}
