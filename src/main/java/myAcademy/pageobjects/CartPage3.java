package myAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import myAcademy.AbstractComponents.AbstractComponent;

public class CartPage3 extends AbstractComponent{
	WebDriver driver;
	public CartPage3(WebDriver driver)
	 {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
     	}
	
	//	List<WebElement> cartProducts =driver.findElements(By.cssSelector(".cartSection h3"));
	  @FindBy(css=".cartSection h3")
	  List<WebElement> cartProducts;
	  
	  @FindBy(xpath="//button[text()='Checkout']")
	  WebElement checkOut;

	  public boolean cartProductsMethod(String productName) {
		   boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		   return match;	   
	  }
	  public AddressDetailsPage4 checkOutMethod() {
		  checkOut.click();
		  AddressDetailsPage4 addressDetailsPage4 =new AddressDetailsPage4(driver);
		  return addressDetailsPage4;
	  }	  
}
