package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;


import testBase.BaseClass;

public class UtilityCustomFunctions extends BaseClass{

//	static List<WebElement> trows;
//	static List<WebElement> tcols;
	static Logger logger = LogManager.getLogger(UtilityCustomFunctions.class);
	public UtilityCustomFunctions(WebDriver driver) {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void clickOutside() {
        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).click().build().perform();
    }
	public static void fWaitNavLink(String sExpModuleName) {
		WebElement eleModuleLink = driver.findElement(By.xpath("//a[normalize-space()='"+sExpModuleName+"']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.elementToBeClickable(eleModuleLink));
		wait.until(ExpectedConditions.visibilityOf(eleModuleLink));
		js.executeScript("arguments[0].scrollIntoView();", eleModuleLink);
	}
	public static void checkPageLoadComplete() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (js.executeScript("return document.readyState").toString().equals("complete")){ 
			   System.out.println("Page Is loaded.");
			   return; 
		} 
	}
	public static boolean IsElementVisible(WebDriver driver,WebElement element) {
		boolean bIsElementVisible=false;
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		wait.until(ExpectedConditions.visibilityOf(element));
		js.executeScript("arguments[0].scrollIntoView();", element);
		bIsElementVisible = true;
		System.out.println(element.getTagName() + " Exist" + " True");
		}catch(Exception e) {
			bIsElementVisible = false;
			System.out.println(element.getTagName() + " Exist" + " False");
		}
		return bIsElementVisible;
	}
	
	public static String getBrowserName(WebDriver driver) {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		return browserName;
	}
	public static void sendKeysNoEnter(WebDriver webDriver,WebElement element, String keyValue) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			wait.until(ExpectedConditions.visibilityOf(element));
			js.executeScript("arguments[0].scrollIntoView();", element);
			element.clear();
			element.sendKeys(keyValue);
			Thread.sleep(1000);
			element.sendKeys(Keys.TAB);
			logger.info(keyValue + " entered in  " + element.getTagName());
			System.out.println(keyValue + " entered in  " + element.getTagName());
		}catch(Exception e) {
			System.out.println(e.getCause());
		}
	}
	
	public static void sendKeys(WebDriver webDriver, WebElement element, String keyValue) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			wait.until(ExpectedConditions.visibilityOf(element));
			js.executeScript("arguments[0].scrollIntoView();", element);
			element.clear();
//			Actions action = new Actions(driver);
//			action.moveToElement(element).doubleClick().build().perform();
			element.sendKeys(keyValue);
