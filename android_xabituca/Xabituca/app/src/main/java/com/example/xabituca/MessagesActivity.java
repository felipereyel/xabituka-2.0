package com.example.xabituca;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    PollMessages call = new PollMessages(this);
    String group_id;
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
        group_id = intentMessageActivity.getStringExtra("group_id");
        String group_name = intentMessageActivity.getStringExtra("group_name");
        String group_description = intentMessageActivity.getStringExtra("group_description");
        TextView nameView = this.findViewById(R.id.message_activity_group_name);
        nameView.setText(group_name);
        TextView descriptionView = this.findViewById(R.id.message_activity_group_description);
        descriptionView.setText(group_description);
        ImageButton buttonSendMessage = findViewById(R.id.button_send_message);
        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.e("aaa", "clicou");
                sendMessage(group_id);
            }
        });
        ImageButton backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        ImageView buttonExitGroup = findViewById(R.id.exit_group);
        buttonExitGroup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                exitGroup(group_id);
            }
        });
        ImageView buttonAddPerson = findViewById(R.id.add_person);
        buttonAddPerson.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addUserGroup();
            }
        });
        call.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (call.isAlive()) {
            call.interrupt();
        }
    }

    public void addUserGroup (){
        Log.e("aaa", "entrou na funcao");
        AddUserDialog dialog = new AddUserDialog();
        dialog.showDialog(this, group_id);
    }

    public void sendMessage(String id_group){
        messages.clear();
        final EditText newMessageView = findViewById(R.id.text_send_message);
        String newMessage = newMessageView.getText().toString();
        Log.e("aaa", newMessage);
        if(newMessage.isEmpty()){
            Log.e("aaa", "vazio");
            return;
        }
        String url = "http://35.225.88.246:8080/messages/"+id_group;
        Log.e("aaa", url);
        Map<String, String> params = new HashMap<String, String>();
        params.put("content", newMessage);
        JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e("aaa", "tentou");
                            if(response.getBoolean("success")){
                                newMessageView.setText("");
                                Log.e("aaa", "sucesso no request2");
                                JSONArray messagesJSONArray = response.getJSONArray("messages");
                                int numberOfMessages = messagesJSONArray.length();
                                for (int i = 0; i < numberOfMessages; i++) {
                                    JSONObject messageJson = messagesJSONArray.getJSONObject(i);
                                    Message newMessage = Message.fromJSON(messageJson);
                                    messages.add(newMessage);
                                }
                                messageAdapter.notifyDataSetChanged();
                                messageListView.setSelection(messageAdapter.getCount() - 1);
                            } else {
                                String reason = response.getString("reason");
                                Log.d("aaa","error => " + reason);
                            }
                        } catch (Exception e) {
                            Log.e("aaa", "erro no request");
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("aaa","error => "+error.toString());
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

    public void exitGroup(String id_group){
        sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        String user_id = sharedPreferences.getString("user_id", "");
        String url = "http://35.225.88.246:8080/membership/"+id_group+"/remove/"+user_id;
        Log.e("aaa", url);
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e("aaa", "tentou");
                            if(response.getBoolean("success")){
                                Log.e("aaa", "sucesso no request");
                                finish();
                            } else {
                                String reason = response.getString("reason");
                                Log.d("aaa","error => " + reason);
                            }
                        } catch (Exception e) {
                            Log.e("aaa", "erro no request");
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("aaa","error => "+error.toString());
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

    public void fetchAllMessages(String id_group) {
        messages.clear();
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
//                                messageListView.smoothScrollToPosition(messageAdapter.getCount() -1);
                                messageListView.setSelection(messageAdapter.getCount() - 1);
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