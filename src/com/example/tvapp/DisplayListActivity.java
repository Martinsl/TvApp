package com.example.tvapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

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
import app.models.Programs;
import app.models.TvProgram;

import com.google.gson.Gson;

public class DisplayListActivity extends Base {

	ListView listView;
	
	private static HttpParams 			httpParameters;
	private static DefaultHttpClient 	httpClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String channelJson = extras.getString("ChannelsJson");
			Log.v("Activity Params", channelJson);
			
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
	                Log.v("############","Items " + programLink);
	                
	            	httpParameters = new BasicHttpParams();
	        		HttpConnectionParams.setConnectionTimeout(httpParameters,
	        				10000);
	        		HttpConnectionParams.setSoTimeout(httpParameters, 20000);
	        		httpClient = new DefaultHttpClient(httpParameters);

	        		String searchURL = baseURL + "get-program/" + programLink;
	        		new makeGetRequest(DisplayListActivity.this).execute(searchURL);	
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
				HttpGet getRequest = new HttpGet(params[0]);
				getRequest.setHeader("accept", "application/json");
				//getRequest.setHeader("accept","text/plain");
				HttpResponse response = httpClient.execute(getRequest);
				result = getResult(response).toString();
				Log.v("Response of GET request", result);
				//programGson = gson.fromJson(result, TvProgram.class);
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
			//Intent intent = new Intent(getBaseContext(), DisplayListActivity.class);
			//intent.putExtra("ChannelsJson", result);
			//startActivity(intent);
		}

		
		private StringBuilder getResult(HttpResponse response)
				throws IllegalStateException, IOException {
			StringBuilder result = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())), 1024);
			String output;
			while ((output = br.readLine()) != null)
				result.append(output);

			return result;
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
		Log.v("Clicked row","Clicked");
		
	}
}