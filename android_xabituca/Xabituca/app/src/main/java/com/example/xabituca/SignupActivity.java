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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends Activity {
    RequestQueue signup_queue;
    String token;
    SharedPreferences sharedPreferences;
    EditText login;
    EditText password;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button botao = findViewById(R.id.signup_button);
        login = (EditText) findViewById(R.id.signup_username);
        password = (EditText) findViewById(R.id.signup_password);
        name = (EditText) findViewById(R.id.signup_name);
        signup_queue = Volley.newRequestQueue(this);
        botao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signup_autentication(login.getText().toString(), password.getText().toString(), name.getText().toString());
            }
        });
    }

    public void signup_autentication(final String login, final String password, final String name){
        String url = "http://35.225.88.246:8080/users/sign-up";
        Map<String, String> params = new HashMap<String, String>();
        params.put("nickname", login);
        params.put("full_name", name);
        params.put("psswd", password);
        JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getBoolean("success")){
                                JSONObject user = response.getJSONObject("user");
                                Toast.makeText(SignupActivity.this, "Ola "+user.getString("fullName")+", agora voce esta cadastrado e logado!", Toast.LENGTH_LONG).show();
                                token = response.getString("token");
                                sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("token", token);
                                editor.apply();
                                redirectToMainActivity();
                            }else{
                                Toast.makeText(SignupActivity.this, "Algo deu errado nos dados informados!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Toast.makeText(SignupActivity.this, "Erro de requisicao!", Toast.LENGTH_SHORT).show();
                    }
        });
        signup_queue.add(request);
    }
    public void redirectToMainActivity() {
        Intent intent = new Intent(this, GroupsActivity.class);
        startActivity(intent);
    }
    public void redirectToLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

