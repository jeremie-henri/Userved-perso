package fr.univ_amu.iut.uServed;

import javafx.scene.image.Image;

public class Annonce {
    protected String  intituleAnnonce;
    protected Utilisateur utilisateur;
    protected ModerateurAuto modAuto;
    protected Image imageAnnonce;

    public Annonce() {
        Main.getBD().addAnnonce(this);

    }

    public String getIntituleAnnonce () {
        return intituleAnnonce;
    }

    public void setIntituleAnnonce(String intituleAnnonce) {
        this.intituleAnnonce = intituleAnnonce;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}