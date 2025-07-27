package projectUtils

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.session.core.model.Product

import CustomKeywords
import internal.GlobalVariable




public class Products {

	@Keyword
	void validateProductCount(int expectedProductCount) {
		int actualProductCount = WebUI.findWebElements(findTestObject('Object Repository/webui/landingPage/inventoryItems'),5).size()

		assert actualProductCount >= expectedProductCount : "The number of actual product count ${actualProductCount} is more than ${expectedProductCount}"
	}

	@Keyword
	void selectAndProductToCart(String productName) {

		TestObject productItem = findTestObject('Object Repository/webui/productsCatalogPage/addProductToCart',['productName': productName])
		WebUI.verifyElementVisible(productItem)
		WebUI.click(productItem)
		WebUI.click(findTestObject('Object Repository/webui/productsCatalogPage/cartLink'))

		WebUI.waitForElementVisible(findTestObject('Object Repository/webui/cartPage/cartItemName',['productName': productName]), 4)
		
		WebUI.takeFullPageScreenshot()

		WebUI.click(findTestObject('Object Repository/webui/cartPage/checkOut'))

		WebUI.waitForElementPresent(findTestObject('Object Repository/webui/checkoutPage/firstName'), 4)
		WebUI.setText(findTestObject('Object Repository/webui/checkoutPage/firstName'), "Katalon")
		WebUI.setText(findTestObject('Object Repository/webui/checkoutPage/lastName'), "Test")
		WebUI.setText(findTestObject('Object Repository/webui/checkoutPage/postalCode'), "10001")

		WebUI.click(findTestObject('Object Repository/webui/checkoutPage/continueButton'))
		WebUI.click(findTestObject('Object Repository/webui/checkoutPage/checkoutFinish'))

		String checkoutMsg = WebUI.getText(findTestObject('Object Repository/webui/checkoutPage/checkoutCompleteMessage'))

		KeywordUtil.logInfo("The final checkout message is ${checkoutMsg}")
		WebUI.takeFullPageScreenshot()
	}

	@Keyword
	void addMultipleProducts() {
		List<List<Objects>> allData = findTestData('ProductDetails').getAllData()
		//println(allData) back-to-products
		String expectedUrl = GlobalVariable.productPageUrl

		for(List<Objects> row: allData) {
			checkAndReturnToUrl(expectedUrl)
			CustomKeywords.'projectUtils.Products.selectAndProductToCart'(row.get(0))
			//WebUI.callTestCase(findTestCase('Test Cases/webui/products/addProductToCart'), [('productName') : row.get(0)])
			//			for(Object data: row) {
			//				print(data+" ")
			//			}
			//			println()
		}
	}

	@Keyword
	boolean checkAndReturnToUrl(String expectedUrl) {
		String currentUrl = WebUI.getUrl();
		if(!currentUrl.equalsIgnoreCase(expectedUrl)) {
			WebUI.navigateToUrl(expectedUrl)
		}
	}
}
