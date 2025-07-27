package projectUtils

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import commonUtils.UIUtils

public class LoginLogoutUtils {
	UIUtils uiutils = null;

	LoginLogoutUtils(){
		this.uiutils= new UIUtils();
	}

	@Keyword
	void webLogin(String url, String username, String password) {

		assert !url.isEmpty() : "The web URL is empty"
		assert !username.isEmpty() : "The username is empty"
		assert !password.isEmpty() : "The password is empty"

		try {
			//uiutils.manageDriverConfiguration()
			RunConfiguration.setWebDriverPreferencesProperty("args", ["--incognito"])
			WebUI.openBrowser(url);
			WebUI.maximizeWindow();
		}
		catch(Exception e) {
			KeywordUtil.markFailedAndStop("The browser was not open with the $url \n"+ e.printStackTrace())
		}

		WebUI.setText(findTestObject('Object Repository/webui/loginPage/usernameInput'), username)
		WebUI.setText(findTestObject('Object Repository/webui/loginPage/passwordInput'), password)
		WebUI.click(findTestObject('Object Repository/webui/loginPage/loginButton'))
		findTestObject('Object Repository/webui/landingPage/inventoryItems')

		TestObject inventoryItems = findTestObject('Object Repository/webui/landingPage/inventoryItems')
		WebUI.waitForElementVisible(inventoryItems, 10)

		int numberOfItems = WebUI.findWebElements(inventoryItems, 5).size()

		KeywordUtil.logInfo("The login is succeeded and number of product items is $numberOfItems")
		WebUI.takeFullPageScreenshot()

		assert numberOfItems > 0 : "The number of product items is zero"
	}

	@Keyword
	void logout(String logoutPageLink){
		String getPageUrl = WebUI.getUrl();
		TestObject burgerMenuObj = findTestObject('Object Repository/webui/landingPage/burgerMenu')
		TestObject logoutLink = findTestObject('Object Repository/webui/landingPage/logoutLink')

		if(getPageUrl.equalsIgnoreCase(logoutPageLink)) {
			WebUI.click(burgerMenuObj)
			WebUI.waitForElementVisible(logoutLink , 5)
			WebUI.click(logoutLink)
		}
		else {
			WebUI.navigateToUrl(logoutPageLink)
			WebUI.waitForElementVisible(burgerMenuObj,5)
			WebUI.click(burgerMenuObj)
			WebUI.waitForElementVisible(logoutLink , 5)
			WebUI.click(logoutLink)
		}
	}
}
