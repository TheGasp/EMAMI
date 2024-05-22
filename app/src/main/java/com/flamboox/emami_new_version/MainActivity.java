package com.flamboox.emami_new_version;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;

import com.flamboox.bdd.AppDatabase;


public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private TextView textViewForgotPassword,textViewSignUp;
    private static AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewSignUp= findViewById(R.id.textViewSignUp);




        this.textViewForgotPassword.setOnClickListener(v -> {
            Intent ouvrirChangerMdP = new Intent(MainActivity.this, MainActivityMDP.class);
            startActivity(ouvrirChangerMdP);
        });

        this.textViewSignUp.setOnClickListener(v -> {
            Intent ouvrirInscritp1 = new Intent(MainActivity.this, MainActivityCreation.class);
            startActivity(ouvrirInscritp1);
        });

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-database").build();
    }

    public static AppDatabase getDatabase() {
        return database;
    }

}


