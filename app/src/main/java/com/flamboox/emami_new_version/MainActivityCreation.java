package com.flamboox.emami_new_version;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;


public class MainActivityCreation extends AppCompatActivity {
    Button BoutonContinuer;
    EditText Nom, Prenom, Email, MdP, ConfirmationMdP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_creation);

        Button BoutonContinuer = findViewById(R.id.buttonContinu);
        EditText Nom = findViewById(R.id.editTextNom);
        EditText Prenom = findViewById(R.id.editTextprenom);
        EditText Email = findViewById(R.id.editTextEmail);
        EditText MdP = findViewById(R.id.editTextMdP);
        EditText ConfirmationMdP = findViewById(R.id.editTextConfirmation);

        this.BoutonContinuer.setOnClickListener(v -> {
            Intent ouvrirPage2 = new Intent(MainActivityCreation.this, MainActivityReseau.class);
            startActivity(ouvrirPage2);
        });
    }}

