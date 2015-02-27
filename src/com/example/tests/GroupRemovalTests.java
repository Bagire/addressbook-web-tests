package com.example.tests;

import org.testng.annotations.Test;

public class GroupRemovalTests extends TestBase{
	
	//Number of removing string
	private String numString = "last()-1";

	@Test
	public void testDeleteGroupByNumString () {
		app.getNavigationHelper().openMainPage();
		app.getNavigationHelper().gotoGroupsPage();
		app.getGroupHelper().deleteGroupByNumString(numString);
	    app.getGroupHelper().returnToGroupPage();
	}

}
