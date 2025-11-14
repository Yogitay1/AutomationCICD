package myAcademy.tests;

import java.io.File;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import myAcademy.TestComponents.BaseTest;
import myAcademy.pageobjects.AddressDetailsPage4;
import myAcademy.pageobjects.CartPage3;
import myAcademy.pageobjects.ConfirmationPage5;
import myAcademy.pageobjects.OrdersPage;
import myAcademy.pageobjects.ProductCatalouge2;
public class SubmitOrderTest2 extends BaseTest {
	//String productName = "ZARA COAT 3";
		@Test(dataProvider="getData" , groups={"Purchase"})
		public void SubmitOrderTest2(HashMap<String,String> input) throws IOException {
		//	LandingPage1 lp = launchApplication();
		ProductCatalouge2 pc=lp.loginApplication(input.get("email"),input.get("password") );
		//ProductCatalouge pc = new ProductCatalouge(driver);
		//List<WebElement>products = pc.productsList();
		pc.filteringTheProduct(input.get("productName"));
		pc.addToCart(input.get("productName"));
		CartPage3 cp=pc.cartButton();
		boolean match = cp.cartProductsMethod(input.get("productName"));
		Assert.assertTrue(match);
		AddressDetailsPage4 addressDetailsPage4= cp.checkOutMethod();
		addressDetailsPage4.enterCvvCode("123");
		addressDetailsPage4.enterCountryName("india");
		ConfirmationPage5 confirmationPage5 =addressDetailsPage4.submitOrder();
		String confirmMessage =confirmationPage5.checkingThePage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));	
	}		
	@Test(dependsOnMethods={"SubmitOrderTest2"})
      public void orderHistoryTest() 
	   {
	       	String prodName = "ZARA COAT 3";
	         ProductCatalouge2 pc=lp.loginApplication("rithu3@gmail.com","Rithu@123");
	         OrdersPage oP= pc.goToOrdersPage();
	       	oP.verifyOrderDisplay(prodName);
    	    Assert.assertTrue(oP.verifyOrderDisplay(prodName));
        }
	@DataProvider
	public Object[][] getData() throws IOException {
	 List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test/java//myAcademy//data//PurchaseOrder.json");
	return new Object[][] {  {data.get(0)},{data.get(1)}  };	
	}
}
//171
//@DataProvider
//public Object[][] getData() {
//	
//	return new Object[][] {{"rithu3@gmail.com", "Rithu@123","ZARA COAT 3"},{"rithu@gmail.com", "Rithu12#","ADIDAS ORIGINAL"}};
//	
//172
//public Object[][] getData() {
//	//getJsonDataToMap()
//	HashMap<String,String> map =new HashMap<String,String>();
//	map.put("email", "rithu3@gmail.com");
//	map.put("password","Rithu@123");
//	map.put("productName","ZARA COAT 3");
//	
//	HashMap<String,String> map1 =new HashMap<String,String>();
//	map1.put("email", "rithu@gmail.com");
//	map1.put("password","Rithu12#");
//	map1.put("productName","ADIDAS ORIGINAL");
//	
//	return new Object[][] {{map},{map1}};
//	
//}
//}