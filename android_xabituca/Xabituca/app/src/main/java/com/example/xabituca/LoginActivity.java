package com.example.xabituca;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class LoginActivity extends Activity {
    RequestQueue login_queue;
    EditText login_attempt;
    EditText password_attempt;
    String token;
    SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        if(token != ""){
            Toast.makeText(LoginActivity.this, "Voce foi logado automaticamente!", Toast.LENGTH_SHORT).show();
            redirectToMainActivity();
        }else{
            Button botao = findViewById(R.id.login_button);
            login_attempt = (EditText ) findViewById(R.id.login_username);
            password_attempt = (EditText ) findViewById(R.id.login_password);
            login_queue = Volley.newRequestQueue(this);
            botao.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    login_autentication(login_attempt.getText().toString(), password_attempt.getText().toString());
                }
            });
        }
    }

    public void login_autentication (final String login, String password){
        String url = "http://35.225.88.246:8080/users/login?nickname="+login+"&psswd="+password;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if(response.getBoolean("success")){
                                JSONObject user = response.getJSONObject("user");
                                Toast.makeText(LoginActivity.this, "Ola "+user.getString("fullName")+", agora voce esta logado!", Toast.LENGTH_LONG).show();
                                token = response.getString("token");
                                sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("token", token);
                                editor.apply();
                                redirectToMainActivity();
                            }else{
                                Toast.makeText(LoginActivity.this, "Credenciais erradas!", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        login_queue.add(request);
    }

    public void redirectToSignUpActivity(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void redirectToMainActivity() {
        Intent intent = new Intent(this, GroupsActivity.class);
        startActivity(intent);
    }

}
