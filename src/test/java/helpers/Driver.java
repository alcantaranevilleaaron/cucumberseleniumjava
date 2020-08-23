package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

	private static WebDriver driver;
	private InputStream inputStream;
	private int implicitTimeout = 3000;
	private String dir = System.getProperty("user.dir");
	private String resources = dir + "/src/test/resources/";
	private String driversPath = resources + "drivers/";
	private String testDataPath = resources + "testdata/";
	
	private By spinner = By.cssSelector("div[class^='spinner']");

	public WebDriver getDriver() {
		return driver;
	}

	public void initialize() {
		System.setProperty("webdriver.chrome.driver", driversPath + "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	public void cleanup() {
		if (driver != null)
			driver.quit();
	}

	public void navigateToUrl(String url) {
		driver.navigate().to(url);
		waitForLoad(driver);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public boolean checkElementIfExists(By by){
		return !driver.findElements(by).isEmpty();
	}
	
	public boolean waitUntilElementIsDisplayed(WebElement element) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, 5);
	        wait.until(ExpectedConditions.visibilityOf(element));
	        return element.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public void waitForElementToBeGone(WebElement element, int timeout) {
	    if (waitUntilElementIsDisplayed(element)) {
	    	WebDriverWait wait = new WebDriverWait(driver, timeout);
	    	wait.until(ExpectedConditions.invisibilityOf(element));
	    	
	    }
	}
	
	public void waitUntilSpinnerIsGone(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		boolean isSpinnerPresent = false;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(spinner));
			isSpinnerPresent = true;
		} catch (TimeoutException timeoutException) {
			System.out.println("TimeoutException >> Driver.waitUntilSpinnerIsGone: "
					+ "Valid error after waiting for spinner for " + timeout + " seconds: "
					+ timeoutException.getMessage());
			isSpinnerPresent = false;
		}
		
		if (isSpinnerPresent){
			try {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
			} catch (TimeoutException timeoutException) {
				System.out.println("TimeoutException >> Driver.waitUntilSpinnerIsGone: "
						+ "Error after waiting for spinner to be gone for " + timeout + " seconds: "
						+ timeoutException.getMessage());
			}
		}
	}	

	public WebElement findElement(By by) {
		return driver.findElement(by);
	}

	public WebElement findElement(By by, boolean wait) {
		if (wait) {
			return driver.findElement(by);
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element = driver.findElement(by);
		driver.manage().timeouts().implicitlyWait(implicitTimeout, TimeUnit.SECONDS);
		return element;
	}

	public void clickElement(WebElement element) {
		element.click();
	}

	public void clickElement(WebElement element, boolean wait) {
		element.click();
		if (wait) {
			waitForLoad(driver);
		}
	}
	
	public String getElementText(WebElement element) {
		return element.getText();
	}

	public String getElementText(WebElement element, boolean removeChildText) {
		String text = element.getText();
		String returnText = "";
		if (removeChildText && text.trim().length() > 0){
			for (WebElement child : element.findElements(By.xpath("./*"))) {
				returnText = text.replaceFirst(child.getText(), "").replaceAll("[^\\dA-Za-z ]", "");
			}
		}
		if (returnText.trim().length() < 1) {
			return text.trim();
		}
		return returnText.trim();
	}

	public void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(pageLoadCondition);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException Exception >> Driver.waitForLoad: " + e.getMessage());
		}

	}
	
	public void switchToNewWindow() {
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		if (tabs.size() > 1) {
			driver.switchTo().window(tabs.get(1));
		}
	}

	public String getConfigValue(String value) throws IOException {
		String returnValue = "";
		try {
			Properties prop = new Properties();

			File file = new File(testDataPath + "config.properties");
			inputStream = new FileInputStream(file);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				System.out.println("Property file not found >> Driver.getConfig");
				return returnValue;
			}

			returnValue = prop.getProperty(value);

		} catch (Exception e) {
			System.out.println("Generic Exception >> Driver.getConfig: " + e.getMessage());
		} finally {
			inputStream.close();
		}
		return returnValue;
	}
}
