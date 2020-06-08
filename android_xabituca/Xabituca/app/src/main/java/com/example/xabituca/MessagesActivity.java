package com.example.xabituca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.xabituca.models.Group;
import com.example.xabituca.models.Message;

import java.util.ArrayList;

public class MessagesActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    RequestQueue requestQueue;
    String token;

    private ListView messageListView;
    private MessageAdapter messageAdapter;
    private ArrayList<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        messages = new ArrayList<Message>();

        requestQueue = Volley.newRequestQueue(this);
//      fetchAllGroups();

        messageListView = findViewById(R.id.message_list_view);

        messageListView.setDivider(null);
        messageListView.setDividerHeight(0);

        messageAdapter = new MessageAdapter(this, messages);
        messageListView.setAdapter(messageAdapter);
    }


    private void fetchAllMessages() {
        // TODO

    }

}