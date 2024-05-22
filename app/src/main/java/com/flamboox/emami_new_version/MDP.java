package com.flamboox.emami_new_version;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.util.Random;

public class MDP extends AppCompatActivity {
    private EditText editTextEmailAddress;
    Button button;
    EditText sendto, subject, body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reinitialisationmdp);
        sendto = findViewById(R.id.editTextEmail);
        String subject = "Réinitialisation du mot de passe ";
        String body = "votre nouveau mot de passe est:      " + generateNewPassword();
        button = findViewById(R.id.buttonEnvoie);

        button.setOnClickListener(view -> {
            String emailsend = sendto.getText().toString();
            String emailsubject = subject.toString();
            String emailbody = body.toString();

            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailsend});
            intent.putExtra(Intent.EXTRA_SUBJECT, emailsubject);
            intent.putExtra(Intent.EXTRA_TEXT, emailbody);

            intent.setType("message/rfc822");

            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
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
