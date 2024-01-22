package pageObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

import freemarker.log.Logger;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import testBase.BaseClass;
import utilities.UtilityCustomFunctions;

public class WorkFlowPage extends BasePage {

	public WorkFlowPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	@FindBy(xpath="(//span[@class='selection']/span/span)[1]")
	WebElement eleSelectWorkflowList;
	
	@FindBy(xpath="//*[@role='tree']")
	WebElement eleWorlflowUl;
	
	@FindBy(xpath="//*[@id='workflowlist']//table//tr")
	WebElement eleWorkflowWebTbl;
	
	@FindBy(xpath="//span[@class='switchery switchery-default']")
	WebElement eleWorkflowStatus;
	
	@FindBy(xpath="//button[@class='btn btn-primary mr-1 workflowstep1submit']")
	WebElement btnstep1Next;
	
	@FindBy(xpath="//button[@id='nextButton']")
	WebElement btnstep2Next;
	
	@FindBy(xpath="//*[@id='workflowlist']//table//tr")
	List<WebElement> WbTblWorkflows;
	
	@FindBy(xpath="//table[@class='table table-striped workflowalltask']//tr")
	List<WebElement> WbTblTasks;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btnSubmit;
	
	
	public void clickSubmit() throws Exception {
		UtilityCustomFunctions.doClick(driver,btnSubmit);
	}
	
	
	public String fWorkFlowStatus(String sModuleName,String sWorkflowName,String sExecCondition) {
		BaseClass bObj = new BaseClass();
		boolean bCurrentWFStatus=false;
		boolean bIfCurWrkFlwNotEnabled = false;
		String bWorkFlowEnabled ="false:0";
		int iEditPos=0;
		String sActWorkflowName="";
		String sActExecCondition="";
		int j=0;
		int tRowCount = WbTblWorkflows.size();
		System.out.println("Workflows count:" + tRowCount);
		for(int i=1;i<tRowCount;i++) {
			
			System.out.println("Current Row Number: " + i);
			bObj.logger.info("Current Workflow Row item: " + i);
			
			List<WebElement> tColumnCount =WbTblWorkflows.get(i).findElements(By.tagName("td"));
			
			String sXpath="(//span[@class='switchery switchery-default'])["+i+"]/small";
			WebElement eleStatus = driver.findElement(By.xpath(sXpath));
			String sAttrValues =eleStatus.getAttribute("style");
			bObj.logger.info("Current style of Worflow Status Element captured");
			System.out.println("Current style of Worflow Status Element captured");
			if(sAttrValues.contains("0px")) {
				bCurrentWFStatus = false;
			}
			else
			{
				bCurrentWFStatus = true;
			}
			System.out.println("Workflow status: of row item: " + i + " " + bCurrentWFStatus);
			bObj.logger.info("Workflow status: of row item: " + i + " " + bCurrentWFStatus);
			String sActModuleValues=tColumnCount.get(1).getText();
			String sModNameArray[] = sActModuleValues.split("\\s+");
			String sActModuleName = sModNameArray[1].trim(); 
			sActWorkflowName=tColumnCount.get(2).getText().trim();
			sActExecCondition=tColumnCount.get(3).getText().trim();
			
			BaseClass.logger.info("Actual Module Name:  " + sActModuleName);
			BaseClass.logger.info("Actual Workflow Name:  " + sActWorkflowName);
			BaseClass.logger.info("Actual Exec Condition Name:  " + sActExecCondition);
			System.out.println("Actual Module Name:  " + sActModuleName);
			System.out.println("Expected Module Name:  " + sModuleName);
			System.out.println("Actual Workflow Name:  " + sActWorkflowName);
			System.out.println("Expected Workflow Name:  " + sWorkflowName);
			System.out.println("Actual Exec Condition Name:  " + sActExecCondition);
			System.out.println("Expected Exec Condition Name:  " + sExecCondition);
			System.out.println("Row Position: Outside If" + i);
			if(sActModuleName.trim().equalsIgnoreCase(sModuleName) && sActWorkflowName.trim().equalsIgnoreCase(sWorkflowName) && sActExecCondition.equalsIgnoreCase(sExecCondition)){
				System.out.println("bCurrentWFStatus:" + bCurrentWFStatus);
				BaseClass.logger.info("Module,workflow,condition matches");
				BaseClass.logger.info("bCurrentWFStatus: " + bCurrentWFStatus);
				if(bCurrentWFStatus==true) {
					bWorkFlowEnabled = "true:"+i;
					BaseClass.logger.info(sActWorkflowName + "Workflow enabled");
					System.out.println(sActWorkflowName + "Workflow enabled");
					iEditPos = i;
				}
				else {
					j = i;
//					bWorkFlowEnabled = "true:"+j;
					bIfCurWrkFlwNotEnabled = true;
				}
			}
			else {
				System.out.println("bCurrentWFStatus: In else block" + bCurrentWFStatus);
				BaseClass.logger.info("Module,workflow,conditions not matched ");
				BaseClass.logger.info("bCurrentWFStatus: " + bCurrentWFStatus);
				
				if(bCurrentWFStatus==true) {
					eleStatus.click();
					BaseClass.logger.info(sActWorkflowName + "Workflow disabled manually");
					System.out.println(sActWorkflowName + "Workflow disabled manually");
				}
			}//conditon checking
		}//for loop
		if(bIfCurWrkFlwNotEnabled==true) {
		String sXpath="(//span[@class='switchery switchery-default'])["+j+"]/small";
		WebElement eleCurrentWFStatus = driver.findElement(By.xpath(sXpath));
		bWorkFlowEnabled = "true:"+j;
		eleCurrentWFStatus.click();
		}
		UtilityCustomFunctions.logWriteConsole(sActWorkflowName + "Disabled Workflow Manually Enabled");
		return bWorkFlowEnabled;
		
	}
	
	
	
