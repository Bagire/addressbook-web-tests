package com.example.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class PrintPhonesTests extends TestBase{

	@Test
	public void testPrintPhones () {
		app.navigateTo().mainPage();
		SortedListOf<ContactData> ContactsList = app.getContactHelper().getContactsList();
		app.navigateTo().printPhones();
		SortedListOf<ContactData> PrintList = app.getPrintPhonesHelper().getPrintList();
		assertThat(ContactsList, equalTo(PrintList));

	}
}
