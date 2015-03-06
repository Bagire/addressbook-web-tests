package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class GroupEditingTests extends TestBase{

	//Number of editing string
	private String numString = "last()-1";

	//Number of editing string (int)
	private int index = 0;

	@Test
	public void testEditGroupByNumStringIfExists () {
	app.getNavigationHelper().openMainPage();
	app.getNavigationHelper().gotoGroupsPage();
	app.getGroupHelper().initGroupByNumString(numString);
	GroupData group = new GroupData();
    group.nameGroup = "new name1";
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupEditing();
    app.getGroupHelper().returnToGroupPage();
	}

	@Test
	public void testEditGroupByIndex () {
	app.getNavigationHelper().openMainPage();
	app.getNavigationHelper().gotoGroupsPage();

	List<GroupData> oldList = app.getGroupHelper().getGroupsList();
	
	app.getGroupHelper().initGroupByIndex(index);
	GroupData group = new GroupData();
    group.nameGroup = "new name2";
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupEditing();
    app.getGroupHelper().returnToGroupPage();
	
	List<GroupData> newList = app.getGroupHelper().getGroupsList();
    
	oldList.remove(index);
	oldList.add(group);
	Collections.sort(oldList);
	assertEquals(newList, oldList);

	}

}

