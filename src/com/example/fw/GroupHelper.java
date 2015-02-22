package com.example.fw;

import org.openqa.selenium.By;

import com.example.tests.GroupData;

public class GroupHelper extends HelperBase {

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}

	public void initGroupCreation() {
		click(By.name("new"));
	  }

	public void fillGroupForm(GroupData group) {
		type(By.name("group_name"), group.nameGroup);
		type(By.name("group_header"), group.header);
		type(By.name("group_footer"), group.footer);
	  }

	public void submitGroupCreation() {
		click(By.name("submit"));
	  }

	public void returnToGroupPage() {
		click(By.linkText("group page"));
		}

	private void findGroupBasedXPathByIndex(int index) {
		click(By.xpath("//input[@name='selected[]'][" + index + "]"));
	}

	public void deleteGroup(int index) {
		findGroupBasedXPathByIndex(index);
		click(By.name("delete"));
	}

	public void initGroup(int index) {
		findGroupBasedXPathByIndex(index);
		click(By.name("edit"));
	}

	public void submitGroupEditing() {
		click(By.name("update"));
	}

}