//			element.sendKeys(Keys.ENTER);
			logger.info(keyValue + " entered in  " + element.getTagName());
			System.out.println(keyValue + " entered in  " + element.getTagName());
		} catch (Exception e) {
			System.out.println(e.getCause());
		}

	}// function end

	public static boolean selectMultiItemfromListBox(WebDriver webDriver, WebElement element, String textValue) {
		// waitForJQueryToLoad(webDriver);
		boolean flag = false;

		try {
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);

			System.out.println("Element:" + element.getTagName());
			if (element.isDisplayed() || element.isEnabled()) {
				String arrValues[] = textValue.split(",");
				element.click();
				Thread.sleep(1000);
				List<WebElement> options = element.findElements(By.tagName("li"));
//				System.out.println(arrValues.length);
//				System.out.println("List Item Size:"+options.size());
				for (int i = 0; i < arrValues.length; i++) {
					for (WebElement option : options) {
						System.out.println("Option in List Box is : " + option.getText());
						System.out.println("Expected Value to be clicked is: " + arrValues[i]);
						if (arrValues[i].equalsIgnoreCase(option.getText().trim())) {

							System.out.println("Inside loop clicked Item is: " + option.getText());
							System.out.println("Inside click loop expected value to clicked is: " + arrValues[i]);
							BaseClass.logger.info("Expected Value:" + arrValues[i]);
							BaseClass.logger.info("DropDown Value:" + option.getText());
							option.click();
							continue;

						}
					}
				}
			}
		} catch (Exception e) {
			// Reporter.log("Exception occured while fnSelectFromComboBox event " +
			// e.getMessage());
			System.out.println(e.getMessage());
		}
		return flag;
	}// function end

	public static boolean selectFromComboBox(WebDriver webDriver, WebElement element, String textValue) {
		// waitForJQueryToLoad(webDriver);
		boolean flag = false;
		try {
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
			if (element.isDisplayed() || element.isEnabled()) {
				List<WebElement> options = element.findElements(By.tagName("option"));
				for (WebElement option : options) {
					if (textValue.equalsIgnoreCase(option.getText().trim())) {
						Thread.sleep(1000);
						option.click();
						option.sendKeys(Keys.ENTER);
						Thread.sleep(1000);
						System.out.println("[" + textValue + "] is selected");
						BaseClass.logger.info("[" + textValue + "] is selected in Dropdown");
						flag = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			// Reporter.log("Exception occured while fnSelectFromComboBox event " +
			// e.getMessage());
			System.out.println(e.getMessage());
		}
		return flag;
	}// function end

	public static boolean selectOneItemfromListBox(WebDriver webDriver, WebElement element, String textValue) {
		// waitForJQueryToLoad(webDriver);
		boolean flag = false;

		try {
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);

			System.out.println("Element:" + element.getTagName());
			if (element.isDisplayed() || element.isEnabled()) {
				Thread.sleep(3000);
				List<WebElement> options = element.findElements(By.tagName("li"));
				System.out.println("List Item Size:" + options.size());
				System.out.print("Expected item:" + textValue);
				int i=0;
				for (WebElement option : options) {
//					System.out.println(i+1 + option.getText().trim());
					if (textValue.equalsIgnoreCase(option.getText().trim())) {
						option.click();
						System.out.println("[" + textValue + "] is selected");
						BaseClass.logger.info("[" + textValue + "] is selected in List");
						flag = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			// Reporter.log("Exception occured while fnSelectFromComboBox event " +
			// e.getMessage());
			System.out.println(e.getMessage());
		}
		return flag;
	}// function end

	
	public static boolean selectItemfromListBox(WebDriver webDriver, WebElement element, String textValue,String sTagName) {
		// waitForJQueryToLoad(webDriver);
		boolean flag = false;

		try {
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
			element.click();
			System.out.println("Element:" + element.getTagName());
			if (element.isDisplayed() || element.isEnabled()) {
				Thread.sleep(3000);
				List<WebElement> options = element.findElements(By.tagName(sTagName));
				System.out.println("List Item Size:" + options.size());
				System.out.print("Expected item:" + textValue);
				int i=0;
				for (WebElement option : options) {
					System.out.println(i+1 + option.getText().trim());
					if (textValue.equalsIgnoreCase(option.getText().trim())) {
						option.click();
						System.out.println("[" + textValue + "] is selected");
						BaseClass.logger.info("[" + textValue + "] is selected in List");
						flag = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			// Reporter.log("Exception occured while fnSelectFromComboBox event " +
			// e.getMessage());
			System.out.println(e.getMessage());
		}
		return flag;
	}// function end
	
	
	public static boolean SelectItemifContains(WebDriver webDriver, WebElement element, String textValue) {
		// waitForJQueryToLoad(webDriver);
		boolean flag = false;

		try {
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);

			System.out.println("Element:" + element.getTagName());
			if (element.isDisplayed() || element.isEnabled()) {
				Thread.sleep(3000);
				List<WebElement> options = element.findElements(By.tagName("li"));
				System.out.println("List Item Size:" + options.size());
				System.out.print("Expected item:" + textValue);
				int i=0;
				for (WebElement option : options) {
					System.out.println(i+1 + option.getText().trim());
					if (option.getText().trim().contains(textValue)) {
						option.click();
						System.out.println("[" + textValue + "] is selected");
						BaseClass.logger.info("[" + textValue + "] is selected in List");
						flag = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			// Reporter.log("Exception occured while fnSelectFromComboBox event " +
			// e.getMessage());
			System.out.println(e.getMessage());
		}
		return flag;
	}// function end
	
	public static void logWriteConsole(String sText) {
		logger.info(sText);
		System.out.println(sText);
	}
	
	public static boolean doClick(WebDriver webDriver, WebElement element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		boolean isClicked = false;
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(50));
			webWait.until(ExpectedConditions.visibilityOf(element));
			webWait.until(ExpectedConditions.elementToBeClickable(element));
			js.executeScript("arguments[0].click();", element);
			isClicked = true;
		} catch (Exception ex) {
			// Reporter.log("Exception occured while doClick event " + ex.getMessage());
			System.out.println("Exception occured while doClick event " + ex.getMessage());
			throw ex;
		}
		return isClicked;
	}
	public static boolean doActionClick(WebDriver webDriver, WebElement element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		boolean isClicked = false;
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
			webWait.until(ExpectedConditions.elementToBeClickable(element));
			webWait.until(ExpectedConditions.visibilityOf(element));
//			js.executeScript("arguments[0].click();", element);
			// BaseClass.logger.info("Clicked...." + element.getAttribute("value"));
			// System.out.println("click element name : " +
			// element.getAttribute("innerText"));

			Actions action = new Actions(webDriver);
			action.moveToElement(element).click().build().perform();

			isClicked = true;
		} catch (Exception ex) {
			// Reporter.log("Exception occured while doClick event " + ex.getMessage());
			System.out.println("Exception occured while doClick event " + ex.getMessage());
			throw ex;
		}
		return isClicked;
	}


	/**
	 * This method helps to get text from web element
	 * 
	 * @param driver  The WebDriver
	 * @param element Holds the web element
	 * 
	 * @return Returns the String
	 * @throws Exception
	 */
	public static String getText(WebDriver webDriver, WebElement element) throws Exception {
		String actualValue = null;
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		try {
			WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(50));
			webWait.until(ExpectedConditions.elementToBeClickable(element));
			webWait.until(ExpectedConditions.visibilityOf(element));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			actualValue = element.getText();
		} catch (Exception ex) {
			// Reporter.log("Exception occured while getValue event " + ex.getMessage());
			System.out.println("Exception occured while getText event " + ex.getMessage());
			// throw ex;
		}
		return actualValue;
	}
	
	public static String getValue(WebDriver webDriver, WebElement element) throws Exception {
		String actualValue = null;
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		try {
			WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
			webWait.until(ExpectedConditions.elementToBeClickable(element));
			webWait.until(ExpectedConditions.visibilityOf(element));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			actualValue = element.getAttribute("value");
		} catch (Exception ex) {
			// Reporter.log("Exception occured while getValue event " + ex.getMessage());
			System.out.println("Exception occured while getValue event " + ex.getMessage());
			// throw ex;
		}
		return actualValue;
	}
	

	public static String selectFirstListItem(WebDriver driver, WebElement element) {
		// waitForJQueryToLoad(webDriver);
		String strFirstItem = "";

		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			System.out.println("Element:" + element.getTagName());
			if (element.isDisplayed() || element.isEnabled()) {
				Thread.sleep(3000);
				List<WebElement> options = element.findElements(By.tagName("li"));
				System.out.println("List Item Size:" + options.size());
				if (options.size() != 0) {
					for (WebElement option : options) {
						strFirstItem = option.getText();
						option.click();
						System.out.println("[" + option.getText() + "] is selected");
						BaseClass.logger.info("[" + option.getText() + "] is selected in List");

						break;
					} // for loop
				} else {
					// Select Next Enabled Date

					strFirstItem = "";

				} // if options!=0

			} // if isDisp/isEnab

		} catch (Exception e) {
			// Reporter.log("Exception occured while fnSelectFromComboBox event " +
			// e.getMessage());
			System.out.println(e.getMessage());
			Assert.fail("Date Not Selected");
		}
		return strFirstItem;

	}// function end

	public static boolean clickLinkItems(WebDriver webDriver, WebElement element, String textValue) {
		// waitForJQueryToLoad(webDriver);
		System.out.println("Expected Link texts: " + textValue);
		boolean flag = false;

		try {
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);

			System.out.println("Element:" + element.getTagName());
			if (element.isDisplayed() || element.isEnabled()) {
				String arrValues[] = textValue.split(",");

				Thread.sleep(1000);
				List<WebElement> options = element.findElements(By.tagName("li"));
				System.out.println("Linnks count:" + options.size());
				System.out.println("Expected 1Value: " + arrValues[0]);
				for (int i = 0; i < arrValues.length; i++) {
					for (WebElement option : options) {
						System.out.println("Option in List Box is : " + option.getText());
						System.out.println("Expected Value to be clicked is: " + arrValues[i]);
						if (arrValues[i].equalsIgnoreCase(option.getText().trim())) {

							System.out.println("Inside loop clicked Item is: " + option.getText());
							System.out.println("Inside click loop expected value to clicked is: " + arrValues[i]);
							BaseClass.logger.info("Expected Value:" + arrValues[i]);
							BaseClass.logger.info("DropDown Value:" + option.getText());
							option.click();
							continue;

						}
					}
				}
			}
		} catch (Exception e) {
			// Reporter.log("Exception occured while fnSelectFromComboBox event " +
			// e.getMessage());
			System.out.println(e.getMessage());
		}
		return flag;
	}// function end

		public static void findAndClick(String strItem, List<WebElement> sLstPicMsgItems) {
		System.out.println("Match item Inside parameter" + sLstPicMsgItems.size());

		List<WebElement> sMatchItems = sLstPicMsgItems;
		System.out.println("Match item:" + sMatchItems.size());

		for (WebElement item : sMatchItems) {
			if (strItem.equalsIgnoreCase(item.getText().trim())) {
				System.out.println("Matched item:" + item.getText());
				item.click();
				break;
			}

		} // for loop

	}// method
	public  static String fArrayConcat(String sGivenText) {
		String sEndText="";
		String sGivenTextArray[] = sGivenText.split("\\s+");
		for(int i=0;i<sGivenTextArray.length;i++) {
			sEndText = sEndText + sGivenTextArray[i] + " "; 
		}
		sEndText = sEndText.trim();
		return sEndText;  
	}

	// get Current Date in given format
	public static String getCurrentDate(String dtFormat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
		Date date = new Date();
		String sDate = dateFormat.format(date);
		return sDate;
	}
	public static void fSoftAssert(String sActValue, String sExpValue, String sMessage, ExtentTest Node)
			throws IOException, InterruptedException {
		
		BaseClass a = new BaseClass();
		UtilityCustomFunctions.logWriteConsole("Actual:"+sActValue);
		UtilityCustomFunctions.logWriteConsole("Expected:"+sExpValue);
		UtilityCustomFunctions.logWriteConsole("Message:"+sMessage);
		if(sActValue!="" && sActValue!=null && sExpValue!="" && sExpValue!=null) {
		if (sActValue.trim().equalsIgnoreCase(sExpValue.trim())) {
//			BaseClass.logger
//					.info(sMessage + " Passed : Actual Value is: " + sActValue + "Expected Value is: " + sExpValue);
			a.freport("Actual: "+sActValue+" Expected: "+sExpValue + " " + sMessage, "pass", Node);
			iValCount = iValCount + 1;
			iPassCount = iPassCount  + 1; 
//			System.out.println(sMessage + " Passed : Actual Value is: " + sActValue + "Expected Value is: " + sExpValue);
			
		} else {
//			BaseClass.logger
//					.info(sMessage + " Failed : Actual Value is: " + sActValue + "Expected Value is: " + sExpValue);
			a.freport("Actual: "+sActValue+" Expected: "+sExpValue + " " + sMessage, "fail", Node);
//			System.out.println(sMessage +  "Failed : Actual Value is: " + sActValue + "Expected Value is:"  + sExpValue);
			iValCount = iValCount + 1;
			iFailCount = iFailCount + 1;
		}
		sAssertinFn.assertEquals(sActValue.trim(), sExpValue.trim());
		}
	}
	
	
}