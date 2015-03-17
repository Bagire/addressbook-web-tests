package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;
import com.example.utils.SortedListOf;

public class ContactHelper extends HelperBase {

	public static boolean CREATION = true;
	public static boolean EDITING = true;
	
	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	private SortedListOf<ContactData> cachedContactsList;

	public SortedListOf<ContactData> getContactsList() {
		if (cachedContactsList == null){
			rebuildContactsListCache();
		}
		return cachedContactsList;
	}

	private void rebuildContactsListCache() {
		cachedContactsList = new SortedListOf<ContactData>();
	    manager.navigateTo().mainPage();
	    List<WebElement> rows = getContactRows();
	    for (WebElement row : rows) {
	    	ContactData contact = new ContactData()
	            .setFirstname(getFirstNameFrom(row))
	            .setLastname(getLastNameFrom(row));
	    	cachedContactsList.add(contact);
	    }
	}

	public ContactHelper createContact(ContactData contact) {
	    manager.navigateTo().mainPage();
    	initContactCreation();
    	fillContactForm(contact, CREATION);
    	submitContactCreation();
    	returnToHomePage();
		rebuildContactsListCache();
		return this;
	}

	public ContactHelper editContactByIndex(ContactData contact, int index) {
		initContactByIndex(index);
		fillContactForm(contact, EDITING);
		submitContactEditing();
		returnToHomePage();
		rebuildContactsListCache();
		return this;
	}

	public ContactHelper deleteContactByIndex(int index) {
		initContactByIndex(index);
		submitContactDeleting();
		returnToHomePage();
		rebuildContactsListCache();
		return this;
	}

	public ContactHelper deleteContactByNumString(String numString) {
		initContactByNumString(numString);
		submitContactDeleting();
		rebuildContactsListCache();
		return this;
	}

// -------------------------------------------------------------------------------------
	
	public ContactHelper initContactCreation() {
		click(By.linkText("add new"));
		return this;
	  }

	public ContactHelper fillContactForm(ContactData contact, boolean typeForm) {
		type(By.name("firstname"),contact.firstname);
		type(By.name("lastname"),contact.lastname);
		type(By.name("address"),contact.address);
		type(By.name("home"),contact.home);
		type(By.name("mobile"),contact.mobile);
		type(By.name("work"),contact.work);
		type(By.name("email"),contact.email);
		type(By.name("email2"),contact.email2);
	    selectByText(By.name("bday"), contact.birthDay);
	    selectByText(By.name("bmonth"), contact.birthMonth);
		type(By.name("byear"),contact.birthYear);
		if (typeForm == CREATION){
			//selectByText(By.name("new_group"), contact.new_group);
		} else {
			if (findElements(By.name("new_group")).size() != 0) {
				throw new Error("Group selector exists in editing form");
			}
		}
		type(By.name("address2"),contact.address2);
		type(By.name("phone2"),contact.phone2);
		return this;
	}

	public ContactHelper initContactByNumString(String numString) {
		String xpathString = "(//a/img[@alt='Edit'])[" + numString + "]";
		click(By.xpath(xpathString));
		return this;
	}

	public ContactHelper initContactByIndex(int index) {
		String xpathString = "(//a/img[@alt='Edit'])[" + (index+1) + "]";
		click(By.xpath(xpathString));
		return this;
	}

	public ContactHelper initContactByPartName(String partName) {
		//first found by partName
		String xpathString = "(//img[ancestor::*[preceding-sibling::td[contains(text(),'" + partName + "')]] and @alt='Edit'])[1]";
		click(By.xpath(xpathString));
		return this;
	}

	public ContactHelper submitContactCreation() {
		click(By.name("submit"));
		cachedContactsList = null;
		return this;
	  }

	public ContactHelper submitContactEditing() {
		click(By.xpath("//*[@type='submit' and @value='Update']"));
		cachedContactsList = null;
		return this;
	}

	public ContactHelper submitContactDeleting() {
		click(By.xpath("//*[@type='submit' and @value='Delete']"));
		cachedContactsList = null;
		return this;
	}

	public ContactHelper returnToHomePage() {
		click(By.linkText("home page"));
		return this;
	  }

	private String getLastNameFrom(WebElement row) {
		//2 but not 3 because lastname and firstname are mixed up
		return row.findElement(By.xpath(".//td[2]")).getText();
	}

	private String getFirstNameFrom(WebElement row) {
		//3 but not 2 because firstname and lastname are mixed up
		return row.findElement(By.xpath(".//td[3]")).getText();
	}

	private List<WebElement> getContactRows() {
		return findElements(By.name("entry"));
	}

}
