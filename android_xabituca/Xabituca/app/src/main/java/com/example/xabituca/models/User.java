package com.example.xabituca.models;

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

    public static User fromJson(JSONObject json) {
        return User.getDummy();
    }

    public static User getDummy() {
        User user = new User(1, "dummy", "DUMMY", 123141241);
        return user;
    }
}