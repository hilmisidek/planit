//import org.openqa.selenium.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.contact;
import page.homepage;
import page.shop;
import testdata.item;
import page.cart;
import org.testng.asserts.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class mainTest {
    
    
    WebDriver driver = new ChromeDriver();

    
        
    @BeforeMethod
    public void setup(){
    String path = System.getProperty("user.dir");
    System.setProperty("webdriver.chrome.driver",path + "\\chromedriver.exe");
    //WebDriver driver = new ChromeDriver();  
    driver.get("https://jupiter.cloud.planittesting.com/");
    System.out.println("Starting TEST");
    
    }

    @Test
    public void test_case_1(){
        contact contactpage = new contact(driver);
        contactpage.gotoContact();
        contactpage.checkMandatoryError();
        contactpage.fillMandatory("Forename", "Email@email.com", "Message here");            
    }

    @Test(invocationCount = 5)
    public void test_case_2() {
    
           
            contact contactpage = new contact(driver);
            contactpage.gotoContact();
            contactpage.fillMandatory("Fore Name", "Email@email.com", "Message here");
            contactpage.submitForm();
            contactpage.checkSubmissionStatus();
            
        
    }

    @Test
    public void test_case_3(){
        item stuffedFrog = new item("Stuffed Frog", 2, 10.99);
        item fluffyBunny = new item("Fluffy Bunny", 5, 9.99);
        item valentineBear = new item("Valentine Bear", 3, 14.99);
        
        shop newshop = new shop(driver);
        newshop.gotoShop();
        newshop.buyItem(stuffedFrog);
        newshop.buyItem(fluffyBunny);
        newshop.buyItem(valentineBear);

        cart newcart = new cart (driver);
        newcart.gotoCart();

        double subtitle1 = newcart.checkSubtotal(stuffedFrog);
        double subtitle2 = newcart.checkSubtotal(fluffyBunny);
        double subtitle3 = newcart.checkSubtotal(valentineBear);
        double expTotal = subtitle1+subtitle2+subtitle3;

        newcart.checkTotal(expTotal);
        
        


    
    }


}
