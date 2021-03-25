package com.example.downloadingwebcontent;

import org.json.JSONException;
import org.json.JSONObject;

public class Comment {
    public int id;
    public int postId;
    public String email;
    public String body;

    public Comment(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt("id");
        this.postId = jsonObject.getInt("postId");
        this.email = jsonObject.getString("email");
        this.body = jsonObject.getString("body");
    }
}
