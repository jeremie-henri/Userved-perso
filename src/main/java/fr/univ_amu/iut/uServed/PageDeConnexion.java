package fr.univ_amu.iut.uServed;

public class PageDeConnexion {

    private String identifiant;
    private String mdp;
    private String mail;
    private Utilisateur utilisateur;




    public void saisirIdentifiant(String _identifiant)
    {
        identifiant=_identifiant;
    }

    public void saisirMotdePasse(String _mdp)
    {
        mdp=_mdp;
    }

    public boolean verificationInfoConnection ()            //Changer dans l'UML
    {
        for (Utilisateur u : Main.getBD().getTabUtilisateurs()) {
            if (u.getIdentifiantUti().equals(identifiant) && u.getMotDePasseUti().equals(mdp)) {
                utilisateur = u;
                return true;
            }
        }
        return false;

    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void saisirMail(String _mail)
    {
        mail=_mail;
    }

    public boolean verificationMail ()
    {

        for (Utilisateur u : Main.getBD().getTabUtilisateurs()) {
            if (u.getProfil().getMailUti().equals(mail))
                return true;
        }
        return false;
    }
}
