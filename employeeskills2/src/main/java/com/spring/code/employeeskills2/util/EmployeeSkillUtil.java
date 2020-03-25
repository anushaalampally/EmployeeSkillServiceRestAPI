package com.spring.code.employeeskills2.util;

import java.util.regex.Pattern;

public class EmployeeSkillUtil {
	// to check the ID is alphanumeric only
	static String regex = "^[a-zA-Z0-9]+$";
	 
	static Pattern pattern = Pattern.compile(regex);
	
	public static boolean isValidId(String id) {
		if((id.length()==32)&&(pattern.matcher(id).matches())) {
			return true;
		}
		return false;
	}
	
public static void main(String args[]) {
	
	String id="4028808e70ff17610170ff18b9790001";
	System.out.println(isValidId(id));
	
	
}

}
