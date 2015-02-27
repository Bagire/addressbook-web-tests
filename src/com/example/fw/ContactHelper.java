package com.example.fw;

import org.openqa.selenium.By;

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
		click(By.xpath("(//a/img[@alt='Edit'])[" + numString + "]"));
	}

	public void deleteContactByNumString(String numString) {
		this.initContactByNumString(numString);
		click(By.xpath("//*[@type='submit' and @value='Delete']"));
	}

	public void submitContactEditing() {
		click(By.xpath("//*[@type='submit' and @value='Update']"));
	}

	public void initContactByPartName(String partName) {
		//first found by partName
		click(By.xpath("(//img[ancestor::*[preceding-sibling::td[contains(text(),'" + partName + "')]] and @alt='Edit'])[1]"));
	}

}
