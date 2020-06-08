package com.example.xabituca;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.xabituca.models.Group;
import com.example.xabituca.models.Message;
import com.example.xabituca.models.UserGroup;

import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter<Message> {
    private final Activity context;
    private final ArrayList<Message> messages;

    SharedPreferences sharedPreferences;
    String username = "reyel";


    public MessageAdapter(Activity context, ArrayList<Message> messages) {
        super(context, 0, messages);
        this.context = context;
        this.messages = messages;
    }

    public View getView(int position, View view, ViewGroup parent) {
        UserGroup usergroup = messages.get(position).userGroup;
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView;
        if (usergroup.user.nickname == username) {
            rowView = inflater.inflate(R.layout.self_message_layout, null,true);
        }
        else {
            rowView = inflater.inflate(R.layout.other_message_layout, null,true);
        }

//        TextView titleView = (TextView) rowView.findViewById(R.id.group_name);
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.group_icon);
//        TextView subtitleText = (TextView) rowView.findViewById(R.id.group_description);
//
//        titleView.setText(messages.get(position).name);
//        subtitleText.setText(groups.get(position).description);
        return rowView;
    };
}





