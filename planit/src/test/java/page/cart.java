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

public class cart {
//variable
    WebDriver driver;
    WebDriverWait wait;
    By mnuCart = By.xpath("//*[@id='nav-cart']");
    String itemName= "";
    Double itemPrice;
    By checkPrice;
    By checkSubtotal;
    
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
    item currItem = checkItem;
    this.itemName=currItem.itemName;
    this.itemPrice=currItem.itemPrice;
    this.checkPrice = By.xpath("//table[@class='table table-striped cart-items']//td[contains(text(),'" + itemName +"')]/following-sibling::td[1]");
    this.checkSubtotal = By.xpath("//table[@class='table table-striped cart-items']//td[contains(text(),'" + itemName + "')]/following-sibling::td[3]");
    wait.until(ExpectedConditions.presenceOfElementLocated(checkPrice));
    String priceRaw = driver.findElement(checkPrice).getText();
    double dblItemPrice = splitPrice(priceRaw);
    System.out.println("Checking item price: " + currItem.itemName);
    Assert.assertEquals(dblItemPrice,itemPrice);
    
    double expectSubTotal = currItem.itemPrice * currItem.itemCount;

    String subTotalRaw = driver.findElement(checkSubtotal).getText();

    double dblsubtotal = splitPrice(subTotalRaw);
    System.out.println("Checking item subtotal: " + currItem.itemName);
    Assert.assertEquals(dblsubtotal,expectSubTotal);

    return dblsubtotal;

}


public void checkTotal(double expTotal){
    String rawTotal[] = driver.findElement(cartTotal).getText().split(" ");
    double dblTotal = Double.parseDouble(rawTotal[1]);
    System.out.println("Checking total");
    Assert.assertEquals(dblTotal, expTotal);


}

public double splitPrice(String rawPrice){

    String inputString = rawPrice;
    int length = inputString.length();
    double price = Double.parseDouble(inputString.substring(1, length));
    return price;


}


}
