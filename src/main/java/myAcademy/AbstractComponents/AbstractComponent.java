package myAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import myAcademy.pageobjects.CartPage3;
import myAcademy.pageobjects.OrdersPage;

public class AbstractComponent {
	
        WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		 this.driver =driver;
		 
	}

   public void waitForElementToAppear(By findByLocator ) 
   {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(25));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findByLocator));
	wait.until(ExpectedConditions.elementToBeClickable(findByLocator));
   }
   public void waitForWebElementToAppear(WebElement findByLocator ) 
   {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(25));
	wait.until(ExpectedConditions.visibilityOf(findByLocator));
   }
   
   public void waitForInvisibleElementToAppear(WebElement spinner )  {
	   //Thread.sleep(3000);
	   //adding thread.sleep bcoz for waiting for invisible elements in the backend there is another invisible element which is also waiting to load the elements
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
    wait.until(ExpectedConditions.invisibilityOf(spinner)); 
	   //return waitForInvisibleElementToAppear(spinner);   
   }
   
// driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
 
   @FindBy(xpath="(//button[@class='btn btn-custom'])[3]")
   WebElement cartButtonToClick;
   
   @FindBy(css="[routerlink*='myorders']")
   WebElement ordersHeader;
   
   public CartPage3 cartButton() {
	   cartButtonToClick.click();
	   CartPage3 cp = new CartPage3(driver);
	   return cp;
   }
    public OrdersPage goToOrdersPage() {
    	ordersHeader.click();
    	OrdersPage oP =new OrdersPage(driver);
    	
    	return oP;
    	
    }
   
   
}









