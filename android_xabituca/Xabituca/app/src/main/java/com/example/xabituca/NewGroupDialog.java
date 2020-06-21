package com.example.xabituca;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class NewGroupDialog {
    Dialog dialog;
    Activity activity;
    GroupsActivity groupsActivity;
    public void showDialog(Activity activity, GroupsActivity groupsActivity){
        this.groupsActivity = groupsActivity;
        dialog = new Dialog(activity);
        this.activity = activity;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.new_group);
        final Button createGroupButton = (Button) dialog.findViewById(R.id.create_group_button);
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createNewGroup();
            }
        });
        dialog.show();
    }

    public void createNewGroup (){
        final TextView groupNameView = (TextView) dialog.findViewById(R.id.group_name);
        final TextView groupDescriptionView = (TextView) dialog.findViewById(R.id.group_description);
        final TextView groupUrlView = (TextView) dialog.findViewById(R.id.url_photo);
        if(groupNameView.getText().toString().isEmpty()){
            Log.e("aaa", "vazio");
            Toast.makeText(activity, "O nome do grupo nao pode ser vazio!", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = "http://35.225.88.246:8080/groups";
        Log.e("aaa", url);
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", groupNameView.getText().toString());
        params.put("description", groupDescriptionView.getText().toString());
        params.put("photo", groupUrlView.getText().toString());
        JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e("aaa", "tentou");
                            if(response.getBoolean("success")){
                                groupsActivity.fetchAllGroups();
                                dialog.dismiss();
                                Log.e("aaa", "sucesso no request2");
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
                SharedPreferences sharedPreferences = activity.getSharedPreferences("token", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", token);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(request);
    }
}