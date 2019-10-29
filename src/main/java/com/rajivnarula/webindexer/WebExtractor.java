package com.rajivnarula.webindexer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebExtractor {

    private IndexSchema indexSchema ;
    public IndexSchema getIndexSchema (){
        return indexSchema ;
    }

    public WebExtractor(String uri) throws Exception {
        URL url = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        String html = content.toString();

        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByClass("accordion-body__contents");
        indexSchema = new IndexSchema (uri , elements.text()) ;
        //System.out.println(indexSchema.toJSON());
    }

}