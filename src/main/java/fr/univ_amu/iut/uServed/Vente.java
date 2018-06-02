package fr.univ_amu.iut.uServed;

import java.net.URL;

public class Vente extends Annonce{


    private String typeObjet;
    private String nomObjet;
    private String etatVente;
    private String description;
    private URL photoObjet;
    private double prixObjet;

    public Vente(Utilisateur _utilisateur, String  _intituleAnnonce, ModerateurAuto _modAuto, String _typeObjet, String _nomObjet, String _etatVente, String _description, URL _photoObjet, double _prixObjet){
        intituleAnnonce=_intituleAnnonce;
        utilisateur=_utilisateur;
        modAuto=_modAuto;
        nomObjet=_nomObjet;
        etatVente=_etatVente;
        description=_description;
        photoObjet=_photoObjet;
        prixObjet=_prixObjet;
        typeObjet=_typeObjet;

    }

    public String getTypeObjet(){return  typeObjet;}
    public String getNomObjet(){return nomObjet;}
    public String getEtatVente(){return etatVente;}
    public String getDescription() {return description;}
    public URL getPhotoObjet(){return photoObjet;}
    public double getPrixObjet(){return prixObjet;}

    public void setTypeObjet(String typeObjet){ this.typeObjet=typeObjet; }

    public void setNomObjet(String nomObjet){ this.nomObjet=nomObjet; }

    public void setEtatVente(String etatVente){ this.etatVente=etatVente; }

    public void setDescription(String description){ this.description=description; }

    public void setPhotoObjet(URL photoObjet){ this.photoObjet=photoObjet; }

    public void setPrixObjet(double prixObjet) { this.prixObjet = prixObjet; }

}
