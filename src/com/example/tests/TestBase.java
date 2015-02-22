package com.example.tests;

import com.example.fw.ApplicationManager;

public class TestBase {

	protected ApplicationManager app;
	
	//For JUnit
	//@org.junit.BeforeClass
	//public void setUp() throws Exception {
	// For TestNG
	@org.testng.annotations.BeforeTest
	public void setUp() throws Exception {
		app = new ApplicationManager();
	  }

	//For JUnit
	//@org.junit.AfterClass
	//public void tearDown() throws Exception {
	// For TestNG
	@org.testng.annotations.AfterTest
	public void tearDown() throws Exception {
	    app.stop();
	  }

}
