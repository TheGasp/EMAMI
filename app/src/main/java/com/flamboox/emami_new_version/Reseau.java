package com.flamboox.emami_new_version;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Reseau extends AppCompatActivity {
    Button BoutonInscrit;
    EditText Facebook, Instagram, Twitter, Snapchat, Discord, TikTok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reseau);

        BoutonInscrit = findViewById(R.id.buttonInscrit);

        Facebook = findViewById(R.id.editTextFacebook);
        Instagram = findViewById(R.id.editTextInstagram);
        Twitter = findViewById(R.id.editTextTwitter);
        Snapchat = findViewById(R.id.editTextSnap);
        Discord = findViewById(R.id.editTextDiscord);
        TikTok=findViewById(R.id.editTextTikTok);
    }

}