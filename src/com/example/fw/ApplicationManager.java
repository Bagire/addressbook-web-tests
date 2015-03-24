package com.example.fw;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class ApplicationManager {
	
	public WebDriver driver;

	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private ContactHelper contactHelper;
	private PrintPhonesHelper printPhonesHelper;
	public Properties properties;
	
	public ApplicationManager(Properties properties) {
		this.properties = properties;
		String browser = properties.getProperty("browser");
		if ("firefox".equals(browser)) {
			driver = new FirefoxDriver();
		} else if ("ie".equals(browser)) {
			driver = new InternetExplorerDriver();
		} else {
			throw new Error("Unsupported browser: " + browser);
		}
	    long timeOut = Long.parseLong(properties.getProperty("timeOut"));
	    driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		driver.get(properties.getProperty("baseUrl") + properties.getProperty("addrUrl"));
	}

	public void stop() {
	    driver.quit();
	}
	
	public NavigationHelper navigateTo () {
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}

	public GroupHelper getGroupHelper () {
		if (groupHelper == null) {
			groupHelper = new GroupHelper(this);
		}
		return groupHelper;
	}

	public ContactHelper getContactHelper () {
		if (contactHelper == null) {
			contactHelper = new ContactHelper(this);
		}
		return contactHelper;
	}

	public PrintPhonesHelper getPrintPhonesHelper () {
		if (printPhonesHelper == null) {
			printPhonesHelper = new PrintPhonesHelper(this);
		}
		return printPhonesHelper;
	}

}
