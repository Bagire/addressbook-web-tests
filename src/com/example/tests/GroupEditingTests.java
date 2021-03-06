package com.example.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupEditingTests extends TestBase{

	private static final String INTERFACE = "interface";
	private static final String DATABASE = "database";
	//Number of editing string (int)
	private int index = 0;

	@Test(dataProvider = "randomValidGroupGenerator")
	public void testEditGroupByIndex (GroupData group) {

	SortedListOf<GroupData> oldList = app.getGroupHelper().getGroupsList(DATABASE);
	
	Random rnd = new Random();
	index=rnd.nextInt(oldList.size()-1);

	app.getGroupHelper().editGroupByIndex(group, index, oldList);
	
	SortedListOf<GroupData> newList = app.getGroupHelper().getGroupsList(INTERFACE);
    
	assertThat(newList, equalTo(oldList.without(index).withAdded(group)));

	}

	@Test(dataProvider = "randomValidGroupGenerator")
	public void testEditGroupByIndexWithModel (GroupData group) {

	app.getModel().setGroups(app.getHibernateHelper().listGroups());

	SortedListOf<GroupData> oldList = app.getModel().getGroups();
	
	Random rnd = new Random();
	index=rnd.nextInt(oldList.size()-1);

	app.getGroupHelper().editGroupByIndexWithModel(group, index, oldList);
	
    SortedListOf<GroupData> newList = app.getModel().getGroups();
    
	assertThat(newList, equalTo(oldList.without(index).withAdded(group)));

	if (needCheck()) {
		if ("yes".equals(app.getProperty("check.db"))) {
			assertThat(app.getModel().getGroups(), equalTo(app.getHibernateHelper().listGroups()));
		}
		if ("yes".equals(app.getProperty("check.ui"))) {
			assertThat(app.getModel().getGroups(), equalTo(app.getGroupHelper().getUiGroupsList()));
		}
	}
  
	}

}

