package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class homepage {
//variable
    WebDriver driver;
    public By mnuContact = By.xpath("//ul[@class='nav']//a[contains(text(),'Contact')]");

//constructor
    public homepage (WebDriver driver){
        this.driver=driver;
        

    }

    //method
//    public void openContactPage(){
    //    driver.findElement(mnuContact).click();

  //  }
//


//method


}
