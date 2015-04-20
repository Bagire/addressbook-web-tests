package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;
import com.example.utils.SortedListOf;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ContactHelper extends WebDriverHelperBase {

	private static final String INTERFACE = "interface";
	private static final String DATABASE = "database";
	public static boolean CREATION = true;
	public static boolean EDITING = true;
	
	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	private SortedListOf<ContactData> cachedContactsList;

	public SortedListOf<ContactData> getContactsList(String method) {
		if (cachedContactsList == null){
			rebuildContactsListCache(method);
		}
		return cachedContactsList;
	}

	private void rebuildContactsListCache(String method) {
	    manager.navigateTo().mainPage();
	    if (method.equals(DATABASE)) {
	    	cachedContactsList = new SortedListOf<ContactData>(manager.getHibernateHelper().listContacts());
	    } else if (method.equals(INTERFACE)) {
	    	cachedContactsList = new SortedListOf<ContactData>();
	    	List<WebElement> rows = getContactRows();
	    	for (WebElement row : rows) {
	    		ContactData contact = new ContactData()
	    		.setFirstname(getFirstNameFrom(row))
	    		.setLastname(getLastNameFrom(row))
	    		.setPhone(getPhoneFrom(row));
	    		cachedContactsList.add(contact);
	    	}
	    }
	}

	public ContactHelper createContact(ContactData contact) {
	    manager.navigateTo().mainPage();
    	initContactCreation();
    	fillContactForm(contact, CREATION);
    	submitContactCreation();
    	returnToHomePage();
		rebuildContactsListCache(INTERFACE);
		return this;
	}

	public ContactHelper createContactWithModel(ContactData contact) {
	    manager.navigateTo().mainPage();
    	initContactCreation();
    	fillContactForm(contact, CREATION);
    	submitContactCreation();
    	returnToHomePage();
    	manager.getModel().addContact(contact);
		return this;
	}

	public ContactHelper editContactByIndex(ContactData contact, int index, SortedListOf<ContactData> list) {
		initContactByIndex(index);
		checkEqualContactData(list, index);
		fillContactForm(contact, EDITING);
		submitContactEditing();
		returnToHomePage();
		rebuildContactsListCache(INTERFACE);
		return this;
	}

	public ContactHelper editContactByIndexWithModel(ContactData contact, int index, SortedListOf<ContactData> list) {
		initContactByIndex(index);
		checkEqualContactData(list, index);
		fillContactForm(contact, EDITING);
		submitContactEditing();
		returnToHomePage();
    	manager.getModel().removeContact(index).addContact(contact);
		return this;
	}

	public ContactHelper deleteContactByIndex(int index) {
		initContactByIndex(index);
		submitContactDeleting();
		returnToHomePage();
		rebuildContactsListCache(INTERFACE);
		return this;
	}

	public ContactHelper deleteContactByIndexWithModel(int index) {
		initContactByIndex(index);
		submitContactDeleting();
		returnToHomePage();
    	manager.getModel().removeContact(index);
		return this;
	}

	public List<ContactData> fillEmptyPhones(List<ContactData> list) {
		for (ContactData contact : list) {
			if (contact.home.equals("")){
				contact.home = contact.mobile;
			}
			if (contact.home.equals("")){
				contact.home = contact.work;
			}
		}
		return list;
	}

	private void checkEqualContactData(SortedListOf<ContactData> list, int index) {
		ContactData contact = list.get(index);
		assertThat(contact, equalTo(getContactDataFromPage()));
	}

	public SortedListOf<ContactData> getUiContactsList() {
	    manager.navigateTo().mainPage();
	    SortedListOf<ContactData> contacts = new SortedListOf<ContactData>();
    	List<WebElement> rows = getContactRows();
    	for (WebElement row : rows) {
    		ContactData contact = new ContactData()
    		.setFirstname(getFirstNameFrom(row))
    		.setLastname(getLastNameFrom(row))
    		.setPhone(getPhoneFrom(row));
    		contacts.add(contact);
    	}
	    return contacts;
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

	public ContactHelper initContactByIndex(int index) {
		String xpathString = "(//a/img[@alt='Edit'])[" + (index+1) + "]";
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

	private String getPhoneFrom(WebElement row) {
		return row.findElement(By.xpath(".//td[5]")).getText();
	}

	private List<WebElement> getContactRows() {
		return findElements(By.name("entry"));
	}

	private ContactData getContactDataFromPage() {
		ContactData	contact = new ContactData();
		contact.firstname = findElement(By.name("firstname")).getAttribute("value");
		contact.lastname = findElement(By.name("lastname")).getAttribute("value");
		contact.address = findElement(By.name("address")).getText();
		contact.home = findElement(By.name("home")).getAttribute("value");
		contact.mobile = findElement(By.name("mobile")).getAttribute("value");
		contact.work = findElement(By.name("work")).getAttribute("value");
		contact.email = findElement(By.name("email")).getAttribute("value");
		contact.email2 = findElement(By.name("email2")).getAttribute("value");
		contact.birthDay = findElement(By.xpath(".//select[@name='bday']/option[@selected='selected']")).getText();
		contact.birthMonth = findElement(By.xpath(".//select[@name='bmonth']/option[@selected='selected']")).getText();
		contact.birthYear = findElement(By.name("byear")).getAttribute("value");
		contact.address2 = findElement(By.name("address2")).getText();
		contact.phone2 = findElement(By.name("phone2")).getAttribute("value");
		return contact;
	}

}
