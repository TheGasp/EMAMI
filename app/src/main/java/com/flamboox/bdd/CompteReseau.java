package com.flamboox.bdd;

public class CompteReseau {
    private String idCompte;
    private String urlReseau;
    private String mailUser;

    // Constructeurs, getters et setters

    public CompteReseau(String idCompte, String urlReseau, String mailUser) {
        this.idCompte = idCompte;
        this.urlReseau = urlReseau;
        this.mailUser = mailUser;
    }

    public String getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(String idCompte) {
        this.idCompte = idCompte;
    }

    public String getUrlReseau() {
        return urlReseau;
    }

    public void setUrlReseau(String urlReseau) {
        this.urlReseau = urlReseau;
    }

    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }
}
