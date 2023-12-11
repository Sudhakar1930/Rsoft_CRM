package pageObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import freemarker.log.Logger;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
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
	
	
	
	
	
	public void clickWorkflowsList(String sModuleName) throws Exception {
		System.out.println("Before clicking MenuItem");
		boolean bFlag = UtilityCustomFunctions.IsElementVisible(driver, eleSelectWorkflowList);
		if(bFlag==true) {
//		UtilityCustomFunctions.doClick(driver, eleSelectWorflowList);
		eleSelectWorkflowList.click();
		System.out.println("After clicking Workflow combox");
		Thread.sleep(2000);
		String sValue = sModuleName +" " + "Workflow"; 
		System.out.println("Item to be selected:  " + sValue);
			UtilityCustomFunctions.selectOneItemfromListBox(driver, eleWorlflowUl, sValue);
			Thread.sleep(1000);
		}
		else {
		 System.out.println("Combo Box missing");	
		}
		
	}
	
	public boolean checkWorkflowStatus(String sModuleName, String sWorkflowName,String sExecCondition) {
		boolean bWorkflowStatus=false;
		boolean bWorkFlowEnabled = false;
		String sActWorkflowName="";
		String sActExecCondition="";
		List<WebElement> tRowCount = driver.findElements(By.xpath("//*[@id='workflowlist']//table//tr"));
		System.out.println("Rowcount:" + tRowCount.size());
		for(int i=1;i<tRowCount.size();i++) {
			System.out.println("Current Row Number: " + i);
			List<WebElement> tColumnCount =tRowCount.get(i).findElements(By.tagName("td"));
			
			String sXpath="(//span[@class='switchery switchery-default'])["+i+"]/small";

			WebElement element = driver.findElement(By.xpath(sXpath));
			String sAttrValues =element.getAttribute("style");
			System.out.println(sAttrValues);
			if(sAttrValues.contains("0px")) {
				bWorkflowStatus = false;
			}
			else
			{
				bWorkflowStatus = true;
			}

			System.out.println("Workflow status: " + bWorkflowStatus);
			String sActModuleValues=tColumnCount.get(1).getText();
			String sModNameArray[] = sActModuleValues.split("\\s+");
			String sActModuleName = sModNameArray[1].trim(); 
			sActWorkflowName=tColumnCount.get(2).getText().trim();
			sActExecCondition=tColumnCount.get(3).getText().trim();
			
			System.out.println("Actual Module Name:  " + sActModuleName);
			System.out.println("Actual Workflow Name:  " + sActWorkflowName);
			System.out.println("Actual Exec Condition Name:  " + sActExecCondition);
			if(sActModuleName.equalsIgnoreCase(sModuleName) && sActWorkflowName.equalsIgnoreCase(sWorkflowName) && sActExecCondition.equalsIgnoreCase(sExecCondition)){
				if(bWorkflowStatus==true) {
					bWorkFlowEnabled = true;
					break;
				}
			}
			else {
				if(bWorkflowStatus==true) {
					element.click();
				}
			}//conditon checking
			
		}//for table row loop
		System.out.println("Workflow status in workflow page: " + bWorkFlowEnabled);
		return bWorkFlowEnabled;
	}//function
	
	
	
	
}
