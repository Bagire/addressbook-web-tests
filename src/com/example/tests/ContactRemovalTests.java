package com.example.tests;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase{
	
	//Number of removing string
	private String numString = "last()";

	@Test
	public void testDeleteContactByNumString () {
		app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoHomePage();
		app.getContactHelper().deleteContactByNumString(numString);
	    app.getContactHelper().returnToHomePage();
	}

}
