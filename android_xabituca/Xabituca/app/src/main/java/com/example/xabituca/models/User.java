package com.example.xabituca.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User
{
    public int id;
    public String nickname;
    public String fullName;
    public long createdAt;

    public User(int id, String nickname, String fullName, long createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.fullName = fullName;
        this.createdAt = createdAt;
    }

    public static User fromJSON(JSONObject json) throws JSONException {
        int _id = json.getInt("id");
        String _nickname = json.getString("nickname");
        String _fullname = json.getString("fullName");
        long _createdAt = json.getLong("createdAt");
        User userFromJson = new User(_id, _nickname, _fullname, _createdAt);
        return userFromJson;
    }

    public static User getDummy() {
        User user = new User(1, "dummy", "DUMMY", 123141241);
        return user;
    }
}