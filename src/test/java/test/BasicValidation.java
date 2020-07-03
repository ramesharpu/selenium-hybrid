package test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.Base;
import utils.Keyword;

public class BasicValidation extends Base{
	Keyword keyword;
	@BeforeClass
	public void beforeClass() {
		keyword = new Keyword();
	}
	
	
	@Test(description = "Testcase to validate the url redirection")
	public void urlRedirectionValidation() {
		String expectedUrl = "https://www.amazon.in/";
		String actualurl = keyword.getCurrentPageUrl();
		assertTrue(expectedUrl.equals(actualurl), "URL redirection error");
	}
	
	@Test(description = "Testcase to validate title")
	public void titleValidation() {
		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actualTitle = keyword.getPageTitle();
		assertTrue(expectedTitle.equals(actualTitle), "Page title does not match.");
	}
}
