package page;

//contact page class


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

public class contact {
//variable
    WebDriver driver;
    //menu item
    By mnuContact = By.xpath("//ul[@class='nav']//a[contains(text(),'Contact')]");

    //form field
    By txtForename = By.xpath("//input [@type='text' and @id='forename']");
    By txtSurname = By.xpath("//input [@type='text' and @id='surname']");
    By txtEmail = By.xpath("//input [@type='email' and @id='email']");
    By txtPhone = By.xpath("//input [@type='text' and @id='telephone]");
    By txtMessage = By.xpath("//textarea [@type='text' and @id='message']");

    //submit button
    By btnSubmit = By.xpath("//a[@class = 'btn-contact btn btn-primary'][text()='Submit']");

    //mandatory error
    By errForename = By.xpath("//*[@id='forename-err']");
    By errEmail=By.xpath("//*[@id='email-err']");
    By errMessage=By.xpath("//*[@id='message-err']");
    By form = By.xpath("//form[@name='form']");
    //header message to check for DOM readiness
    By headMessage = By.xpath("//div[@id='header-message']");
    
    //progress bar and submission message
    By progressBar = By.xpath("//div[@class='progress progress-info wait']/div");
    By alertSubmitPass = By.xpath("//div[@class='alert alert-success']");
    
    //EC wait
    WebDriverWait wait;

    //form date
    String foreName;
    String email;
    String message;
    


//constructor
    public contact (WebDriver driver){
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));    

    }

    public void gotoContact(){
        driver.findElement(mnuContact).click();
        String currURL = driver.getCurrentUrl();
        System.out.println("CURRENT URL : " + currURL);
        wait.until(ExpectedConditions.presenceOfElementLocated(headMessage));
        System.out.println("Contact Page is Ready");

    }

    public void checkMandatoryError(){
        submitForm();
        //validate mandatory error element displayed
        Assert.assertEquals(true, driver.findElement(errForename).isDisplayed());
        Assert.assertEquals(true, driver.findElement(errEmail).isDisplayed());
        Assert.assertEquals(true, driver.findElement(errMessage).isDisplayed());

        //validate mandatory error message
        Assert.assertEquals(driver.findElement(errForename).getText(),"Forename is required");
        Assert.assertEquals(driver.findElement(errEmail).getText(),"Email is required");
        Assert.assertEquals( driver.findElement(errMessage).getText(), "Message is required");
        

    }
    
    public void fillMandatory(String forename, String email, String message){
        //set object properties
        this.foreName=forename;
        this.email=email;
        this.message=message;

        //fill form and validate error message is gone by checking the count of the error element not > 0
        driver.findElement(txtForename).sendKeys(forename);
        Assert.assertEquals(driver.findElements(errForename).size()>0,false);

        driver.findElement(txtEmail).sendKeys(email);
        Assert.assertEquals(driver.findElements(errEmail).size()>0,false);

        driver.findElement(txtMessage).sendKeys(message);
        Assert.assertEquals(driver.findElements(errMessage).size()>0,false);
    }

    public void submitForm(){
        wait.until(ExpectedConditions.presenceOfElementLocated(btnSubmit));
        driver.findElement(btnSubmit).click();
    }

    public void checkSubmissionStatus() {
        wait.until(ExpectedConditions.presenceOfElementLocated(progressBar));
        while (driver.findElements(progressBar).size()>0){
             //do nothing just to wait for the progress bar to go away
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(alertSubmitPass));
        
        //set submission succes message using forename
        String expText= "Thanks " + foreName + ", " + "we appreciate your feedback." ;
        
        //validate submission success message
        Assert.assertEquals(driver.findElement(alertSubmitPass).getText(), expText);


    }



//method


}
