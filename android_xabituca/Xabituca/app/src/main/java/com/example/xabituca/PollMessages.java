package com.example.xabituca;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class PollMessages extends Thread {
    MessagesActivity activity;
    PollMessages(MessagesActivity activity) {
        this.activity = activity;
    }
    public void run() {
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                activity.fetchAllMessages(activity.group_id);
            }
        },500,5000);
    }
}
