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

	private void findGroupBasedXPathByNumString(String numString) {
		String xpathString = "//input[@name='selected[]'][" + numString + "]";
		isElementPresent(By.xpath(xpathString));
		click(By.xpath(xpathString));
	}

	public void deleteGroupByNumString(String numString) {
		findGroupBasedXPathByNumString(numString);
		click(By.name("delete"));
	}

	public void initGroupByNumString(String numString) {
		findGroupBasedXPathByNumString(numString);
		click(By.name("edit"));
	}

	private void findGroupBasedXPathByIndex(int index) {
		String xpathString = "//input[@name='selected[]'][" + (index+1) + "]";
		click(By.xpath(xpathString));
	}

	public void deleteGroupByIndex(int index) {
		findGroupBasedXPathByIndex(index);
		click(By.name("delete"));
	}

	public void initGroupByIndex(int index) {
		findGroupBasedXPathByIndex(index);
		click(By.name("edit"));
	}

	public void submitGroupEditing() {
		click(By.name("update"));
	}

	public List<GroupData> getGroupsList() {
		List<GroupData> groupsList = new ArrayList<GroupData>();
		List<WebElement> checkboxes = findElements(By.name("selected[]"));
		for (WebElement checkbox : checkboxes) {
			GroupData group = new GroupData();
			String title = checkbox.getAttribute("title");
			group.nameGroup = title.substring("Select (".length(), title.length() - ")".length());
			groupsList.add(group);
		}
		
		return groupsList;
	}

}
