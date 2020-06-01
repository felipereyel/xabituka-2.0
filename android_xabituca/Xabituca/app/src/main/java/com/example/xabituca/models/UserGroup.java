package com.example.xabituca.models;

import org.json.JSONObject;

public class UserGroup
{
    public int id;
    public User user;
    public boolean admin;
    public long joinedAt;

    public UserGroup(int id, User user, boolean admin, long joinedAt) {
        this.id = id;
        this.user = user;
        this.admin = admin;
        this.joinedAt = joinedAt;
    }

    public static UserGroup fromJson(JSONObject json) {
        return UserGroup.getDummy();
    }

    public static UserGroup getDummy() {
        User user = User.getDummy();

        UserGroup userGroup = new UserGroup(1, user, true, 1241241);

        return userGroup;
    }
}