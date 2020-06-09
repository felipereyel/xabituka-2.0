package com.example.xabituca.models;
import org.json.JSONException;
import org.json.JSONObject;

public class UserGroup {
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

    public static UserGroup fromJSON(JSONObject json) throws JSONException {
        int _id = json.getInt("id");
        boolean _admin = json.getBoolean("admin");
        JSONObject userJSON = json.getJSONObject("user");
        User _user = User.fromJSON(userJSON);
        long _joinedAt = json.getLong("joinedAt");
        UserGroup userGroupFromJson = new UserGroup(_id, _user, _admin, _joinedAt);
        return userGroupFromJson;
    }

    public static UserGroup getDummy() {
        User user = User.getDummy();
        UserGroup userGroup = new UserGroup(1, user, true, 1241241);
        return userGroup;
    }
}