package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By loginButtonInput = By.cssSelector("button[type='submit']");
    public By invalidCredential = By.xpath("//p[text()='Invalid credentials']");
    public By requiredField = By.xpath("//span[text()='Required']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterUsername(String Username){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(Username);
    }

    public void enterPassword(String Password){
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(Password);
    }

    public void clickLogin(){
        driver.findElement(loginButtonInput).click();
    }

    public String invalidCredentialError(){
        return driver.findElement(invalidCredential).getText();
    }

    public String requiredError(){
        return driver.findElement(requiredField).getText();
    }
}
