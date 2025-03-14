package page;

//shop page class

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
//import testdata class
import testdata.item;

public class shop {
    WebDriver driver;
    WebDriverWait wait;
    //variable
    //declared outside method to reuse in all method
    String itemName;
    By buyItem;

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
        //pass item object to local object
        item currItem=itemBuy;
        //set current item properties
        this.itemName=currItem.itemName;
        //this is dynamic based on what we pass in the object
        this.buyItem = By.xpath("//h4[@class='product-title ng-binding'][text()='" + itemName + "']/following-sibling::p/a[text()='Buy']");
        int currCount = itemBuy.itemCount;

        wait.until(ExpectedConditions.presenceOfElementLocated(buyItem));
        
        //click buy button based on item object count itemCount property
        for (int count=0;count < currCount;count++){
            //get cart total count before buy
            String currCart =  driver.findElement(cartTotalCount).getText();
            //parse to integer
            int oldCart_int = Integer.parseInt(currCart);
            //click buy
            driver.findElement(buyItem).click();
            //get car total count after buy
            currCart =  driver.findElement(cartTotalCount).getText();
            //parse to inetger
            int currCart_int = Integer.parseInt(currCart);
            //validate cart total count increased
            Assert.assertEquals(currCart_int, oldCart_int+1);
        }
            
    }



    


    //method

}
