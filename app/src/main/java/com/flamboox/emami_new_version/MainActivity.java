package com.flamboox.emami_new_version;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewSignUp = findViewById(R.id.textViewSignUp);




        this.textViewForgotPassword.setOnClickListener(v -> {
            Intent ouvrirChangerMdP = new Intent(MainActivity.this, MainActivityMDP.class);
            startActivity(ouvrirChangerMdP);
        });



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ouvrirInscrip1 = new Intent(MainActivity.this, MainActivityCreation.class);
                startActivity(ouvrirInscrip1);
            }
        });




    }
}


