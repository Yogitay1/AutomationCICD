package myAcademy.tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import myAcademy.TestComponents.BaseTest;
import myAcademy.TestComponents.Retry;
import myAcademy.pageobjects.AddressDetailsPage4;
import myAcademy.pageobjects.CartPage3;
import myAcademy.pageobjects.ConfirmationPage5;
import myAcademy.pageobjects.ProductCatalouge2;
public class ErrorValidationsTest extends BaseTest {
	
		@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
		public void loginErrorValidation() throws IOException {
	     lp.loginApplication("rithu3@gmail.co", "Rithu@123");
		//if you dont get any locator path example if something is disappearing in sparkle of seconds then by using selectors hub ask it to give relevant css or xpath locator
       Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
		
		}
		//(retryAnalyzer=Retry.class)
		@Test
		public void ProductErrorValidation() {
			String productName = "ZARA COAT 3";
		     ProductCatalouge2 pc=lp.loginApplication("rithu@gmail.com", "Rithu12#");
		        List<WebElement>products = pc.productsList();
				pc.addToCart(productName);
				CartPage3 cp=	pc.cartButton();
				boolean match = cp.cartProductsMethod("ZARA COAT 3");
				Assert.assertTrue(match);
			}
}