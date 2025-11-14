package myAcademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() 
	//this method getReportObject()  should give report and get object out of it
	 {
		//the main 2 imp classes to generate extent reports are 
		//ExtentReports , ExtentSparkReporter
		String path= System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	//ExtentSparkReporter is responsible to create one html file and do some configurations
		   reporter.config().setReportName("Web Automation Results");
	       reporter.config().setDocumentTitle("Test Results");
	  //by using ExtentSparkReporter i created one report like where path should be and some basic configs
	  //So the ExtentSparkReporter is a helper class, which is helping to create some configuration,and that will finally report to its main class here main class is extent reports.
	
	 ExtentReports extent = new ExtentReports();
     extent.attachReporter(reporter);
	 //So there is a method called attachReporter.Okay, so once you create report,you have to attach it to your main class,    
	 extent.setSystemInfo("Tester","yogitha");
	 // to create a test for every test case we can write like 
	 extent.createTest(path);
	 // for every test case  its not possible to write bcoz there are 100 test cases so there is testng Listeners  
	  return extent;
	 
	 }
	

}
