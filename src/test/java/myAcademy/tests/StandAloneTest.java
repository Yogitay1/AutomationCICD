package myAcademy.tests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
public class StandAloneTest {
	public static void main (String[] args) throws IOException {
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		//this will install the current version of the chromedriver
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/auth/login");
		//rithu3@gmail.com
		//Rithu@123
		driver.findElement(By.id("userEmail")).sendKeys("rithu3@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rithu@123");
	driver.findElement(By.id("login")).click();
  //So let's do it some nice way using the latest Java methods here.So first of all, let's grab all these products.
  // How to grab? So find out some generic web element so that if you use plural findElements,so you can grab all the products into list.
  //To do that, you need to find a common locator ,which is applicable for all these.
List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
//now got all the list of items now i have to iterate each and every product and check for which product the desired title is displayed
//for(WebElement product : products){
//	String pName= product.findElement(By.cssSelector("h5")).getText();
//    if(pName.equalsIgnoreCase("ADIDAS ORIGINAL")){
//        product.findElement(By.xpath("(//button[contains(text(),'Add To Cart')])[2]")).click();
//        break;
//    }
//	}

  WebElement prod =products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	//for using stream the java version should be latest
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='btn btn-custom'])[3]")));
	driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
	
	//check if jara coat 3 is added to cart or not 
       List<WebElement> cartProducts =driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
       Assert.assertTrue(match);
       driver.findElement(By.xpath("//button[text()='Checkout']")).click();
       driver.findElement(By.xpath("(//input[@class='input txt'])[1]")).sendKeys("123");
    //  driver.findElement(By.xpath("(//input[@class='input txt text-validated'])[2]")).sendKeys("ind");
   //System.out.println(driver.findElement(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")).getText());
   Actions a = new Actions(driver);
   a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"india").build().perform();
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();	   
	
	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit ")));
	driver.findElement(By.cssSelector(".action__submit ")).click();
	 
	String s =driver.findElement(By.cssSelector(".hero-primary")).getText();
	   Assert.assertTrue(s.equalsIgnoreCase("Thankyou for the order."));
	}
	   
	}
