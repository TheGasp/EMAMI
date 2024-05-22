package com.flamboox.bdd;

public class Reseau {
    private String url;
    private String denomination_reseau;

    public Reseau() {}

    public Reseau(String url, String denomination_reseau, String logo) {
        this.url = url;
        this.denomination_reseau = denomination_reseau;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDenomination_reseau() {
        return denomination_reseau;
    }

    public void setDenomination_reseau(String denomination_reseau) {
        this.denomination_reseau = denomination_reseau;
    }

}
