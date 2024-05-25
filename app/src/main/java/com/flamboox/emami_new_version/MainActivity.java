package com.flamboox.emami_new_version;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.flamboox.bdd.AppDatabase;
import com.flamboox.bdd.Reseau;
import com.flamboox.bdd.ReseauBDD;
import com.flamboox.bdd.CompteReseau;
import com.flamboox.bdd.CompteReseauBDD;
import com.flamboox.bdd.User;
import com.flamboox.bdd.UserBDD;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private ReseauBDD reseauBDD;
    private CompteReseauBDD compteReseauBDD;
    private UserBDD userBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser la base de données
        appDatabase = new AppDatabase(this);

        // Initialiser les gestionnaires de table
        reseauBDD = new ReseauBDD(this);
        compteReseauBDD = new CompteReseauBDD(this);
        userBDD = new UserBDD(this);

        // Ouvrir les bases de données
        reseauBDD.open();
        compteReseauBDD.open();
        userBDD.open();

        // Exemple d'insertion de données
        Reseau reseau = new Reseau("http://example.com", "Example Network", "http://example.com/logo.png");
        long reseauId = reseauBDD.insertReseau(reseau);
        if (reseauId != -1) {
            Log.d("MainActivity", "Reseau inséré avec succès : " + reseauId);
        } else {
            Log.d("MainActivity", "Erreur lors de l'insertion du reseau");
        }

        // Fermer les bases de données
        reseauBDD.close();
        compteReseauBDD.close();
        userBDD.close();
    }
}
