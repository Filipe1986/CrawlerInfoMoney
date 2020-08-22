import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.*;
public class main {

public static void main(String []args) throws IOException{
	 int numeroPages = 3;
	 String date = "21.08.20";
	 String url = "www.infomoney.com.br/";
	 for(int i= 1; i < numeroPages +1; i++) {
		  JSONArray infomoneylinksBypage = getInfomoneylinksBypage(url, date, i );
		 for (Object object : infomoneylinksBypage) {
			 new BasicWebCrawler().getPageLinks(object.toString());
		}
	 }
  }

	private static JSONArray getInfomoneylinksBypage(String url , String date, int numPage ) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder()
	      .build();
	    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
	    String bodyContent = "action=infinite_scroll"
	    		+ "&order=DESC"
	    		+ "&page=" 			+ numPage
	    		+ "&currentday=" 	+ date
	    		+ "&query_args[category_name]=mercados";
	    RequestBody body = RequestBody.create(bodyContent, mediaType);
	    Request request = new Request.Builder()
	      .url("https://" + url + "?infinity=scrolling")
	      .method("POST", body)
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
	      .addHeader("cookie", "_ga=GA1.3.379981790.1598021713; _gid=GA1.3.435452826.1598021713; _omappvp=BRacD3FKBlWpc13khHU7lkPwRC80XpIXTLk1VhgUzl0OAV3SrFrFVd3LnebOezTPfthWZMnh624bOaqX68Ywo0aFiI3IGmZb; _fbp=fb.2.1598021713444.420219971; __gads=ID=29aed2251625c544:T=1598021713:S=ALNI_Mb7Rktyp9anAZVWd8YjxZLQ58YVfA; nvg22862=c2512751d06198c3f6c8372df09|2_235; __hstc=254361559.3bab43dd30ab35c7639fb820f6db0d02.1598021714118.1598021714118.1598021714118.1; hubspotutk=3bab43dd30ab35c7639fb820f6db0d02; __hssrc=1; tt_c_vmt=1598021715; tt_c_c=direct; tt_c_s=direct; tt_c_m=direct; tt.u=0100007F86AB3E5FCD06FD63021D9A07; tt.nprf=; __qca=P0-1720626327-1598021715541; __hssc=254361559.19.1598021714119; _ttuu.s=1598026990309")
	      .build();
	    Response response = client.newCall(request).execute();
	    JSONObject myObject = new JSONObject(response.body().string());

	    return myObject.getJSONObject("postflair").names();
	}
}