	public void fClickWorkflowsList(String sModuleName) throws Exception {
		System.out.println("Before clicking MenuItem");
		boolean bFlag = UtilityCustomFunctions.IsElementVisible(driver, eleSelectWorkflowList);
		if(bFlag==true) {
//		UtilityCustomFunctions.doClick(driver, eleSelectWorflowList);
		eleSelectWorkflowList.click();
		BaseClass.logger.info("Clicked Workflow from Combox Box List");
		System.out.println("After clicking Workflow combox");
		Thread.sleep(2000);
		String sValue = sModuleName +" " + "Workflow"; 
		System.out.println("Item to be selected:  " + sValue);
		BaseClass.logger.info("Item to be selected:  " + sValue);
			UtilityCustomFunctions.selectOneItemfromListBox(driver, eleWorlflowUl, sValue);
			BaseClass.logger.info("Item selected in combox Box  " + sValue);
			System.out.println("Item selected in combox Box  " + sValue);
			Thread.sleep(1000);
		}
		else {
		 System.out.println("Combo Box missing");
		 BaseClass.logger.info("Workflow combox box missing");
		}
		
	}
	
	public void fNavigateToTask(int iEditPos) throws Exception {
		String sEditXpath="(//i[@class='fa fa-edit'])[" + iEditPos +"]";
		WebElement btnEditWFRow  = driver.findElement(By.xpath(sEditXpath));
		UtilityCustomFunctions.doClick(driver, btnEditWFRow);
		BaseClass.logger.info("Work flow clicked");
		System.out.println("Work flow clicked");
		Thread.sleep(1000);
		UtilityCustomFunctions.doClick(driver, btnstep1Next);
		BaseClass.logger.info("Cliked Step 1 Next Button to Condition Page");
		System.out.println("Cliked Step 1 Next Button to Condition Page");
		UtilityCustomFunctions.doClick(driver, btnstep2Next);
		BaseClass.logger.info("Cliked Step 2 Next Button to Task List Page");
		System.out.println("Cliked Step 2 Next Button to Task List Page");
		Thread.sleep(1000);
	}
	
