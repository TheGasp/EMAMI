package com.flamboox.emami_new_version;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NumTelActivity extends AppCompatActivity {

    private EditText editTextNum;
    public ImageButton button;
    public Utilisateur user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.param_numtel);

        //user = (Utilisateur) getIntent().getSerializableExtra("utilisateur");

        editTextNum = findViewById(R.id.editTextNumTel);
        button = findViewById(R.id.buttonNum);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //user.changeNum(editTextNum.getText());
                System.out.println("YAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                System.out.println(user.getNumTel());

                //Intent intent = new Intent(context, PhotoActivity.class);
                //context.startActivity(intent);
            }
        });


    }


}

