package com.example.tests;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase{
	
	//Number of removing string
	private String numString = "last()";

	@Test
	public void testDeleteContactByNumStringIfExists () {
		app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoHomePage();
		if (app.getContactHelper().deleteContactByNumString(numString)){
			app.getContactHelper().returnToHomePage();
		}
	}

}
