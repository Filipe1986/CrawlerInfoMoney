import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.*;

public class main {

	public static void main(String[] args) throws IOException {

		int numPages = 3;
		String url = "www.infomoney.com.br/";
		String date = "21/08/20";
		String categoria = "mercados";
		
		WebCrawler wc = new WebCrawler();

		for (int i = 1; i < numPages + 1; i++) {
			JSONArray infomoneylinksBypage = Rest.postInfomoneyLatestNewsPagelinks(url, date, categoria, i);
			infomoneylinksBypage.forEach(newsLink ->{
				wc.getPageInfo(newsLink.toString());
			});
			
		}
	}
}
