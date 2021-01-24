package com.compare.bl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HtmlParser {

	public static void parseAliExpress(String keywords) throws IOException {
		String url = "https://aliexpress.com/wholesale?&SearchText=" + keywords;
//		RestTemplate rTemplate = new RestTemplate();
//		ResponseEntity<?> response2 = rTemplate.getForEntity(url, String.class);
//		Document doc = Jsoup.parse(response2.getBody().toString());
		Document doc = Jsoup.connect(url).get();

//		File f = new File("c:\\tmp\\doc.txt");
//		FileWriter fw = new FileWriter(f);
//		fw.write(doc.toString());
//		fw.close();

		Element items = doc.getElementsByClass("list-items").first();
		// Element items = doc.selectFirst("ul.list-items");
		System.out.println(items);
	}

	@SuppressWarnings("unchecked")
	public static String parseAsos(String keywords){
		String url = "https://www.asos.com/search/?q=" + keywords;
		try {
		Document doc = Jsoup.connect(url).get();

		Element itemsDiv = doc.getElementsByAttributeValue("data-auto-id", "productList").first();	
		Element section = itemsDiv.getElementsByTag("section").first();
		Elements articles = section.getElementsByTag("article");
		JSONArray itemsArr = new JSONArray();
		for (Element elem : articles) {
			JSONObject item = new JSONObject();
			Element img = elem.getElementsByTag("img").first(); // .attr("src");
			Element title = elem.getElementsByTag("p").first();// .html();
			if (img != null && title != null) {
				item.put("link", elem.getElementsByTag("a").first().attr("href"));
				item.put("title", title.html());
				item.put("img", "https:" + img.attr("src"));
				item.put("price", elem.getElementsByTag("p").get(1).getElementsByTag("span").get(1).html());
				itemsArr.add(item);
			}
		}

		
		File f = new File("c:\\tmp\\asos.json");
		FileWriter fw = new FileWriter(f);
		fw.write(itemsArr.toString());
		fw.close();	

		return itemsArr.toString();
		}catch(IOException ex) {
			System.out.println("Damn it! " + ex.getMessage());
		}
		
		return "error!";
	}
	
	@SuppressWarnings("unchecked")
	public static String parseNext(String keywords){
		String url = "https://www.next.co.il/he/search?w=" + keywords;
		try {
		Document doc = Jsoup.connect(url).get();

		Element itemsDiv = doc.getElementsByAttributeValue("data-pagenumber", "1").first();	
		Elements articles = itemsDiv.getElementsByTag("article");
		JSONArray itemsArr = new JSONArray();
		for (Element elem : articles) {
			JSONObject item = new JSONObject();
			Element img = elem.getElementsByClass("Images").first().getElementsByTag("img").first(); // .attr("src");
			Element title = elem.getElementsByClass("Title").first().getElementsByTag("a").first();// .html();
			if (img != null && title != null) {
				item.put("link", title.attr("href"));
				item.put("title", title.getElementsByTag("span").html());
				item.put("img", img.attr("src"));
				item.put("price", elem.getElementsByClass("price").first().getElementsByTag("a").html());
				itemsArr.add(item);
			}
		}

		
		File f = new File("c:\\tmp\\next.json");
		FileWriter fw = new FileWriter(f);
		fw.write(itemsArr.toString());
		fw.close();	

		return itemsArr.toString();
		}catch(IOException ex) {
			System.out.println("Damn it! " + ex.getMessage());
		}
		
		return "error!";
	}
}
