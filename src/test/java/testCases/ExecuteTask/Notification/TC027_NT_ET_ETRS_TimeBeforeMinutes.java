package testCases.ExecuteTask.Notification;

import org.testng.annotations.BeforeTest;

import testBase.BaseClass;

public class TC027_NT_ET_ETRS_TimeBeforeMinutes extends BaseClass{
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC027_NT_ET_ETRS_TimeBeforeMinutes");
	}
	
	
}
