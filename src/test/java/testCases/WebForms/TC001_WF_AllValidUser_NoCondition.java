package testCases.WebForms;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.IndvControlsPage;
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.UtilityCustomFunctions;


public class TC001_WF_AllValidUser_NoCondition extends BaseClass{
	@BeforeTest
	public void testName() {
		test = extent.createTest("TC001_WF_AllValidUser_NoCondition");
	}

	@Test	
	public void testWebFormUser() throws Exception {
		
		node = test.createNode("AllValidUser_NoCondition");
		String sBrowserName=utilities.UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		System.out.println("Test Execution on Browser: "+ sBrowserName);
		String sPath="\\WebForm\\WF_AllValidUser_NoCondition_";
		CRMReUsables ObjCRMRs = new CRMReUsables(); 
		IndvControlsPage IndvObj = new IndvControlsPage(driver); 
		ObjCRMRs.fWFSubmitSF("Test",sPath,"Sheet1",false,node);
		
		driver.get(rb.getString("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sUserName =  rb.getString("userName");
		String sPassword =  rb.getString("passWord");
		String sAssignedTo = rb.getString("AssignedTo");
		String sUserName1 =  rb.getString("userName4");
		String sPassword1 =  rb.getString("passWord4");
		String sAssignedTo1 = rb.getString("AssignedTo4");
		String sUserName2 =  rb.getString("userName2");
		String sPassword2 =  rb.getString("passWord2");
		String sAssignedTo2 = rb.getString("AssignedTo2");
		ObjCRMRs.fLoginCRM(sAppUrl,sCompName,sUserName,sPassword);
		ObjCRMRs.fNavigatetoUserMgmt("Sudhakar");
		
			
		
			
		}//Test
		
		
		
	
}
