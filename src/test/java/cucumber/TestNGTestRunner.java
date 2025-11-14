package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="myAcademy.stepDefinations",monochrome=true,tags="@ErrorValidation",plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
//cucumber only needs testng or junit to run the feature files , if you are using testng assertions then go with testng and extend with "AbstractTestNGCucumberTests"
}
