package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.Driver;

public class ChatPage extends BaseClass {

	public ChatPage(Driver driver) {
		super(driver);
		PageFactory.initElements(driver.getDriver(), this);
	}
	
	@FindBy(className = "topbannerchat")
	public static WebElement bannerChat;
	
	public boolean checkBannerIfAvail(){
		return driver.checkElementIfExists(By.className("topbannerchat"));
	}
}
