package com.example.xabituca;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GroupsActivity extends Activity {

//    TextView texto;
//    RequestQueue queue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
//        Button botao = findViewById(R.id.button);
//        texto = findViewById(R.id.resultado);
//        queue = Volley.newRequestQueue(this);
//        botao.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                request_data();
//            }
//        });
    }

//    public void request_data (){
//        String url = "http://35.225.88.246:8080/users";
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try{
//                            JSONArray jsonArray = response.getJSONArray("users");
//                            StringBuilder te = new StringBuilder();
//                            for(int i = 0; i < jsonArray.length(); i++){
//                                JSONObject user = jsonArray.getJSONObject(i);
//                                int id = user.getInt("id");
//                                String nickname = user.getString("nickname");
//                                String fullName = user.getString("fullName");
////                                String photo = user.getString("photo");
//                                int createdAt = user.getInt("createdAt");
//                                te.append(nickname);
//                                te.append("\n");
//                            }
//                            texto.setText(te);
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        texto.setText("Usuários: Deu erro ai!");
//                    }
//                });
//        queue.add(request);
//    }

    public void redirectToLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
