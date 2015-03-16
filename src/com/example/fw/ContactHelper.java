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

	public SortedListOf<ContactData> getContactsList() {
		SortedListOf<ContactData> contactsList = new SortedListOf<ContactData>();
	    manager.navigateTo().mainPage();
		List<WebElement> checkboxes = findElements(By.name("selected[]"));
		for (WebElement checkbox : checkboxes) {
			ContactData contact = new ContactData();
			String title = checkbox.getAttribute("title");
			String name = title.substring("Select (".length(), title.length() - ")".length());
			contact.firstname = name.substring(0, name.lastIndexOf(" ")); 
			contact.lastname = name.substring(name.lastIndexOf(" ")+1);
			contactsList.add(contact);
		}
		
		return contactsList;
	}

	public ContactHelper createContact(ContactData contact) {
	    manager.navigateTo().mainPage();
    	initContactCreation();
    	fillContactForm(contact, CREATION);
    	submitContactCreation();
    	returnToHomePage();
		return this;
	}

	public void editContactByIndex(ContactData contact, int index) {
		initContactByIndex(index);
		fillContactForm(contact, EDITING);
		submitContactEditing();
		returnToHomePage();
	}

	public ContactHelper deleteContactByIndex(int index) {
		initContactByIndex(index);
		submitContactDeleting();
		returnToHomePage();
		return this;
	}

	public ContactHelper deleteContactByNumString(String numString) {
		initContactByNumString(numString);
		submitContactDeleting();
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
		return this;
	  }

	public ContactHelper submitContactEditing() {
		click(By.xpath("//*[@type='submit' and @value='Update']"));
		return this;
	}

	private ContactHelper submitContactDeleting() {
		click(By.xpath("//*[@type='submit' and @value='Delete']"));
		return this;
	}

	public ContactHelper returnToHomePage() {
		click(By.linkText("home page"));
		return this;
	  }

}
