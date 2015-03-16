package com.example.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;

public class TestBase {

	protected static ApplicationManager app;
	
	@BeforeTest
	public void setUp() throws Exception {
		app = new ApplicationManager();
	  }

	@AfterTest
	public void tearDown() throws Exception {
	    app.stop();
	  }

	  @DataProvider
	  public Iterator<Object[]> randomValidGroupGenerator() {
		  List<Object[]> list = new ArrayList<Object[]>();
		  for (int i=0; i<5; i++) {
		    GroupData group = new GroupData()
		    	.withNameGroup(generateRandomString())
		    	.withHeader(generateRandomString())
		    	.withFooter(generateRandomString());
		    list.add(new Object[]{group});
		  }
		  return list.iterator();
	  }

	  @DataProvider
	  public Iterator<Object[]> randomValidContactGenerator(){
		  List<Object[]> list = new ArrayList<Object[]>();
		  for (int i=0; i<4; i++) {
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
		    list.add(new Object[]{contact});
		  }
		  return list.iterator();
	  }

	  public String generateRandomEmail(){
		  Random rnd = new Random();
	      return (""+rnd.nextInt(99999999)+"@email.com");
	  }
	  
	  public String generateRandomString(){
		  Random rnd = new Random();
		    if (rnd.nextInt(3) == 0){
			    return "";
		    }else{
			    return "test" + rnd.nextInt();
		    }
	  }
	  
	  public String generateRandomNumString(){
		  Random rnd = new Random();
	      return (""+rnd.nextInt(999999));
	  }
	  
	  public String generateRandomNum(int ind){
		  Random rnd = new Random();
	      return (""+(rnd.nextInt(ind)+1));
	  }
	  
	  public String generateRandomMonth(){
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

	  public String generateYearBetween(int start, int end) {
	    	return "" + (start + (int) Math.round(Math.random() * (end - start)));
	    }

}
