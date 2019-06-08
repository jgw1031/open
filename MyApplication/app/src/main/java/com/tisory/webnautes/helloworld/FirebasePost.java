package com.tisory.webnautes.helloworld;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;



@IgnoreExtraProperties
public class FirebasePost {
    public String id;
    public String name;
    public String pw;
    public String gender;
    public String phoen;
    public String age;
    public String TITLE;
    public String CONTENTS;
    public String AREA;
    public String TIME;

    public FirebasePost(String id, String TITLE, String CONTENTS, String AREA, String TIME) {
        this.id = id;
        this.TITLE = TITLE;
        this.CONTENTS = CONTENTS;
        this.AREA = AREA;
        this.TIME = TIME;
    }
    public FirebasePost(String id, String name, String pw, String gender, String phoen, String age) {
        this.id = id;
        this.name = name;
        this.pw = pw;
        this.gender = gender;
        this.phoen = phoen;
        this.age = age;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);
        result.put("pw", pw);
        result.put("gender", gender);
        result.put("phoen", phoen);
        result.put("age", age);
        result.put("TITLE",TITLE);
        result.put("CONTENTS",CONTENTS);
        result.put("AREA",AREA);
        result.put("TIME",TIME);
        return result;
    }
}