package com.example.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import com.example.utils.SortedListOf;

import java.util.Random;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase{
	
	private static final String DATABASE = "database";
	private static final String INTERFACE = "interface";

	//Number of removing string (int)
	private int index = 0;

	@Test
	public void testDeleteContactByIndex () {
		app.navigateTo().mainPage();

		SortedListOf<ContactData> oldList = app.getContactHelper().getContactsList(DATABASE);
		
		Random rnd = new Random();
		index=rnd.nextInt(oldList.size()-1);

		app.getContactHelper().deleteContactByIndex(index);

		SortedListOf<ContactData> newList = app.getContactHelper().getContactsList(INTERFACE);
	    
		assertThat(newList, equalTo(oldList.without(index)));

		}

	@Test
	public void testDeleteContactByIndexWithModel () {
		app.navigateTo().mainPage();

		app.getModel().setContacts(app.getHibernateHelper().listContacts());
		
		SortedListOf<ContactData> oldList = app.getModel().getContacts();
		
		Random rnd = new Random();
		index=rnd.nextInt(oldList.size()-1);

		app.getContactHelper().deleteContactByIndexWithModel(index);

		SortedListOf<ContactData> newList = app.getModel().getContacts();
	    
		assertThat(newList, equalTo(oldList.without(index)));

		if (needCheck()) {
			if ("yes".equals(app.getProperty("check.db"))) {
				assertThat(app.getModel().getContacts(), equalTo(app.getHibernateHelper().listContacts()));
			}
			if ("yes".equals(app.getProperty("check.ui"))) {
				assertThat(app.getModel().getContacts(), equalTo(app.getContactHelper().getUiContactsList()));
			}
		}
	  
	}

}
