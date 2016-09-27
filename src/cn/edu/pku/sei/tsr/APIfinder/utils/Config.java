package cn.edu.pku.sei.tsr.APIfinder.utils;

import java.util.ResourceBundle;

public class Config {
	private static String			CONFIG_FILE_NAME	= "config";

	private static ResourceBundle	bundle;

	// 静态私有方法，用于从属性文件中取得属性值
	static {
		try {
			bundle = ResourceBundle.getBundle(CONFIG_FILE_NAME);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getValue(String key) {
		return bundle.getString(key);
	}
	
	public static String getDataRootDir() {
		return getValue("datarootdir");
	}

	public static String getLocalDBUrl() {
		return getValue("localdburl");
	}

	public static String getLocalDBUserName() {
		return getValue("localdbusername");
	}

	public static String getLocalDBPassword() {
		return getValue("localdbpassword");
	}

	public static String getLocalDBJDBCDriverName() {
		return getValue("localdbjdbcdrivername");
	}

	public static String getDateFormat() {
		return getValue("dateformat");
	}

	public static String getKeywordsDictionaryDir() {
		return getValue("keywordsdictionarydir");
	}

	public static String getDataObjDir() {
		return getValue("dataobjdir");
	}

	public static String getDataCodeObjDir() {
		return getValue("datacodeobjdir");
	}

	public static String getDataDocDir() {
		return getValue("datadocdir");
	}

	public static String getLexicalModelFile() {
		return getValue("lexicalmodelfile");
	}

	public static void main(String[] args) {
		System.out.println(getDataRootDir());
		System.out.println(getLocalDBJDBCDriverName());
		System.out.println(getLocalDBPassword());
		System.out.println(getLocalDBUrl());
		System.out.println(getLocalDBUserName());
		System.out.println(getDateFormat());
		System.out.println(getDataDocDir());
		System.out.println(getDataObjDir());
		System.out.println(getLexicalModelFile());
		System.out.println(getKeywordsDictionaryDir());
	}
}
