package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;


public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void setUpSuite(){
        System.out.println("-----Launching Suite-----");
    }

    @BeforeMethod
    public void setUp(){
        System.out.println("Launching browser.....");
        System.setProperty("webdriver.chrome.driver","C:\\selenium webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Closing browser.....");
        if (driver != null){
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownSuite(){
        System.out.println("-----Closing Suite-----");
    }
}
