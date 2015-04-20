package com.example.fw;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class ApplicationManager {
	
	private WebDriver driver;

	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private ContactHelper contactHelper;
	private PrintPhonesHelper printPhonesHelper;
	private HibernateHelper hibernateHelper;
	public Properties properties;
	private ApplicationModel model;
	
	public ApplicationManager(Properties properties) {
		this.properties = properties;
		model = new ApplicationModel();
//		model.setGroups(getHibernateHelper().listGroups());
//		model.setContacts(getHibernateHelper().listContacts());
	}

	public void stop() {
	    driver.quit();
	}
	
	public ApplicationModel getModel(){
		return model;
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
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

	public WebDriver getDriver() {
		if (driver == null) {
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
		return driver;
	}

	public HibernateHelper getHibernateHelper() {
		if (hibernateHelper == null) {
			hibernateHelper = new HibernateHelper(this);
		}
		return hibernateHelper;
	}

}
