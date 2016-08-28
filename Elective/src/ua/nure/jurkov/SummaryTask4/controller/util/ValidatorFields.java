package ua.nure.jurkov.SummaryTask4.controller.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorFields {
	
	public static boolean isOnlyText(String name){
		
		if(validateByRegExp("[a-zA-Z]+", name) && !validateByRegExp("[à-ÿÀ-ß]+", name)){
			return true;
		}
		else if(!validateByRegExp("[a-zA-Z]+", name) && validateByRegExp("[à-ÿÀ-ß]+", name)){
			return true;
		}
		
		return false;
	}
	
	public static boolean isEmail(String email){
		if(validateByRegExp("[\\w\\d\\s\\.]+[@]{1}[a-z]+[.]{1}[a-z]{2,6}", email)){
			return true;
		}
		
		return false;
	}
	
	public static boolean isPass(String pass){
		if(validateByRegExp("[a-zA-Z0-9]{6,10}", pass)){
			return true;
		}
		
		return false;
	}
	
	private static boolean validateByRegExp(String regExp, String text){
		Pattern p = Pattern.compile(regExp);
		Matcher matcher = p.matcher(text); 
		
		return matcher.matches();
	}
	
	public static boolean validateDate(String date){

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);

		try {
			formatter.parse(date);
			
		} catch (ParseException e) {
			
		    return false;
		}
		
		return true;
	}
	
	public static boolean beforeDate(String date1, String date2){
		Date startDate = Date.valueOf(date1);
		
		Date endDate = Date.valueOf(date2);
		
		if(startDate.compareTo(endDate) > 0){
			return false;
		}
		
		return true;
	}
	
	public static boolean onlyNumbers(String str){
		if(validateByRegExp("[\\d]+", str)){
			return true;
		}
		
		return false;
	}
	
	public static boolean afterNowDate(String date){
		
		java.util.Date now = Calendar.getInstance().getTime();
		
		if(now.before(Date.valueOf(date))){
			return true;
		}
		
		return false;
	}
}
