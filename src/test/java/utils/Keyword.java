package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import pom.Login;
import pom.Navigation;
import pom.ProductSearch;

public class Keyword extends Base{

	Navigation nav;
	ProductSearch ps;
	WebDriverWait wait;

	public Keyword() {
		nav = new Navigation(driver);
		ps = new ProductSearch(driver);
		wait = new WebDriverWait(driver, 20);
	}

	public WebDriver getDriverObject() {
		return driver;
	}

	public String getCurrentPageUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void navigateToUrl(String url) {
		driver.navigate().to(url);
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void browserBackButton() {
		driver.navigate().back();
	}

	public void browserForwardButton() {
		driver.navigate().forward();
	}

	public List<String> getAllWindowDetails() {
		Set<String> listOfWindows = driver.getWindowHandles();
		List<String> tabs = new ArrayList<String>(listOfWindows);
		return tabs;
	}

	/**
	 * 
	 * @param number
	 * Window number starts from 0. 
	 */
	public void closeWindow(String windowNumber) {
		driver.switchTo().window(windowNumber);
		driver.close();
	}

	public void closeCurrentWindow(){
		driver.close();
	}

	public void switchToNewWindow(String windowNumber) {
		driver.switchTo().window(windowNumber);
	}

	public void switchToMainPage() {
		driver.switchTo().defaultContent();
	}


	public Map<String, String> invalidLogin(String emailId) {
		Login login = new Login(driver);
		return login.invalidLoginMessage(emailId);
	}

	public String validLogin(String username, String password) {
		Login login = new Login(driver);
		login.successfulLogin(username, password);
		String welcomeMessage = By.xpath("//span[@class='nav-line-3']").toString();
		return welcomeMessage;
	}

	public void logout() {
		Login login = new Login(driver);
		login.logout();
	}

	public void createWishList(){
		nav.createWishList();
	}

	public void amazonPay(){
		nav.amazonPay();
	}

	public void newReleases(){
		nav.newReleases();
	}

	public void searchProduct(String searchText){
		ps.searchProduct(searchText);
	}

	public String getSearchResultsSummary(){
		return ps.getSearchResultsSummary();
	}

	public void avgCustomerReviewFilter(int stars){
		ps.avgCustomerReviewFilter(4);
	}

	public String getTheFirstProductText(String searchText){
		return ps.getTheFirstProductText(searchText);
	}

	public void selectTheFirstProduct(String searchText){
		ps.selectTheFirstProduct(searchText);
	}

	public String getProductPrice() {
		return ps.getPrice().substring(2);
	}

	public void enterPincode(String pincode) {
		ps.deliveryLocation(pincode);
	}

	public void switchToSponsoredProductIframe() {
		ps.switchToSponsoredProductIframe();
		String xpath = "//a[@class='a-link-normal sp_hqp_shared_adLink a-text-normal']";
		WebElement elem = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
		elem.click();
	}

	public void addToCart() {
		ps.addToCart();
	}

	public void closeAddToCartWindow() {
		ps.closeAddToCartWindow();
	}

	public void scrollToWebElement(String elementText) {
		WebElement elem = driver.findElement(By.xpath("//span[text()='" + elementText + "']"));
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].scrollIntoView();", elem);
	}
























}
