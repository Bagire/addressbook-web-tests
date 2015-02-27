package com.example.tests;

import org.testng.annotations.Test;

public class ContactEditingTests extends TestBase{

	//Number of editing string
	private String numString = "1";
	
	//Part of name of editing string
	private String partName = "Сидор";

	@Test
	public void testEditContactByNumString () {
	app.getNavigationHelper().openMainPage();
    app.getNavigationHelper().gotoHomePage();
	app.getContactHelper().initContactByNumString(numString);
    ContactData contact = new ContactData();
    contact.firstname = "Акакий";
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactEditing();
    app.getContactHelper().returnToHomePage();
	}


	@Test
	public void testEditContactByPartName () {
	app.getNavigationHelper().openMainPage();
    app.getNavigationHelper().gotoHomePage();
	app.getContactHelper().initContactByPartName(partName);
    ContactData contact = new ContactData();
    contact.firstname = "Серж";
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactEditing();
    app.getContactHelper().returnToHomePage();
	}

}

