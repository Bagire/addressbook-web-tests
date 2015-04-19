package com.example.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupEditingTests extends TestBase{

	//Number of editing string (int)
	private int index = 0;

	@Test(dataProvider = "randomValidGroupGenerator")
	public void testEditGroupByIndex (GroupData group) {

//	SortedListOf<GroupData> oldList = app.getGroupHelper().getGroupsList();
	SortedListOf<GroupData> oldList = new SortedListOf<GroupData>(app.getHibernateHelper().listGroups());
	
	Random rnd = new Random();
	index=rnd.nextInt(oldList.size()-1);

	app.getGroupHelper().editGroupByIndex(group, index, oldList);
	
	SortedListOf<GroupData> newList = app.getGroupHelper().getGroupsList();
    
	assertThat(newList, equalTo(oldList.without(index).withAdded(group)));

	}

}

