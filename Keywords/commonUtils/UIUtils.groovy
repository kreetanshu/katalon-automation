package commonUtils

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory

public class UIUtils {

	void manageDriverConfiguration() {
		ChromeOptions options = new ChromeOptions()
		//		Map<String, Object> prefs = new HashMap<>()
		//
		//		prefs.put("credentials_enable_service", false)
		//		prefs.put("profile.password_manager_enabled", false)
		//		prefs.put("password_leak_detection_enabled", false)
		//
		//		options.setExperimentalOption("prefs", prefs)
		//		options.addArguments("--disable-features=SafeBrowsing,PasswordLeakToggleMove")
		//		options.addArguments("--disable-popup-blocking")
		//		options.addArguments("--disable-features=AutofillServerCommunication,PasswordManagerOnboarding")

		options.addArguments("--incognito")


		WebDriver driver = new ChromeDriver(options)
		DriverFactory.changeWebDriver(driver)
	}
}
