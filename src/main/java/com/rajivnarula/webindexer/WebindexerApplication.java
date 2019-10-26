package com.rajivnarula.webindexer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebindexerApplication {

	public static void main(String[] args) {
		System.out.println ("Yo !"+args[0]) ;
		SpringApplication.run(WebindexerApplication.class, args);
	}

}
