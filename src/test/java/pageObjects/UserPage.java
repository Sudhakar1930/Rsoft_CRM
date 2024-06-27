package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import utilities.UtilityCustomFunctions;

public class UserPage extends BasePage{
	
	
	public UserPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//input[@name='name']")
	WebElement txtUserSearch;
	
	@FindBy(xpath="//table[@class='table table-striped']//tr//td")
	List<WebElement> tblUsersTd;
	
	@FindBy(xpath="//table[@class='table table-striped']//tr//div[@class='col-sm-9']")
	List<WebElement> tblUsersTr;
	
	@FindBy(xpath="//td/label[contains(text(),'Availability')]/following::td[1]")
	WebElement lblAvailabilityStatus;
	
	@FindBy(xpath="//td/label[contains(text(),'Admin')]/following::td[1]")
	WebElement lblAdminStatus;
	
	@FindBy(xpath="//i[@class='fa fa-chevron-right']/parent::button")
	WebElement btnRightNavigate;
	
	@FindBy(xpath="//i[@class='fa fa-chevron-left']/parent::button")
	WebElement btnLeftNavigate;
	
	@FindBy(xpath="//button[normalize-space()='Add User']")
	WebElement btnAddUser;
	
	@FindBy(xpath="//input[@name='name']")
	WebElement txtUser;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtPassword;
	
	
	@FindBy(xpath="//input[@id='confirmpassword']")
	WebElement txtConfirmPwd;
	
	
	@FindBy(xpath="//input[@name='users_firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@name='users_lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//label[contains(text(),'Roles')]/following::span[1]")
	WebElement cmbRoles;
	
	@FindBy(xpath="//label[contains(text(),'Login Access')]/following::span[1]")
	WebElement cmbLoginAccess;
	
	@FindBy(xpath="//ul[@class='select2-results__options' and @role='tree']")
	WebElement cmbDropDown;
	
	@FindBy(xpath="//label[contains(text(),'Availability')]/following::input[2]")
	WebElement chkAvailability;
	
	@FindBy(xpath="//label[contains(text(),'Admin')]/following::input[1]")
	WebElement chkAdmin;
	
	@FindBy(xpath="//button[@class='btn btn-primary usersubmit ModuleSubmit']")
	WebElement btnSave;
	
	@FindBy(xpath="//button[@class='btn btn-danger mr-1 addfieldpopopclose']")
	WebElement btnClose;
	
	@FindBy(xpath="//h4[normalize-space()='User Management']")
	WebElement lblUserManagement;
	
	
	@FindBy(xpath="//div[@id='block1']//div[contains(@class,'bg-gradient-x-purple-blue')]")
	WebElement lblUserSettings;
	
	@FindBy(xpath="//div[normalize-space()='Duplicate(s) detected!']")
	WebElement lblDuplicateMsg;
	
	public boolean IsDuplicateMsgExist() throws InterruptedException {
		boolean IsDuplicate = false;
		try {
		IsDuplicate  = UtilityCustomFunctions.IsElementVisible(driver, lblDuplicateMsg);
		}catch(Exception e) {
			IsDuplicate = false;	
		}
		return IsDuplicate;
	}
	
	public void fClickUserMenuItem() throws InterruptedException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(lblUserSettings));
			wait.until(ExpectedConditions.elementToBeSelected(lblUserSettings));
			wait.until(ExpectedConditions.visibilityOf(lblUserSettings));
//			js.executeScript("arguments[0].scrollIntoView();", lblUserSettings);
//			js.executeScript("arguments[0].click();", lblUserSettings);
			Thread.sleep(10000);
			lblUserSettings.click();
						
