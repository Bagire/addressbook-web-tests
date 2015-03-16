package com.example.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import com.example.utils.SortedListOf;
import java.util.Random;
import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase{
	
	//Number of removing string (int)
	private int index = 0;

	@Test
	public void testDeleteContactByIndex () {
		app.navigateTo().mainPage();

		SortedListOf<ContactData> oldList = app.getContactHelper().getContactsList();
		
		Random rnd = new Random();
		index=rnd.nextInt(oldList.size()-1);

		app.getContactHelper().deleteContactByIndex(index);

		SortedListOf<ContactData> newList = app.getContactHelper().getContactsList();
	    
		assertThat(newList, equalTo(oldList.without(index)));

		}
}
