package com.example.tests;

public class GroupEditingTests extends TestBase{

	@org.testng.annotations.Test
	public void deleteSomeGroup () {
	app.getNavigationHelper().openMainPage();
	app.getNavigationHelper().gotoGroupsPage();
	app.getGroupHelper().initGroup(1);
    GroupData group = new GroupData();
    group.nameGroup = "new name";
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupEditing();
    app.getGroupHelper().returnToGroupPage();
	}

}

