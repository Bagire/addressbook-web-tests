package com.example.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;

public class ContactDataGenerator {

	public static void main(String[] args) throws IOException {
		if (args.length < 3) {
			System.out.println("Please specify parameters: <amount of test data>, <file>, <format>");
			return;
		}
		int amount = Integer.parseInt(args[0]);
		File file = new File(args[1]);
		String format = args[2];
		
		if (file.exists()){
			System.out.println("File exists, plese remove it manually: " + file);
			return;
		}
		
		List<ContactData> contacts = generateRandomContacts(amount);
		if ("csv".equals(format)){
			saveContactsToCsvFile(contacts, file);
		} else if ("xml".equals(format)) {
			saveContactsToXmlFile(contacts, file);
		} else {
			System.out.println("Unknown format " + format);
		}
		return;

	}

	private static void saveContactsToXmlFile(List<ContactData> contacts, File file) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("contact", ContactData.class);
		String xml = xstream.toXML(contacts);
		FileWriter writer = new FileWriter(file);
		writer.write(xml);
		writer.close();
	}

	private static void saveContactsToCsvFile(List<ContactData> contacts, File file) throws IOException {
		FileWriter writer = new FileWriter(file);
		for (ContactData contact : contacts) {
			writer.write(contact.getFirstname() + ","
					   + contact.getLastname() + ","
					   + contact.getAddress() + ","
					   + contact.getHome() + ","
					   + contact.getMobile() + ","
					   + contact.getWork() + ","
					   + contact.getEmail() + ","
					   + contact.getEmail2() + ","
					   + contact.getBirthDay() + ","
					   + contact.getBirthMonth() + ","
					   + contact.getBirthYear() + ","
					   + contact.getAddress2() + ","
					   + contact.getPhone2() + "\n");
			}
		writer.close();
	}

	public static List<ContactData> generateRandomContacts(int amount) {
		  List<ContactData> list = new ArrayList<ContactData>();
		  for (int i=0; i<amount; i++) {
		    ContactData contact = new ContactData();
		    contact.firstname = generateRandomString();
		    contact.lastname = generateRandomString();
			contact.address = generateRandomString();
		    contact.home = generateRandomNumString();
		    contact.mobile = generateRandomNumString();
		    contact.work = generateRandomNumString();
		    contact.email = generateRandomEmail();
		    contact.email2 = generateRandomEmail();
		    contact.birthDay = generateRandomNum(31);
		    contact.birthMonth = generateRandomMonth();
		    contact.birthYear = generateYearBetween(1900, 2015);
			contact.address2 = generateRandomString();
		    contact.phone2 = generateRandomNumString();
		    list.add(contact);
		  }
		  return list;
	}

	  public static String generateRandomEmail(){
		  Random rnd = new Random();
	      return (""+rnd.nextInt(99999999)+"@email.com");
	  }
	  
	  public static String generateRandomString(){
		  Random rnd = new Random();
		    if (rnd.nextInt(3) == 0){
			    return "";
		    }else{
			    return "test" + rnd.nextInt();
		    }
	  }
	  
	  public static String generateRandomNumString(){
		  Random rnd = new Random();
	      return (""+rnd.nextInt(999999));
	  }
	  
	  public static String generateRandomNum(int ind){
		  Random rnd = new Random();
	      return (""+(rnd.nextInt(ind)+1));
	  }
	  
	  public static String generateRandomMonth(){
		  String month="";
		  Random rnd = new Random();
		  switch (rnd.nextInt(12)){
		    case 0:	month = "January"; break;
		    case 1: month = "February"; break;
		    case 2: month = "March"; break;
		    case 3: month = "April"; break;
		    case 4: month = "May"; break;
		    case 5: month = "June"; break;
		    case 6: month = "July"; break;
		    case 7: month = "August"; break;
		    case 8: month = "September"; break;
		    case 9: month = "October"; break;
		    case 10: month = "November"; break;
		    case 11: month = "December"; break;
		    }
		  return month;
	  }

	  public static String generateYearBetween(int start, int end) {
	    	return "" + (start + (int) Math.round(Math.random() * (end - start)));
	    }

}
