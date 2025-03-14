package page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testdata.item;

public class shop {
    WebDriver driver;
    WebDriverWait wait;
    //variable
    String itemName;
    By buyItem; //= By.xpath("//h4[@class='product-title ng-binding'][text()='" + itemBuy + "']/following-sibling::p/a[text()='Buy']");
    By mnuShop = By.xpath("//ul[@class='nav']//a[contains(text(),'Shop')]");
    By cartTotalCount = By.xpath("//li[@id='nav-cart']//span[@class='cart-count ng-binding']");

    //constructor
    public shop(WebDriver driver){
    this.driver=driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
    }

    public void gotoShop(){
        driver.findElement(mnuShop).click();        
    }

    public void buyItem(item itemBuy){
        item currItem=itemBuy;
        this.itemName=currItem.itemName;
        this.buyItem = By.xpath("//h4[@class='product-title ng-binding'][text()='" + itemName + "']/following-sibling::p/a[text()='Buy']");
        int currCount = itemBuy.itemCount;
        wait.until(ExpectedConditions.presenceOfElementLocated(buyItem));
        //int oldCart_int=0;
        
        for (int count=0;count < currCount;count++){
            String currCart =  driver.findElement(cartTotalCount).getText();
            int oldCart_int = Integer.parseInt(currCart);
            driver.findElement(buyItem).click();
            currCart =  driver.findElement(cartTotalCount).getText();
            int currCart_int = Integer.parseInt(currCart);
            Assert.assertEquals(currCart_int, oldCart_int+1);
        }
            
    }



    


    //method

}
