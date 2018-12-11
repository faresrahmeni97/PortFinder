package com.example.fares.port_finder.Login;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fares.port_finder.HomePage.MainActivity;
import com.example.fares.port_finder.R;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    public Button button;
    public Button button2;
    public TextView txtutilisateur;
    public TextView txtpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       myDB = new DatabaseHelper(this);
        button = findViewById(R.id.creercompte);
        button2 = findViewById(R.id.login);
        txtutilisateur = findViewById(R.id.username);
        txtpassword = findViewById(R.id.password);
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

                if(myDB.checkLogin(txtutilisateur.getText().toString(),txtpassword.getText().toString())){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Compte ou mot de passe Incorrect",Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
               button2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_white));
               return false;
            }
        });
    }


}
