package testBase;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;


import utilities.ExtentReportManager;
import utilities.UtilityCustomFunctions;

public class BaseClass extends ExtentReportManager{
	public static WebDriver driver;
	public static Logger logger;
	public ResourceBundle rb;
	public static SoftAssert sAssertinFn;
	protected static File file;
	
	public void testname(String testname, String name) {
		test = extent.createTest(testname);
		node = test.createNode(name);
		extent.attachReporter(sparkReporter);
	}

	
	//@BeforeClass(groups= {"Sanity","Regression","Master"})
//	public void fRunBeforeTest() {
//		
//	}
	
	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void setup(String br)
	{
		System.out.println("Before Class: ");
		rb = ResourceBundle.getBundle("config");//Read config.properties.
		sAssertinFn = new SoftAssert();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		switch(br) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			
			options.addArguments("--start-maximized");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("-–disable-notifications");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//			options.addArguments("--headless");     
	        //options.addArguments("--disable-gpu");
	        //options.addArguments("--window-size=1400,800");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("autofill.profile_enabled", false);
			
			prefs.put("profile.default_content_setting_values.media_stream_mic", 1); 
			prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
			prefs.put("profile.default_content_setting_values.geolocation",1); 
			prefs.put("profile.default_content_setting_values.notifications",1); 
			
			options.setExperimentalOption("prefs", prefs);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(capabilities);
			
			driver=new ChromeDriver(options);
			break;
		case "edge":
			EdgeOptions eoptions = new EdgeOptions();

			eoptions.addArguments("--start-maximized");
			eoptions.addArguments("--remote-allow-origins=*");
			eoptions.addArguments("--disable-infobars");
			eoptions.addArguments("--disable-extensions");
			eoptions.addArguments("-–disable-notifications");
			eoptions.addArguments("--guest");
			eoptions.addArguments("use-fake-device-for-media-stream");
			eoptions.addArguments("use-fake-ui-for-media-stream");    
			// microphone popup not allows now.
			
            Map<String,Object> prefsedge = new HashMap<>();
            Map<String,Object> profile = new HashMap <>();
            Map<String,Object> contentSettings = new HashMap<>();
            contentSettings.put("geolocation", 2);
            profile.put("managed_default_content_settings", contentSettings);
            prefsedge.put("profile", profile);
            eoptions.setExperimentalOption("prefs", prefsedge);
            capabilities.merge(eoptions);
			
			//location popup still comes
		
			capabilities.setCapability(EdgeOptions.CAPABILITY, eoptions);
			eoptions.merge(capabilities);
			driver= new EdgeDriver(eoptions);
			break;
		case "firefox":
			FirefoxOptions foptions = new FirefoxOptions();
			
			FirefoxProfile fPrf = new FirefoxProfile();
			
			
			
			fPrf.setPreference("dom.webnotifications.enabled", false);
	
			fPrf.setPreference("geo.enabled", false);
			
			fPrf.setPreference("permissions.default.microphone",1);
			fPrf.setPreference("permissions.default.camera",1);
			
//			foptions.addArguments("-–disable-notifications");
			//foptions.addPreference("dom.webnotifications.enabled",false);

			foptions.addArguments("--guest");
			foptions.addArguments("--start-maximized");
////			foptions.addArguments("--remote-allow-origins=*");
//			foptions.addArguments("--disable-infobars");
//			foptions.addArguments("--disable-extensions");
//			foptions.addArguments("-–disable-notifications");
			foptions.setProfile(fPrf);
			driver = new FirefoxDriver(foptions);
			
			/*
			
		
		DesiredCapabilities capabilities=DesiredCapabilities.firefox();
		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		driver = new FirefoxDriver(capabilities);
		driver.get(“your Web site”);


			Map<String, Object> fprefs = new HashMap<String, Object>();
			fprefs.put("autofill.profile_enabled", false);
			
			fprefs.put("profile.default_content_setting_values.media_stream_mic", 1); 
			fprefs.put("profile.default_content_setting_values.media_stream_camera", 1);
			fprefs.put("profile.default_content_setting_values.geolocation",1); 
			fprefs.put("profile.default_content_setting_values.notifications",1); 
			
			//foptions.setExperimentalOption("prefs", fprefs);
			foptions.addPreference(br, capabilities);
			capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS,foptions);
			foptions.merge(capabilities);
			*/
			
			//capabilities.setCapability(br, capabilities);
			//driver = new FirefoxDriver(foptions);
			
			
		
		default:
			//driver = new ChromeDriver();
			
		}
		
		logger = LogManager.getLogger(this.getClass());
		
		
		//WebDriverManager.chromedriver().setup();
		
		
		//options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		/*
		options.addArguments("--disable-autofill");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("-–disable-notifications");
		
		*/
		//options.AddUserProfilePreference("autofill.profile_enabled", false);
		
		
		/*
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("disable-popup-blocking", true);
		prefs.put("disable-infobars", true);
		*/
		
		
		// To remove the message "Your browser is controlled by automated software."
		//prefs.put("excludeSwitches", new String[] {"enable-automation"});
		//options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		// To disable the auto save page password popup.
		
		
		
		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get(rb.getString("appURL"));
		
		
		//driver.manage().window().maximize();
	}
	//@AfterClass(groups= {"Sanity","Regression","Master","DataDriven"})
	
	
	
//	@AfterClass(alwaysRun = true)
//	public void tearDown() {
//		driver.quit();
//	}
	
	
	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}

	public String randomeNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(10);
		return (generatedString2);
	}
	
	public String randomAlphaNumeric() {
		String st = RandomStringUtils.randomAlphabetic(4);
		String num = RandomStringUtils.randomNumeric(3);
		
		return (st+"@"+num);
	}
	
	
	public void freport(String dec, String status,ExtentTest node) throws IOException, InterruptedException {
		Media img = null;
		String filepath = "";
		
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		System.err.println(number);
		try {
		String date = new SimpleDateFormat("dd-MMM-yy").format(new Date());
		String date1 = new SimpleDateFormat("HH.mm").format(new Date());
		filepath= "./reports/Screenshots/"+date+"/"+ date1 +"_"+ number + ".jpg";
		Thread.sleep(1000);		
		file = new File(filepath);
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		FileUtils.copyFile(takesScreenshot.getScreenshotAs(OutputType.FILE),
					file);
			
//		img = MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir")+"/reports/Screenshots/"+date+"/"+date1+"_"+number+".jpg").build();
		img = MediaEntityBuilder.createScreenCaptureFromPath("./../Screenshots/"+date+"/"+date1+"_"+number+".jpg").build();
		
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		if(status.equalsIgnoreCase("pass")) {
			System.err.println("Before node Pass");
			System.out.println(dec + " Pass");
			
			node.pass(dec, img);

		}else if (status.equalsIgnoreCase("fail")) {
			System.err.println("Before node Fail");
			node.fail(dec, img);
			System.err.println("After node Fail");
			System.out.println(dec + " fail");
		}
		}
	
	

	
	
}
