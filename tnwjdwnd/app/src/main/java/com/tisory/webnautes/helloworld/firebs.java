package com.tisory.webnautes.helloworld;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;



@IgnoreExtraProperties
public class firebs {
    public String id;
    public String name;
    public String gender;
    public String TITLE;
    public String CONTENTS;
    public String AREA;
    public String TIME;
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);
        result.put("gender", gender);
        result.put("TITLE",TITLE);
        result.put("CONTENTS",CONTENTS);
        result.put("AREA",AREA);
        result.put("TIME",TIME);
        return result;
    }
}