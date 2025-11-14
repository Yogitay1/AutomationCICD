package myAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myAcademy.pageobjects.ConfirmationPage5;
import myAcademy.AbstractComponents.AbstractComponent;

public class AddressDetailsPage4 extends AbstractComponent {
	   WebDriver driver;
	public  AddressDetailsPage4(WebDriver driver)
	  {
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver,this);

       	}
	
	@FindBy(xpath="(//input[@class='input txt'])[1]")
	WebElement cvvCode;
	
	By waitForResult=  By.cssSelector(".ta-results");
	
	//	driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();	   
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountryInSuggestedList;
	
	
	By waitForSubmit= By.cssSelector(".action__submit ");
	
	//driver.findElement(By.cssSelector(".action__submit ")).click();
	@FindBy(css=".action__submit ")
	WebElement clickSubmit;
	
//  Actions a = new Actions(driver);
//  a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"india").build().perform();
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement enterCountryName;
	
      public void enterCvvCode(String cvvNumber) 
        {
	       cvvCode.sendKeys(cvvNumber);
         }
	
      public void enterCountryName(String countryName) {
    	  Actions a = new Actions(driver);
    	  a.sendKeys(enterCountryName,countryName).build().perform();
    	  waitForElementToAppear(waitForResult);
    	  selectCountryInSuggestedList.click();
      }
      public ConfirmationPage5 submitOrder() {
    	  waitForElementToAppear(waitForSubmit);
    	  clickSubmit.click();
    	  ConfirmationPage5 confirmationPage5 = new ConfirmationPage5(driver);
    	 return confirmationPage5 ;
      } 
}