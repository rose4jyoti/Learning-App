package com.learnalphabets;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.learnalphabet.database.DataBaseAdapter;
import com.learnalphabets.extras.BeanClass;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class AlphabetAdapter extends BaseAdapter {
	private Context context;
	private final String[] alphabets;
	 private String[] sixAlphabets = new String[6]; 

	public AlphabetAdapter(Context context, String[] alphabets) {
		this.context = context;
		this.alphabets = alphabets;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			gridView = inflater.inflate(R.layout.custom_six_alphabets, null);

			TextView textView = (TextView) gridView.findViewById(R.id.label);
			
			textView.setText(alphabets[position]);

			ImageView flag = (ImageView) gridView .findViewById(R.id.flag);
			

			String oneAlphabet = alphabets[position];
	  	
	  	/*
	  	 * get the six alphabet name from database 
	  	 */
	  	ArrayList arrayList = BeanClass.getArrayList();
			for(int i=0; i<arrayList.size(); i++){
			
				String data = (String) arrayList.get(i);
				sixAlphabets[i] = data;
				
			}
			
			
			
			if (oneAlphabet.equals(sixAlphabets[0])) {
				int id1  = context.getResources().getIdentifier(sixAlphabets[0] ,"drawable", AlphabetAdapter.class.getPackage().getName());
				flag.setImageResource(id1);
			} else if (oneAlphabet.equals(sixAlphabets[1])) {
				int id2  = context.getResources().getIdentifier(sixAlphabets[1] ,"drawable", AlphabetAdapter.class.getPackage().getName());
				flag.setImageResource(id2);
			} else if (oneAlphabet.equals(sixAlphabets[2])) {
				int id3  = context.getResources().getIdentifier(sixAlphabets[2] ,"drawable", AlphabetAdapter.class.getPackage().getName());
				flag.setImageResource(id3);
			} else if (oneAlphabet.equals(sixAlphabets[3])) {
				int id4 = context.getResources().getIdentifier(sixAlphabets[3] ,"drawable", AlphabetAdapter.class.getPackage().getName());
				flag.setImageResource(id4);
			} else if (oneAlphabet.equals(sixAlphabets[4])) {
				int id5 = context.getResources().getIdentifier(sixAlphabets[4] ,"drawable", AlphabetAdapter.class.getPackage().getName());
				flag.setImageResource(id5);
			} else if (oneAlphabet.equals(sixAlphabets[5])) {
				int id6 = context.getResources().getIdentifier(sixAlphabets[5] ,"drawable", AlphabetAdapter.class.getPackage().getName());
				flag.setImageResource(id6);
			} else{
				flag.setImageResource(R.drawable.def);
		    }
			
			

		} else {
			gridView = (View) convertView;
		}

		return gridView;
	}
	
	@Override
	public int getCount() {
		return alphabets.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}