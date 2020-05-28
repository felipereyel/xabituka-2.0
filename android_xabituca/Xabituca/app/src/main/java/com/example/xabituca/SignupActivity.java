package com.example.xabituca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void redirectToMainActivity(View view) {
        Intent intent = new Intent(this, GroupsActivity.class);
        startActivity(intent);
    }
    public void redirectToLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
