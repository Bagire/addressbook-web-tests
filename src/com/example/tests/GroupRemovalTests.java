package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class GroupRemovalTests extends TestBase{
	
	//Number of removing string
	private String numString = "last()-1";
	
	//Number of removing string (int)
	private int index = 0;

	@Test
	public void testDeleteGroupByNumString () {
		app.getNavigationHelper().openMainPage();
		app.getNavigationHelper().gotoGroupsPage();
		app.getGroupHelper().deleteGroupByNumString(numString);
		app.getGroupHelper().returnToGroupPage();
	}

	@Test
	public void testDeleteGroupByIndex () {
		app.getNavigationHelper().openMainPage();
		app.getNavigationHelper().gotoGroupsPage();

		List<GroupData> oldList = app.getGroupHelper().getGroupsList();
		
		Random rnd = new Random();
		index=rnd.nextInt(oldList.size()-1);

		app.getGroupHelper().deleteGroupByIndex(index);
		app.getGroupHelper().returnToGroupPage();
		
		List<GroupData> newList = app.getGroupHelper().getGroupsList();
	    
		oldList.remove(index);
		Collections.sort(oldList);
		assertEquals(newList, oldList);

	}

}
