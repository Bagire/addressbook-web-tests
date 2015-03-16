package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

import static com.example.fw.ContactHelper.EDITING;

public class ContactEditingTests extends TestBase{

	//Number of editing string
	private String numString = "2";
	
	//Number of editing string (int)
	private int index = 0;

	//Part of name of editing string
	private String partName = "Сидор";

	@Test
	public void testEditContactByNumString () {
	app.navigateTo().mainPage();
	app.getContactHelper()
		.initContactByNumString(numString);
    ContactData contact = new ContactData();
    contact.firstname = "Акакий";
    app.getContactHelper()
    	.fillContactForm(contact, EDITING)
    	.submitContactEditing()
    	.returnToHomePage();
	}

	@Test(dataProvider = "randomValidContactGenerator")
	public void testEditContactByIndex (ContactData contact) {
	app.navigateTo().mainPage();

	List<ContactData> oldList = app.getContactHelper().getContactsList();
	
	Random rnd = new Random();
	index=rnd.nextInt(oldList.size()-1);

	app.getContactHelper().editContactByIndex(contact, index);

	List<ContactData> newList = app.getContactHelper().getContactsList();
    
	oldList.remove(index);
	oldList.add(contact);
	Collections.sort(oldList);
	assertEquals(newList, oldList);

	}

	@Test
	public void testEditContactByPartName () {
	app.navigateTo().mainPage();
	app.getContactHelper()
		.initContactByPartName(partName);
	ContactData contact = new ContactData();
	contact.firstname = "Серж";
	app.getContactHelper()
		.fillContactForm(contact, EDITING)	
		.submitContactEditing()
		.returnToHomePage();
	}

}

