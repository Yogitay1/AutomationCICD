package myAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import myAcademy.AbstractComponents.AbstractComponent;
//pagefactory will hold elements and action or operations not data


public class ProductCatalouge2  extends AbstractComponent {
	WebDriver driver;
    public ProductCatalouge2(WebDriver driver) 
	 {
	       super(driver); //by using the super keyword sending the knowledge of driver from (child)product catalouge to (parent)abstract component 
		   //initialization
    	
     	   this.driver =driver;
	      PageFactory.initElements(driver ,this);
	   }
	
	    //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	    //pageFactory
	    @FindBy(css=".mb-3")
	    List<WebElement> products;
	    
	  //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	    @FindBy(css=".ng-animating")
	    WebElement spinner;
	    
	   // driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
	   // @FindBy(xpath="(//button[@class='btn btn-custom'])[3]")
	    //WebElement cartButtonToClick;
	    //Writing cartbutton in abstract compoenent is because it is re usable for every page , and it is  common for every page
	   
	    
    
	//page factory is only applicable for driver.find elements now i am writing for waitwebdriver
	By productsBy = By.cssSelector(".mb-3");
	
	By toastContainer =By.cssSelector("#toast-container");
	
	By addToCartBox = By.cssSelector(".card-body button:last-of-type");
	
	//By.xpath("(//button[@class='btn btn-custom'])[3]")
	
	By cartButton =By.xpath("(//button[@class='btn btn-custom'])[3]");
	
	    public List<WebElement> productsList() {
	    	waitForElementToAppear(productsBy);
	    	return products;
	    }
	
	    
	  //  WebElement prod =products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	public WebElement filteringTheProduct(String productName) {
		  
		  WebElement prod =products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		  return prod;
	}
	
	public void addToCart(String productName) 
	  {
	    WebElement itemNeededToAddCart = filteringTheProduct(productName).findElement(addToCartBox);
	    itemNeededToAddCart.click();
	    waitForElementToAppear(toastContainer);
	   waitForInvisibleElementToAppear(spinner);
	   waitForElementToAppear(cartButton);
		
	   }
//	public void cartButtonClicking() {
//	  
//	  // cartButton();
//	  
//	}
	
	
	
}