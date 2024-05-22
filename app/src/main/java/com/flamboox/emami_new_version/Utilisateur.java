package com.flamboox.emami_new_version;

import android.text.Editable;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

public class Utilisateur implements Serializable {

    String prénom;
    String nom;

    String image;

    String numTel;

    ArrayList<String[]> reseauxTot;
    ArrayList<String[]> reseauxAffiche;
    int affNum;





    public Utilisateur(String prénom, String nom, String image, String numTel, ArrayList<String[]> reseauxTot, ArrayList<String[]> reseauxAffiche){
        this.prénom = prénom;
        this.nom = nom;
        this.image = image;
        this.numTel = numTel;
        this.reseauxTot = reseauxTot;
        this.reseauxAffiche = reseauxAffiche;
        affNum = 1;

    }


    public ArrayList<String[]> getReseauxTot(){

        return reseauxTot;
    }

    public ArrayList<String[]> getReseauxAffiche(){

        return reseauxAffiche;
    }

    public String getPhoto() {
        return image;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prénom;
    }

    public String getNumTel() {
        return numTel;
    }


    public void AjouterReseau(String nomReseau, String nomProfil){

        String [] r1 = {nomReseau,nomProfil};
        reseauxTot.add(r1);
        reseauxAffiche.add(r1);
    }

    public void NePasAfficherReseau(String nomReseau){

        for (int i = 0; i< this.reseauxAffiche.size();i++){
            String[]tableau=this.reseauxAffiche.get(i);
            if (tableau[0].equals(nomReseau)){
                this.reseauxAffiche.remove(i);
                break;
            }
        }
    }

    public void AfficherReseau(String nomReseau){

        for (int i = 0; i< this.reseauxTot.size();i++){
            String[]tableau=this.reseauxTot.get(i);
            if (tableau[0].equals(nomReseau)){
                reseauxAffiche.add(tableau);
                break;
            }
        }
    }



    public void NePasAfficherTel() {
        affNum = 0;
    }

    public void AfficherTel() {
        affNum = 1;
    }

    public int getAffNum() {
        return affNum;
    }

    public void changeNum(Editable text) {
        numTel = text.toString();
    }
}

