package com.crawler;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.*;

public class main {

	public static void main(String[] args){

		int numPages = 3;
		String url = "www.infomoney.com.br/";
		LocalDate localDate = LocalDate.now();
		String date = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
		String categoria = "mercados";
		
		WebCrawler wc = new WebCrawler();

		for (int i = 1; i < numPages + 1; i++) {
			JSONArray infomoneylinksBypage = null;
			infomoneylinksBypage = Rest.postInfomoneyLatestNewsPagelinks(url, date, categoria, i);
			
			infomoneylinksBypage.forEach(newsLink ->{
				wc.getPageInfo(newsLink.toString());
			});
			
		}
	}
}
