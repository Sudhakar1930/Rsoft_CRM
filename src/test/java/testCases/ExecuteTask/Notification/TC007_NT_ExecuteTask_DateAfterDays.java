package testCases.ExecuteTask.Notification;

import org.testng.annotations.BeforeTest;

import testBase.BaseClass;

public class TC007_NT_ExecuteTask_DateAfterDays extends BaseClass{
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC007_NT_ExecuteTask_DateAfterDays");
	}
	
	
	
}
