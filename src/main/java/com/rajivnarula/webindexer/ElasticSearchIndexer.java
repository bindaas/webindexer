package com.rajivnarula.webindexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class ElasticSearchIndexer {

    private Payload payload ;
    public  ElasticSearchIndexer (Payload payload){
        this.payload = payload ;
    }

	public void index () {

        try {

          UUID uuid = UUID.randomUUID();
          URL url = new URL("http://localhost:9200/qwerty/_doc/"+uuid.toString());
          System.out.println("indexing to "+url.toString());
          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
          conn.setDoOutput(true);
          conn.setRequestMethod("POST");
          conn.setRequestProperty("Content-Type", "application/json");
  
          String input = payload.toJSON();
  
          OutputStream os = conn.getOutputStream();
          os.write(input.getBytes());
          os.flush();
  
          if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
              throw new RuntimeException("Failed : HTTP error code : "
                  + conn.getResponseCode());
          }
  
          BufferedReader br = new BufferedReader(new InputStreamReader(
                  (conn.getInputStream())));
  
          String output;
          while ((output = br.readLine()) != null) {
              // for debugging
          }
  
          conn.disconnect();
  
        } catch (MalformedURLException e) {
  
          e.printStackTrace();
  
        } catch (IOException e) {
  
          e.printStackTrace();
  
       }
  
      }

}