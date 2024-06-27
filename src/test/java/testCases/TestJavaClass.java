package testCases;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestJavaClass {

	public static void main(String[] args) {
		WebDriver driver;
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-extensions");
		options.addArguments("-–disable-notifications");
		options.addArguments("--disable-user-media-security=true");
		options.setExperimentalOption("debuggerAddress", "localhost:9992");
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
////		options.addArguments("--headless");     
//        //options.addArguments("--disable-gpu");
//        //options.addArguments("--window-size=1400,800");

//		Map<String, Object> prefs = new HashMap<String, Object>();
//		prefs.put("autofill.profile_enabled", false);
//		options.add_argument("--disable-infobars")
//		options.add_argument("--window-size=800,600")
		
//		options.add_experimental_option("prefs", {"profile.default_content_setting_values.media_stream_mic": 1,     
//		    "profile.default_content_setting_values.media_stream_camera": 1,   
//		    "profile.default_content_setting_values.geolocation": 1,           
//		    "profile.default_content_setting_values.notifications": 1          
//		  });
		
//		options.addArguments("allow-file-access-from-files");
//		options.addArguments("use-fake-device-for-media-stream");
//		options.addArguments("use-fake-ui-for-media-stream");
//		
//		prefs.put("profile.default_content_setting_values.media_stream_mic", 1); 
//		prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
//		prefs.put("profile.default_content_setting_values.geolocation",1); 
//		prefs.put("profile.default_content_setting_values.notifications",1); 
		
//		options.setExperimentalOption("prefs", prefs);
//		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//		options.merge(capabilities);
		
		driver=new ChromeDriver(options);
		driver.get("https://www.mvnrepository.com");

	}

}
