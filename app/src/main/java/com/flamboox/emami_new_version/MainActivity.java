package com.flamboox.emami_new_version;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private TextView textViewForgotPassword,textViewSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewSignUp= findViewById(R.id.textViewSignUp);




        this.textViewForgotPassword.setOnClickListener(v -> {
            Intent ouvrirChangerMdP = new Intent(MainActivity.this, MDP.class);
            startActivity(ouvrirChangerMdP);
        });

        this.textViewSignUp.setOnClickListener(v -> {
            Intent ouvrirInscritp1 = new Intent(MainActivity.this, Creation.class);
            startActivity(ouvrirInscritp1);
        });

    }
}


