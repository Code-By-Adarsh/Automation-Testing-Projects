package tests;

import base.BaseTest;
import listener.BaseListener;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SauceDemo.LoginPage;
import pages.SauceDemo.ProductsPage;

import java.time.Duration;
import java.util.Set;

@Listeners(BaseListener.class)
public class ProductPageTest extends BaseTest {
    //All the test perform from standard user view.
    private WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    private void wait1(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

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
    public void verifyHeader(){
        WebElement element = driver.findElement(By.cssSelector(".app_logo"));
        Assert.assertTrue(element.isDisplayed(),"Swag Labs is not visible.");
    }

    @Test
    public void verifyBurgerButton(){
        WebElement element = driver.findElement(By.cssSelector("#react-burger-menu-btn"));
        Assert.assertTrue(element.isDisplayed(),"Burger button is not visible.");
        element.click();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#react-burger-cross-btn")));
        Assert.assertTrue(element1.isDisplayed(),"Navigation bar button is not working properly.");
    }

    @Test
    public void verifyCartIconAndFunction(){
        WebElement element = driver.findElement(By.cssSelector(".shopping_cart_link"));
        Assert.assertTrue(element.isDisplayed(),"Cart icon is not visible.");
        element.click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://www.saucedemo.com/cart.html"),"Cart button is not working properly.");
    }

    @Test
    public void verifyProductTitle(){
        WebElement element = driver.findElement(By.cssSelector("span[data-test='title']"));
        Assert.assertTrue(element.isDisplayed(),"Product title is not visible.");
    }

    @Test
    public void verifyFilterIcon(){
        WebElement element = driver.findElement(By.cssSelector(".product_sort_container"));
        Assert.assertTrue(element.isDisplayed(),"Filter icon is not visible.");
    }

    @Test
    public void verifyProductImages(){
        ProductsPage productsPage = new ProductsPage(driver);
        System.out.println("Total images: "+productsPage.getTotalImages());
        Assert.assertFalse(productsPage.areImagesBroken(),"Broken image available.");
    }

    @Test
    public void verifyProductName(){
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertFalse(productsPage.areProductRight(),"Expected: Fault in product name But all product name is right.");
    }

    @Test
    public void verifyFooter(){
        WebElement element = driver.findElement(By.cssSelector(".footer"));
        Assert.assertTrue(element.isDisplayed(),"Footer is not visible.");
    }

    @Ignore
    @Test
    public void verifyTwitter(){
        ProductsPage productsPage = new ProductsPage(driver);
        String mainWindow = driver.getCurrentUrl();
        Assert.assertTrue(productsPage.areTwitterPerfect().isDisplayed(),"Twitter icon is not visible.");
        WebElement element1 = productsPage.areTwitterPerfect();
        System.out.println("Main url: "+mainWindow);
        element1.click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        wait1();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Set<String> allWindows = driver.getWindowHandles();
        for (String window:allWindows){
            if (!window.equals(mainWindow)){
                driver.switchTo().window(window);
                break;
            }
        }
        wait.until(ExpectedConditions.urlContains("x.com/saucelabs"));
        System.out.println("Current url: "+driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("x.com/saucelabs"),"Url doesn't matched.");
    }

    @Test
    public void verifyCartButton(){
        ProductsPage productsPage = new ProductsPage(driver);
        int itemInCart = productsPage.areCartButtonWork();
        System.out.println("Total carts clicked buttons: "+itemInCart);
        String element = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        System.out.println("Total item added: "+element);
        Assert.assertEquals(itemInCart,Integer.parseInt(element),"Expected: Total 6 items added to cart but there is error in cart button working.");
    }

    @Override
    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
