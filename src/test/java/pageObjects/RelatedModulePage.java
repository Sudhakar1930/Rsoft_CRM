package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.CRMReUsables;
import utilities.UtilityCustomFunctions;

public class RelatedModulePage extends BasePage {

	public RelatedModulePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//div[@id='inputBox']/input[@type='text'][@name='search_value']")
	WebElement eleRelMatrSearchBox;
	
	@FindBy(xpath="//i[contains(@class,'fa fa-search')]")
	WebElement btnSearch;
	
	@FindBy(xpath="//table[@class='table table-striped popupview']/tbody")
	WebElement WbTblRow;
	
	
	public void SelectRelatedModuleRow(String sRelModText) throws Exception {
		CRMReUsables objCRMRs = new CRMReUsables();
		Thread.sleep(1000);
//		UtilityCustomFunctions.sendKeys(driver, eleRelMatrSearchBox, sRelModText);
		UtilityCustomFunctions.logWriteConsole("Search Button clicked in Related Module Window");
		Thread.sleep(1000);
		UtilityCustomFunctions.doClick(driver, btnSearch);
		UtilityCustomFunctions.logWriteConsole("Button Search");
		Thread.sleep(3000);
		List<WebElement> tTableRows = WbTblRow.findElements(By.tagName("tr"));
		UtilityCustomFunctions.logWriteConsole("table row Count:" + tTableRows.size());
		Thread.sleep(2000);
		int iRowCount = tTableRows.size();
		if(iRowCount>=1) {
			tTableRows.get(0).click();
			Thread.sleep(3000);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("No Search Record Present in Related Module Search");
		}
	}
	
	
	
	
	
}
