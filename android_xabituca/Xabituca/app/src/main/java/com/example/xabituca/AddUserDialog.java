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

public class AddUserDialog {
    Dialog dialog;
    Activity activity;
    public void showDialog(Activity activity, final String group_id){
        dialog = new Dialog(activity);
        this.activity = activity;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.add_user);
        final Button addUserButton = (Button) dialog.findViewById(R.id.add_user_button);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               addUserGroup(group_id);
            }
        });
        dialog.show();
    }

    public void addUserGroup (String group_id){
        final TextView nicknameView = (TextView) dialog.findViewById(R.id.nickname);
        if(nicknameView.getText().toString().isEmpty()){
            Log.e("aaa", "vazio");
            Toast.makeText(activity, "O nome do usuario nao pode ser nulo!", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = "http://35.225.88.246:8080/membership/"+group_id+"/add?admin=true&nickname="+nicknameView.getText().toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e("aaa", "tentou");
                            if(response.getBoolean("success")){
                                dialog.dismiss();
                                Log.e("aaa", "sucesso no request2");
                            } else {
                                String reason = response.getString("reason");
                                Toast.makeText(activity, "O nome do usuario nao existe ou ja esta no grupo!", Toast.LENGTH_SHORT).show();
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
