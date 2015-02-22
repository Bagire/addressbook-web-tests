package com.example.tests;


public class ContactCreationTests extends TestBase {

  //For JUnit
  @org.junit.Test
  // For TestNG
  //@org.testng.annotations.Test
  public void testNonEmptyContactCreation() throws Exception {
    openMainPage();
    gotoHomePage();
    initContactCreation();
    ContactData contact = new ContactData();
    contact.firstname = "Сидор";
    contact.lastname = "Сидоров";
    contact.address = "Адрес Сидорова 1";
    contact.home = "111111";
    contact.mobile = "333333";
    contact.work = "444444";
    contact.email = "s@s.ru";
    contact.email2 = "s@s.com";
    contact.bday = "1";
    contact.bmonth = "November";
    contact.byear = "1984";
    contact.new_group = "Rob";
    contact.address2 = "Адрес Сидорова 2";
    contact.phone2 = "222222";
    fillContactForm(contact);
    submitContactCreation();
    returnToHomePage();
  }

}
