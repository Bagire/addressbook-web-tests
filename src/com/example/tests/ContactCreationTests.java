package com.example.tests;

import static com.example.tests.ContactDataGenerator.loadContactsFromCsvFile;
import static com.example.tests.ContactDataGenerator.loadContactsFromXmlFile;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.example.utils.SortedListOf;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

	private static final String DATABASE = "database";
	private static final String INTERFACE = "interface";

	@DataProvider
	public Iterator<Object[]> groupsFromFile()  throws IOException {
	  String dataFile = app.properties.getProperty("contactsDataFile");
	  if (dataFile.endsWith(".xml") == true) {
		  return wrapContactsForProvider (loadContactsFromXmlFile(new File(dataFile))).iterator();
	  } else {
		  return wrapContactsForProvider (loadContactsFromCsvFile(new File(dataFile))).iterator();
	  }
	}

//  @Test(dataProvider = "randomValidContactGenerator")
  @Test(dataProvider = "groupsFromFile")
  public void testContactCreationWithValidData(ContactData contact) throws Exception {

//	SortedListOf<ContactData> oldList = new SortedListOf<ContactData>(app.getHibernateHelper().listContacts());
	SortedListOf<ContactData> oldList = app.getContactHelper().getContactsList(DATABASE);
	
    app.getContactHelper().createContact(contact);
    
    SortedListOf<ContactData> newList = app.getContactHelper().getContactsList(INTERFACE);
    
	assertThat(newList, equalTo(oldList.withAdded(contact)));
  }

  @Test(dataProvider = "groupsFromFile")
  public void testContactCreationWithValidDataWithModel(ContactData contact) throws Exception {

	app.getModel().setContacts(app.getHibernateHelper().listContacts());
	
	SortedListOf<ContactData> oldList = app.getModel().getContacts();
	
    app.getContactHelper().createContactWithModel(contact);
    
	SortedListOf<ContactData> newList = app.getModel().getContacts();
    
	assertThat(newList, equalTo(oldList.withAdded(contact)));

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
