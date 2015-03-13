package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import static com.example.fw.ContactHelper.CREATION;

public class ContactCreationTests extends TestBase {

  @Test(dataProvider = "randomValidContactGenerator")
  public void testContactCreationWithValidData(ContactData contact) throws Exception {
    app.navigateTo().mainPage();
    app.navigateTo().homePage();

	List<ContactData> oldList = app.getContactHelper().getContactsList();
	
    app.getContactHelper()
    	.initContactCreation()
    	.fillContactForm(contact, CREATION)
    	.submitContactCreation()
    	.returnToHomePage();

	List<ContactData> newList = app.getContactHelper().getContactsList();
    
	oldList.add(contact);
	Collections.sort(oldList);
	assertEquals(newList, oldList);

  }

}
