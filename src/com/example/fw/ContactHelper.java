package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;

public class ContactHelper extends HelperBase {

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public void initContactCreation() {
		click(By.linkText("add new"));
	  }

	public void fillContactForm(ContactData contact) {
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
	    //selectByText(By.name("new_group"), contact.new_group);
		type(By.name("address2"),contact.address2);
		type(By.name("phone2"),contact.phone2);
	}

	public void submitContactCreation() {
		click(By.name("submit"));
	  }

	public void returnToHomePage() {
		click(By.linkText("home page"));
	  }

	public void initContactByNumString(String numString) {
		String xpathString = "(//a/img[@alt='Edit'])[" + numString + "]";
		click(By.xpath(xpathString));
	}

	public void deleteContactByNumString(String numString) {
		initContactByNumString(numString);
		click(By.xpath("//*[@type='submit' and @value='Delete']"));
	}

	public void initContactByIndex(int index) {
		String xpathString = "(//a/img[@alt='Edit'])[" + (index+1) + "]";
		click(By.xpath(xpathString));
	}

	public void deleteContactByIndex(int index) {
		initContactByIndex(index);
		click(By.xpath("//*[@type='submit' and @value='Delete']"));
	}

	public void submitContactEditing() {
		click(By.xpath("//*[@type='submit' and @value='Update']"));
	}

	public void initContactByPartName(String partName) {
		//first found by partName
		String xpathString = "(//img[ancestor::*[preceding-sibling::td[contains(text(),'" + partName + "')]] and @alt='Edit'])[1]";
		click(By.xpath(xpathString));
	}

	public List<ContactData> getContactsList() {
		List<ContactData> contactsList = new ArrayList<ContactData>();
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

}
