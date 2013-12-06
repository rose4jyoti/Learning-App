package com.learnalphabets.extras;

import java.util.ArrayList;

public class BeanClass {
	
	private static String alphabetPos;
	private static ArrayList<String> arrayListValue;
	
	public static void setAlphabetSelected(String alphabet){
		
		alphabetPos = alphabet;
	}
	
	public static String getAlphabetSelected(){
	
	return alphabetPos;
	}
	
	public static void setArrayList(ArrayList<String> arrayList){
		
		arrayListValue = arrayList;
	}
	public static ArrayList<String> getArrayList(){
		
		return arrayListValue;
		}

}
