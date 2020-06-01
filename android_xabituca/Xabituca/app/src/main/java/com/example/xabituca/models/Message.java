package com.example.xabituca.models;

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

    public static Message fromJson(JSONObject json) {
        return Message.getDummy();
    }

    public static Message getDummy() {
        UserGroup userGroup = UserGroup.getDummy();
        Message message = new Message(1, "dummy text", userGroup);

        return message;
    }
}