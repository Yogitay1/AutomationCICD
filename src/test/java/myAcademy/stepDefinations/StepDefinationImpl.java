package myAcademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import myAcademy.TestComponents.BaseTest;
import myAcademy.pageobjects.AddressDetailsPage4;
import myAcademy.pageobjects.CartPage3;
import myAcademy.pageobjects.ConfirmationPage5;
import myAcademy.pageobjects.LandingPage1;
import myAcademy.pageobjects.ProductCatalouge2;

public class StepDefinationImpl extends BaseTest{
	public LandingPage1 lp;
	public ProductCatalouge2 pc;
	public ConfirmationPage5 confirmationPage5;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException {
		lp = launchApplication();
	}
	// Given Logged in with username<name> and password <password>
	 @Given("^Logged in with username(.+) and password (.+)$")
	 public void Logged_in_with_username_and_password(String username , String password){
		 pc=lp.loginApplication(username , password);
	 }
	 //When I add product <productName>to cart
	 @When("^I add product (.+)to cart$")
	public void add_product_to_cart(String productName) {
		 List<WebElement>products = pc.productsList();
		 pc.addToCart(productName);  
	 }
// And checkout the <productName> and will submit the order , basically AND IS THE conjuction for the previous one , so go with previous one  , here our previous one is @when
	 @When("^checkout the (.+) and submit the order$")
	 public void checkout_product_and_submit_order(String productName){
		    CartPage3 cp=pc.cartButton();
			boolean match = cp.cartProductsMethod(productName);
			Assert.assertTrue(match);
			AddressDetailsPage4 addressDetailsPage4= cp.checkOutMethod();
			//addressDetailsPage4.enterCvvCode("123");
			addressDetailsPage4.enterCountryName("india");
		    confirmationPage5 =addressDetailsPage4.submitOrder(); 
	 }
	 @Then("check for {string} message is displayed on the confirmationPage")
	 public void message_displayed_on_confirmationPage(String string )
	 {
	    String confirmMessage =confirmationPage5.checkingThePage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order.")); 
		driver.close();
	 } 
	 @Then("{string} message is displayed")
	 public void  something_message_is_displayed(String strArg1) {
		   Assert.assertEquals(strArg1, lp.getErrorMessage());
		   driver.close();
	 }
	 
}
