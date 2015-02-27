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

	private boolean findGroupBasedXPathByNumString(String numString) {
		String xpathString = "//input[@name='selected[]'][" + numString + "]";
		if (isElementPresent(By.xpath(xpathString))){
			click(By.xpath(xpathString));
			return true;
			}
		else {
			return true;
			}
	}

	public boolean deleteGroupByNumString(String numString) {
		if (findGroupBasedXPathByNumString(numString)){
			click(By.name("delete"));
			return true;
			}
		else {
			return true;
			}
	}

	public boolean initGroupByNumString(String numString) {
		if (findGroupBasedXPathByNumString(numString)){
			click(By.name("edit"));
			return true;
			}
		else {
			return true;
			}
	}

	public void submitGroupEditing() {
		click(By.name("update"));
	}

}
