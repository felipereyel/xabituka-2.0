package com.example.xabituca.models;

import java.util.List;

public class Group
{
    public int id;
    public String name;
    public String photo;
    public User owner;
    public long createdAt;
    public String description;
    public List<Message> lastMessage;
}