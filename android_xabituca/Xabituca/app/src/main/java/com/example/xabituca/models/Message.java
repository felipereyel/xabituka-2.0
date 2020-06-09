package com.example.xabituca.models;

import android.util.Log;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class Message {
    public int id;
    public String content;
    public UserGroup userGroup;
//    public long createdAt;

    public Message(int id, String content, UserGroup userGroup) {
        this.id = id;
        this.content = content;
        this.userGroup = userGroup;
//        this.createdAt = createdAt;
    }

    public static Message fromJSON(JSONObject json) {
        try {
            int _id = json.getInt("id");
            String _content = json.getString("content");
//            this.createdAt = createdAt;
            JSONObject userGroupJSON = json.getJSONObject("userGroup");
            UserGroup _userGroup = UserGroup.fromJSON(userGroupJSON);
            Message messageFromJson = new Message(_id, _content, _userGroup);
            return messageFromJson;
        }
        catch(Exception e) {
            e.printStackTrace();
            Log.e("exception", "exception ao criar grupo");
            return null;
        }
//        return Message.getDummy();
    }

    public static Message getDummy() {
        UserGroup userGroup = UserGroup.getDummy();
        Message message = new Message(1, "dummy text", userGroup);

        return message;
    }
}