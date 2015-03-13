package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase{
	
	//Number of removing string
	private String numString = "last()";

	//Number of removing string (int)
	private int index = 0;

	@Test
	public void testDeleteContactByNumString () {
		app.navigateTo().mainPage();
	    app.navigateTo().homePage();
		app.getContactHelper()
			.deleteContactByNumString(numString)
			.returnToHomePage();
	}

	@Test
	public void testDeleteContactByIndex () {
		app.navigateTo().mainPage();
	    app.navigateTo().homePage();

		List<ContactData> oldList = app.getContactHelper().getContactsList();
		
		Random rnd = new Random();
		index=rnd.nextInt(oldList.size()-1);

		app.getContactHelper()
			.deleteContactByIndex(index)
			.returnToHomePage();

		List<ContactData> newList = app.getContactHelper().getContactsList();
	    
		oldList.remove(index);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

		}
}
