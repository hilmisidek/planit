package page;
//cart page class


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

public class cart {
    //variable
    WebDriver driver;
    WebDriverWait wait;
    By mnuCart = By.xpath("//*[@id='nav-cart']");
    String itemName;
    Double itemPrice;
    //item price
    By checkPrice;
    //item subtotal
    By checkSubtotal;
    //item quantity in cart
    By checkQuantity;
    //cart total
    By cartTotal = By.xpath("//strong[@class='total ng-binding']");


    //constructor
    public cart(WebDriver driver){
        this.driver=driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(30));
    }


    //method

    public void gotoCart(){
        driver.findElement(mnuCart).click();
    }

    public double checkSubtotal(item checkItem){
        //pass test object to local item object
        item currItem = checkItem;
        //set object properties
        this.itemName=currItem.itemName;
        this.itemPrice=currItem.itemPrice;
        //dynamic based on item name
        this.checkPrice = By.xpath("//table[@class='table table-striped cart-items']//td[contains(text(),'" + itemName +"')]/following-sibling::td[1]");
        this.checkSubtotal = By.xpath("//table[@class='table table-striped cart-items']//td[contains(text(),'" + itemName + "')]/following-sibling::td[3]");
        this.checkQuantity = By.xpath("//table[@class='table table-striped cart-items']//td[contains(text(),'" + itemName + "')]/following-sibling::td[2]/input");
        //wait until cart is ready
        wait.until(ExpectedConditions.presenceOfElementLocated(checkPrice));
        
        //get item price
        String priceRaw = driver.findElement(checkPrice).getText();
        //convert to double by calling local method splitPrice passing string and retrn as double
        double dblItemPrice = splitPrice(priceRaw);
              
        //get item quantity
        String qtyRaw = driver.findElement(checkQuantity).getDomProperty("value");
        int intQty = Integer.parseInt(qtyRaw);

        //count epected subtotal = item price * quantity (item object itemCount property)
        double expectSubTotal = dblItemPrice * intQty;

        //get item subtotal
        String subTotalRaw = driver.findElement(checkSubtotal).getText();
        //convert to double by calling local method splitPrice passing string and retrn as double
        double dblsubtotal = splitPrice(subTotalRaw);

        System.out.println("\n\tChecking item subtotal: ");
        System.out.println("\tItem Name: " + currItem.itemName);
        System.out.println("\tItem Quantity: " + intQty);
        System.out.println("\tItem Price: " + dblItemPrice);
        System.out.println("\tItem Subtotal: " + dblsubtotal);
        //validate item subtotal
        Assert.assertEquals(dblsubtotal,expectSubTotal);
        //return to main class to use in other method
        return dblsubtotal;

    }

    public void checkPrice(item checkItem){
         //pass test object to local item object
         item currItem = checkItem;
         //set object properties
         this.itemName=currItem.itemName;
         this.itemPrice=currItem.itemPrice;
         //dynamic based on item name
         this.checkPrice = By.xpath("//table[@class='table table-striped cart-items']//td[contains(text(),'" + itemName +"')]/following-sibling::td[1]");
         
         //wait until cart is ready
         wait.until(ExpectedConditions.presenceOfElementLocated(checkPrice));
         //get item price
         String priceRaw = driver.findElement(checkPrice).getText();
         //convert to double by calling local method splitPrice passing string and retrn as double
         double dblItemPrice = splitPrice(priceRaw);
 
         System.out.println("\n\tChecking item price: "); 
         System.out.println("\tItem Name: "+ currItem.itemName);
         System.out.println("\tItem Price: " + dblItemPrice);
         //validate item price
         Assert.assertEquals(dblItemPrice,itemPrice);
     }

    


    public void checkTotal(double expTotal){
        //get cart total, split into 2 string array , $ and number
        String rawTotal[] = driver.findElement(cartTotal).getText().split(" ");
        //parse to double
        double dblTotal = Double.parseDouble(rawTotal[1]);
        System.out.println("\n\tChecking total");
        System.out.println("\tTotal: " + dblTotal);
        //validate total
        Assert.assertEquals(dblTotal, expTotal);


    }

    public double splitPrice(String rawPrice){

        String inputString = rawPrice;
        int length = inputString.length();
        double price = Double.parseDouble(inputString.substring(1, length));
        return price;


    }


}
