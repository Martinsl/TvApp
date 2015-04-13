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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText user;
	private EditText password;
	private static HttpParams 			httpParameters;
	private static DefaultHttpClient 	httpClient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		user = (EditText) findViewById(R.id.user);
		password = (EditText) findViewById(R.id.password);
		
		Log.v("teste", "ok");
		httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				10000);
		HttpConnectionParams.setSoTimeout(httpParameters, 20000);
		httpClient = new DefaultHttpClient(httpParameters);
		
		new makeGetRequest(this).execute("http://api-tv-program.herokuapp.com/get-tv-program/MDO/13-04-2015");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

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
	
	public void loginOnClick(View v) {
		String informedUser = user.getText().toString();
		String informedPass = password.getText().toString();
		
		if(informedUser.equals("user") && informedPass.equals("123")) {
			startActivity(new Intent(this, HomeActivity.class));
		} else {
			String errorMessage = getString(R.string.auth_error);
			Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
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
			this.dialog.setMessage("Retrieving Donations List");
			this.dialog.show();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			if (dialog.isShowing())
				dialog.dismiss();
		}

		@Override
		protected String doInBackground(String... params) {
			
			String result = "";
			try {
				HttpGet getRequest = new HttpGet("http://api-tv-program.herokuapp.com/get-tv-program/MDO/13-04-2015");
				getRequest.setHeader("accept", "application/json");
				//getRequest.setHeader("accept","text/plain");
				HttpResponse response = httpClient.execute(getRequest);
				result = getResult(response).toString();
				Log.v("Response of GET request", result);
				
			} catch (Exception e) {
				Log.v("TVAPP","ASYNC ERROR" + e.getMessage());
			}
			return result;
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
