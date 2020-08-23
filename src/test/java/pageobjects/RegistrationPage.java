package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.Driver;

public class RegistrationPage extends BaseClass {

	public RegistrationPage(Driver driver) {
		super(driver);
		PageFactory.initElements(driver.getDriver(), this);
	}

	@FindBy(id = "registration-h1")
	public static WebElement registrationHeader;
	
	public boolean checkHeaderIfAvail(){
		return driver.checkElementIfExists(By.id("registration-h1"));
	}
}
