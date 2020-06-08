package com.example.xabituca;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.xabituca.models.Group;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroupsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    RequestQueue requestQueue;
    String token;

    private ListView groupListView;
    private GroupAdapter groupAdapter;
    private ArrayList<Group> groups;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        groups = new ArrayList<Group>();

        requestQueue = Volley.newRequestQueue(this);
        fetchAllGroups();

        groupListView = findViewById(R.id.group_list_view);

        groupListView.setDivider(null);
        groupListView.setDividerHeight(0);

        groupAdapter = new GroupAdapter(this, groups);
        groupListView.setAdapter(groupAdapter);

//        Button quit_login = findViewById(R.id.button);
//        quit_login.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                quit();
//                redirectToLoginActivity();
//            }
//        });
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.group_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.group_menu_logout) {
            this.quit();
            this.redirectToLoginActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void redirectToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void quit() {
        sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("token");
        editor.apply();
    }

    private void fetchAllGroups() {
        String url = "http://35.225.88.246:8080/groups";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean success = response.getBoolean("success");
                            if (success) {
                                JSONArray groupsJSONArray = response.getJSONArray("groups");

                                int numberOfGroups = groupsJSONArray.length();
                                for (int i = 0; i < numberOfGroups; i++) {
                                    JSONObject groupJson = groupsJSONArray.getJSONObject(i);
                                    Group newGroup = Group.fromJSON(groupJson);

                                    groups.add(newGroup);
                                }
                                groupAdapter.notifyDataSetChanged();
                            }
                            else {
                                String reason = response.getString("reason");
                                Log.d("ERROR","error => " + reason);
                            }
                        } catch (Exception e) {
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
//
//    public void login_autentication (final String login, String password){
//        String url = "http://35.225.88.246:8080/users/login?nickname="+login+"&psswd="+password;
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try{
//                            if(response.getBoolean("success")){
//                                JSONObject user = response.getJSONObject("user");
//                                Toast.makeText(LoginActivity.this, "Ola "+user.getString("fullName")+", agora voce esta logado!", Toast.LENGTH_LONG).show();
//                                token = response.getString("token");
//                                sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = sharedPreferences.edit();
//                                editor.putString("token", token);
//                                editor.apply();
//                                redirectToMainActivity();
//                            }else{
//                                Toast.makeText(LoginActivity.this, "Credenciais erradas!", Toast.LENGTH_SHORT).show();
//                            }
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        });
//        login_queue.add(request);
//    }

}
