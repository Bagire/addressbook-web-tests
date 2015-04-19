package com.example.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class PrintPhonesTests extends TestBase{

	private static final String INTERFACE = "interface";

	@Test
	public void testPrintPhones () {
		SortedListOf<ContactData> ContactsList = app.getContactHelper().getContactsList(INTERFACE);
		SortedListOf<ContactData> PrintList = app.getPrintPhonesHelper().getPrintList();
		assertThat(ContactsList, equalTo(PrintList));

	}
}
