package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupRemovalTests extends TestBase{
	
	private static final String INTERFACE = "interface";
	private static final String DATABASE = "database";
	//Number of removing string (int)
	private int index = 0;

	@Test
	public void testDeleteGroupByIndex () {

		SortedListOf<GroupData> oldList = app.getGroupHelper().getGroupsList(DATABASE);
		
		Random rnd = new Random();
		index=rnd.nextInt(oldList.size()-1);

		app.getGroupHelper().deleteGroupByIndex(index);
		
		SortedListOf<GroupData> newList = app.getGroupHelper().getGroupsList(INTERFACE);
	    
		assertThat(newList, equalTo(oldList.without(index)));

	}

	@Test
	public void testDeleteGroupByIndexWithModel () {

		app.getModel().setGroups(app.getHibernateHelper().listGroups());

		SortedListOf<GroupData> oldList = app.getModel().getGroups();
		
		Random rnd = new Random();
		index=rnd.nextInt(oldList.size()-1);

		app.getGroupHelper().deleteGroupByIndexWithModel(index);
		
	    SortedListOf<GroupData> newList = app.getModel().getGroups();
	    
		assertThat(newList, equalTo(oldList.without(index)));

		if ("yes".equals(app.getProperty("check.db"))) {
			assertThat(app.getModel().getGroups(), equalTo(app.getHibernateHelper().listGroups()));
		}
		if ("yes".equals(app.getProperty("check.ui"))) {
			assertThat(app.getModel().getGroups(), equalTo(app.getGroupHelper().getUiGroupsList()));
		}
	  
	}

}
