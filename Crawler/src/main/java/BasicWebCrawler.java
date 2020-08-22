import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;

public class BasicWebCrawler {

	private HashSet<String> links;

	public BasicWebCrawler() {
		links = new HashSet<String>();
	}

	public void getPageInfo(String URL) {
		if (!links.contains(URL)) {
			try {

				if (links.add(URL)) {
					System.out.println("URL: " + URL);
				}

				Document document = Jsoup.connect(URL).get();
				System.out.println("título: " + document.getElementsByClass("page-title-1").text());
				System.out.println("subtítulo: " + document.getElementsByClass("article-lead").text());
				System.out.println("Autor: " + document.getElementsByClass("author-name").text());
				System.out.println("Data: " + document.getElementsByClass("entry-date").text());
				System.out.println("Conteúdp: " + document.getElementsByClass("article-content").text());

			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
			}
		}
	}

}