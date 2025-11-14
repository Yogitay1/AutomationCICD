package myAcademy.TestComponents;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import myAcademy.resources.ExtentReporterNG;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;


public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	//There is a concurrency issue here that multiple times, multiple tests trying to accessone single variable which is keeping overridden
//So to solve this, in Java there is one class called ThreadLocal,
  ThreadLocal<ExtentTest>  extentTest =new ThreadLocal(); //thread safe , it provides unique id
	//So here, you have to tell what kind of objects you want to place in this ThreadLocal for every entry.
    //So whenever you create a new object,you have to push that object into this ThreadLocal.
	//
	@Override
	public   void onTestStart(ITestResult result) {
		//the test related data will be stored in the result where the knowledge is given by ITestResult
		test = extent.createTest(result.getMethod().getMethodName());
		//pushing the object into this ThreadLocal
		 extentTest.set(test);//unique thread id(ErrorValidationTest)->Test  //gets unique id , Now, if three tests are running here,
  //that means Each Java execution have its own ID.o, what it does is it just not only set this test object,
 //it also picks extract the thread ID of which test is executing that.So, with that thread ID and this object,it creates one map inside this
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
		
	}
	
	
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());//for example Error Valiadation is trying to get its unique id and "test" will work
		
		//it will check its thread ID. Okay.This is your thread ID it will check in "set".
       //So, this test object will be retrieved when you call get. That's all.

//To keep it simple, remember these two methods,set and store your objects
//if you think those are not thread safe,and get will extract it ,So that way, it is fully synchronized,
		
		
		
		//test.fail(result.getThrowable());
		 try {
		driver=	(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		//i cannot use get method to get or use the driver ,i will use getclass bcoz feilds are associated at class level not at method level.
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 String filePath = null;
		  try {
			      filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		       }     catch (IOException e) {
			e.printStackTrace();
		     }
		  extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	}
	
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush(); // without this we cant see the report generation on screen
	}

	
}



