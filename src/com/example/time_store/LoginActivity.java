package com.example.time_store;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


class UserLoginTask extends AsyncTask<String, Void, String> {
	private Exception exception;
	
	protected String doInBackground(String... para){
		String username = para[0];
		String password = para[1];
		String url_str = para[2];
		Log.d("NET", username);
		Log.d("NET", password);
		Log.d("NET", url_str);
		URL url = null;
		try {
			url = new URL(url_str);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setUseCaches(false);
			urlConnection.setRequestProperty("Content-Type", "application/json");
			//urlConnection.setRequestProperty("Charset", "utf-8");
			
			urlConnection.connect();
			
			DataOutputStream dop = new DataOutputStream(urlConnection.getOutputStream());
			JSONObject jsonPara = new JSONObject();
			jsonPara.put("username", username);
			jsonPara.put("password", password);
			dop.writeBytes(jsonPara.toString());
			dop.flush();
			dop.close();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String result = "";
			String readLine = null;
			String s = null;
			while ((readLine=bufferedReader.readLine())!=null) {
				result += readLine;
			}
			bufferedReader.close();
			urlConnection.disconnect();
			Log.d("NET", result);
			return result;
		} 
		catch (MalformedURLException e) {
			Log.e("TIME", "Uncaught exception", e);
		}
		catch (IOException e) {
			Log.e("TIME", "Uncaught exception", e);
		}
		catch (JSONException e) {
			Log.e("TIME", "Uncaught exception", e);
		}
		return null;
	}
	

	protected void onPostExcuete(String result) {
		Log.d("NET", "Register success");
		Log.d("NET", result);
	}

	
}



public class LoginActivity extends ActionBarActivity {

	UserLoginTask userLoginTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		
		final EditText username = (EditText)findViewById(R.id.login_username);
		final EditText password = (EditText)findViewById(R.id.login_password);
		Button submit = (Button)findViewById(R.id.login_submit);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username_str = username.getText().toString();
				String password_str = password.getText().toString();
				//Toast.makeText(RegisterActivity.this, username_str+" "+password_str, Toast.LENGTH_SHORT).show();
				String Url_str = "http://104.236.159.207:5000/time_store/api/login";
				userLoginTask = new UserLoginTask();
				userLoginTask.execute(username_str,password_str,Url_str);
				
					
			}
		});
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
}
