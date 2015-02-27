package com.example.tests;

import org.testng.annotations.Test;

public class GroupEditingTests extends TestBase{

	//Number of editing string
	private String numString = "last()-1";

	@Test
	public void testEditGroupByNumString () {
	app.getNavigationHelper().openMainPage();
	app.getNavigationHelper().gotoGroupsPage();
	app.getGroupHelper().initGroupByNumString(numString);
    GroupData group = new GroupData();
    group.nameGroup = "new name1";
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupEditing();
    app.getGroupHelper().returnToGroupPage();
	}

}

