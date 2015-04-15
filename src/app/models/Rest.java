package app.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;


public class Rest {

	private static HttpParams 			httpParameters;
	private static DefaultHttpClient 	httpClient;
	
	public static void setup() {
		httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				10000);
		HttpConnectionParams.setSoTimeout(httpParameters, 20000);
		httpClient = new DefaultHttpClient(httpParameters);
	}
	
	public static String get(String url) {
		String result = "";
		try {
			Log.v("Params", url);
			Rest.setup();
			HttpGet getRequest = new HttpGet(url);
			getRequest.setHeader("accept", "application/json");
			//getRequest.setHeader("accept","text/plain");
			HttpResponse response = httpClient.execute(getRequest);
			result = getResult(response).toString();
		} catch (Exception e) {
			Log.v("REST","ASYNC ERROR" + e.getMessage());
		}
		return result;
	}
	
	private static StringBuilder getResult(HttpResponse response)
			throws IllegalStateException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())), 1024);
		String output;
		while ((output = br.readLine()) != null)
			result.append(output);

		return result;
	}

	public static void shutDown() {
		httpClient.getConnectionManager().shutdown();
	}
}
