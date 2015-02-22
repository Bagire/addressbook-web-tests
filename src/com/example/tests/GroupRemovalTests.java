package com.example.tests;

public class GroupRemovalTests extends TestBase{
	
	@org.testng.annotations.Test
	public void deleteSomeGroup () {
		app.getNavigationHelper().openMainPage();
		app.getNavigationHelper().gotoGroupsPage();
		app.getGroupHelper().deleteGroup(1);
	    app.getGroupHelper().returnToGroupPage();
	}

}
