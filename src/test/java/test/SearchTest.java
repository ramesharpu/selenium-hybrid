package test;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.Base;
import utils.Keyword;

public class SearchTest extends Base{
	WebDriver driver;
	WebDriverWait wait;
	
	String searchText = "Redmi Note 8 Pro";
	
	Keyword action;
	@BeforeClass
	public void beforeClass() {
		action = new Keyword();
		driver = action.getDriverObject();
		wait = new WebDriverWait(driver, 20);
	}
	
	@Test(description = "Test case to search for Mi Mobile")
	public void searchForMiMobile() {
		action.searchProduct(searchText);
		String totalResult = action.getSearchResultsSummary(); 
		assertTrue(totalResult.contains(searchText));
	}

	@Test(description = "Test case to select 4 Starts and above average customer rating",
			dependsOnMethods = "searchForMiMobile")
	public void select4StartAndUpRating() {
		action.avgCustomerReviewFilter(4);
		String actualFilterElem = driver.findElement(By.xpath("//a/span[text()='4 Stars & Up']")).getText();
		String filterMessage = "4 Stars & Up";
		assertTrue(actualFilterElem.equals(filterMessage));
	}
	
	@Test(description = "Test case to select mi mobile after searching it",
			dependsOnMethods = "select4StartAndUpRating")
	public void productSelectionTest() {
		String productLinkName = action.getTheFirstProductText(searchText);
		action.selectTheFirstProduct(searchText);

		List<String> tabs = action.getAllWindowDetails(); 
		action.closeCurrentWindow();
		action.switchToNewWindow(tabs.get(1));
		
		String pageTitle = driver.getTitle();
		assertTrue(pageTitle.contains(productLinkName));		
	}
	
	@Parameters({"mobilePrice"})
	@Test(description = "Test case to validate the Mi Mobile price",
			dependsOnMethods = "productSelectionTest")
	public void priceValidation(String expectedPrice) {
		String actualPrice = action.getProductPrice();
		assertTrue(actualPrice.equals(expectedPrice));
	}

	@Parameters({"pincode"})
	@Test(description = "Test case to validate delivery location",
			dependsOnMethods = "productSelectionTest")
	public void deliveryLocationValidation(String pincode) {
		action.enterPincode(pincode);
		String pincodeConfirmation = driver.findElement(By.xpath("//div[@id='newAccordionRow']//div[@id='contextualIngressPtLabel_deliveryShortLine']/span[2]")).getText();
		assertTrue(pincodeConfirmation.contains(pincode));
	}

	@Test(description = "Test case to validate the sponsored link",
			dependsOnMethods = "deliveryLocationValidation")
	public void sponsoredProductValidation() {
		action.switchToSponsoredProductIframe();
		
		List<String> tabs = action.getAllWindowDetails();
		action.switchToNewWindow(tabs.get(1));
		action.closeCurrentWindow();
		action.switchToNewWindow(tabs.get(0));
		action.switchToMainPage();
	}

	@Test(description = "Test case to validate the Add to Cart functionality",
			dependsOnMethods = "sponsoredProductValidation")
	public void addToCartValidation() {
		String xpathExpression = "//div[@id='attach-added-to-cart-message']//h4[@class='a-alert-heading' and contains(text(), 'Added to Cart')]";
		String expectedMsg = "Added to Cart";
		action.addToCart();
		String confirmMsg = driver.findElement(By.xpath(xpathExpression)).getText();
		assertTrue(confirmMsg.equals(expectedMsg));
		action.closeAddToCartWindow();
	}
		
	@Test(description = "Test case to scroll down till Technical Details section on the product details page",
			dependsOnMethods = "addToCartValidation")
	public void scrollTillTechnicalDetails() {
		action.scrollToWebElement("Technical Details");
	}



}
