package com.utils;

import java.util.Properties;

/**
 * @author Dmitry Sherstobitov
 * 
 */

public class LoadProperties {

	private static final String PROP_FILE_PATH = "/properties";

	public static String loadProperty(String name) {
		Properties props = new Properties();
		try {
			props.load(LoadProperties.class.getResourceAsStream(PROP_FILE_PATH));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		String value = "";

		if (name != null) {
			value = props.getProperty(name);
		}
		return value;
	}
}