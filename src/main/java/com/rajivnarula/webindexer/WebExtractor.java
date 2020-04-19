package com.rajivnarula.webindexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebExtractor {

    private Payload payload ;
    public Payload getPayload (){
        return payload ;
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
        Element titleElement = doc.getElementsByTag("title").first() ;

        Elements elements = doc.getElementsByClass("index-me");

        payload = new Payload (titleElement.text() ,uri , elements.text()) ;
        Timestamp t = new Timestamp(System.currentTimeMillis());
        long timestamp = t.getTime();
        File file = new File(timestamp+"_payload.json");
        if (file.createNewFile()){
            FileWriter writer = new FileWriter(file);
            writer.write(payload.toJSON());
            writer.close();

        }else{
            throw new RuntimeException("Too fast ???");
        }

    }

}