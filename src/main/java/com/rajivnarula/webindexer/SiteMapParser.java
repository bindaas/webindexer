package com.rajivnarula.webindexer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SiteMapParser {

	private List<String> URLs = new ArrayList <String>();

	public SiteMapParser(String siteMapXML) throws Exception {
		File siteMapFile = new File (siteMapXML);
	
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(siteMapFile);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("url");
		System.out.println("Found "+ nList.getLength() +" URLs");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String url = eElement.getElementsByTagName("loc").item(0).getTextContent() ;
				//System.out.println("URL : " + url);
				URLs.add(url.trim()) ;
			}
		}

	}

	public List<String> getURLs(){
		return URLs ;
	}

}
