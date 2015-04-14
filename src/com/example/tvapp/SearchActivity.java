package com.example.tvapp;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class SearchActivity extends Base {
	
	Calendar calendar = Calendar.getInstance();
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("New Search");
		setContentView(R.layout.activity_search);
		
		AutoCompleteTextView channelNameField;
		channelNameField = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

		String[] channels = getResources().getStringArray(R.array.channel_names);
		ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,channels);
		channelNameField.setAdapter(adapter);
				   
		display = (TextView) findViewById(R.id.date_display);
		Button dateButton = (Button) findViewById(R.id.button_datePicker);
		dateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;
				int day = calendar.get(Calendar.DAY_OF_MONTH);

				new DatePickerDialog(SearchActivity.this, listener, year, month, day).show();
			}
		});
	}

	DatePickerDialog.OnDateSetListener listener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			display.setVisibility(View.VISIBLE);
			display.setText("Selected date: " + dayOfMonth + "/" + monthOfYear + "/" + year);
		}
	};
}