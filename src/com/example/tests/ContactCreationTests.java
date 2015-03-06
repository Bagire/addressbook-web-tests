package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testNonEmptyContactCreation() throws Exception {
    app.getNavigationHelper().openMainPage();
    app.getNavigationHelper().gotoHomePage();

	List<ContactData> oldList = app.getContactHelper().getContactsList();
	
    app.getContactHelper().initContactCreation();
    ContactData contact = new ContactData();
    contact.firstname = "Сидор";
    contact.lastname = "Сидоров";
    contact.address = "Адрес Сидорова 1";
    contact.home = "111111";
    contact.mobile = "333333";
    contact.work = "444444";
    contact.email = "s@s.ru";
    contact.email2 = "s@s.com";
    contact.birthDay = "1";
    contact.birthMonth = "November";
    contact.birthYear = "1984";
    contact.newGroup = "Rob";
    contact.address2 = "Адрес Сидорова 2";
    contact.phone2 = "222222";
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();

	List<ContactData> newList = app.getContactHelper().getContactsList();
    
	oldList.add(contact);
	Collections.sort(oldList);
	assertEquals(newList, oldList);

  }

}