	public boolean fValidateTaskStatus(String sWorkFlow,String sActionType,String sActionTitle) throws Exception {
		boolean bWorkflowTaskEnabled = false;
		boolean bIsCurrentTaskDisabled = false;
		int tTaskRowCount = WbTblTasks.size()-1; 
		System.out.println("Rowcount:" + tTaskRowCount);
		BaseClass.logger.info("Number of Tasks Listed for this Workflow: " + sWorkFlow + " : " + tTaskRowCount);
		System.out.println("Number of Tasks Listed for this Workflow: " + sWorkFlow + " : " + tTaskRowCount);
		int j = 0;
		for(int i=1;i<=tTaskRowCount;i++) {
			System.out.println("Current Row Number: " + i);
		
			String sTaskXPath = "(//tr//input[@name='activetemp' and @type='checkbox'])["+i+"]";
			WebElement eleTaskCheckBox = driver.findElement(By.xpath(sTaskXPath));
	
			boolean isCheckBoxSelected = eleTaskCheckBox.isSelected();
			BaseClass.logger.info("Is Task Active: "+ i + " : "+ isCheckBoxSelected);
			System.out.println("Is Task Active: "+ i + " : "+ isCheckBoxSelected);
			List<WebElement> tTaskColumnCount =WbTblTasks.get(i).findElements(By.tagName("td"));
			String sActActionType = UtilityCustomFunctions.getText(driver, tTaskColumnCount.get(1));
			String sActActionTitle = UtilityCustomFunctions.getText(driver, tTaskColumnCount.get(2));
			sActActionType = sActActionType.trim();
			sActActionTitle = sActActionTitle.trim();
			System.out.println("Actual type:" + sActActionType + "actual title:" + sActActionTitle);
			System.out.println("Expected type:" + sActionType + "Expected title:" + sActionTitle);
			UtilityCustomFunctions.logWriteConsole("Retrieved Action Type:" + sActActionType);
			UtilityCustomFunctions.logWriteConsole("Retrieved Action Title:" + sActActionTitle);
			if(sActActionType.trim().equalsIgnoreCase(sActionType) && sActActionTitle.trim().equalsIgnoreCase(sActionTitle)) {
				if(isCheckBoxSelected==true) {
					bWorkflowTaskEnabled = true;
					BaseClass.logger.info("Action Type and Action Tile matches and also Task Button Enabled");
					System.out.println("Action Type and Action Tile matches and also Task Button Enabled");
				}
				else {
					j = i;
					bIsCurrentTaskDisabled = true;
				}
			}
			else {
				System.out.println(" when task not matches:"+ isCheckBoxSelected +"  actual action type:" + sActActionType + "action title:" + sActActionTitle);
				if(isCheckBoxSelected==true) {
					System.out.println("checkbox sttus" + isCheckBoxSelected +"actual action type:" + sActActionType + "action title:" + sActActionTitle);
					eleTaskCheckBox.click();
					BaseClass.logger.info("Task Deactivated for Action Type:" + sActActionType + "and Action Title:" + sActActionTitle);
					System.out.println("Task Deactivated for Action Type:" + sActActionType + "and Action Title:" + sActActionTitle);
				}
			}
		}//for loop
		System.out.println("Current Task status: " + j + bIsCurrentTaskDisabled);
		if(bIsCurrentTaskDisabled==true) {
		System.out.println("Inside the if condition when task is disabled");
		String sTaskXPath = "(//tr//input[@name='activetemp' and @type='checkbox'])["+j+"]";
		WebElement eleTaskCheckBox = driver.findElement(By.xpath(sTaskXPath));
		eleTaskCheckBox.click();
		bWorkflowTaskEnabled = true;
		}
		clickSubmit();
		return bWorkflowTaskEnabled;
	}//function
	
	
	
	
	
}
