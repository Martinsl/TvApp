package com.example.tvapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import app.models.Program;

import com.google.gson.Gson;

public class ProgramActivity extends Activity {

	private TextView nameView;
	private TextView descriptionView;
	private TextView ageRatingView;
	private TextView detailsView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_program);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			
			nameView = (TextView) findViewById(R.id.program_name);
			descriptionView = (TextView) findViewById(R.id.program_description);
			ageRatingView = (TextView) findViewById(R.id.age_rating);
			detailsView = (TextView) findViewById(R.id.program_details);
			
			String programJson = extras.getString("ProgramJson");
			//Log.v("Activity Params", programJson);
			Gson gson = new Gson();
			final Program programGson = gson.fromJson(programJson, Program.class);
			Log.v("££££££££", programGson.description);
			
			nameView.setText(programGson.name);
			descriptionView.setText(programGson.description);
			ageRatingView.setText(programGson.ageRating);
			detailsView.setText(programGson.details);
		}
	}
}
