package com.learnalphabets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class AlphabetAdapter  extends BaseAdapter {
	private Context context;
	private final String[] countries;

	public AlphabetAdapter(Context context, String[] countries) {
		this.context = context;
		this.countries = countries;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			gridView = inflater.inflate(R.layout.custom_six_alphabets, null);

			TextView textView = (TextView) gridView.findViewById(R.id.label);
			
			textView.setText(countries[position]);

			ImageView flag = (ImageView) gridView .findViewById(R.id.flag);

			String mobile = countries[position];

			if (mobile.equals("Apple")) {
				flag.setImageResource(R.drawable.apple);
			} else if (mobile.equals("Aeroplane")) {
				flag.setImageResource(R.drawable.airplane);
			} else if (mobile.equals("Ant")) {
				flag.setImageResource(R.drawable.ant);
			} else if (mobile.equals("Alligator")) {
				flag.setImageResource(R.drawable.alligator);
			} else if (mobile.equals("Arrow")) {
				flag.setImageResource(R.drawable.arrow);
			} else {
				flag.setImageResource(R.drawable.arm);
			}

		} else {
			gridView = (View) convertView;
		}

		return gridView;
	}

	@Override
	public int getCount() {
		return countries.length;
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