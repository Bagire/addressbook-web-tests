package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test(dataProvider = "randomValidContactGenerator")
  public void testContactCreationWithValidData(ContactData contact) throws Exception {
    app.getNavigationHelper().openMainPage();
    app.getNavigationHelper().gotoHomePage();

	List<ContactData> oldList = app.getContactHelper().getContactsList();
	
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();

	List<ContactData> newList = app.getContactHelper().getContactsList();
    
	oldList.add(contact);
	Collections.sort(oldList);
	assertEquals(newList, oldList);

  }

}
