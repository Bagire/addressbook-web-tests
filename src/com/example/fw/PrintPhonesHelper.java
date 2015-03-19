package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;
import com.example.utils.SortedListOf;

public class PrintPhonesHelper extends HelperBase{

	public PrintPhonesHelper(ApplicationManager manager) {
		super(manager);
	}

	String lastName = "";
	String firstName = "";
	String phone = "";
	
	public SortedListOf<ContactData> getPrintList() {
		manager.navigateTo().printPhones();
		SortedListOf<ContactData> printList = new SortedListOf<ContactData>();
	    List<WebElement> elems = getPrintCells();
	    for (WebElement elem : elems) {
	    	if (checkEmpty(elem) != true){
	    		getDataFrom (elem);
	    		ContactData contact = new ContactData()
            		.setFirstname(firstName)
            		.setLastname(lastName)
            		.setPhone(phone);
	    		printList.add(contact);
		    }
	    }
		return printList;
	}

	private void getDataFrom(WebElement elem) {
		// first - begin, last - end
		String[] listData = elem.getText().split("\n");
		getName(listData[0].replace(":", ""));
		if (listData.length > 1){
				phone = getPhones(listData);
		}
	}

	private String getPhones(String[] listData) {
		String phoneH = "";
		String phoneM = "";
		String phoneW = "";
		for (int i = 1; i < listData.length; i++) {
			String string = listData[i];
			if (string.startsWith("H:") == true) {
				phoneH = string.substring(3).replaceAll(" ", "");
			}
			if (string.startsWith("M:") == true) {
				phoneM = string.substring(3).replaceAll(" ", "");
			}
			if (string.startsWith("W:") == true) {
				phoneW = string.substring(3).replaceAll(" ", "");
			}
		}
		if (phoneH != "") {return phoneH;}
		if (phoneM != "") {return phoneM;}
		if (phoneW != "") {return phoneW;}
		return "";
	}

	private void getName(String name) {
		if (name.endsWith(" ") == true){
			firstName = name.trim();
			lastName = "";
		} else {
			String[] listName = name.split(" ");	
			if (listName.length == 0){
				firstName = "";
				lastName = "";
			}
			if (listName.length == 1){
				firstName = "";
				lastName = listName[0];
			}
			if (listName.length >= 2){
				firstName = listName[0];
				lastName = listName[1];
			}
		}
	}

	private boolean checkEmpty(WebElement elem) {
		if ((elem.getAttribute("valign") == null) 
				&& (elem.getText().equals("."))){
			return true;	
		}
		return false;
	}

	private List<WebElement> getPrintCells() {
		if (findElements(By.id("view")).size() > 0){
			return findElement(By.id("view")).findElements(By.xpath("//td"));
		}
		return null;
	}

}
