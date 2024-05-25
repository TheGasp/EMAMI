package com.flamboox.bdd;

public class User {
    private String mail;
    private String nom;
    private String prenom;
    private String numTel;
    private String adressePhoto;
    private String statut;

    // Constructeurs, getters et setters

    public User(String mail, String nom, String prenom, String numTel, String adressePhoto, String statut) {
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.adressePhoto = adressePhoto;
        this.statut = statut;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAdressePhoto() {
        return adressePhoto;
    }

    public void setAdressePhoto(String adressePhoto) {
        this.adressePhoto = adressePhoto;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
