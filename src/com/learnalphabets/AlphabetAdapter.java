package com.learnalphabets;

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
			

			String mobile = alphabets[position];

			if (mobile.equals("apple")) {
				flag.setImageResource(R.drawable.apple);
				
			} else if (mobile.equals("ant")) {
				flag.setImageResource(R.drawable.ant);
			} else if (mobile.equals("airplane")) {
				flag.setImageResource(R.drawable.airplane);
			} else if (mobile.equals("alligator")) {
				flag.setImageResource(R.drawable.alligator);
			} else if (mobile.equals("arrow")) {
				flag.setImageResource(R.drawable.arrow);
			} else if (mobile.equals("arm")) {
					flag.setImageResource(R.drawable.arm);
			} else if (mobile.equals("ball")) {
				flag.setImageResource(R.drawable.ball);
		  } else if (mobile.equals("banana")) {
				flag.setImageResource(R.drawable.banana);
		  } else if (mobile.equals("boy")) {
				flag.setImageResource(R.drawable.boy);
		  } else if (mobile.equals("bell")) {
				flag.setImageResource(R.drawable.bell);
		  } else if (mobile.equals("boat")) {
				flag.setImageResource(R.drawable.boat);
		  } else if (mobile.equals("book")) {
				flag.setImageResource(R.drawable.book);
		  }else{
				flag.setImageResource(R.drawable.def);
		  }
			
			//int i = context.getResources().getIdentifier(mobile, "drawable", this.getPackageName());

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