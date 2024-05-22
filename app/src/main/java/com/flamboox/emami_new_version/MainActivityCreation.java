package com.flamboox.emami_new_version;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.text.TextWatcher;
import android.os.Handler;
import android.widget.Toast;

public class MainActivityCreation extends AppCompatActivity {
    Button BoutonContinuer;
    EditText Nom, Prenom, Email, MdP, ConfirmationMdP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_creation);

        BoutonContinuer = findViewById(R.id.buttonContinu);
        Nom = findViewById(R.id.editTextNom);


        Prenom = findViewById(R.id.editTextprenom);
        Email = findViewById(R.id.editTextEmail);
        MdP = findViewById(R.id.editTextMdP);
        ConfirmationMdP = findViewById(R.id.editTextConfirmation);

        /*if(MdP.equals(ConfirmationMdP)){
            System.out.println(MdP);}
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "les deux mots de passe ne sont pas identique", Toast.LENGTH_SHORT);
            toast.show();}*/

        this.BoutonContinuer.setOnClickListener(v -> {
            Intent ouvrirPage2 = new Intent(MainActivityCreation.this, MainActivityReseau.class);
            startActivity(ouvrirPage2);
        });
    }}

