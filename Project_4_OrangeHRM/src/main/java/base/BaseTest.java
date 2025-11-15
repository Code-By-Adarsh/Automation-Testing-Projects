package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void setUpSuite(){
        System.out.println("-----Launching Suite: OrangeHRM Automation Project-----");
    }

    @BeforeMethod
    public void setUp(){
        System.out.println("-----Launching Browser-----");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("-----Closing Browser-----");
        if (driver != null){
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownSuite(){
        System.out.println("-----Closing Suite: OrangeHRM Automation Project-----");
    }
}
