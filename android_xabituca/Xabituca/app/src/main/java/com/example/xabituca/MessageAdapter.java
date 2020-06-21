package com.example.xabituca;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.xabituca.models.Message;
import com.example.xabituca.models.UserGroup;
import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter<Message> {
    private final Activity context;
    private final ArrayList<Message> messages;
    SharedPreferences sharedPreferences;
    String username;

    public MessageAdapter(Activity context, ArrayList<Message> messages) {
        super(context, 0, messages);
        this.context = context;
        this.messages = messages;
        this.sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        this.username = sharedPreferences.getString("username", "");
    }

    public View getView(int position, View view, ViewGroup parent) {
        UserGroup usergroup = messages.get(position).userGroup;
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;
//        Log.e("USERNAME", username);
//        Log.e("MESSAGE NICKNAME", usergroup.user.nickname);
//        String username_text;
        if (usergroup.user.nickname.equals(username)) {
            rowView = inflater.inflate(R.layout.self_message_layout, null,true);
        } else {
            rowView = inflater.inflate(R.layout.other_message_layout, null,true);
            TextView nameView = rowView.findViewById(R.id.name);
            nameView.setText(messages.get(position).userGroup.user.nickname);
        }
        TextView contentView = rowView.findViewById(R.id.content);
        contentView.setText(messages.get(position).content);
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.group_icon);
//        TextView subtitleText = (TextView) rowView.findViewById(R.id.group_description);
//        titleView.setText(messages.get(position).name);
//        subtitleText.setText(groups.get(position).description);
        return rowView;
    };
}





