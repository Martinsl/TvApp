package com.example.tvapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import app.models.Program;
import app.models.Programs;
import app.models.Rest;
import app.models.TvProgram;

import com.google.gson.Gson;

public class DisplayListActivity extends Base {

	ListView listView;
	String date;
	String programTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String channelJson = extras.getString("ChannelsJson");
			date = extras.getString("Date");
			
			Gson gson = new Gson();
			final TvProgram channelG = gson.fromJson(channelJson, TvProgram.class);
			
			listView = (ListView) findViewById(R.id.channelSchedule);
		    ChannelAdapter adapter = new ChannelAdapter(this, channelG);
		    listView.setAdapter(adapter);
		    
		    listView.setOnItemClickListener(new OnItemClickListener() {

	            @Override
	            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	                    long arg3) {
	                // TODO Auto-generated method stub
	            	String programLink = channelG.programs.get(arg2).link;
	            	programTime = channelG.programs.get(arg2).time;
	            	
	        		String searchURL = baseURL + "get-program/" + programLink;
	        		new makeGetRequest(DisplayListActivity.this).execute(searchURL, programTime);	
	            }

	        });
		} else { 
			Toast.makeText(this, "Error populating list", Toast.LENGTH_SHORT).show();
			 finish();
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
				result = Rest.get(params[0]);
				Log.v("Response of GET request", result);
			} catch (Exception e) {
				Log.v("TVAPP","ASYNC ERROR" + e.getMessage());
			}
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			if (dialog.isShowing())
				dialog.dismiss();

			Intent intent = new Intent(getBaseContext(), ProgramActivity.class);
			intent.putExtra("ProgramJson", result);
			intent.putExtra("Date", date);
			intent.putExtra("Time", programTime);
			startActivity(intent);
		}
	}
}

class ChannelAdapter extends ArrayAdapter<TvProgram> implements OnClickListener {

	private Context context;
	public  TvProgram programsList;

	public ChannelAdapter(Context context, TvProgram programsList) {
		super(context, R.layout.row_display);
	    this.context   = context;
	    this.programsList = programsList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	    LayoutInflater inflater = (LayoutInflater) context
	    		.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	    View view = inflater.inflate(R.layout.row_display, parent, false);
	    Programs program = programsList.programs.get(position);
	    TextView nameView = (TextView) view.findViewById(R.id.program_name);
	    TextView timeView = (TextView) view.findViewById(R.id.program_time);
	    
	    nameView.setText(program.name);
	    timeView.setText(program.time);
	    return view;
	}

	@Override
	public int getCount() {
	    return programsList.programs.size();
	}

	@Override
	public void onClick(View v) {		
	}
}