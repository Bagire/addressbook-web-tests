package com.example.tests;


public class AnotherGroupCreationTests extends TestBase {

  @org.testng.annotations.Test
  public void testNonEmptyGroupCreation() throws Exception {
    openMainPage();
	gotoGroupsPage();
    initGroupCreation();
    GroupData group = new GroupData();
    group.nameGroup = "name 1";
    group.header = "header 1";
    group.footer = "footer 1";
	fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  @org.testng.annotations.Test
  public void testEmptyGroupCreation() throws Exception {
    openMainPage();
	gotoGroupsPage();
    initGroupCreation();
    fillGroupForm(new GroupData("", "", ""));
    submitGroupCreation();
    returnToGroupPage();
  }
  
}
