package com.flamboox.emami_new_version;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MDP extends AppCompatActivity {
    private EditText editTextEmailAddress;
    private Button button;
    private EditText emailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reinitialisationmdp);

        emailAddress= findViewById(R.id.editTextEmail);
        button = findViewById(R.id.buttonEnvoie);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = generateNewPassword();

                gestionEmail.sendPasswordResetEmail(String.valueOf(emailAddress),newPassword);

                Toast.makeText(MDP.this, "Un email de réinitialisation du mot de passe a été envoyé.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private String generateNewPassword() {
        Random random = new Random();

        char[] CHARACTERS = {'@', '/', ':', '?', '!', ';', '#', '$'};
        int index = random.nextInt(CHARACTERS.length);
        char a = CHARACTERS[index];

        StringBuilder lettreMdP = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            boolean minuscule = random.nextBoolean(); //choisit 0 ou 1 aléatoirement pour avoir une majuscule ou une min
            char randomChar = (char) (random.nextInt(26) + (minuscule ? 'a' : 'A'));
            lettreMdP.append(randomChar);
        }
        String b = lettreMdP.toString();

        int c = random.nextInt(90) + 10;

        return Character.toString(a) + b + c;}}
