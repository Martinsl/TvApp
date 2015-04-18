package com.example.tvapp;



import java.util.Calendar;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import persistence.*;
import android.widget.AdapterView.OnItemClickListener;
import app.models.Rest;

public class FavoritesActivity extends Base {

	private ListView listView;
	private DatabaseHandler dbHelper;
	private SimpleCursorAdapter dataAdapter;
	private Cursor cursor;
	String date;
	String programAcron;
	String searchURL;
	String channel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);

		dbHelper = new DatabaseHandler(this);
		dbHelper.open(this);
		//dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 0, 0);

		/** for tests
		Favorites exp = new Favorites("HBO","Game of Thrones - Season 5","20:00","25/04/2015");
		dbHelper.addFavorites(exp);
		dbHelper.addFavorites(exp);
		dbHelper.addFavorites(exp);
		dbHelper.addFavorites(exp);
		dbHelper.addFavorites(exp);
		dbHelper.addFavorites(exp);
		 **/

		//displayListView();
		//refreshData();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		displayListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	 **/
	//METHOD WHICH WILL HANDLE DYNAMIC INSERTION

	private void displayListView() {


		cursor = dbHelper.fetchAllFavorites();

		// The desired columns to be bound
		String[] columns = new String[] {
				DatabaseHandler.KEY_CHANNELNAME,
				//DatabaseHandler.KEY_PROGRAMNAME,
				//DatabaseHandler.KEY_TIME,
				//DatabaseHandler.KEY_DATE
		};

		// the XML defined views which the data will be bound to
		int[] to = new int[] { 
				R.id.fav_channelName,
				//R.id.fav_programName,
				//R.id.fav_time,
				//R.id.fav_date
		};

		// create the adapter using the cursor pointing to the desired data 
		//as well as the layout information

		dataAdapter = new SimpleCursorAdapter(
				this, R.layout.row_favorites, 
				cursor, 
				columns, 
				to,
				0);

		listView = (ListView) findViewById(R.id.fav_list);
		// Assign adapter to ListView

		if (listView == null) {
			Log.d("listeviwe", "null");
		}

		listView.setAdapter(dataAdapter);
		listView.setVisibility(View.VISIBLE);



		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view, 
					int position, long id) {
				
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				channel = cursor.getString(1);
				programAcron = acronymHash.get(channel);
				date  =  formatDate();
				searchURL = baseURL + "get-tv-program/" + programAcron + "/" +date;
				new makeGetRequest(listView.getContext()).execute(searchURL);
				/** Get the cursor, positioned to the corresponding row in the result set
			  
			   //Favorites exp = new Favorites(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getDouble(3));
			   Intent intent = new Intent(FavoritesActivity.this, DisplayListActivity.class);
			   Bundle bundle = new Bundle();
			   bundle.putInt("id", Integer.parseInt(cursor.getString(0)));
			   bundle.putString("desc", cursor.getString(1));
			   bundle.putString("dt", cursor.getString(2));
			   bundle.putDouble("am", cursor.getDouble(3));
			   intent.putExtras(bundle);		   
			   startActivity(intent);**/	 
			}
		});
		refreshData();
	}



	public void refreshData(){
		cursor.requery();
		dataAdapter.notifyDataSetChanged();

	}
	
	public String formatDate(){
		Calendar calendar = Calendar.getInstance();
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		int monthOfYear = calendar.get(Calendar.MONTH);
		int year =calendar.get(Calendar.YEAR);

		return  (dayOfMonth<10?("0"+dayOfMonth):(dayOfMonth))
				+ "-" + (monthOfYear<10?("0"+(monthOfYear+1)):(monthOfYear+1))
				+ "-" + year;
		
	}
	
	public class makeGetRequest extends AsyncTask<String, Void, String> {

		protected ProgressDialog 		dialog;
		protected Context 				context;

		public makeGetRequest(Context context)
		{
			this.context = context;
		}

		public makeGetRequest(OnItemClickListener onItemClickListener) {
			// TODO Auto-generated constructor stub
		}

		public makeGetRequest(AdapterView<?> listView) {
			// TODO Auto-generated constructor stub
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
			intent.putExtra("Date", date);
			intent.putExtra("channelName", channel);
			intent.putExtra("acron", programAcron);
			startActivity(intent);
		}
	}
}

