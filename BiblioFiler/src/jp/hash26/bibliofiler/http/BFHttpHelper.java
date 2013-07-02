package jp.hash26.bibliofiler.http;

import java.io.ByteArrayOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;


// http://d.hatena.ne.jp/language_and_engineering/20111121/p1

public class BFHttpHelper extends AsyncTask<Object, Object, Object> {

	public void start() {
		execute(new Object());
	}

	private void parseJson(String str) {
		Log.d("BF", str);

		try {
			JSONObject rootObject = new JSONObject(str);
			JSONArray itemsArray = rootObject.getJSONArray("Items");

			for (int i = 0; i < itemsArray.length(); i++) {
				JSONObject items = itemsArray.getJSONObject(i);
				JSONObject item = items.getJSONObject("Item");
				String title = item.getString("title");
				Log.d("title", title);
			}

		} catch (JSONException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	@Override
	protected Object doInBackground(Object... params) {

		HttpClient httpClient = new DefaultHttpClient();
		String url = null;
		HttpGet request = new HttpGet(url);
		HttpResponse httpResponse = null;

		try {
			httpResponse = httpClient.execute(request);
		} catch (Exception e) {
			Log.d("HttpSampleActivity", "Error Execute");
			Log.d("HttpSampleActivity", e.toString());
		}

		int status = httpResponse.getStatusLine().getStatusCode();

		if (HttpStatus.SC_OK == status) {
			try {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				httpResponse.getEntity().writeTo(outputStream);
				parseJson(outputStream.toString());
			} catch (Exception e) {
				Log.d("HttpSampleActivity", "Error");
			}

		} else {
			Log.d("HttpSampleActivity", "Status" + status);

		}
		return status;
	}
}
