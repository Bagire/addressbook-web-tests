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

	SortedListOf<ContactData> oldList = app.getContactHelper().getContactsList();
	
    app.getContactHelper().createContact(contact);
    
    SortedListOf<ContactData> newList = app.getContactHelper().getContactsList();
    
	assertThat(newList, equalTo(oldList.withAdded(contact)));
  }

}
