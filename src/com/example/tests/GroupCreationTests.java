package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test(dataProvider = "randomValidGroupGenerator")
  public void testGroupCreationWithValidData(GroupData group) throws Exception {

	List<GroupData> oldList = app.getGroupHelper().getGroupsList();
	
    app.getGroupHelper().createGroup(group);

	List<GroupData> newList = app.getGroupHelper().getGroupsList();
    
	oldList.add(group);
	Collections.sort(oldList);
	assertEquals(newList, oldList);

  }

}
