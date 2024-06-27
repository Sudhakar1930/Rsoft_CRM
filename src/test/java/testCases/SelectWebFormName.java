package testCases;

import org.testng.annotations.Test;

import pageObjects.AllListPage;
import pageObjects.CRMSettingsPage;
import pageObjects.IndvControlsPage;
import pageObjects.UserPage;
import pageObjects.WebFormsPage;
import testBase.BaseClass;
import utilities.CRMReUsables;

public class SelectWebFormName extends BaseClass {

	@Test
	void test() throws InterruptedException, Exception {
		// TODO Auto-generated method stub
		
		CRMReUsables ObjCRMRs = new CRMReUsables(); 
		IndvControlsPage IndvObj = new IndvControlsPage(driver); 
		UserPage objUP = new UserPage(driver);
		AllListPage objALP = new AllListPage(driver);
		 
		CRMSettingsPage objCRMSTngs = new CRMSettingsPage(driver);
		WebFormsPage objWSP = new WebFormsPage(driver);
		String sAppUrl = "https://rdot.in/public/login";
		driver.get(sAppUrl);
		String sCompName = "PRIYAN";
		String sUserName = "Sudhakar";
		String sPassword="Automation@123";
		ObjCRMRs.fLoginCRM(sAppUrl,sCompName,sUserName,sPassword);
//		objCRMSTngs.fCRMNavigate("Integration", "Web Forms");
//		objWSP.fSelectWebForm("wnTestAutomation","ETNotification");
		
		Thread.sleep(3000);
		ObjCRMRs.fNavigatetoUserMgmt();
		String sFoundUser = objUP.fGetFirstAvailableAdminUser();
		System.out.println("User Name: " + sFoundUser);
		
		
		
		
		
	}

}
