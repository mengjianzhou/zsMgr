package com.robert.JavaUtil.properties;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ResourceBundleUtil {
	
	public static void main(String[] args) {
		
		ResourceBundle bundle = PropertyResourceBundle.getBundle("common");
		System.out.println(bundle.getString("name"));
		
	}
}
