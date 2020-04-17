package com.rajivnarula.webindexer;

import com.google.gson.Gson;

public class Payload {

    private String uri ;
    private String text ;
    private String title ;

    
    public Payload (String title ,String uri , String text){
        this.title = title ;
        this.uri = uri ;
        this.text = text ;

    }

    public String getTitle (){
        return title ;
    }

    public String getText (){
        return text ;
    }

    public String getUri (){
        return uri ;
    }
    public String toJSON (){
        Gson gson = new Gson();

        String json = gson.toJson(this);
        return json ;
    }

}