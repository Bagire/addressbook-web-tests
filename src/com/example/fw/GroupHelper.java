package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.GroupData;
import com.example.utils.SortedListOf;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class GroupHelper extends WebDriverHelperBase {

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}

	private SortedListOf<GroupData> cachedGroupsList;
	
	public SortedListOf<GroupData> getGroupsList() {
		if (cachedGroupsList == null){
			rebuildGroupsListCache();
		}
		return cachedGroupsList;
	}

	private void rebuildGroupsListCache() {
		cachedGroupsList = new SortedListOf<GroupData>();
		manager.navigateTo().groupsPage();
		List<WebElement> checkboxes = getGroupRows();
		for (WebElement checkbox : checkboxes) {
			cachedGroupsList.add(new GroupData().withNameGroup(getNameGroup(checkbox)));
		}
	}

	public GroupHelper createGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		initGroupCreation();
    	fillGroupForm(group);
    	submitGroupCreation();
    	returnToGroupPage();
		rebuildGroupsListCache();
		return this;
	}

	public GroupHelper editGroupByIndex(GroupData group, int index, SortedListOf<GroupData> list) {
		initGroupByIndex(index);
		checkEqualGroupData(list, index);
		fillGroupForm(group);
		submitGroupEditing();
		returnToGroupPage();
		rebuildGroupsListCache();
		return this;
	}

	public GroupHelper deleteGroupByIndex(int index) {
		findGroupBasedXPathByIndex(index);
		submitGroupDeleting();
		returnToGroupPage();
		rebuildGroupsListCache();
		return this;
	}

	private void checkEqualGroupData(SortedListOf<GroupData> list, int index) {
		GroupData group = list.get(index);
		assertThat(group, equalTo(getGroupDataFromPage()));
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

	public GroupHelper findGroupBasedXPathByIndex(int index) {
		String xpathString = "//input[@name='selected[]'][" + (index+1) + "]";
		click(By.xpath(xpathString));
		return this;
	}

	public GroupHelper initGroupByIndex(int index) {
		findGroupBasedXPathByIndex(index);
		click(By.name("edit"));
		return this;
	}

	public GroupHelper submitGroupCreation() {
		click(By.name("submit"));
		cachedGroupsList = null;
		return this;
	  }

	public GroupHelper submitGroupEditing() {
		click(By.name("update"));
		cachedGroupsList = null;
		return this;
	}

	public GroupHelper submitGroupDeleting() {
		click(By.name("delete"));
		cachedGroupsList = null;
		return this;
	}

	public GroupHelper returnToGroupPage() {
		click(By.linkText("group page"));
		cachedGroupsList = null;
		return this;
		}

	private String getNameGroup(WebElement checkbox) {
		String title = checkbox.getAttribute("title");
		return title.substring("Select (".length(), title.length() - ")".length());
	}

	private List<WebElement> getGroupRows() {
		return findElements(By.name("selected[]"));
	}

	private GroupData getGroupDataFromPage() {
		GroupData group = new GroupData();
		group.setNameGroup(findElement(By.name("group_name")).getAttribute("value"));
		group.setHeader (findElement(By.name("group_header")).getText().replace("\r", ""));
		group.setFooter (findElement(By.name("group_footer")).getText().replace("\r", ""));
		return group;
	}

}
