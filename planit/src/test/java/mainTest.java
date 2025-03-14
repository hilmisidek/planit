//import org.openqa.selenium.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import page object class
import page.contact;
import page.homepage;
import page.shop;
import page.cart;
//import test object class
import testdata.item;

import org.testng.asserts.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class mainTest {
        
    WebDriver driver = new ChromeDriver();

    //BeforeMethod is used because of test invocation in test case 2
    @BeforeMethod
    public void setup(){
        //set chrome driver path
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",path + "\\chromedriver.exe");
        
        driver.get("https://jupiter.cloud.planittesting.com/");
        System.out.println("\nStarting TEST");
        }
            
    @AfterTest    
    public void teardown(){
         driver.close();
    }

    @Test
    public void test_case_1(){
        //create new contactpag object
        contact contactpage = new contact(driver);
        //goto contact page
        contactpage.gotoContact();
        //submit without entering mandatory field
        contactpage.checkMandatoryError();
        //enter all mandatory and check errors are gone
        contactpage.fillMandatory("Forename", "Email@email.com", "Message here");            
    }

    //run test 5 times
    @Test(invocationCount = 5)
    public void test_case_2() {
        //create new contactpage object
        contact contactpage = new contact(driver);
        //goto contact page
        contactpage.gotoContact();
        //fill all mandatory input
        contactpage.fillMandatory("Fore Name", "Email@email.com", "Message here");
        //submit form
        contactpage.submitForm();
        //check submission status
        contactpage.checkSubmissionStatus();               
    }

    @Test
    public void test_case_3(){
        //create new object for item to buy with name, quantity to buy and item price
        item stuffedFrog = new item("Stuffed Frog", 2, 10.99);
        item fluffyBunny = new item("Fluffy Bunny", 5, 9.99);
        item valentineBear = new item("Valentine Bear", 3, 14.99);
        
        //create newshop object
        shop newshop = new shop(driver);
        //goto shop page
        newshop.gotoShop();
        
        //buy each item passing item object as parameter
        newshop.buyItem(stuffedFrog);
        newshop.buyItem(fluffyBunny);
        newshop.buyItem(valentineBear);

        //create newcart object
        cart newcart = new cart (driver);
        //open cart page
        newcart.gotoCart();
        //check subtotal for each item, passing item object as parameter and return to local variable
        double subtotal1 = newcart.checkSubtotal(stuffedFrog);
        double subtotal2 = newcart.checkSubtotal(fluffyBunny);
        double subtotal3 = newcart.checkSubtotal(valentineBear);

        //check item price
        newcart.checkPrice(stuffedFrog);
        newcart.checkPrice(fluffyBunny);
        newcart.checkPrice(valentineBear);
        
        //calculate total from sum(subtotal)
        double expTotal = subtotal1+subtotal2+subtotal3;
        //check total
        newcart.checkTotal(expTotal);
    }


}
