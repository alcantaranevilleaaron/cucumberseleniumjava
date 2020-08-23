package pageobjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.Driver;

public class LoginPage extends BaseClass {
	
	private String passwordWarning = "Please log in with your password and complete additional authentication";

	public LoginPage(Driver driver) {
		super(driver);
		PageFactory.initElements(driver.getDriver(), this);
	}

	@FindBy(id = "userIdentifier")
	public static WebElement userName;
	
	@FindBy(id = "password")
	public static WebElement userPassword;

	@FindBy(css = "button[type='submit']")
	public static WebElement login;
	
	@FindBy(id = "react-language-list-container")
	public static WebElement languageContainer;
	
	@FindBy(css = "div[class='modal-content']")
	public static WebElement modalContent;
	
	@FindBy(className = "livechatlink")
	public static WebElement liveChat;
	
	@FindBy(css = "img[src='/assets/image/logo/logo.svg']")
	public static WebElement logo;
	
	@FindBy(css = "div[class^='alert alert']")
	public static WebElement alertMessage;
		
	public void goTo(){
		try {
			String url = driver.getConfigValue("siteValue");
			driver.navigateToUrl(url + "en/login/");
		} catch (IOException e) {
			System.out.println("IO Exception >> LoginPage.goTo: " + e.getMessage());
		}
	}
	
	public void login(String username) {
		userName.sendKeys(username);
		driver.clickElement(login, true);
		driver.waitUntilSpinnerIsGone(10);
	}

	public void login(String username, String password) {
		login(username);
		if (getAlertMessage().toLowerCase().contains(passwordWarning.toLowerCase())){
			userPassword.sendKeys(password);
			driver.clickElement(login, true);
			driver.waitUntilSpinnerIsGone(10);
		}
	}

	public void clickLanguage(String language) {
		WebElement moreLanguage = languageContainer.findElement(By.cssSelector("span[role='button']"));
		driver.clickElement(moreLanguage, true);
		WebElement languageLink = modalContent.findElement(By.cssSelector("a[href='/" + language + "/login/'"));
		driver.clickElement(languageLink, true);
	}
	
	public void clickPageLink(String link) {
		WebElement pageLink = driver.findElement(By.linkText(link));
		driver.clickElement(pageLink, true);
	}
	
	public void clickMobileApp(String app){
		WebElement appLink = driver.findElement(By.cssSelector("img[alt='" + app + "']"));
		driver.clickElement(appLink, true);
	}
	
	public HomePage clickLogo(){
		driver.clickElement(logo, true);
		return new HomePage(driver);
	}
	
	public ChatPage clickChatSupport(){
		driver.clickElement(liveChat, true);
		driver.switchToNewWindow();
		return new ChatPage(driver);
	}
	
	public RegistrationPage clickRegistration(){
		clickPageLink("Register Now!");
		return new RegistrationPage(driver);
	}
	
	public String getPageHeader(){
		driver.switchToNewWindow();
		WebElement pageHeader = driver.findElement(By.tagName("h1"));
		String pageHeaderText = driver.getElementText(pageHeader, true).replace("\"", "");
		return pageHeaderText;
	}
	
	public String getAlertMessage(){
		String alertReturn = "";
		if (driver.checkElementIfExists(By.cssSelector("div[class^='alert alert']"))){
			alertReturn = driver.getElementText(alertMessage);
		}
		return alertReturn;
		
	}
}
