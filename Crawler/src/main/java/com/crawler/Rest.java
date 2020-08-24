package com.crawler;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Rest {
	static JSONArray postInfomoneyLatestNewsPagelinks(String url, String date, String categoria, int numPage) {
		
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
		String bodyContent = "action=infinite_scroll" + "&order=DESC" + "&page=" + numPage + "&currentday=" + date + "&query_args[category_name]="+ categoria;
		RequestBody body = RequestBody.create(bodyContent, mediaType);
		
		Request request = new Request.Builder().url("https://" + url + "?infinity=scrolling").method("POST", body)
				.addHeader("authority", url)
				.addHeader("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36")
				.addHeader("x-requested-with", "XMLHttpRequest")
				.addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
				.addHeader("accept", "*/*")
				.addHeader("origin", "https://" + url)
				.addHeader("sec-fetch-site", "same-origin")
				.addHeader("sec-fetch-mode", "cors")
				.addHeader("sec-fetch-dest", "empty")
				.addHeader("referer", "https://" + url)
				.addHeader("accept-language", "en-US,en;q=0.9")
				.build();
		Response response = null;
		JSONObject myObject = null;
		
		try {
			response = client.newCall(request).execute();
			myObject = new JSONObject(response.body().string());
		} catch (JSONException | IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return myObject.getJSONObject("postflair").names();
	}
	
}
