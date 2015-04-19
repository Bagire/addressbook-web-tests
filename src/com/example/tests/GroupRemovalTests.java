package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupRemovalTests extends TestBase{
	
	//Number of removing string (int)
	private int index = 0;

	@Test
	public void testDeleteGroupByIndex () {

//		SortedListOf<GroupData> oldList = app.getGroupHelper().getGroupsList();
		SortedListOf<GroupData> oldList = new SortedListOf<GroupData>(app.getHibernateHelper().listGroups());
		
		Random rnd = new Random();
		index=rnd.nextInt(oldList.size()-1);

		app.getGroupHelper().deleteGroupByIndex(index);
		
		SortedListOf<GroupData> newList = app.getGroupHelper().getGroupsList();
	    
		assertThat(newList, equalTo(oldList.without(index)));

	}

}
