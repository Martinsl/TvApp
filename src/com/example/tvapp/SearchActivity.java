package com.example.tvapp;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import app.models.Rest;

public class SearchActivity extends Base {

	Calendar calendar = Calendar.getInstance();
	TextView display;
	AutoCompleteTextView channelNameField;
	String date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("New Search");
		setContentView(R.layout.activity_search);

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
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);

				new DatePickerDialog(SearchActivity.this, listener, year, month, day).show();
			}
		});
	}

	DatePickerDialog.OnDateSetListener listener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			//http://stackoverflow.com/questions/10203924/displaying-date-in-a-double-digit-format
			date = (dayOfMonth<10?("0"+dayOfMonth):(dayOfMonth))
					+ "-" + (monthOfYear<10?("0"+(monthOfYear+1)):(monthOfYear+1))
					+ "-" + year;

			display.setVisibility(View.VISIBLE);
			display.setText("Selected date: " + date);
		}
	};

	public void searchOnClick(View v) {
		String programName = channelNameField.getText().toString();
		String programAcron = acronymHash.get(programName);
		String searchURL = baseURL + "get-tv-program/" + programAcron + "/" + date;

		if(programAcron == null || date == null){
			if (programAcron == null){
				Toast.makeText(this, "Please, type a valid channel.", Toast.LENGTH_SHORT).show();
			} else if(date == null ) {
				Toast.makeText(this, "Please, select a valid date", Toast.LENGTH_SHORT).show();
			}
		} else{
			Toast.makeText(this, searchURL, Toast.LENGTH_SHORT).show();

			new makeGetRequest(this).execute(searchURL);
		}
	}

	private class makeGetRequest extends AsyncTask<String, Void, String> {

		protected ProgressDialog 		dialog;
		protected Context 				context;

		public makeGetRequest(Context context)
		{
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();	
			this.dialog = new ProgressDialog(context, 1);
			this.dialog.setMessage("Retrieving List");
			this.dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			String result = "";
			try {
				Rest.setup();
				result = Rest.get(params[0]);
				Log.v("Response of GET request", result);
			} catch (Exception e) {
				Log.v("TVAPP","ASYNC ERROR" + e.getMessage());
				finish();
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (dialog.isShowing())
				dialog.dismiss();
			Intent intent = new Intent(getBaseContext(), DisplayListActivity.class);
			intent.putExtra("ChannelsJson", result);
			startActivity(intent);
		}
	}
}