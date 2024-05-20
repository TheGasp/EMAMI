package com.flamboox.bdd;

// import android.media.Image;

public class RequeteEnCours {

    private User user;

    public RequeteEnCours(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getMailUser() {
        return this.user.getMail();
    }

    public String getDenominationUser() {
        return this.user.getDenomination();
    }

    @Override
    public String toString() {
        return "RequeteEnCours{" +
                "user=" + user +
                '}';
    }

}
