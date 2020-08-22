import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;

public class WebCrawler {

	private HashSet<String> links;

	public WebCrawler() {
		links = new HashSet<String>();
	}

	public void getPageInfo(String URL) {
		if (!links.contains(URL)) {
			try {

				if (links.add(URL)) {
					System.out.println("URL: " + URL);
				}

				Document document = Jsoup.connect(URL).get();
				
				String formattedAuthor = formatAuthor(document.getElementsByClass("author-name").text());
				String formattedTime = formatDate(document);
				String formattedContent = formatContent(document);
				
				
				System.out.println("título: " 		+ document.getElementsByClass("page-title-1").text());
				System.out.println("subtítulo: " 	+ document.getElementsByClass("article-lead").text());
				System.out.println("Autor: " 		+ formattedAuthor);
				System.out.println("Data: " 		+ formattedTime);
				System.out.println("Conteúdo: " 	+ formattedContent);

			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
			} 
		}
	}


	private String formatContent(Document document) {
		Elements elements = document.getElementsByClass("article-content");
		Document doc = Jsoup.parse(elements.toString());
		String elementsP = doc.select("p").text();
		
		String [] arrayContent = elementsP.split("Leia também:");
		return arrayContent[0].split("Curso gratuito do InfoMoney")[0];
		
    }


	private String formatDate(Document document) {
		String notFormattedDate = document.getElementsByClass("entry-date").attr("datetime");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = null;
		
		try {
			date = sdf.parse(notFormattedDate);
		} catch (ParseException e) {
			return "Data em formato inválido";
		}
		
		return  output.format(date);
	}
	
	private String formatAuthor(String author) {
		
		return author.replace("Por ", "");
	}

}