package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupRemovalTests extends TestBase{
	
	private static final String INTERFACE = "interface";
	private static final String DATABASE = "database";
	//Number of removing string (int)
	private int index = 0;

	@Test
	public void testDeleteGroupByIndex () {

		SortedListOf<GroupData> oldList = app.getGroupHelper().getGroupsList(DATABASE);
		
		Random rnd = new Random();
		index=rnd.nextInt(oldList.size()-1);

		app.getGroupHelper().deleteGroupByIndex(index);
		
		SortedListOf<GroupData> newList = app.getGroupHelper().getGroupsList(INTERFACE);
	    
		assertThat(newList, equalTo(oldList.without(index)));

	}

}
