package pages.SauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;


    public ProductsPage(WebDriver driver){
        this.driver = driver;
    }
    //locator
    private By productImage = By.cssSelector(".inventory_item_img img");
    private By addToCartButton = By.cssSelector("btn.btn_primary.btn_small.btn_inventory");
    private By productName = By.cssSelector(".inventory_item_name ");
    private By twitter = By.linkText("Twitter");
    private By cartButton = By.cssSelector(".btn.btn_primary.btn_small.btn_inventory ");

    //action
    public int getTotalImages(){
        List<WebElement> images = driver.findElements(productImage);
        return images.size();
    }

    public boolean areImagesBroken(){
        List<WebElement> productImages = driver.findElements(productImage);
        for (WebElement img:productImages) {
            String src = img.getAttribute("src");
            if (src == null || src.trim().isEmpty() || src.contains("404")) {
                System.out.println("Broken Image found: "+src);
                return true;
            }
        }
        return false;
    }

    public boolean areAddToCartButtonsNotClickable(){
        List<WebElement> addToCartButtons = driver.findElements(addToCartButton);
        boolean clickFailed = false;
        for (int i=0; i<addToCartButtons.size(); i++){
            try{
                addToCartButtons.get(i).click();
            }catch (Exception e){
                System.out.println("Button click failed at index: " + i);
                clickFailed = true;
            }
        }
        return false;
    }

    public boolean areProductRight(){
        List<WebElement> productNames = driver.findElements(productName);
        for (WebElement name:productNames){
            if (!(name.getText().contains("Sauce"))) {
                System.out.println("Incorrect product name found: "+name.getText());
                return false;
            }
        }
        return true;
    }

    public WebElement areTwitterPerfect(){
        WebElement element = driver.findElement(twitter);
        return element;
    }

    public int areCartButtonWork(){
        int i = 0;
        List<WebElement> cartButtons = driver.findElements(cartButton);
        for (WebElement button:cartButtons){
            button.click();
            i++;
        }
        return i;
    }
}
