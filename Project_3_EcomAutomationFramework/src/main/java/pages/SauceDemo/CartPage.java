package pages.SauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage{
    private WebDriver driver;

    private By cartButton = By.cssSelector(".shopping_cart_link");
    public By product = By.cssSelector(".inventory_item_name ");
    private By productAddButton = By.cssSelector(".btn.btn_primary.btn_small.btn_inventory ");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public void cart(){
        driver.findElement(cartButton).click();
    }

    public void productAddTest(){
        String productName = driver.findElement(product).getText();
        driver.findElement(productAddButton).click();
        System.out.println(productName+" Product successfully added to cart.");
        cart();
    }

}
