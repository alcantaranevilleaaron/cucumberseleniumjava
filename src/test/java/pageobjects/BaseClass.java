package pageobjects;

import helpers.Driver;

public abstract class BaseClass {
	public Driver driver;
	public boolean bResult;

	public BaseClass(Driver driver){
		this.driver = driver;
		this.bResult = true;
	}

}
