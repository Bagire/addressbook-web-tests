package com.example.tests;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testNonEmptyGroupCreation() throws Exception {
	app.getNavigationHelper().openMainPage();
	app.getNavigationHelper().gotoGroupsPage();

	List<GroupData> oldList = app.getGroupHelper().getGroupsList();
	
	app.getGroupHelper().initGroupCreation();
    GroupData group = new GroupData();
    group.nameGroup = "name 1";
    group.header = "header 1";
    group.footer = "footer 1";
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();

	List<GroupData> newList = app.getGroupHelper().getGroupsList();
    
	oldList.add(group);
	Collections.sort(oldList);
	assertEquals(newList, oldList);

  }

  @Test
  public void testEmptyGroupCreation() throws Exception {
	  app.getNavigationHelper().openMainPage();
	  app.getNavigationHelper().gotoGroupsPage();

      List<GroupData> oldList = app.getGroupHelper().getGroupsList();
		
	  GroupData group = new GroupData("", "", "");
	  app.getGroupHelper().initGroupCreation();
      app.getGroupHelper().fillGroupForm(group);
	  app.getGroupHelper().submitGroupCreation();
	  app.getGroupHelper().returnToGroupPage();
	  List<GroupData> newList = app.getGroupHelper().getGroupsList();
	    
	  oldList.add(group);
	  Collections.sort(oldList);
	  assertEquals(newList, oldList);

  }

}
