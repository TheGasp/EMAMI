package com.flamboox.bdd;

public class Reseau {
    private String url;
    private String denomination;
    private String adresseLogo;

    // Constructeurs, getters et setters

    public Reseau(String url, String denomination, String adresseLogo) {
        this.url = url;
        this.denomination = denomination;
        this.adresseLogo = adresseLogo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getAdresseLogo() {
        return adresseLogo;
    }

    public void setAdresseLogo(String adresseLogo) {
        this.adresseLogo = adresseLogo;
    }
}
