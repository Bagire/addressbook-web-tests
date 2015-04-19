package com.example.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import com.example.utils.SortedListOf;

import java.util.Random;

import org.testng.annotations.Test;

public class ContactEditingTests extends TestBase{

	private static final String DATABASE = "database";
	private static final String INTERFACE = "interface";

	//Number of editing string (int)
	private int index = 0;

	@Test(dataProvider = "randomValidContactGenerator")
	public void testEditContactByIndex (ContactData contact) {
	app.navigateTo().mainPage();

	SortedListOf<ContactData> oldList = app.getContactHelper().getContactsList(DATABASE);
	
	Random rnd = new Random();
	index=rnd.nextInt(oldList.size()-1);

	app.getContactHelper().editContactByIndex(contact, index, oldList);

	SortedListOf<ContactData> newList = app.getContactHelper().getContactsList(INTERFACE);
    
	assertThat(newList, equalTo(oldList.without(index).withAdded(contact)));

	}

}

