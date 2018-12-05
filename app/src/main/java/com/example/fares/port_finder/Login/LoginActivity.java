package com.example.fares.port_finder.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fares.port_finder.HomePage.MainActivity;
import com.example.fares.port_finder.R;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    public Button button;
    public Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       myDB = new DatabaseHelper(this);
        button = findViewById(R.id.creercompte);
        button2 = findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,creerCompteActivity.class);
                startActivity(intent);
                button = findViewById(R.id.creercompte);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
