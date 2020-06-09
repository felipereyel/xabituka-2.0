package com.example.xabituca;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.xabituca.models.Message;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessagesActivity extends Activity {
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
        messageListView = findViewById(R.id.message_list_view);
        messageListView.setDivider(null);
        messageListView.setDividerHeight(0);
        messageAdapter = new MessageAdapter(this, messages);
        messageListView.setAdapter(messageAdapter);
        Intent intentMessageActivity = getIntent();
        String group_id = intentMessageActivity.getStringExtra("group_id");
        String group_name = intentMessageActivity.getStringExtra("group_name");
        String group_description = intentMessageActivity.getStringExtra("group_description");
        TextView nameView = this.findViewById(R.id.message_activity_group_name);
        nameView.setText(group_name);
        TextView descriptionView = this.findViewById(R.id.message_activity_group_description);
        descriptionView.setText(group_description);
//        Bundle params = intentMessageActivity.getExtras();
        if(group_id != null){
            fetchAllMessages(group_id);
        }else{
            Toast.makeText(MessagesActivity.this, "Id de grupo nao encontrado!", Toast.LENGTH_LONG).show();
        }
    }

    private void fetchAllMessages(String id_group) {
        String url = "http://35.225.88.246:8080/messages/"+id_group;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e("error", "sucesso no request");
                            boolean success = response.getBoolean("success");
                            if (success) {
                                JSONArray messagesJSONArray = response.getJSONArray("messages");
                                int numberOfMessages = messagesJSONArray.length();
                                for (int i = 0; i < numberOfMessages; i++) {
                                    JSONObject messageJson = messagesJSONArray.getJSONObject(i);
                                    Message newMessage = Message.fromJSON(messageJson);
                                    messages.add(newMessage);
                                }
                                messageAdapter.notifyDataSetChanged();
                            }
                            else {
                                String reason = response.getString("reason");
                                Log.d("ERROR","error => " + reason);
                            }
                        } catch (Exception e) {
                            Log.e("error", "erro no request");
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                token = sharedPreferences.getString("token", "");
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", token);
                return params;
            }
        };
        requestQueue.add(request);
    }
}