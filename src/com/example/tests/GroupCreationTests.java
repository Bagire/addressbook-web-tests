package com.example.tests;

import static com.example.tests.GroupDataGenerator.loadGroupsFromCsvFile;
import static com.example.tests.GroupDataGenerator.loadGroupsFromXmlFile;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupCreationTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> groupsFromFile()  throws IOException {
	  String dataFile = app.properties.getProperty("groupsDataFile");
	  if (dataFile.endsWith(".xml") == true) {
		  return wrapGroupsForProvider (loadGroupsFromXmlFile(new File(dataFile))).iterator();
	  } else {
		  return wrapGroupsForProvider (loadGroupsFromCsvFile(new File(dataFile))).iterator();
	  }
	}

//  @Test(dataProvider = "randomValidGroupGenerator")
  @Test(dataProvider = "groupsFromFile")
  public void testGroupCreationWithValidData(GroupData group) throws Exception {

//	SortedListOf<GroupData> oldList = app.getGroupHelper().getGroupsList();
	SortedListOf<GroupData> oldList = new SortedListOf<GroupData>(app.getHibernateHelper().listGroups());
	
    app.getGroupHelper().createGroup(group);

//    SortedListOf<GroupData> newList = new SortedListOf<GroupData>(app.getHibernateHelper().listGroups());
    SortedListOf<GroupData> newList = app.getGroupHelper().getGroupsList();
    
	assertThat(newList, equalTo(oldList.withAdded(group)));
	}

}
