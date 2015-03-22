package com.example.fw;

import java.util.Random;

public class DataGenerator {

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
