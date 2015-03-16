package com.example.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

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

	SortedListOf<GroupData> oldList = app.getGroupHelper().getGroupsList();
	
	Random rnd = new Random();
	index=rnd.nextInt(oldList.size()-1);

	app.getGroupHelper().editGroupByIndex(group, index);
	
	SortedListOf<GroupData> newList = app.getGroupHelper().getGroupsList();
    
	assertThat(newList, equalTo(oldList.without(index).withAdded(group)));

	}

}

