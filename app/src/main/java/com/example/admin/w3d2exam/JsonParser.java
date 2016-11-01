package com.example.admin.w3d2exam;

import com.example.admin.w3d2exam.entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 11/1/2016.
 */

public class JsonParser {
    private String myJson = "";

    public JsonParser(String myJson) {
        this.myJson = myJson;
    }

    public ArrayList<User> parseJson(){
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<User>>(){}.getType();
        return gson.fromJson(myJson, listType);
    }

}
