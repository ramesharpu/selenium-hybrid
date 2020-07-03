package test;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.Base;
import utils.Keyword;

public class LoginTest extends Base{
	Keyword action;
	@BeforeClass
	public void beforeClass() {
		action = new Keyword();
	}
	
	String expectedEmailErrorHeader = "There was a problem";
	String expectedEmailErrorMessage = "We cannot find an account with that email address";

	String expectedPhoneErrorHeader = "Incorrect phone number";
	String expectedPhoneErrorMessage = "We cannot find an account with that mobile number";


	@Parameters({"invalidEmailId"})
	@Test(description = "Test to validate login functionality with invalid email id")
	public void invalidEmailValidation(String emailId) {
		Map<String, String> result = action.invalidLogin(emailId);
		assert(expectedEmailErrorHeader.equals(result.get("errorHeader")));
		assert(expectedEmailErrorMessage.equals(result.get("errorMessage")));
	}
	
	@Parameters({"invalidPhoneNumber"})
	@Test(description = "Test to validate login functionality with invalid mobile number")
	public void invalidPhoneValidation(String invalidPhoneNumber) {
		Map<String, String> result = action.invalidLogin(invalidPhoneNumber);
		assert(expectedPhoneErrorHeader.equals(result.get("errorHeader")));
		assert(expectedPhoneErrorMessage.equals(result.get("errorMessage")));
	}

	@Parameters({"validUsername", "validPassword"})
	@Test(description = "Test to validate login functionality with valid username/password",
	enabled = false)
	public void validEmailLoginValidation(String username, String password) {
		String welcomeString = "Hello, Sign in";
		String actualWelcomeString = action.validLogin(username, password);
		assertTrue(!actualWelcomeString.equals(welcomeString));
		action.logout();
	}
	@Test
	void f() {
		System.out.println("test");
	}
}
