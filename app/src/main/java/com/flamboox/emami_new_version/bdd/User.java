package com.flamboox.bdd;

public class User {
    private String mail;
    private String denomination;
    private String statut;

    public User(String mail, String denomination) {
        this.mail = mail;
        this.denomination = denomination;
    }

    public User(String mail, String denomination, String statut) {
        this.mail = mail;
        this.denomination = denomination;
        this.statut = statut;
    }

    public String getMail() {
        return mail;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "User{" +
                "mail='" + mail + '\'' +
                ", denomination='" + denomination + '\'' +
                '}';
    }
}

