package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.Driver;

public class AccountPage extends BaseClass {

	public AccountPage(Driver driver) {
		super(driver);
		PageFactory.initElements(driver.getDriver(), this);
	}
	
	@FindBy(css = "li[id='navbar-logout-button']")
	public static WebElement logout;
	
}
