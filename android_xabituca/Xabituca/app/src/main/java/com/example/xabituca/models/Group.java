package com.example.xabituca.models;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Optional;

public class Group
{
    public int id;
    public String name;
//    public String photo;
    public User owner;
//    public long createdAt;
    public String description;
    public Message lastMessage;

    public Group(int id, String name, User owner, String description, Message lastMessage) {
        this.id = id;
        this.name = name;
//        this.photo = photo;
        this.owner = owner;
//        this.createdAt = createdAt;
        this.description = description;
        this.lastMessage = lastMessage;
    }

    public static Group fromJSON(JSONObject json) {
        try {
            int _id = json.getInt("id");
            String _name = json.getString("name");
            Log.e("_name", _name);
            String _description = json.getString("description");
//            this.createdAt = createdAt;

            JSONObject ownerJson = json.getJSONObject("owner");
            User _owner = User.fromJson(ownerJson);


            JSONArray _lastMessageJson = json.getJSONArray("lastMessage");
            Message _lastMessage = Message.fromJson(_lastMessageJson.getJSONObject(0));

            Group groupFromJson = new Group(_id, _name, _owner, _description, _lastMessage);

            return groupFromJson;
        }
        catch(Exception e) {
            e.printStackTrace();
            Log.e("exception", "exception ao criar grupo");
            return null;
        }
    }

    public static Group getDummy() {
        Group dummyGroup = new Group(
            1,
            "dummy group",
            User.getDummy(),
            "dummy description",
            Message.getDummy()
        );

        return dummyGroup;
    }
}