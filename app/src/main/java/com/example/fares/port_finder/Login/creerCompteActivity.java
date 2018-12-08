package com.example.fares.port_finder.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fares.port_finder.R;

public class creerCompteActivity extends AppCompatActivity {
    public EditText username;
    public EditText password;
    public EditText nom;
    public EditText prenom;
    public EditText adresse;
    public EditText datenaiss;
    public Button valider;
    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_compte);
        myDB = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        adresse = findViewById(R.id.adresse);
        datenaiss = findViewById(R.id.datenaiss);
    valider = findViewById(R.id.valider);
    valider.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(myDB.checkLogin(username.getText().toString(),password.getText().toString())){
                Toast.makeText(getApplicationContext(),"Compte Existant",Toast.LENGTH_SHORT).show();
            }else{
            myDB.insertData(username.getText().toString(),password.getText().toString(),
            nom.getText().toString(),prenom.getText().toString(),adresse.getText().toString(),datenaiss.getText().toString());
            Toast.makeText(getApplicationContext(),"Compte Creer avec succes",Toast.LENGTH_SHORT).show();
            finish();}
        }
    });

    }
}
