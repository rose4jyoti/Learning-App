package com.learnalphabets.extras;

import java.util.ArrayList;

public class BeanClass {
	
	private static String alphabetPos;
	private static boolean alphabetSound = true;
	private static boolean bgSound = false;
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

  public static void setAlphabetSound(boolean alphabet_sound){
		
		alphabetSound = alphabet_sound;
	}
	
	public static boolean getAlphabetSound(){
	
	return alphabetSound;
	}
  public static void setBgSound(boolean bg_sound){
		
		bgSound = bg_sound;
	}
	
	public static boolean getBgSound(){
	
	return bgSound;
	}
}
