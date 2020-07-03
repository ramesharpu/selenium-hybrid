package test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.Base;
import utils.Keyword;

public class NavigationTest extends Base{
	Keyword action;
	
	@BeforeClass
	public void beforeClass() {
		action = new Keyword();

	}

	@Test(description = "'Create A Wish List' navigation test")
	public void createWishListNavigationTest() {
		action.createWishList();
		String text = driver.findElement(By.xpath("//div[@role='heading']")).getText();
		assertTrue(text.equals("Your Lists"));
	}

	@Test(description = "'Amazon Pay' navigation test")
	public void amazonPayNavigationTest() {
		action.amazonPay();
		boolean elem = driver.findElement(By.xpath("//span[contains(text(),'Amazon Pay balance')]")).isDisplayed();
		assertTrue(elem);
	}

	@Test(description = "'New Releases' navigation test")
	public void newReleasesNavigationTest() {
		action.newReleases();	  
		boolean elem = driver.findElement(By.xpath("//div[contains(text(),'Amazon Hot New Releases')]")).isDisplayed();
		assertTrue(elem);
	}


}
