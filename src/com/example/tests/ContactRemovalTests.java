package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase{
	
	//Number of removing string
	private String numString = "last()";

	//Number of removing string (int)
	private int index = 0;

	@Test
	public void testDeleteContactByNumString () {
		app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoHomePage();
		app.getContactHelper().deleteContactByNumString(numString);
		app.getContactHelper().returnToHomePage();
	}

	@Test
	public void testDeleteContactByIndex () {
		app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoHomePage();

		List<ContactData> oldList = app.getContactHelper().getContactsList();
		
		app.getContactHelper().deleteContactByIndex(index);
		app.getContactHelper().returnToHomePage();

		List<ContactData> newList = app.getContactHelper().getContactsList();
	    
		oldList.remove(index);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

		}
}
