package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.GroupData;

public class GroupHelper extends HelperBase {

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}

	public List<GroupData> getGroupsList() {
		List<GroupData> groupsList = new ArrayList<GroupData>();
		manager.navigateTo().groupsPage();
		List<WebElement> checkboxes = findElements(By.name("selected[]"));
		for (WebElement checkbox : checkboxes) {
			String title = checkbox.getAttribute("title");
			String nameGroup = title.substring("Select (".length(), title.length() - ")".length());
			groupsList.add(new GroupData().withNameGroup(nameGroup));
		}
		return groupsList;
	}

	public GroupHelper createGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		initGroupCreation();
    	fillGroupForm(group);
    	submitGroupCreation();
    	returnToGroupPage();
		return this;
	}

	public GroupHelper editGroupByIndex(GroupData group, int index) {
		initGroupByIndex(index);
		fillGroupForm(group);
		submitGroupEditing();
		returnToGroupPage();
		return this;
	}

	public GroupHelper deleteGroupByIndex(int index) {
		findGroupBasedXPathByIndex(index);
		submitGroupDeleting();
		returnToGroupPage();
		return this;
	}

	public GroupHelper deleteGroupByNumString(String numString) {
		findGroupBasedXPathByNumString(numString);
		submitGroupDeleting();
		return this;
	}

// -------------------------------------------------------------
	
	public GroupHelper initGroupCreation() {
		click(By.name("new"));
		return this;
	}

	public GroupHelper fillGroupForm(GroupData group) {
		type(By.name("group_name"), group.getNameGroup());
		type(By.name("group_header"), group.getHeader());
		type(By.name("group_footer"), group.getFooter());
		return this;
	  }

	private GroupHelper findGroupBasedXPathByNumString(String numString) {
		String xpathString = "//input[@name='selected[]'][" + numString + "]";
		isElementPresent(By.xpath(xpathString));
		click(By.xpath(xpathString));
		return this;
	}

	private GroupHelper findGroupBasedXPathByIndex(int index) {
		String xpathString = "//input[@name='selected[]'][" + (index+1) + "]";
		click(By.xpath(xpathString));
		return this;
	}

	public GroupHelper initGroupByIndex(int index) {
		findGroupBasedXPathByIndex(index);
		click(By.name("edit"));
		return this;
	}

	public GroupHelper initGroupByNumString(String numString) {
		findGroupBasedXPathByNumString(numString);
		click(By.name("edit"));
		return this;
	}

	public GroupHelper submitGroupCreation() {
		click(By.name("submit"));
		return this;
	  }

	public GroupHelper submitGroupEditing() {
		click(By.name("update"));
		return this;
	}

	public GroupHelper submitGroupDeleting() {
		click(By.name("delete"));
		return this;
	}

	public GroupHelper returnToGroupPage() {
		click(By.linkText("group page"));
		return this;
		}

}
