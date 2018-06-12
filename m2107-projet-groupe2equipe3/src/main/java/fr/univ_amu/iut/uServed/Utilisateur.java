package fr.univ_amu.iut.uServed;


import java.util.ArrayList;

public class Utilisateur {
    private String identifiantUti;
    private String motDePasseUti;
    private Profil profil;
    private int numeroUti;
    private ArrayList<Annonce> annoncesUtilisateur;
    private ArrayList<Avis> avisUtilisateur;
    private ArrayList<Message> messageUtilisateur;

    public Utilisateur (String _identifiantUti , String _motDePasseUti , Profil _profil){
        identifiantUti=_identifiantUti;
        motDePasseUti=_motDePasseUti;
        profil=_profil;
        numeroUti=Main.getBD().getTabUtilisateurs().size();
        Main.getBD().addUtilisateur(this);
    }

    public String getIdentifiantUti() {
        return identifiantUti;
    }

    public String getMotDePasseUti() {
        return motDePasseUti;
    }

    public void setIdentifiantUti(String identifiantUti) {
        this.identifiantUti = identifiantUti;
    }

    public void setMotDePasseUti(String motDePasseUti) {
        this.motDePasseUti = motDePasseUti;
    }

    public Profil getProfil() {
        return profil;
    }

    public void addMessage (Message m){
        messageUtilisateur.add(m);
    }
}
