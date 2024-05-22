package com.flamboox.emami_new_version;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfilActivity extends AppCompatActivity {
    ArrayList<String[]> reseaux = new ArrayList<>();

    ArrayList<String[]> res = new ArrayList<>();
    Utilisateur user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);            //Le layout où se trouve la liste

        reseaux.add(new String[]{"facebook", "Lee1"});
        reseaux.add(new String[]{"instagram", "Lee2"});
        reseaux.add(new String[]{"snapchat", "Lee3"});
        reseaux.add(new String[]{"x", "Lee4"});
        reseaux.add(new String[]{"tiktok", "Lee4"});

        res.add(new String[]{"facebook", "Lee1"});
        res.add(new String[]{"instagram", "Lee2"});
        res.add(new String[]{"snapchat", "Lee3"});
        res.add(new String[]{"x", "Lee4"});
        res.add(new String[]{"tiktok", "Lee4"});

        user = new Utilisateur("Leelou", "Lebrun", "leelouphoto", "O7 90 98 78 76", res, reseaux);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProfil);
        GererListeProfil adapter = new GererListeProfil(this, user);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
