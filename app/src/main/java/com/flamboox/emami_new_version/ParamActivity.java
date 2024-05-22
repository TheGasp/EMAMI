package com.flamboox.emami_new_version;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParamActivity extends AppCompatActivity {
    ArrayList<String[]> reseaux = new ArrayList<>();

    ArrayList<String[]> res = new ArrayList<>();
    Utilisateur user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parametre_profil);            //Le layout où se trouve la liste

        reseaux.add(new String[] {"facebook", "Lee1"});
        reseaux.add(new String[] {"instagram", "Lee2"});
        reseaux.add(new String[] {"snapchat", "Lee3"});
        reseaux.add(new String[] {"x", "Lee4"});
        reseaux.add(new String[] {"tiktok", "Lee4"});

        user = new Utilisateur("Leelou", "Lebrun", "leelouphoto","O7 90 98 78 76", reseaux, res);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewParam);
        GererListeParam adapter = new GererListeParam(this,user);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}

