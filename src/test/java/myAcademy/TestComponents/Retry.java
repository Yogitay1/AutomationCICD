package myAcademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
//But after completing those listeners, if it failed, it'll come here to check and ask us,do I need to rerun again?just to make sure I might be flaky test.Do you want me to rerun?
	int maxTry = 2;
	int count =0;
	
	@Override
	public boolean retry(ITestResult result) {
	   if(count<maxTry) {
		  count++;
		  System.out.println("Retrying test " + result.getName() + " again (" + count + "/" + maxTry + ")");
		  return true;
	   }
		
		return false;
	}

}
//If you want to rerun,how many times you want to rerun? people sayrun two times or three timesto make sure if it is a real failure, okay?
//And some people say just run one more time	and see if it is still giving the same failure result.So, whenever a test fails,
//after it reporting to the extent report,it'll also come to this block and check,do I have to rerun again, okay?
//That's the beauty of this Retry Analyzer.



//Now, how do you tell your test to reach this method automatically?
//So previously the listeners will be automatically activated by providing in the XML file,
//but these listeners will be applicable for only iTest listener.
//If you have another interface called iRetry Analyzer that you cannot directly declare there.

//So for that, so whatever test you want to rerun, if it failed, okay, not every time.
//If it failed, and if you want to rerun then go to that particular test.So here I have doubt that it might fail, right.
//So here you have to tell Retry Analyzer here where you have to give that attribute and it will ask, "Okay, where is the class for me to retry?"


//So just give  in that class,retryAnalyzer=Retry.class.So it'll check for that class.