package stepdefinitions;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.Driver;
import pageobjects.ChatPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.RegistrationPage;

public class LoginStepDef {

	private Driver driver;
	private LoginPage loginPage;
	private ChatPage chatPage;
	private RegistrationPage registrationPage;
	private HomePage homePage;

	public LoginStepDef() {
		driver = Hooks.getDriver();
		loginPage = new LoginPage(driver);
	}

	@Given("^a user navigate to login page$")
	public void goToLogin() {
		loginPage.goTo();
	}
	
	@When("^I click the \"([^\"]*)\" language link$") 
	public void languageClick(String lang) {
		loginPage.clickLanguage(lang);
	}
	
	@When("^I click the \"([^\"]*)\" page link$") 
	public void pageClick(String link) {
		loginPage.clickPageLink(link);
	}
	
	@When("^I click the \"([^\"]*)\" application link$") 
	public void appClick(String app) {
		loginPage.clickMobileApp(app);
	}
	
	@When("^I click the chat support$") 
	public void chatSupportClick() {
		chatPage = loginPage.clickChatSupport();
	}
	
	@When("^I want to register$") 
	public void registerClick() {
		registrationPage = loginPage.clickRegistration();
	}
	
	@When("^I click the logo$") 
	public void logoClick() {
		homePage = loginPage.clickLogo();
	}
	
	@When("^I input the \"([^\"]*)\" username$") 
	public void inputUsername(String username) {
		loginPage.login(username);
	}
	
	@When("^I input a valid credential$") 
	public void inputUsernameAndPassword() {
		try {
			String username = driver.getConfigValue("username");
			String password = driver.getConfigValue("password");
			loginPage.login(username, password);
		} catch (IOException e) {
			System.out.println("IO Exception >> LoginStepDef.inputUsernameAndPassword: " + e.getMessage());
		}		
	}
	
	@Then("^page should be in \"([^\"]*)\" language$") 
	public void assertUrl(String lang) throws IOException {
		assertEquals(driver.getConfigValue("siteValue") + lang + "/login/", driver.getCurrentUrl());
	}
	
	@Then("^page should be in \"([^\"]*)\" page$") 
	public void assertHeader(String header) {
		assertEquals(header, loginPage.getPageHeader());
	}
	
	@Then("^I should be able to open a chat support window$") 
	public void assertChatWindow() {
		assertEquals(chatPage.checkBannerIfAvail(), true);
	}
	
	@Then("^I will be redirected to registration page$") 
	public void assertRegistrationWindow() {
		assertEquals(registrationPage.checkHeaderIfAvail(), true);
	}
	
	@Then("^I will be redirected to home page$") 
	public void assertHomeWindow() {
		assertEquals(homePage.checkLogoIfAvail(), true);
	}
	
	@Then("^I will receieved an error \"([^\"]*)\" message$") 
	public void assertErrorMessage(String errorMessage) {
		assertEquals(errorMessage.trim(), loginPage.getAlertMessage().trim());
	}
}
