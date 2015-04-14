package com.example.tvapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Base {

	private EditText user;
	private EditText password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		user = (EditText) findViewById(R.id.user);
		password = (EditText) findViewById(R.id.password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflate the menu; this adds items to the action bar if it is present.
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
		
		if(informedUser.equals("") && informedPass.equals("")) {
			startActivity(new Intent(this, HomeActivity.class));
		} else {
			String errorMessage = getString(R.string.auth_error);
			Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
		}
	}
}