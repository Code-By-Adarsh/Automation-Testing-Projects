package tests;

import java.util.Random;
import base.BaseTest;
import listener.BaseListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SauceDemo.LoginPage;

@Listeners(BaseListener.class)
public class LoginPageTest extends BaseTest {
    Random rand = new Random();
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void validLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        String [] username = {"standard_user","locked_out_user","problem_user","performance_glitch_user","error_user","visual_user"};
        int randomUsername = rand.nextInt(0,5);
        loginPage.enterUsername(username[randomUsername]);
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        Assert.assertEquals(driver.getTitle(),"Swag Labs","Title not matched.");
    }
}
