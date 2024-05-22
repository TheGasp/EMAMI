package com.flamboox.bdd;

public class CompteReseau {
    private int idUser;
    private String url;
    private int urlReseau;

    public CompteReseau() {}

    public CompteReseau(int idUser, String url, int urlReseau) {
        this.idUser = idUser;
        this.url = url;
        this.urlReseau = urlReseau;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUrlReseau() {
        return urlReseau;
    }

    public void setUrlReseau(int urlReseau) {
        this.urlReseau = urlReseau;
    }
}

