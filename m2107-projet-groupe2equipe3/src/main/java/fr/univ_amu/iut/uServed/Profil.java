package fr.univ_amu.iut.uServed;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class Profil {
    private String nomUti;
    private String prenomUti;
    private String telephoneUti;
    private char sexeUti;
    private String adressePostaleUti;
    private String ville;
    private String bioUti;
    private String statutUti;
    private String mailUti;
    private String dateNaissanceUti;
    private Image photoProfilUti;
    private ArrayList<Avis> avisUti = new ArrayList<>();

    public Profil (String _nomUti, String _prenomUti,String _telephoneUti,char _sexeUti , String _adressePostaleUti , String _ville, String _bioUti , String _statutUti, String _mailUti ,String _dateNaissanceUti) {
        nomUti=_nomUti;
        prenomUti = _prenomUti;
        telephoneUti = _telephoneUti;
        sexeUti = _sexeUti;
        adressePostaleUti = _adressePostaleUti;
        ville=_ville;
        bioUti = _bioUti;
        statutUti = _statutUti;
        mailUti = _mailUti;
        dateNaissanceUti = _dateNaissanceUti;
    }

    public void addAvis (Avis a){
        avisUti.add(a);
    }
    public void setNomUti(String nomUti) {
        this.nomUti = nomUti;
    }

    public void setPrenomUti(String prenomUti) {
        this.prenomUti = prenomUti;
    }

    public void setTelephoneUti(String telephoneUti) {
        this.telephoneUti = telephoneUti;
    }

    public void setSexeUti(char sexeUti) {
        this.sexeUti = sexeUti;
    }

    public void setAdressePostaleUti(String adressePostaleUti) {
        this.adressePostaleUti = adressePostaleUti;
    }

    public void setBioUti(String bioUti) {
        this.bioUti = bioUti;
    }

    public void setStatutUti(String statutUti) {
        this.statutUti = statutUti;
    }

    public void setMailUti(String mailUti) {
        this.mailUti = mailUti;
    }

    public void setDateNaissanceUti(String dateNaissanceUti) {
        this.dateNaissanceUti = dateNaissanceUti;
    }

    public void setPhotoProfilUti(Image photoProfilUti) {
        this.photoProfilUti = photoProfilUti;
    }

    public String getNomUti() {
        return nomUti;
    }

    public String getPrenomUti() {
        return prenomUti;
    }

    public String getTelephoneUti() {
        return telephoneUti;
    }

    public char getSexeUti() {
        return sexeUti;
    }

    public String getVille() {
        return ville;
    }

    public String getAdressePostaleUti() {
        return adressePostaleUti;
    }

    public String getBioUti() {
        return bioUti;
    }

    public String getStatutUti() {
        return statutUti;
    }

    public String getMailUti() {
        return mailUti;
    }

    public String getDateNaissanceUti() {
        return dateNaissanceUti;
    }

    public Image getPhotoProfilUti() {
        return photoProfilUti;
    }

    public ArrayList<Avis> getAvisUti() {
        return avisUti;
    }
}
