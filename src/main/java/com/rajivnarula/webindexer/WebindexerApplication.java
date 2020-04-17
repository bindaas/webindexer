package com.rajivnarula.webindexer;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebindexerApplication {

	public static void main(String[] args) throws Exception {
		System.out.println ("Yo !"+args[0]) ;
		SpringApplication.run(WebindexerApplication.class, args);
		SiteMapParser smp = new SiteMapParser(args[0]);
		List <String> urls = smp.getURLs();
		
		for (String uri :urls){
			System.out.println("Extracting ->"+ uri);
			WebExtractor webExtractor = new WebExtractor (uri);
			Payload payload = webExtractor.getPayload();
			System.out.println("Indexing ->"+ uri);
			ElasticSearchIndexer esi = new ElasticSearchIndexer(payload) ;
			esi.index();
		}
	}

}