//			wait.until(ExpectedConditions.elementToBeClickable(btnAddUser));
//			wait.until(ExpectedConditions.visibilityOf(btnAddUser));
		}catch(Exception e) {
			e.getCause();
		}
	}
	
	//******************* Wait Methods **********************************
	
	public void fWaitTillUserMgmt(ExtentTest node) throws IOException, InterruptedException {
		if(UtilityCustomFunctions.IsElementVisible(driver, lblUserManagement)) {
			UtilityCustomFunctions.fSoftAssert("User Saved Successfully", "User Saved Successfully", "pass", node);
		}
	}
	
	//************************** Set Methods ************************
	public void setAdmin(String sValue) {
		if(UtilityCustomFunctions.IsElementVisible(driver, chkAdmin)) {
			if(!sValue.equalsIgnoreCase("ON")) {
				if(chkAdmin.isSelected()) {
					chkAdmin.click();
				}
			}
			else {
				if(!chkAdmin.isSelected()) {
					chkAdmin.click();
				}
			}
		}
	}
	
	public void setAvailability(String sValue) {
		if(UtilityCustomFunctions.IsElementVisible(driver, chkAvailability)) {
		if(!sValue.equalsIgnoreCase("ON")) {
			if(chkAvailability.isSelected()) {
				chkAvailability.click();
			}
		}
		else {
			if(!chkAvailability.isSelected()) {
				chkAvailability.click();
			}
		}
		}
	}
	
	public void setLoginAccess(String sLoginAccess) throws InterruptedException {
		cmbLoginAccess.click();
		Thread.sleep(1000);
		UtilityCustomFunctions.selectOneItemfromListBox(driver,cmbDropDown,sLoginAccess);
		
	}
	
	public void setRole(String sRole) throws InterruptedException {
		cmbRoles.click();
		Thread.sleep(1000);
		UtilityCustomFunctions.selectOneItemfromListBox(driver,cmbDropDown,sRole);
		
	}
	public void setUserFirstName(String sFName) throws InterruptedException {
		Thread.sleep(1000);
		UtilityCustomFunctions.sendKeys(driver, txtFirstName, sFName);
		Thread.sleep(1000);
		
	}
	
	public void setUserLastName(String sLName) {
	
		UtilityCustomFunctions.sendKeys(driver, txtLastName, sLName);
	}
	
	
	public void setConfirmPwd(String sPassword) {
		
		UtilityCustomFunctions.sendKeys(driver, txtConfirmPwd, sPassword);
	}
	
	
		public void setUserPwd(String sPassword) {
			
			UtilityCustomFunctions.sendKeys(driver, txtPassword, sPassword);
		}
	
		
		public void setUserEmail(String sEmail) {
			
			UtilityCustomFunctions.sendKeys(driver, txtEmail, sEmail);
		}
		
		
		public void setUserName(String sUsrName) {
			
			UtilityCustomFunctions.sendKeys(driver, txtUser, sUsrName);
		}
		
	//******************* click Methods ***************************************
		
		public void clickSaveBtn() throws Exception {
			if(UtilityCustomFunctions.IsElementVisible(driver, btnSave)) {
				UtilityCustomFunctions.doClick(driver, btnSave);
			}
		}
		
		public void clickAddUserBtn() throws Exception {
				
			UtilityCustomFunctions.doClick(driver, btnAddUser);

		}
	
		//******************* Get Methods ***************************************
		
		public String fGetFirstAvailableAdminUser() throws InterruptedException {
		boolean bFlag = false;
		boolean bUserFound =  false;
		String sUNPath = "";
		String sXpath = "";
		String sUserName = "";
		String sAdminPath="";
		 outerloop:
		 do{
			int iRowCount = tblUsersTr.size();
			for(int i=1;i<=iRowCount;i++) {
				int k=0;
				sXpath = "(//table[@class='table table-striped']//tr//span[@class='switchery switchery-default']/small)[" + i + "]";
//				sUNPath = "(//table[@class='table table-striped']//tr//div[@class='col-sm-9'])[" + i + "]";
				
				k = i + 1;
				sAdminPath="//*[@id='userlistview']/table/tbody/tr["+ k +"]/td[7]";
				sUNPath = "//*[@id='userlistview']/table/tbody/tr["+ k +"]/td[3]";
				WebElement eleUserCol = driver.findElement(By.xpath(sUNPath));
				WebElement eleTglAvailability = driver.findElement(By.xpath(sXpath));
				WebElement eleAdminYes= driver.findElement(By.xpath(sAdminPath));
				String sUserDetail = eleUserCol.getText();
				String sAdminToggle = eleAdminYes.getText();
//				System.out.println("UserDetails:" + sUserDetail);
				String[] sSplitUserArr = sUserDetail.split("\\s+");
//				System.out.println("Array 0 Index Name" + sSplitUserArr[0]);
				System.out.println("Admin Xpath:"+sAdminPath);
				System.out.println("UserName Xpath:"+sUNPath);
				System.out.println("Availability Xpath:"+sXpath);
				
				System.out.println("UserName:"+sSplitUserArr[0] +":Admin:"+sAdminToggle+":Availability:"+eleTglAvailability.getAttribute("style"));
				if(!sSplitUserArr[0].equalsIgnoreCase("rsoft")) {
				if(sAdminToggle.trim().equalsIgnoreCase("Yes")){
				String sAttrValues =eleTglAvailability.getAttribute("style");
//				System.out.println(sSplitUserArr[0] + " Availability: " + sAttrValues);
					if(sAttrValues.contains("11px")) {
						sUserName = sUserDetail;
						System.out.println("Within User with Availability:" + sUserName);
						bUserFound = true;
						break outerloop;
					}//if Availability
				}//Is Admin
			  }//Is Not RSoft
			}//For Loop Current Page
			if(btnRightNavigate.isEnabled()==false) {
				bFlag =true;
			}
			if(btnRightNavigate.isEnabled()==true) {
				btnRightNavigate.click();
				Thread.sleep(3000);
			}
		}while(bFlag==false);
		 if(bUserFound==false) {
			 sUserName = null;
		 }
		 return sUserName; 
	}
	//*************Get First Available Non Admin User ***************
		public String fGetFirstAvailableNonAdminUser() throws InterruptedException {
			boolean bFlag = false;
			boolean bUserFound =  false;
			String sUNPath = "";
			String sXpath = "";
			String sUserName = "";
			String sAdminPath="";
			 outerloop:
			 do{
				int iRowCount = tblUsersTr.size();
				for(int i=1;i<=iRowCount;i++) {
					int k = 0;
					sXpath = "(//table[@class='table table-striped']//tr//span[@class='switchery switchery-default']/small)[" + i + "]";
//					sUNPath = "(//table[@class='table table-striped']//tr//div[@class='col-sm-9'])[" + i + "]";
					k = i + 1;
					sAdminPath="//*[@id='userlistview']/table/tbody/tr["+ k +"]/td[7]";
					sUNPath = "//*[@id='userlistview']/table/tbody/tr["+ k +"]/td[3]";
					WebElement eleUserCol = driver.findElement(By.xpath(sUNPath));
					WebElement eleTglAvailability = driver.findElement(By.xpath(sXpath));
					WebElement eleAdminYes= driver.findElement(By.xpath(sAdminPath));
					String sUserDetail = eleUserCol.getText();
					String sAdminToggle = eleAdminYes.getText();
//					System.out.println("UserDetails:" + sUserDetail);
					String[] sSplitUserArr = sUserDetail.split("\\s+");
//					System.out.println("Array 0 Index Name" + sSplitUserArr[0]);
					if(!sSplitUserArr[0].equalsIgnoreCase("rsoft")) {
					if(sAdminToggle.trim().equalsIgnoreCase("No")){
					String sAttrValues =eleTglAvailability.getAttribute("style");
//					System.out.println(sSplitUserArr[0] + " Availability: " + sAttrValues);
						if(sAttrValues.contains("11px")) {
//							sUserName = sSplitUserArr[0];
							sUserName = sUserDetail;
							System.out.println("Within User with Availability:" + sUserName);
							bUserFound = true;
							break outerloop;
						}//if Availability
					}//Is Admin
				  }//Is Not RSoft
				}//For Loop Current Page
				if(btnRightNavigate.isEnabled()==false) {
					bFlag =true;
				}
				if(btnRightNavigate.isEnabled()==true) {
					btnRightNavigate.click();
					Thread.sleep(3000);
				}
			}while(bFlag==false);
			 if(bUserFound==false) {
				 sUserName = null;
			 }
			 return sUserName; 
		}
		
		
		
	public int fSearchUser(String sUserName) {
		UserPage objUP = new UserPage(driver);
		int i=0;
		txtUserSearch.clear();
		txtUserSearch.sendKeys(sUserName);
		txtUserSearch.sendKeys(Keys.ENTER);
		if(tblUsersTr.size()>=1) {
			tblUsersTr.get(0).click();
			i = 1;
		}
		else {
			i=0;
		}
		return i;
	}
	public int fGetUserTableSize() {
		return tblUsersTr.size(); 
	}
	public String fGetUserAvailability() throws Exception {
		String sAvailStatus="";
		String sReturnValue="";
		
			sAvailStatus = UtilityCustomFunctions.getText(driver, lblAvailabilityStatus);
			sReturnValue = sAvailStatus;
		
		return sReturnValue;
	}
	public String fGetUserAdmin() throws Exception {
		String sAdminStatus="";
		String sReturnValue="";
		
			sAdminStatus = UtilityCustomFunctions.getText(driver, lblAdminStatus);
			sReturnValue = sAdminStatus;
		
		return sReturnValue; 
		
	}
	public String fGetFirstAdminUser() throws InterruptedException {
		boolean bFlag = false;
		boolean bUserFound =  false;
		String sUNPath = "";
		String sXpath = "";
        String sAdminPath="";
//		System.out.println("accessible  name: " + btnRightNavigate.getAccessibleName());
//		System.out.println("Aria Role: " + btnRightNavigate.getAriaRole());
//		System.out.println("right enabled: " + btnRightNavigate.isEnabled());
//		
//		System.out.println("accessible  name: " + btnLeftNavigate.getAccessibleName());
//		System.out.println("Aria Role: " + btnLeftNavigate.getAriaRole());
//		System.out.println("Left enabled: " + btnLeftNavigate.isEnabled());
		String sUserName = "";
		 outerloop:
		 do{
			int iRowCount = tblUsersTr.size();
			for(int i=1;i<=iRowCount;i++) {
				// sXpath = "(//table[@class='table table-striped']//tr//span[@class='switchery switchery-default']/small)[" + i + "]";
				sUNPath = "(//table[@class='table table-striped']//tr//div[@class='col-sm-9'])[" + i + "]";
                sAdminPath = "(//table[@class='table table-striped']//tr/td[7])[" + i+1 + "]";
				WebElement eleUserCol = driver.findElement(By.xpath(sUNPath));
				WebElement eleTglAvailability = driver.findElement(By.xpath(sXpath));
                WebElement eleIsAdmin = driver.findElement(By.xpath(sAdminPath));
				String sUserDetail = eleUserCol.getText();
//				System.out.println("UserDetails:" + sUserDetail);
				String[] sSplitUserArr = sUserDetail.split("\\s+");
//				System.out.println("Array 0 Index Name" + sSplitUserArr[0]);
				if(!sSplitUserArr[0].equalsIgnoreCase("rsoft")) {
				String sAttrValues =eleTglAvailability.getAttribute("style");
//				System.out.println(sSplitUserArr[0] + " Availability: " + sAttrValues);
					if(sAdminPath.equalsIgnoreCase("Yes")) {
						sUserName = sSplitUserArr[0];
//						System.out.println("Within User with Admin:" + sUserName);
						bUserFound = true;
						break outerloop;
					}
				}
			}
			if(btnRightNavigate.isEnabled()==false) {
				bFlag =true;
			}
			if(btnRightNavigate.isEnabled()==true) {
				btnRightNavigate.click();
				Thread.sleep(3000);
			}
		}while(bFlag==false);
		 if(bUserFound==false) {
			 sUserName = null;
		 }
		 return sUserName; 
	}
	// ******************** First Admin Non Availability User ****************
	public String fGetFirstAdminNonAvailabilityUser() throws InterruptedException {
		boolean bFlag = false;
		boolean bUserFound =  false;
		String sUNPath = "";
		String sXpath = "";
		String sUserName = "";
		String sAdminPath="";
		 outerloop:
		 do{
			int iRowCount = tblUsersTr.size();
			for(int i=1;i<=iRowCount;i++) {
				int k = 0;
				sXpath = "(//table[@class='table table-striped']//tr//span[@class='switchery switchery-default']/small)[" + i + "]";
//				sUNPath = "(//table[@class='table table-striped']//tr//div[@class='col-sm-9'])[" + i + "]";
				k = i + 1;
				sAdminPath="//*[@id='userlistview']/table/tbody/tr["+ k +"]/td[7]";
				sUNPath = "//*[@id='userlistview']/table/tbody/tr["+ k +"]/td[3]";
				WebElement eleUserCol = driver.findElement(By.xpath(sUNPath));
				WebElement eleTglAvailability = driver.findElement(By.xpath(sXpath));
				WebElement eleAdminYes= driver.findElement(By.xpath(sAdminPath));
				String sUserDetail = eleUserCol.getText();
				String sAdminToggle = eleAdminYes.getText();
//				System.out.println("UserDetails:" + sUserDetail);
				String[] sSplitUserArr = sUserDetail.split("\\s+");
//				System.out.println("Array 0 Index Name" + sSplitUserArr[0]);
				if(!sSplitUserArr[0].equalsIgnoreCase("rsoft")) {
				if(sAdminToggle.trim().equalsIgnoreCase("Yes")){
				String sAttrValues =eleTglAvailability.getAttribute("style");
//				System.out.println(sSplitUserArr[0] + " Availability: " + sAttrValues);
					if(sAttrValues.contains("0px")) {
						sUserName = sUserDetail;
						System.out.println("Within User with Availability:" + sUserName);
						bUserFound = true;
						break outerloop;
					}//if Availability
				}//Is Admin
			  }//Is Not RSoft
			}//For Loop Current Page
			if(btnRightNavigate.isEnabled()==false) {
				bFlag =true;
			}
			if(btnRightNavigate.isEnabled()==true) {
				btnRightNavigate.click();
				Thread.sleep(3000);
			}
		}while(bFlag==false);
		 if(bUserFound==false) {
			 sUserName = null;
		 }
		 return sUserName; 
	}
	//**************** First Active Non Admin User **********************
	public String fGetFirstActiveNonAdminUser() throws InterruptedException {
		boolean bFlag = false;
		boolean bUserFound =  false;
		String sUNPath = "";
		String sXpath = "";
		String sUserName = "";
		String sAdminPath="";
		 outerloop:
		 do{
			int iRowCount = tblUsersTr.size();
			for(int i=1;i<=iRowCount;i++) {
				int k = 0;
				sXpath = "(//table[@class='table table-striped']//tr//span[@class='switchery switchery-default']/small)[" + i + "]";
//				sUNPath = "(//table[@class='table table-striped']//tr//div[@class='col-sm-9'])[" + i + "]";
				k = i + 1;
				sAdminPath="//*[@id='userlistview']/table/tbody/tr["+ k +"]/td[7]";
				sUNPath = "//*[@id='userlistview']/table/tbody/tr["+ k +"]/td[3]";
				WebElement eleUserCol = driver.findElement(By.xpath(sUNPath));
				WebElement eleTglAvailability = driver.findElement(By.xpath(sXpath));
				WebElement eleAdminYes= driver.findElement(By.xpath(sAdminPath));
				String sUserDetail = eleUserCol.getText();
				String sAdminToggle = eleAdminYes.getText();
//				System.out.println("UserDetails:" + sUserDetail);
				String[] sSplitUserArr = sUserDetail.split("\\s+");
//				System.out.println("Array 0 Index Name" + sSplitUserArr[0]);
				if(!sSplitUserArr[0].equalsIgnoreCase("rsoft")) {
				if(sAdminToggle.trim().equalsIgnoreCase("No")){
				    String sAttrValues =eleTglAvailability.getAttribute("style");
				    if(sAttrValues.contains("0px")) {
	                    sUserName = sUserDetail;
						System.out.println("Within User with Availability:" + sUserName);
						bUserFound = true;
						break outerloop;
					}//if Availability
					  
				}//Is Admin
			  }//Is Not RSoft
			}//For Loop Current Page
			if(btnRightNavigate.isEnabled()==false) {
				bFlag =true;
			}
			if(btnRightNavigate.isEnabled()==true) {
				btnRightNavigate.click();
				Thread.sleep(3000);
			}
		}while(bFlag==false);
		 if(bUserFound==false) {
			 sUserName = null;
		 }
		 return sUserName; 
	}
	//Get First Active User Other Than RSoft
	public String fGetFirstActiveUser() throws InterruptedException {
		boolean bFlag = false;
		boolean bUserFound =  false;
		String sUNPath = "";
		String sXpath = "";
		String sUserName = "";
		 outerloop:
		 do{
			int iRowCount = tblUsersTr.size();
			for(int i=1;i<=iRowCount;i++) {
				int k=0;
				k = i + 1;
//				sUNPath = "(//table[@class='table table-striped']//tr//div[@class='col-sm-9'])[" + i + "]";
				sUNPath = "//*[@id='userlistview']/table/tbody/tr["+ k +"]/td[3]";
				WebElement eleUserCol = driver.findElement(By.xpath(sUNPath));
				String sUserDetail = eleUserCol.getText();
				String[] sSplitUserArr = sUserDetail.split("\\s+");
				if(!sSplitUserArr[0].equalsIgnoreCase("rsoft")) {
					sUserName = sSplitUserArr[0];
					bUserFound = true;
					 break outerloop;
					
				}
			}
			if(btnRightNavigate.isEnabled()==false) {
				bFlag =true;
			}
			if(btnRightNavigate.isEnabled()==true) {
				btnRightNavigate.click();
				Thread.sleep(3000);
			}
		}while(bFlag==false);
		 if(bUserFound==false) {
			 sUserName = null;
		 }
		 return sUserName; 
	}
	
}
