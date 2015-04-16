package com.example.tvapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import app.models.Program;

import com.google.gson.Gson;

public class ProgramActivity extends Activity {

	private TextView nameView;
	private TextView descriptionView;
	private TextView ageRatingView;
	private TextView detailsView;
	private String eventDate;
	private String eventName;
	private int eventDay;
	private int eventMonth;
	private int eventYear;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_program);
		
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			nameView = (TextView) findViewById(R.id.program_name);
			descriptionView = (TextView) findViewById(R.id.program_description);
			//ageRatingView = (TextView) findViewById(R.id.age_rating);
			//detailsView = (TextView) findViewById(R.id.program_details);
			
			String programJson = extras.getString("ProgramJson");
			Log.v("Activity Params", programJson);
			eventDate = extras.getString("Date");
			
			Gson gson = new Gson();
			final Program programGson = gson.fromJson(programJson, Program.class);
			Log.v("��������", programGson.Description);
			eventName = programGson.Name;
			
			formatDate();
			
			nameView.setText(programGson.Name);
			descriptionView.setMovementMethod(new ScrollingMovementMethod());
			descriptionView.setText(programGson.Description);
			
			//ageRatingView.setText(programGson.ageRating);
			//detailsView.setText(programGson.details);
		} else { 
			Toast.makeText(this, "Error populating list", Toast.LENGTH_SHORT).show();
			 finish();
		}
	}

	public void calendarOnClick(View v) {
		
		 Calendar cal = Calendar.getInstance();
		 cal.set(Calendar.DAY_OF_MONTH, eventDay);
		 cal.set(Calendar.MONTH, eventMonth);
		 cal.set(Calendar.YEAR, eventYear);
		 
		 Intent intent = new Intent(Intent.ACTION_EDIT);
		 intent.setType("vnd.android.cursor.item/event");
		 intent.putExtra("beginTime", cal.getTimeInMillis());
		 intent.putExtra("allDay", false);
		 //intent.putExtra("rrule", "FREQ=YEARLY");
		 intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
		 intent.putExtra("title", eventName);
		 startActivity(intent);
	}
	
	public void formatDate(){
		eventDay = Integer.parseInt(eventDate.substring(0,2));
		eventMonth = Integer.parseInt(eventDate.substring(3,5));
		eventYear = Integer.parseInt(eventDate.substring(6));
	}
}
