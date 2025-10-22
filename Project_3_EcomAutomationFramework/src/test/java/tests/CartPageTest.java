package tests;

import base.BaseTest;
import listener.BaseListener;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SauceDemo.CartPage;
import pages.SauceDemo.LoginPage;

import java.time.Duration;

@Listeners(BaseListener.class)
public class CartPageTest extends BaseTest {


    @Override
    @BeforeMethod
    public void setUp(){
        System.out.println("Launching browser.....");
        System.setProperty("webdriver.chrome.driver","C:\\selenium webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }

    @Test
    public void cartUrl(){
        CartPage cartPage = new CartPage(driver);
        cartPage.cart();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"),"Cart page is miss.");
    }

    @Test
    public void cartTitle(){
        CartPage cartPage = new CartPage(driver);
        cartPage.cart();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertTrue(title.contains("Cart"),"Title not matched.");
    }

    @Test
    public void verifyQTY(){
        CartPage cartPage = new CartPage(driver);
        cartPage.cart();
        String title = driver.findElement(By.cssSelector(".cart_quantity_label")).getText();
        Assert.assertTrue(title.contains("QTY"),"QTY not matched.");
    }

    @Test
    public void verifyDesc(){
        CartPage cartPage = new CartPage(driver);
        cartPage.cart();
        String title = driver.findElement(By.cssSelector(".cart_desc_label")).getText();
        Assert.assertTrue(title.contains("Desc"),"Description title not matched.");
    }

    @Test
    public void verifyFooter(){
        CartPage cartPage = new CartPage(driver);
        cartPage.cart();
        WebElement element = driver.findElement(By.cssSelector(".footer"));
        Assert.assertTrue(element.isDisplayed(),"Footer is not visible.");
    }

    @Test
    public void verifyContinueShoppingVisibility(){
        CartPage cartPage = new CartPage(driver);
        cartPage.cart();
        WebElement element = driver.findElement(By.id("continue-shopping"));
        Assert.assertTrue(element.isDisplayed(),"Continue shopping button is not visible.");
    }

    @Test(dependsOnMethods = "verifyContinueShoppingVisibility")
    public void verifyContinueShoppingWorking(){
        CartPage cartPage = new CartPage(driver);
        cartPage.cart();
        WebElement element = driver.findElement(By.id("continue-shopping"));
        element.click();
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current url: "+currentUrl);
        Assert.assertTrue(currentUrl.contains("inventory.html"),"Continue Shopping button is not working.");
    }

    @Test
    public void verifyCheckoutVisibility(){
        CartPage cartPage = new CartPage(driver);
        cartPage.cart();
        WebElement element = driver.findElement(By.cssSelector("#checkout"));
        Assert.assertTrue(element.isDisplayed(),"Checkout button is not visible.");
    }

    @Test(dependsOnMethods = "verifyCheckoutVisibility")
    public void verifyCheckoutWorking(){
        CartPage cartPage = new CartPage(driver);
        cartPage.cart();
        WebElement element = driver.findElement(By.cssSelector("#checkout"));
        element.click();
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current url: "+currentUrl);
        Assert.assertTrue(currentUrl.contains("one.html"),"Checkout button is not working.");
    }

    @Test
    public void addToCartTest(){
        CartPage cartPage = new CartPage(driver);
        cartPage.productAddTest();
        String productDetail = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        Assert.assertEquals(driver.findElement(cartPage.product).getText(),productDetail,"Product not added in cart. Add to cart button is not working properly in Cart page.");
    }

    @Override
    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
