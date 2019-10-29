package com.rajivnarula.webindexer;

import com.google.gson.Gson;

public class IndexSchema {

    private String uri ;
    private String data ;
    
    public IndexSchema (String uri , String data){
        this.uri = uri ;
        this.data = data ;

    }

    public String getData (){
        return data ;
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