package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

import pageObjects.DetailViewPage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.UtilityCustomFunctions;
public class TestHiddenControl extends BaseClass{
	
	
	@Test
	public void testedit() throws Exception {
		LoginPage objLP = new LoginPage(driver);
		DetailViewPage objDVP = new DetailViewPage(driver);
		String sUserName1 =  rb.getString("userName3");
		String sPassword1 =  rb.getString("passWord3");
		driver.get("https://rdot.in/public/admin?Module=Crmmodonlyonfirstsave&Related=Summary&record=91619&view=Detail");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		objLP.setCompanyName("PRIYAN");
		objLP.setUserName("Sudhakar");
		objLP.setPassword("Automation@123");
		objLP.clickLoginSubmit();
		Actions action = new Actions(driver);
		String sXpath1 = "(//div[@class='col-lg-10'])[1]";
		WebElement eleArrSummary = driver.findElement(By.xpath(sXpath1));
		Thread.sleep(2000);
		action.moveToElement(eleArrSummary).perform();
		
		Thread.sleep(1000);
//		String sXpath ="//i[@class='fa fa-edit 5862_icon hide']";
		String sXpath="//table[contains(@class,'table table-bordered')]/tbody[1]/tr[2]/td[2]/div[1]/div[2]/i[1]";
		WebElement eleRecEdit = driver.findElement(By.xpath(sXpath));
		eleRecEdit.click();
//		UtilityCustomFunctions.doClick(driver, eleRecEdit);
//		UtilityCustomFunctions.doActionClick(driver, eleRecEdit);
//		action.moveToElement(eleRecEdit).click().build().perform();
//		action.doubleClick(eleRecEdit).perform();
		Thread.sleep(5000);
	}

}


