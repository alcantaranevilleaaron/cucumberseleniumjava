package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helpers.Driver;

public class HomePage extends BaseClass {

	public HomePage(Driver driver) {
		super(driver);
		PageFactory.initElements(driver.getDriver(), this);
	}
	
	@FindBy(css = "img[class='front-page lazyloaded']")
	public static WebElement logo;
	
	public boolean checkLogoIfAvail(){
		return driver.checkElementIfExists(By.cssSelector("img[class='front-page lazyloaded']"));
	}
}
