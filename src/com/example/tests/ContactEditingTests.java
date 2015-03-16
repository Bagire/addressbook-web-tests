package com.example.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import com.example.utils.SortedListOf;
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

	SortedListOf<ContactData> oldList = app.getContactHelper().getContactsList();
	
	Random rnd = new Random();
	index=rnd.nextInt(oldList.size()-1);

	app.getContactHelper().editContactByIndex(contact, index);

	SortedListOf<ContactData> newList = app.getContactHelper().getContactsList();
    
	assertThat(newList, equalTo(oldList.without(index).withAdded(contact)));

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

