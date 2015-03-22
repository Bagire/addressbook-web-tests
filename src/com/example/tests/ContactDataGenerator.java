package com.example.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.fw.DataGenerator.generateRandomString;
import static com.example.fw.DataGenerator.generateRandomNumString;
import static com.example.fw.DataGenerator.generateRandomEmail;
import static com.example.fw.DataGenerator.generateRandomMonth;
import static com.example.fw.DataGenerator.generateYearBetween;
import static com.example.fw.DataGenerator.generateRandomNum;

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

	public static List<ContactData> loadContactsFromXmlFile(File file) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("contact", ContactData.class);
		return (List<ContactData>) xstream.fromXML(file);
	}

	public static List<ContactData> loadContactsFromCsvFile(File file) throws IOException {
		  List<ContactData> list = new ArrayList<ContactData>();
		  FileReader reader = new FileReader(file);
		  BufferedReader buffReader = new BufferedReader(reader);
		  String line = buffReader.readLine();
		  while (line != null){
			  String[] part = line.split(",");
			  ContactData contact = new ContactData();
			  contact.firstname = part[0];
			  contact.lastname = part[1];
			  contact.address = part[2];
			  contact.home = part[3];
			  contact.mobile = part[4];
			  contact.work = part[5];
			  contact.email = part[6];
			  contact.email2 = part[7];
			  contact.birthDay = part[8];
			  contact.birthMonth = part[9];
			  contact.birthYear = part[10];
			  contact.address2 = part[11];
			  contact.phone2 = part[12];
			  list.add(contact);
			  line = buffReader.readLine();
		  }
		  buffReader.close();
		  return list;
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

}
