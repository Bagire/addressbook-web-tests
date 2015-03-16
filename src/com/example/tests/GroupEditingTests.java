package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class GroupEditingTests extends TestBase{

	//Number of editing string
	private String numString = "last()-1";

	//Number of editing string (int)
	private int index = 0;

	@Test
	public void testEditGroupByNumString () {
	app.navigateTo().groupsPage();
	app.getGroupHelper()
		.initGroupByNumString(numString);
	GroupData group = new GroupData();
    group.withNameGroup ("new name1");
    app.getGroupHelper()
    	.fillGroupForm(group)
    	.submitGroupEditing()
    	.returnToGroupPage();
	}

	@Test(dataProvider = "randomValidGroupGenerator")
	public void testEditGroupByIndex (GroupData group) {

	List<GroupData> oldList = app.getGroupHelper().getGroupsList();
	
	Random rnd = new Random();
	index=rnd.nextInt(oldList.size()-1);

	app.getGroupHelper().editGroupByIndex(group, index);
	
	List<GroupData> newList = app.getGroupHelper().getGroupsList();
    
	oldList.remove(index);
	oldList.add(group);
	Collections.sort(oldList);
	assertEquals(newList, oldList);

	}

}

