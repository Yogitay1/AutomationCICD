package myAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import myAcademy.pageobjects.LandingPage1;

public class BaseTest {
	
	   public  WebDriver driver;
	    public LandingPage1 lp;
	         
	public WebDriver initializeDriver() throws IOException {
   //properties class //if resources have dot"(.)properties" now in java we have excellent feature to read that
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\yogit\\Seleniumjava\\SeleniumFrameworkDesignUdemy\\src\\main\\java\\myAcademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName =System.getProperty("browser")!=null? System.getProperty("browser") :prop.getProperty("browser");
		
		//String browserName = prop.getProperty("browser");
		
	if(browserName.contains("chrome"))	{
		
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
	    //this will install the current version of the chromedriver
		if(browserName.contains("headless")) {
		options.addArguments("headless");
		options.addArguments("--headless=new");
		}
	    driver = new ChromeDriver(options);
	    driver.manage().window().setSize(new Dimension(1440,990));
	    //helps to run in full screen
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	else if(browserName.equalsIgnoreCase("firefox")) {
		//System.setProperty("webdriver.gecko.driver","c://users//document//");
		//driver = new FirefoxDriver();
		   FirefoxOptions options = new FirefoxOptions();
		    driver = new FirefoxDriver(options);
	}
	else if(browserName.equalsIgnoreCase("edge")) {
		//System.setProperties("webdriver.edge.driver");
		System.setProperty("webdriver.edge.driver","edge.exe");
		driver = new EdgeDriver();
		
	}
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	driver.manage().window().maximize();
	return driver;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts	 = (TakesScreenshot) driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		 File destFile = new File (System.getProperty("user.dir")+"//reports//" +testCaseName +".Png");
		  FileUtils.copyFile(source, destFile);
		return System.getProperty("user.dir")+"//reports//" +testCaseName +".Png";	
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage1 launchApplication() throws IOException {
		 driver = initializeDriver();
	 lp = new LandingPage1(driver);
			lp.goTo();
			return lp;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
	 // driver.close();	
	}
	
	public List<HashMap<String ,String>>getJsonDataToMap(String filePath) throws IOException{
    //read or convert json to string with java we can do it 
	String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
	//convert string to hashmap -Jackson Databind
	//Jackson Databind is dependency which converts string content to hash map
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,String>>  data=mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){
		
	});
	return data;
	
	}
	
	
}