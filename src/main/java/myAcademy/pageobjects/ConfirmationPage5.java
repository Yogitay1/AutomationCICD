package myAcademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myAcademy.AbstractComponents.AbstractComponent;


public class ConfirmationPage5 extends AbstractComponent {
	  WebDriver driver;
	public ConfirmationPage5( WebDriver driver) 
	{
		super(driver);
	     this.driver =driver;
	     PageFactory.initElements(driver,this);
	}
	
	
	   @FindBy(css=".hero-primary")
	   WebElement confirming;
	
//	String s =driver.findElement(By.cssSelector(".hero-primary")).getText();
//	   Assert.assertTrue(s.equalsIgnoreCase("Thankyou for the order."));
	public String checkingThePage() {
		
		return  confirming.getText();
		}
	
	
}
