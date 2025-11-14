package myAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import myAcademy.AbstractComponents.AbstractComponent;
//pagefactory will hold elements and action or operations not data
public class LandingPage1 extends AbstractComponent {
	WebDriver driver;
	
	public LandingPage1(WebDriver driver) {
		super(driver);
		//initialization
		this.driver =driver;
	    PageFactory.initElements(driver ,this);
	}
	
	
	
	//WebElement userEmail =driver.findElement(by.id="userEmail");
	//pageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
	public ProductCatalouge2 loginApplication(String email ,String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalouge2 pc = new ProductCatalouge2(driver);
		return pc;	
	}
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/auth/login");
	}
	

}