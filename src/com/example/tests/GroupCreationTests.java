package com.example.tests;

public class GroupCreationTests extends TestBase {
  //For JUnit
  //@org.junit.Test
  // For TestNG
  @org.testng.annotations.Test
  public void testNonEmptyGroupCreation() throws Exception {
	app.getNavigationHelper().openMainPage();
	app.getNavigationHelper().gotoGroupsPage();
	app.getGroupHelper().initGroupCreation();
    GroupData group = new GroupData();
    group.nameGroup = "name 1";
    group.header = "header 1";
    group.footer = "footer 1";
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
  }

  //For JUnit
  //@org.junit.Test
  // For TestNG
  @org.testng.annotations.Test
  public void testEmptyGroupCreation() throws Exception {
	  app.getNavigationHelper().openMainPage();
	  app.getNavigationHelper().gotoGroupsPage();
	  app.getGroupHelper().initGroupCreation();
	  app.getGroupHelper().fillGroupForm(new GroupData("", "", ""));
	  app.getGroupHelper().submitGroupCreation();
	  app.getGroupHelper().returnToGroupPage();
  }
}
